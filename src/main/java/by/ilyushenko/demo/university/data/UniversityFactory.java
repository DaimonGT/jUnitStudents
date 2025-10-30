package by.ilyushenko.demo.university.data;

import by.ilyushenko.demo.university.model.*;

public class UniversityFactory {
    public static University createSampleUniversity() {
        University uni = new University("U001", "Tech University");

        Group g1 = new Group("G001", "CS-101");
        Group g2 = new Group("G002", "Math-201");

        Student s1 = new Student("S001", "Алексей", 1);
        Student s2 = new Student("S002", "Мария", 1);
        Student s3 = new Student("S003", "Иван", 2);
        Student s4 = new Student("S004", "Ольга", 2);
        Student s5 = new Student("S005", "Пётр", 1);

        g1.addStudent(s1); g1.addStudent(s2); g1.addStudent(s3);
        g2.addStudent(s3); g2.addStudent(s4); g2.addStudent(s5);

        Lesson l1 = new Lesson("L001", "Программирование", LessonType.LECTURE);
        Lesson l2 = new Lesson("L002", "Алгоритмы", LessonType.PRACTICE);
        Lesson l3 = new Lesson("L003", "Математика", LessonType.LAB);

        l1.addAttendee(s1); l1.addAttendee(s2);
        l2.addAttendee(s1); l2.addAttendee(s3);
        l3.addAttendee(s4); l3.addAttendee(s5);

        g1.addLesson(l1); g1.addLesson(l2);
        g2.addLesson(l3);

        uni.addGrade(new GradeRecord(s1, "Программирование", Grade.A));
        uni.addGrade(new GradeRecord(s1, "Алгоритмы", Grade.B));
        uni.addGrade(new GradeRecord(s2, "Программирование", Grade.C));
        uni.addGrade(new GradeRecord(s3, "Алгоритмы", Grade.A));
        uni.addGrade(new GradeRecord(s4, "Математика", Grade.B));
        uni.addGrade(new GradeRecord(s5, "Математика", Grade.F));

        uni.addGroup(g1);
        uni.addGroup(g2);

        return uni;
    }
}


