package com.thinkitive.microservice.userservice.service;

import com.thinkitive.microservice.userservice.entity.User;

import java.util.List;

public interface UserService {
    public User getUser(int id);
    public User createUser(User user);
    public void deleteUser(int id);
    public User updateUser(int id, User user);
    List<User> getAllUser();
}