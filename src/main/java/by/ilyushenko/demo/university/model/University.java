package by.ilyushenko.demo.university.model;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String universityId;
    private String name;
    private List<Group> groups;
    private List<GradeRecord> grades;

    public University(String universityId, String name) {
        this.universityId = universityId;
        this.name = name;
        this.groups = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    public void addGroup(Group group) { groups.add(group); }
    public void addGrade(GradeRecord grade) { grades.add(grade); }

    public String getUniversityId() { return universityId; }
    public String getName() { return name; }
    public List<Group> getGroups() { return groups; }
    public List<GradeRecord> getGrades() { return grades; }

    @Override
    public String toString() {
        return "University{id='" + universityId + "', name='" + name + "', groups=" + groups.size() + "}";
    }
}


