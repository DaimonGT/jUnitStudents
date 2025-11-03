package by.ilyushenko.demo.university.service;

import by.ilyushenko.demo.university.model.Group;
import by.ilyushenko.demo.university.model.Student;
import by.ilyushenko.demo.university.model.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UniversityTasksServiceMockitoTest {
    @Mock // создаем бин
    private University university;

    @InjectMocks //
    private UniversityTasksServiceImpl service;

    private List<Group> groups;
    private List<Student> students;

    @BeforeEach
    void setUp(){

        students = new ArrayList<>();
        Student s1 = new Student("1", "Дмитрий", 5);
        Student s2 = new Student("2", "Евгений", 3);

        groups = new ArrayList<>();
        Group g1 = new Group("1", "CS-100");
        Group g2 = new Group("2", "Math-200");

        students.add(s1);
        students.add(s2);

        g1.addStudent(s1);
        g2.addStudent(s2);

        groups.add(g1);
        groups.add(g2);
    }

    @Test
    void countTotalStudents(){
        when(university.getGroups()).thenReturn(groups);
        int count = service.countTotalStudents(university);
        assertEquals(2, count);
        verify(university, times(1)).getGroups();
    }

    @Test
    void findStudentsByName(){
        when(university.getGroups()).thenReturn(groups);
        List<Student> result = service.findStudentsByName(university, "Дмитрий");
        assertEquals(1, result.size());
        assertEquals("Дмитрий", result.get(0).getName());
        verify(university, atLeastOnce()).getGroups();
    }

    @Test
    void findStudentsByYear(){
        when(university.getGroups()).thenReturn(groups);
        List<Student> studentsByYear = service.findStudentsByYear(university, 5);
        assertEquals(1, studentsByYear.size());
        assertEquals(5, studentsByYear.getFirst().getYear());
        verify(university, atLeastOnce()).getGroups(); // убеждаемся, что метод getGroups вызывается один раз
    }
}
