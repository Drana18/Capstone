package org.example.Data;

import org.example.models.Budget;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BudgetRepository {
    Budget findById(int budgetId) throws DataAccessException;

    List<Budget> findAll() throws DataAccessException;

    Budget createBudget(Budget budget) throws DataAccessException;

    Budget updateBudget(int budgetId, Budget budget) throws DataAccessException;

    void deleteBudget(int budgetId) throws DataAccessException;

    List<Budget> findByUserId(int userId) throws DataAccessException;
}
