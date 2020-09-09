package org.example.TestTaskAikam.Model;

import java.util.List;

public class CustomerForStat {
    private String name;
    private List<Purchases> purchases;
    private int totalExpenses;

    public CustomerForStat(String lastName, String firstName) {
        this.name = lastName + " " + firstName;
    }

    public String getName() {
        return name;
    }

    public List<Purchases> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchases> purchases) {
        this.purchases = purchases;
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(int totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
}
