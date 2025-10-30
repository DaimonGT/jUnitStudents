package by.ilyushenko.demo.university.model;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private String lessonId;
    private String subject;
    private LessonType type;
    private List<Student> attendees;

    public Lesson(String lessonId, String subject, LessonType type) {
        this.lessonId = lessonId;
        this.subject = subject;
        this.type = type;
        this.attendees = new ArrayList<>();
    }

    public void addAttendee(Student student) {
        if (!attendees.contains(student)) {
            attendees.add(student);
        }
    }

    public String getLessonId() { return lessonId; }
    public String getSubject() { return subject; }
    public LessonType getType() { return type; }
    public List<Student> getAttendees() { return attendees; }

    @Override
    public String toString() {
        return "Lesson{id='" + lessonId + "', subject='" + subject + "', type=" + type.getDisplayName() +
               ", attendees=" + attendees.size() + "}";
    }
}


