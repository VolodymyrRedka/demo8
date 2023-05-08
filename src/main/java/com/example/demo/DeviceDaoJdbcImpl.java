package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceDaoJdbcImpl implements DeviceDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "RedkaVolodymyr1991";
    private static final String TABLE_NAME = "devices";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    public boolean addDevice(Device device) {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO " + TABLE_NAME + " (name, mac_address, user_id) " +
                             "VALUES (?, ?, ?)")) {
            statement.setString(1, device.getName());
            statement.setString(2, device.getMacAddress());
            statement.setInt(3, device.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteDevice(int id) {

    }


    @Override
    public void deleteDeviceById(int id) {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "DELETE FROM " + TABLE_NAME + " WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean updateDevice(Device device) {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "UPDATE " + TABLE_NAME + " SET name = ?, mac_address = ?, user_id = ? " +
                             "WHERE id = ?")) {
            statement.setString(1, device.getName());
            statement.setString(2, device.getMacAddress());
            statement.setInt(3, device.getUserId());
            statement.setInt(4, device.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Device getDeviceById(int id) {
        Device device = null;
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT * FROM " + TABLE_NAME + " WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                device = new Device(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("mac_address"),
                        resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return device;
    }

    @Override
    public List<Device> getAllDevices() {
        List<Device> devices = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME)) {
            while (resultSet.next()) {
                Device device = new Device(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("mac_address"),
                        resultSet.getInt("user_id"));
                devices.add(device);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }
}
