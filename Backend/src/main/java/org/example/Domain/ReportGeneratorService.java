package org.example.Domain;

import java.util.*;

import org.example.Data.BudgetJdbcTemplateRepository;
import org.example.Data.ExpenseJdbcTemplateRepository;
import org.example.models.Budget;
import org.example.models.Expense;
import org.springframework.stereotype.Service;

@Service
public class ReportGeneratorService {

    private ExpenseJdbcTemplateRepository expenseRepository;
    private BudgetJdbcTemplateRepository budgetRepository;

    public ReportGeneratorService(ExpenseJdbcTemplateRepository expenseRepository,
                                  BudgetJdbcTemplateRepository budgetRepository) {
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
    }

    public void generateExpenseReport(int userId) {
        // Retrieve expenses and budgets for the given user ID
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        List<Budget> budgets = budgetRepository.findByUserId(userId);

        // Group expenses and budgets by month
        Map<String, List<Expense>> expensesByMonth = groupExpenseByMonth(expenses);
        Map<String, List<Budget>> budgetsByMonth = groupBudgetByMonth(budgets);

        // Calculate total expenses and budget amounts for each month
        Map<String, Double> totalExpensesByMonth = calculateExpenseTotalAmounts(expensesByMonth);
        Map<String, Double> totalBudgetsByMonth = calculateBudgetTotalAmounts(budgetsByMonth);

        // Generate report
        generateReport(totalExpensesByMonth, totalBudgetsByMonth);
    }

    private Map<String, List<Expense>> groupExpenseByMonth(List<Expense> entities) {
        Map<String, List<Expense>> result = new HashMap<>();
        for (Expense entity : entities) {
            String month = entity.getDate().substring(0, 7); // Extract year and month
            result.computeIfAbsent(month, k -> new ArrayList<>()).add(entity);
        }
        return result;
    }

    private Map<String, List<Budget>> groupBudgetByMonth(List<Budget> entities) {
        Map<String, List<Budget>> result = new HashMap<>();
        for (Budget entity : entities) {
            String month = entity.getStartDate().substring(0, 7); // Extract year and month
            result.computeIfAbsent(month, k -> new ArrayList<>()).add(entity);
        }
        return result;
    }

    private Map<String, Double> calculateExpenseTotalAmounts(Map<String, List<Expense>> entitiesByMonth) {
        Map<String, Double> result = new HashMap<>();
        for (String key : entitiesByMonth.keySet()) {
            double totalAmount = 0;
            for (Expense expense : entitiesByMonth.get(key)) {
                totalAmount = totalAmount + expense.getAmount();
            }

            result.put(key, totalAmount);
        }
        return result;
    }

    private Map<String, Double> calculateBudgetTotalAmounts(Map<String, List<Budget>> entitiesByMonth) {
        Map<String, Double> result = new HashMap<>();
        for (String key : entitiesByMonth.keySet()) {
            double totalAmount = 0;
            for (Budget expense : entitiesByMonth.get(key)) {
                totalAmount = totalAmount + expense.getAmount();
            }

            result.put(key, totalAmount);
        }
        return result;
    }

    private void generateReport(Map<String, Double> totalExpensesByMonth, Map<String, Double> totalBudgetsByMonth) {
        System.out.println("----------------------------  REPORT ----------------------------  ");

        for (Map.Entry<String, Double> entry : totalExpensesByMonth.entrySet()) {

            String month = entry.getKey();
            double totalExpenses = entry.getValue();
            double totalBudget = totalBudgetsByMonth.getOrDefault(month, 0.0);
            System.out.println("Month: " + month);
            System.out.println("Total Expenses: " + totalExpenses);
            System.out.println("Total Budget: " + totalBudget);
            System.out.println("Budget Adherence: " + (totalExpenses <= totalBudget ? "Adhered" : "Exceeded"));
            System.out.println("_______________________________________________________________");

        }
    }
}
