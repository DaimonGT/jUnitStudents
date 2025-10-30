package by.ilyushenko.demo.university.model;

public enum Grade {
    A(5), B(4), C(3), D(2), F(1);

    private final int value;

    Grade(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}


