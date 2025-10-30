package by.ilyushenko.demo.university.model;

public class Student {
    private String studentId;
    private String name;
    private int year;

    public Student(String studentId, String name, int year) {
        this.studentId = studentId;
        this.name = name;
        this.year = year;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return "Student{id='" + studentId + "', name='" + name + "', year=" + year + "}";
    }
}


