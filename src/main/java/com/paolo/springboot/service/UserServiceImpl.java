package com.paolo.springboot.service;

import com.paolo.springboot.controller.LoginController;
import com.paolo.springboot.entity.Department;
import com.paolo.springboot.entity.User;

import com.paolo.springboot.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;


    private byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    public User saveUser(User user) {
        //encode password md5
        String plaintextPassword = user.getPassword();
        byte[] md5InBytes = digest(plaintextPassword.getBytes(UTF_8));
        String md5Password = bytesToHex(md5InBytes);
        LOGGER.info("PLAIN PASSWORD --> " + user.getPassword());
        user.setPassword(md5Password);
        LOGGER.info("MD5 PASSWORD --> " + md5Password);

        return userRepository.save(user);
    }

    @Override
    public User updateUserById(Long userId, User user) {
        User userFromDB = userRepository.findById(userId).get();

        if (Objects.nonNull(user.getUsername()) && !user.getUsername().equals(userFromDB.getUsername())) {
            userFromDB.setUsername(user.getUsername());
        }


        if (Objects.nonNull(user.getPassword()) && !user.getPassword().equals(userFromDB.getPassword())) {
            userFromDB.setPassword(user.getPassword());
        }

        return userRepository.save(userFromDB);
    }

    @Override
    public User getUser(Long userId) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
