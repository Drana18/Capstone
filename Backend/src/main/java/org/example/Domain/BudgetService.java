package org.example.Domain;

import org.example.Data.BudgetJdbcTemplateRepository;
import org.example.models.Budget;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    private final BudgetJdbcTemplateRepository repository;

    public BudgetService(BudgetJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    public Budget findById(int budgetId) throws DataAccessException {
        return repository.findById(budgetId);
    }

    public List<Budget> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public Budget createBudget(Budget budget) throws DataAccessException {
        return repository.createBudget(budget);
    }

    public Budget updateBudget(int budgetId, Budget budget) throws DataAccessException {
        return repository.updateBudget(budgetId, budget);
    }

    public void deleteBudget(int budgetId) throws DataAccessException {
        repository.deleteBudget(budgetId);
    }
}
