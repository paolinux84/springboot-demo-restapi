package com.paolo.springboot.service;

import com.paolo.springboot.entity.Department;
import com.paolo.springboot.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User updateUserById(Long userId, User user);

    User getUser(Long userId);

    List<User> getUsers();


}
