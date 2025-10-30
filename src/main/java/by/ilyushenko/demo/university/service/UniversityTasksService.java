package by.ilyushenko.demo.university.service;

import by.ilyushenko.demo.university.model.*;

import java.util.*;
import java.util.Map.Entry;

// Сервисный интерфейс согласно предпочтению использовать интерфейсы [[memory:8676565]
public interface UniversityTasksService {
    List<Student> findStudentsByName(University uni, String name);
    List<Group> findGroupsByName(University uni, String name);
    List<Lesson> findLessonsBySubject(University uni, String subject);
    List<Student> findStudentsWithGrades(University uni);
    List<Group> findGroupsWithLessons(University uni);
    List<Student> findAttendeesOfLesson(University uni, String lessonId);
    Set<String> findSubjectsWithGrades(University uni);
    int countTotalStudents(University uni);
    List<Lesson> findLessonsByTypeSimple(University uni, LessonType type);
    List<Student> findStudentsWithoutGrades(University uni);
    List<Student> findStudentsByYear(University uni, int year);
    List<Lesson> findLessonsByType(University uni, LessonType type);
    Map<String, Double> calculateAverageGradeBySubject(University uni);
    List<Student> findAbsentStudents(University uni);
    List<Group> findLargestGroups(University uni, int count);
    List<Student> findPerfectStudents(University uni);
    Set<String> findSubjectsInMultipleGroups(University uni);
    Map<Student, List<Group>> findInactiveStudentsInGroups(University uni);
    List<Lesson> findMostPopularLessons(University uni, int limit);
    List<Entry<Student, Double>> getStudentRanking(University uni);
}


