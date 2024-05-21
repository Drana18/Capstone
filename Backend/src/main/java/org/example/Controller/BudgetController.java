package org.example.Controller;

import org.example.Domain.BudgetService;
import org.example.models.Budget;
import org.example.models.Category;
import org.example.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = {"http://localhost:5173"})
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public List<Budget> findAll() throws DataAccessException {
        return budgetService.findAll();
    }

    @GetMapping("/{budgetId}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable int budgetId) throws DataAccessException {
        Budget budget = budgetService.findById(budgetId);
        if (budget != null) {
            return ResponseEntity.ok(budget);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createBudget(@RequestBody Map<String, Object> requestBody) throws DataAccessException {
      Budget budget = new Budget();
      budget.setAmount((double)requestBody.get("amount"));
      budget.setStartDate((String)requestBody.get("startDate"));
      budget.setEndDate((String)requestBody.get("endDate"));
      Category category = new Category();
        category.setId((int)requestBody.get("categoryId"));
      budget.setCategory(category);
      User user = new User();
        user.setId((int)requestBody.get("userId"));
        budget.setUser(user);
        Budget createdBudget = budgetService.createBudget(budget);
        return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Budget> updateBudget(@PathVariable int budgetId, @RequestBody Map<String, Object> requestBody) throws DataAccessException {
        Budget budget = new Budget();
        budget.setId((int)requestBody.get("budget_id"));
        budget.setAmount((double)requestBody.get("amount"));
        budget.setStartDate((String)requestBody.get("startDate"));
        budget.setEndDate((String)requestBody.get("endDate"));
        Category category = new Category();
        category.setId((int)requestBody.get("categoryId"));
        budget.setCategory(category);
        User user = new User();
        user.setId((int)requestBody.get("userId"));
        budget.setUser(user);
        Budget updatedBudget = budgetService.updateBudget(budgetId, budget);
        if (updatedBudget != null) {
            return ResponseEntity.ok(updatedBudget);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<Void> deleteBudget(@PathVariable int budgetId) throws DataAccessException {
        budgetService.deleteBudget(budgetId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
