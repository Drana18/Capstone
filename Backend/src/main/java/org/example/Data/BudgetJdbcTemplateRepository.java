package org.example.Data;

import org.example.Data.BudgetMapper;
import org.example.Data.BudgetRepository;
import org.example.models.Budget;
import org.example.Data.CategoryJdbcTemplateRepository;
import org.example.Data.UserJdbcTemplateRepository;
import org.example.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class BudgetJdbcTemplateRepository implements BudgetRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CategoryJdbcTemplateRepository categoryJdbcTemplateRepository;
    private final UserJdbcTemplateRepository userJdbcTemplateRepository;

    @Autowired
    public BudgetJdbcTemplateRepository(JdbcTemplate jdbcTemplate,
                                        CategoryJdbcTemplateRepository categoryJdbcTemplateRepository,
                                        UserJdbcTemplateRepository userJdbcTemplateRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryJdbcTemplateRepository = categoryJdbcTemplateRepository;
        this.userJdbcTemplateRepository = userJdbcTemplateRepository;
    }

    @Override
    public Budget findById(int budgetId) throws DataAccessException {
        final String sql = """
                select *
                from budgets
                where budgets_id = ?;""";
        return jdbcTemplate
                .query(sql, new BudgetMapper(), budgetId)
                .stream().findFirst().orElse(null);
    }

    @Override
    public List<Budget> findByUserId(int userId) throws DataAccessException {
        final String sql = """
                select *
                from budgets
                where user_id = ?;""";
        return jdbcTemplate
                .query(sql, new BudgetMapper(), userId);
    }

    @Override
    public List<Budget> findAll() throws DataAccessException {
        final String sql = """
                select * from budgets;
                """;
        return jdbcTemplate.query(sql, new BudgetMapper());
    }

    @Override
    public Budget createBudget(Budget budget) throws DataAccessException {
       // String sql = "INSERT INTO budgets (start_date, end_date, amount, category_id, user_id) VALUES (?, ?, ?, ?, ?)";

      //  jdbcTemplate.update(sql, budget.getStartDate(), budget.getEndDate(),
            //    budget.getAmount(), budget.getCategory().getId(), budget.getUser().getId());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate) .withTableName("budgets")
                .usingGeneratedKeyColumns("budgets_id");
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("start_date", budget.getStartDate());
        parameters.put("end_date", budget.getEndDate());
        parameters.put("amount", budget.getAmount());
        parameters.put("category_id", budget.getCategory().getId());
        parameters.put("user_id", budget.getUser().getId());
        Integer newId = insert.executeAndReturnKey(parameters).intValue();

        budget.setId(newId);

        Category category = findByCategoryID(budget.getCategory().getId());
        budget.setCategory(category);
        budget.setUser(null);
        return budget;
    }


    public Category findByCategoryID(int categoryId) throws DataAccessException {
        final String sql = """
                select *
                from categories
                where categories_id = ?;""";
        return jdbcTemplate.query(sql, new CategoryMapper(), categoryId)
                .stream().findFirst().orElse(null);
    }

    @Override
    public Budget updateBudget(int budgetId, Budget budget) throws DataAccessException {
        String sql = "UPDATE budgets SET start_date = ?, end_date = ?, amount = ?, category_id = ?, user_id = ? WHERE budgets_id = ?";

        jdbcTemplate.update(sql, budget.getStartDate(), budget.getEndDate(),
                budget.getAmount(), budget.getCategory().getId(), budget.getUser().getId(), budgetId);

        return budget;
    }

    @Override
    public void deleteBudget(int budgetId) throws DataAccessException {
        String sql = "DELETE FROM budgets WHERE budgets_id = ?";

        jdbcTemplate.update(sql, budgetId);
    }
}
