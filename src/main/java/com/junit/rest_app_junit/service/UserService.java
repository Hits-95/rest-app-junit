package com.junit.rest_app_junit.service;

import com.junit.rest_app_junit.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User updateUser(Long id, User userDetails);

    String deleteUser(Long id);
}

