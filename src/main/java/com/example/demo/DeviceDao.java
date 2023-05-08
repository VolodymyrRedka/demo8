package com.example.demo;

import java.util.List;

public interface DeviceDao {
    boolean addDevice(Device device);
    void deleteDevice(int id);
    boolean updateDevice(Device device);
    Device getDeviceById(int id);
    List<Device> getAllDevices();
    void deleteDeviceById(int id);
}
