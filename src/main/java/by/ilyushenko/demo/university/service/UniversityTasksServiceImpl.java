package by.ilyushenko.demo.university.service;

import by.ilyushenko.demo.university.model.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UniversityTasksServiceImpl implements UniversityTasksService {

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = Collections.synchronizedSet(new HashSet<>());
        return t -> seen.add(keyExtractor.apply(t));
    }

    private static List<Group> allGroups(University uni) {
        return uni.getGroups();
    }

    private static List<Student> allStudents(University uni) {
        return allGroups(uni).stream()
                .flatMap(g -> g.getStudents().stream())
                .filter(distinctByKey(Student::getStudentId))
                .collect(Collectors.toList());
    }

    private static List<Lesson> allLessons(University uni) {
        return allGroups(uni).stream()
                .flatMap(g -> g.getLessons().stream())
                .collect(Collectors.toList());
    }

    public List<Student> findStudentsByName(University uni, String name) {
        return allStudents(uni).stream()
                .filter(s -> Objects.equals(s.getName(), name))
                .collect(Collectors.toList());
    }

    public List<Group> findGroupsByName(University uni, String name) {
        return allGroups(uni).stream()
                .filter(g -> Objects.equals(g.getName(), name))
                .collect(Collectors.toList());
    }

    public List<Lesson> findLessonsBySubject(University uni, String subject) {
        return allLessons(uni).stream()
                .filter(l -> Objects.equals(l.getSubject(), subject))
                .collect(Collectors.toList());
    }

    public List<Student> findStudentsWithGrades(University uni) {
        return uni.getGrades().stream()
                .map(GradeRecord::getStudent)
                .filter(distinctByKey(Student::getStudentId))
                .collect(Collectors.toList());
    }

    public List<Group> findGroupsWithLessons(University uni) {
        return allGroups(uni).stream()
                .filter(g -> !g.getLessons().isEmpty())
                .collect(Collectors.toList());
    }

    public List<Student> findAttendeesOfLesson(University uni, String lessonId) {
        return allLessons(uni).stream()
                .filter(l -> Objects.equals(l.getLessonId(), lessonId))
                .findFirst()
                .map(Lesson::getAttendees)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(distinctByKey(Student::getStudentId))
                .collect(Collectors.toList());
    }

    public Set<String> findSubjectsWithGrades(University uni) {
        return uni.getGrades().stream()
                .map(GradeRecord::getSubject)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public int countTotalStudents(University uni) {
        return allStudents(uni).size();
    }

    public List<Lesson> findLessonsByTypeSimple(University uni, LessonType type) {
        return allLessons(uni).stream()
                .filter(l -> l.getType() == type)
                .collect(Collectors.toList());
    }

    public List<Student> findStudentsWithoutGrades(University uni) {
        Set<String> withGrades = uni.getGrades().stream()
                .map(gr -> gr.getStudent().getStudentId())
                .collect(Collectors.toSet());
        return allStudents(uni).stream()
                .filter(s -> !withGrades.contains(s.getStudentId()))
                .collect(Collectors.toList());
    }

    public List<Student> findStudentsByYear(University uni, int year) {
        return allStudents(uni).stream()
                .filter(s -> s.getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Lesson> findLessonsByType(University uni, LessonType type) {
        return findLessonsByTypeSimple(uni, type);
    }

    public Map<String, Double> calculateAverageGradeBySubject(University uni) {
        return uni.getGrades().stream()
                .collect(Collectors.groupingBy(GradeRecord::getSubject,
                        Collectors.averagingInt(gr -> gr.getGrade().getValue())));
    }

    public List<Student> findAbsentStudents(University uni) {
        Set<String> attendees = allLessons(uni).stream()
                .flatMap(l -> l.getAttendees().stream())
                .map(Student::getStudentId)
                .collect(Collectors.toSet());
        return allStudents(uni).stream()
                .filter(s -> !attendees.contains(s.getStudentId()))
                .collect(Collectors.toList());
    }

    public List<Group> findLargestGroups(University uni, int count) {
        return allGroups(uni).stream()
                .sorted(Comparator.comparingInt((Group g) -> g.getStudents().size()).reversed())
                .limit(Math.max(0, count))
                .collect(Collectors.toList());
    }

    public List<Student> findPerfectStudents(University uni) {
        Map<String, List<GradeRecord>> byStudent = uni.getGrades().stream()
                .collect(Collectors.groupingBy(gr -> gr.getStudent().getStudentId()));

        return allStudents(uni).stream()
                .filter(s -> byStudent.containsKey(s.getStudentId()))
                .filter(s -> byStudent.get(s.getStudentId()).stream().allMatch(gr -> gr.getGrade() == Grade.A))
                .collect(Collectors.toList());
    }

    public Set<String> findSubjectsInMultipleGroups(University uni) {
        Map<String, Set<String>> subjectToGroupIds = new HashMap<>();
        for (Group g : allGroups(uni)) {
            Set<String> subjectsInGroup = g.getLessons().stream()
                    .map(Lesson::getSubject)
                    .collect(Collectors.toSet());
            for (String subject : subjectsInGroup) {
                subjectToGroupIds.computeIfAbsent(subject, k -> new HashSet<>()).add(g.getGroupId());
            }
        }
        return subjectToGroupIds.entrySet().stream()
                .filter(e -> e.getValue().size() >= 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Map<Student, List<Group>> findInactiveStudentsInGroups(University uni) {
        Map<String, Student> idToStudent = allStudents(uni).stream()
                .collect(Collectors.toMap(Student::getStudentId, Function.identity()));

        Map<Student, List<Group>> result = new LinkedHashMap<>();
        for (Group g : allGroups(uni)) {
            Set<String> attendees = g.getLessons().stream()
                    .flatMap(l -> l.getAttendees().stream())
                    .map(Student::getStudentId)
                    .collect(Collectors.toSet());
            for (Student s : g.getStudents()) {
                if (!attendees.contains(s.getStudentId())) {
                    Student key = idToStudent.getOrDefault(s.getStudentId(), s);
                    result.computeIfAbsent(key, k -> new ArrayList<>()).add(g);
                }
            }
        }
        return result;
    }

    public List<Lesson> findMostPopularLessons(University uni, int limit) {
        return allLessons(uni).stream()
                .sorted(Comparator.comparingInt((Lesson l) -> l.getAttendees().size()).reversed())
                .limit(Math.max(0, limit))
                .collect(Collectors.toList());
    }

    public List<Map.Entry<Student, Double>> getStudentRanking(University uni) {
        Map<String, List<GradeRecord>> byStudentId = uni.getGrades().stream()
                .collect(Collectors.groupingBy(gr -> gr.getStudent().getStudentId()));

        Map<String, Student> studentRef = new HashMap<>();
        for (Student s : allStudents(uni)) {
            studentRef.putIfAbsent(s.getStudentId(), s);
        }
        byStudentId.values().stream().flatMap(List::stream)
                .map(GradeRecord::getStudent)
                .forEach(s -> studentRef.putIfAbsent(s.getStudentId(), s));

        List<Map.Entry<Student, Double>> ranking = new ArrayList<>();
        for (Map.Entry<String, List<GradeRecord>> e : byStudentId.entrySet()) {
            String id = e.getKey();
            List<GradeRecord> records = e.getValue();
            double avg = records.stream().mapToInt(gr -> gr.getGrade().getValue()).average().orElse(0.0);
            ranking.add(new AbstractMap.SimpleEntry<>(studentRef.get(id), avg));
        }

        ranking.sort(Map.Entry.<Student, Double>comparingByValue(Comparator.reverseOrder()));
        return ranking;
    }
}


