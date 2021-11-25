package com.intelliatech.service;

import com.intelliatech.bean.User;

import java.util.List;

public interface UserService {

    User addUser(User user);
    List<User> getUsers();
    User getUser(int userId);
    void deleteUser(int userId);
    User updateUser(User user);
}
