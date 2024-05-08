
package org.example.Data;

import org.example.Data.CategoryMapper;
import org.example.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryJdbcTemplateRepository implements org.example.Data.CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Category findById(int expenseId) throws DataAccessException {
        final String sql = """
                select *
                from categories
                where categories_id = ?;""";
        return jdbcTemplate.query(sql, new CategoryMapper(), expenseId)
                .stream().findFirst().orElse(null);
    }

}
