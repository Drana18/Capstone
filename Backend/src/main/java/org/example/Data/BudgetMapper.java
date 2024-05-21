package org.example.Data;

import org.example.models.Budget;
import org.example.Data.CategoryJdbcTemplateRepository;
import org.example.Data.UserJdbcTemplateRepository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BudgetMapper implements RowMapper<Budget> {


    @Override
    public Budget mapRow(ResultSet rs, int rowNum) throws SQLException {
        Budget budget = new Budget();
        budget.setId(rs.getInt("budgets_id"));
        budget.setStartDate(rs.getString("start_date"));
        budget.setEndDate(rs.getString("end_date"));
        budget.setAmount(rs.getDouble("amount"));




        return budget;
    }
}
