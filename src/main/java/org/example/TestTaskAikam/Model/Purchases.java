package org.example.TestTaskAikam.Model;

public class Purchases {
    private String name;
    private int expenses;

    public Purchases(String name, int expenses) {
        this.name = name;
        this.expenses = expenses;
    }

    public String getName() {
        return name;
    }

    public int getExpenses() {
        return expenses;
    }
}
