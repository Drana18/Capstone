package org.example.models;

import java.util.Objects;

public class Expense {

    private int id;
    private String description;
    private double amount;
    private Category category;
    private User user;
    private String date;

    public Expense() {

    }

    public Expense(int id, String description, double amount, Category category, User user, String date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.user = user;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Expense expense = (Expense) o;
        return Double.compare(expense.amount, amount) == 0 &&
                Objects.equals(id, expense.id) &&
                Objects.equals(description, expense.description) &&
                Objects.equals(category, expense.category) &&
                Objects.equals(user, expense.user) &&
                Objects.equals(date, expense.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, amount, category, user, date);
    }
}
