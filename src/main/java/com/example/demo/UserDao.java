package com.example.demo;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
}
