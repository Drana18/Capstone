package org.example.Data;

import org.example.Data.UserMapper;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements org.example.data.UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(int userId) throws DataAccessException {
        final String sql = """
                select *
                from users
                where users_id = ?;""";
        return jdbcTemplate.query(sql, new UserMapper(), userId)
                .stream().findFirst().orElse(null);
    }

    @Override
    public List<User> findAll() throws DataAccessException {
        final String sql = """
                select * from users;
                """;
        return jdbcTemplate.query(sql, new UserMapper());
    }
}
