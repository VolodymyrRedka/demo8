package com.example.demo;

import java.util.List;

public class UserController {
    private UserDao userDao;

    public UserController() {
        this.userDao = new UserDaoJdbcImpl();
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public void addUser(int id,String firstName, String lastName, String phoneNumber, String gender) {
        User newUser = new User(id,firstName, lastName, phoneNumber, gender);
        userDao.addUser(newUser);
    }

    public void updateUser(int id, String firstName, String lastName, String phoneNumber, String gender) {
        User userToUpdate = userDao.getUserById(id);
        if (userToUpdate != null) {
            userToUpdate.setFirstName(firstName);
            userToUpdate.setLastName(lastName);
            userToUpdate.setPhoneNumber(phoneNumber);
            userToUpdate.setGender(gender);
            userDao.updateUser(userToUpdate);
        }
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }
}
