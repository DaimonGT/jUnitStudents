package by.ilyushenko.demo.university.model;

public class GradeRecord {
    private Student student;
    private String subject;
    private Grade grade;

    public GradeRecord(Student student, String subject, Grade grade) {
        this.student = student;
        this.subject = subject;
        this.grade = grade;
    }

    public Student getStudent() { return student; }
    public String getSubject() { return subject; }
    public Grade getGrade() { return grade; }

    @Override
    public String toString() {
        return student.getName() + " → " + subject + ": " + grade;
    }
}


