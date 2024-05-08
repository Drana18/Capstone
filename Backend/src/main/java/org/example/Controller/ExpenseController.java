package org.example.Controller;

import org.example.Domain.ExpenseService;
import org.example.models.Expense;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173"})
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> findAll() throws DataAccessException {
        return expenseService.findAll();
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable int expenseId) throws DataAccessException {
        Expense expense = expenseService.findById(expenseId);
        if (expense != null) {
            return ResponseEntity.ok(expense);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) throws DataAccessException {
        Expense createdExpense = expenseService.createExpense(expense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@PathVariable int expenseId, @RequestBody Expense expense)
            throws DataAccessException {
        Expense updatedExpense = expenseService.updateExpense(expenseId, expense);
        if (updatedExpense != null) {
            return ResponseEntity.ok(updatedExpense);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable int expenseId) throws DataAccessException {
        expenseService.deleteExpense(expenseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
