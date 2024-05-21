package org.example.Data;

import org.example.models.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {

        String categoryName = rs.getString("name");
        String categoryDescription = rs.getString("description");
        Category category = new Category(categoryName, categoryDescription);

        category.setId(rs.getInt("categories_id"));

        return category;

    }
}