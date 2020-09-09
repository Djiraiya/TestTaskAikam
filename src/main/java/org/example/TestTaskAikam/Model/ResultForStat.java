package org.example.TestTaskAikam.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ResultForStat {
    private final String type = "stat";
    private int totalDays;
    private List<CustomerForStat> customers;
    private int totalExpenses;
    private double avgExpenses;

    public ResultForStat(int totalDays, List<CustomerForStat> customers) {
        this.totalDays = totalDays;
        this.customers = customers;
        for (CustomerForStat customer: customers) {
            this.totalExpenses += customer.getTotalExpenses();
        }
        double total = Double.valueOf(this.totalExpenses) / customers.size();
        this.avgExpenses = BigDecimal.valueOf(total)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public String getType() {
        return type;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public List<CustomerForStat> getCustomers() {
        return customers;
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    public double getAvgExpenses() {
        return avgExpenses;
    }
}
