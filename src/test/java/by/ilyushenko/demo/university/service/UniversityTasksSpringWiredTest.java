package by.ilyushenko.demo.university.service;

import by.ilyushenko.demo.university.data.UniversityFactory;
import by.ilyushenko.demo.university.model.University;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(UniversityTasksServiceImpl.class)
public class UniversityTasksSpringWiredTest {
    @Autowired
    private UniversityTasksService service;

    @Test
    @DisplayName("Проверяем контекст и бин")
    void contextAndBean(){
        assertNotNull(service);
        University university = UniversityFactory.createSampleUniversity();
        assertTrue(service.countTotalStudents(university) > 0);
    }
}
