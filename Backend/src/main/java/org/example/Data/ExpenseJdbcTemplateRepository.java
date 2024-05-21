package org.example.Data;

import org.example.Data.CategoryJdbcTemplateRepository;
import org.example.Data.UserJdbcTemplateRepository;
import org.example.models.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseJdbcTemplateRepository implements org.example.data.Expense.ExpenseRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CategoryJdbcTemplateRepository categoryJdbcTemplateRepository;
    private final UserJdbcTemplateRepository userJdbcTemplateRepository;

    @Autowired
    public ExpenseJdbcTemplateRepository(JdbcTemplate jdbcTemplate,
                                         CategoryJdbcTemplateRepository categoryJdbcTemplateRepository,
                                         UserJdbcTemplateRepository userJdbcTemplateRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryJdbcTemplateRepository = categoryJdbcTemplateRepository;
        this.userJdbcTemplateRepository = userJdbcTemplateRepository;
    }

    @Override
    public Expense findById(int expenseId) throws DataAccessException {
        final String sql = """
                select *
                from expenses
                where expenses_id = ?;""";
        return jdbcTemplate
                .query(sql, new org.example.Data.ExpenseMapper(categoryJdbcTemplateRepository, userJdbcTemplateRepository), expenseId)
                .stream().findFirst().orElse(null);
    }

    @Override
    public List<Expense> findByUserId(int userId) throws DataAccessException {
        final String sql = """
                select *
                from expenses
                where user_id = ?;""";
        return jdbcTemplate
                .query(sql, new org.example.Data.ExpenseMapper(categoryJdbcTemplateRepository, userJdbcTemplateRepository), userId);
    }

    @Override
    public List<Expense> findAll() throws DataAccessException {
        final String sql = """
                select * from expenses;
                """;
        return jdbcTemplate.query(sql, new org.example.Data.ExpenseMapper(categoryJdbcTemplateRepository, userJdbcTemplateRepository));
    }

    @Override
    public Expense createExpense(Expense expense) throws DataAccessException {
        String sql = "INSERT INTO expenses (date, description, amount, category_id, user_id) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, expense.getDate(), expense.getDescription(), expense.getAmount(),
                expense.getCategory().getId(), expense.getUser().getId());

        // Assuming your database generates the ID automatically, no need to fetch the ID separately
        return expense;
    }

    @Override
    public Expense updateExpense(int expenseId, Expense expense) throws DataAccessException {
        String sql = "UPDATE expenses SET date = ?, description = ?, amount = ?, category_id = ?, user_id = ? WHERE expenses_id = ?";

        jdbcTemplate.update(sql, expense.getDate(), expense.getDescription(), expense.getAmount(),
                expense.getCategory().getId(), expense.getUser().getId(), expenseId);

        return expense;
    }

    @Override
    public void deleteExpense(int expenseId) throws DataAccessException {
        String sql = "DELETE FROM expenses WHERE expenses_id = ?";

        jdbcTemplate.update(sql, expenseId);
    }
}
