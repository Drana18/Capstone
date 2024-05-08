package org.example.data;

import org.example.models.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserRepository {
    User findById(int userId) throws DataAccessException;

    List<User> findAll() throws DataAccessException;
}
