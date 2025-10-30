package by.ilyushenko.demo.university.demo;

import by.ilyushenko.demo.university.data.UniversityFactory;
import by.ilyushenko.demo.university.model.University;
import by.ilyushenko.demo.university.service.UniversityTasksService;
import by.ilyushenko.demo.university.service.UniversityTasksServiceImpl;

public class UniversityDemo {
    public static void main(String[] args) {
        University uni = UniversityFactory.createSampleUniversity();
        UniversityTasksService service = new UniversityTasksServiceImpl();

        System.out.println("=== Университет ===");
        System.out.println(uni);

        System.out.println("\n=== Группы ===");
        uni.getGroups().forEach(System.out::println);

        System.out.println("\n=== Количество студентов ===");
        System.out.println(service.countTotalStudents(uni));
    }
}


