package com.junit.rest_app_junit.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.junit.rest_app_junit.model.User;
import com.junit.rest_app_junit.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new User("Hitesh", "hitesh@example.com");
        user1.setId(1L);
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(user1)).thenReturn(user1);
        User created = userService.createUser(user1);
        assertEquals("Hitesh", created.getName());
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(user1);
        when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.getAllUsers();
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        Optional<User> result = userService.getUserById(1L);
        assertTrue(result.isPresent());
        assertEquals("Hitesh", result.get().getName());
    }

    @Test
    void testUpdateUser() {
        User updatedUser = new User("Hitesh A", "hitesh.a@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(1L, updatedUser);
        assertEquals("Hitesh A", result.getName());
        assertEquals("hitesh.a@example.com", result.getEmail());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);
        String result = userService.deleteUser(1L);
        assertEquals("User deleted with id 1", result);
        verify(userRepository, times(1)).deleteById(1L);
    }
}

