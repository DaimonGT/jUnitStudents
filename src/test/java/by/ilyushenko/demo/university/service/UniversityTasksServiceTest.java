package by.ilyushenko.demo.university.service;

import by.ilyushenko.demo.university.data.UniversityFactory;
import by.ilyushenko.demo.university.model.Lesson;
import by.ilyushenko.demo.university.model.LessonType;
import by.ilyushenko.demo.university.model.Student;
import by.ilyushenko.demo.university.model.University;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UniversityTasksServiceTest {

    private UniversityTasksService service;
    private University uni;

    @BeforeEach
    void setUp(){
        uni = UniversityFactory.createSampleUniversity();
        service = new UniversityTasksServiceImpl();
    }

    @Test
    void findStudentByNameShouldReturnExactMatches(){
        List<Student> students = service.findStudentsByName(uni, "Алексей");
        assertEquals(1, students.size());
        assertEquals("Алексей", students.get(0).getName());
    }

    @Test
    void findLessonBySubject(){
        List<Lesson> subjects = service.findLessonsBySubject(uni, "Алгоритмы");
        assertEquals(1, subjects.size());
        assertEquals("Алгоритмы", subjects.get(0).getSubject());
    }

    @Test
    void findStudentsWithGrades() {
        List<Student> withGrades = service.findStudentsWithGrades(uni);
        assertEquals(5, withGrades.size());
    }
    
    @Test
    void findStudentsWithoutGrades(){
        List<Student> withoutGrades = service.findStudentsWithoutGrades(uni);
        assertTrue(withoutGrades.isEmpty());
    }

    @Test
    void groupedAssertions(){
        List<Student> studentsByName = service.findStudentsByName(uni, "Алексей");
        Student student = studentsByName.isEmpty() ? null : studentsByName.get(0);
        assertAll("student-alexey",
                () -> assertNotNull(student),
                () -> assertEquals("Алексей", student.getName()),
                ()-> assertTrue(student.getYear() >= 1));
    }

    @Test
    void timeAndTypeAssertions() {
        assertTimeout(Duration.ofMillis(100), () -> service.countTotalStudents(uni));
        Object type = service.findLessonsByType(uni, LessonType.LAB);
        assertInstanceOf(List.class, type);
    }

    @Test
    void assertDoesNotThrow(){
       Assertions.assertDoesNotThrow(() -> service.findLessonsBySubject(uni, "Алгоритмы"));
    }


    static Stream<String> subjectsProvider(){
        return Stream.of("Программирование","Алгоритмы", "Математика");
    }

    @ParameterizedTest
    @MethodSource("subjectsProvider")
    void methodSource_subjects(String subject){
        assertNotNull(service.findLessonsBySubject(uni, subject));
    }

    @Test
    void findMostPopularLessons(){
        List<Lesson> mostPopularLessons = service.findMostPopularLessons(uni, 2);
        assertEquals(2, mostPopularLessons.size());
        assertTrue(mostPopularLessons.stream().allMatch(l-> l.getAttendees().size() == 2));
    }

    @Test
    void getStudentRanking(){
        List<Map.Entry<Student, Double>> ranking = service.getStudentRanking(uni);
        assertEquals(5, ranking.size());
        assertEquals("Иван", ranking.get(0).getKey().getName());
        assertEquals(5.0, ranking.get(0).getValue());
        assertEquals("Пётр", ranking.getLast().getKey().getName());
        assertEquals(1.0, ranking.getLast().getValue());



    }
}
