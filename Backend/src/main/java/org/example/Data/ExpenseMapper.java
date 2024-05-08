package org.example.Data;

import org.example.models.Expense;
import org.example.Data.CategoryJdbcTemplateRepository;
import org.example.Data.UserJdbcTemplateRepository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseMapper implements RowMapper<Expense> {

    private final CategoryJdbcTemplateRepository categoryJdbcTemplateRepository;
    private final UserJdbcTemplateRepository userJdbcTemplateRepository;

    public ExpenseMapper(CategoryJdbcTemplateRepository categoryJdbcTemplateRepository,
                         UserJdbcTemplateRepository userJdbcTemplateRepository) {
        this.categoryJdbcTemplateRepository = categoryJdbcTemplateRepository;
        this.userJdbcTemplateRepository = userJdbcTemplateRepository;
    }

    @Override
    public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
        Expense expense = new Expense();
        expense.setId(rs.getInt("expenses_id"));
        expense.setDate(rs.getString("date"));
        expense.setDescription(rs.getString("description"));
        expense.setAmount(rs.getDouble("amount"));

        // Assuming Category and User are also being retrieved correctly
        expense.setCategory(categoryJdbcTemplateRepository.findById(rs.getInt("category_id")));
        expense.setUser(userJdbcTemplateRepository.findById(rs.getInt("user_id")));

        return expense;
    }
}
