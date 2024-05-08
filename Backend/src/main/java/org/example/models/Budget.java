package org.example.models;

import java.util.Objects;

public class Budget {

    private int id;
    private double amount;
    private Category category;
    private User user;
    private String startDate;
    private String endDate;

    public Budget() {

    }

    public Budget(int id, double amount, Category category, User user, String startDate,
                  String endDate) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Budget budget = (Budget) o;
        return Double.compare(budget.amount, amount) == 0 &&
                Objects.equals(id, budget.id) &&
                Objects.equals(category, budget.category) &&
                Objects.equals(user, budget.user) &&
                Objects.equals(startDate, budget.startDate) &&
                Objects.equals(endDate, budget.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, category, user, startDate, endDate);
    }
}
