package by.ilyushenko.demo.university.model;

public enum LessonType {
    LECTURE("Лекция"),
    PRACTICE("Практика"),
    LAB("Лабораторная");

    private final String displayName;

    LessonType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


