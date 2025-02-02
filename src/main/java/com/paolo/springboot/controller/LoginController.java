package com.paolo.springboot.controller;

import com.paolo.springboot.entity.Department;
import com.paolo.springboot.entity.User;
import com.paolo.springboot.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();

    }

    @PostMapping("/users")
    public User saveUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
        LOGGER.info("Saved new User " + user.toString());
        return user;
    }


    @PutMapping("/users/{id}")
    public User updateDepartmentById(@PathVariable("id") Long userId,
                                           @RequestBody User user) {
        return userService.updateUserById(userId, user);
    }



}
