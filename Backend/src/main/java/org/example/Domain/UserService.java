package org.example.Domain;

import org.example.data.UserRepository;
import org.example.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final org.example.Data.UserJdbcTemplateRepository repository;

    public UserService(org.example.Data.UserJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    public User findById(int userId) throws DataAccessException {
        return repository.findById(userId);
    }

    public List<User> findAll() throws DataAccessException {
        return repository.findAll();
    }

}
