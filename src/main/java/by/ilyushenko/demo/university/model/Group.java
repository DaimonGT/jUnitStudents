package by.ilyushenko.demo.university.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupId;
    private String name;
    private List<Student> students;
    private List<Lesson> lessons;

    public Group(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;
        this.students = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public void addStudent(Student student) { students.add(student); }
    public void addLesson(Lesson lesson) { lessons.add(lesson); }

    public String getGroupId() { return groupId; }
    public String getName() { return name; }
    public List<Student> getStudents() { return students; }
    public List<Lesson> getLessons() { return lessons; }

    @Override
    public String toString() {
        return "Group{id='" + groupId + "', name='" + name + "', students=" + students.size() + "}";
    }
}


