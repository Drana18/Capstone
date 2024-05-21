package org.example.Controller;

import org.example.Domain.UserService;

import org.example.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173"})
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<User> findAll() throws DataAccessException {
        return userService.findAll();
    }


    @GetMapping("/{Id}")
    public ResponseEntity<User> getById(@PathVariable int Id) throws DataAccessException {
        User user = userService.findById(Id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}