package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserDao userDao = new UserDaoJdbcImpl();
        User newUser = new User(0, "Іван", "Іванов", "123-45-67", "чоловік");
        userDao.addUser(newUser);

        userDao = new UserDaoJdbcImpl();
        // Додавання нового користувача
        newUser = new User(0, "Симон", "Свирид", "123-45-67", "чоловік");
        userDao.addUser(newUser);
        System.out.println("Новий користувач доданий: " + newUser);

        // Отримання користувача за id та оновлення його інформації
        int userId = 1;
        User user = userDao.getUserById(userId);
        if (user != null) {
            user.setFirstName("Петро");
            user.setLastName("Петров");
            user.setPhoneNumber("098-76-54");
            userDao.updateUser(user);
            System.out.println("Інформація про користувача оновлена: " + user);
        } else {
            System.out.println("Користувача з id " + userId + " не знайдено.");
        }

        // Видалення користувача за id
        int deleteUserById = 2;
        userDao.deleteUser(deleteUserById);
        System.out.println("Користувач з id " + deleteUserById + " видалений.");

        // Отримання всіх користувачів
        List<User> userList = userDao.getAllUsers();
        System.out.println("Список користувачів: ");
        for (User u : userList) {
            System.out.println(u);
        }

        DeviceDao deviceDao = new DeviceDaoJdbcImpl();
        // Додавання нового пристрою
        Device newDevice = new Device(0,"Телевізор", "AA:BB:CC:DD:EE:FF", 66);
        deviceDao.addDevice(newDevice);
        System.out.println("Новий пристрій доданий: " + newDevice);

        // Отримання пристрою за id та оновлення його інформації
        int deviceId = 72;
        Device device = deviceDao.getDeviceById(deviceId);
        if (device != null) {
            device.setName("Холодильник");
            device.setMacAddress("11:22:33:44:55:66");
            device.setUserId(66);
            deviceDao.updateDevice(device);
            System.out.println("Інформація про пристрій оновлена: " + device);
        } else {
            System.out.println("Пристрій з id " + deviceId + " не знайдено.");
        }

        // Видалення пристрою за id
        int deleteDeviceById = 2;
        deviceDao.deleteDeviceById(deleteDeviceById);
        System.out.println("Пристрій з id " + deleteDeviceById + " видалений.");
        // Отримання всіх пристроїв
        List<Device> deviceList = deviceDao.getAllDevices();
        System.out.println("Список пристроїв:");
        for (Device devices : deviceList) {
            System.out.println(devices);
        }

    }
}
