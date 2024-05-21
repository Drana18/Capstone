package org.example.Domain;

import org.example.Data.ExpenseJdbcTemplateRepository;
import org.example.Data.ExpenseJdbcTemplateRepository;
import org.example.models.Expense;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseJdbcTemplateRepository repository;

    public ExpenseService(ExpenseJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    public Expense findById(int expenseId) throws DataAccessException {
        return repository.findById(expenseId);
    }

    public List<Expense> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public Expense createExpense(Expense expense) throws DataAccessException {
        return repository.createExpense(expense);
    }

    public Expense updateExpense(int expenseId, Expense expense) throws DataAccessException {
        return repository.updateExpense(expenseId, expense);
    }

    public void deleteExpense(int expenseId) throws DataAccessException {
        repository.deleteExpense(expenseId);
    }
}
