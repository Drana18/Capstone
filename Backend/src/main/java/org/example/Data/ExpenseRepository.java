package org.example.data.Expense;

import org.example.models.Expense;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ExpenseRepository {
    Expense findById(int expenseId) throws DataAccessException;

    List<Expense> findByUserId(int userId) throws DataAccessException;

    List<Expense> findAll() throws DataAccessException;

    Expense createExpense(Expense expense) throws DataAccessException; // Updated method parameter

    Expense updateExpense(int expenseId, Expense expense) throws DataAccessException; // Updated method parameter

    void deleteExpense(int expenseId) throws DataAccessException;
}
