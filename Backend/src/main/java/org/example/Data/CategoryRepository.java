package org.example.Data;

import org.example.models.Category;
import org.springframework.dao.DataAccessException;

public interface CategoryRepository {
    Category findById(int expenseId) throws DataAccessException;

}