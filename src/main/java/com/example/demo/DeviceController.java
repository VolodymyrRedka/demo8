package com.example.demo;
import java.util.List;

public class DeviceController {
    private final DeviceDao deviceDao;

    public DeviceController(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    public List<Device> getAllDevices() {
        return deviceDao.getAllDevices();
    }

    public Device getDeviceById(int id) {
        return deviceDao.getDeviceById(id);
    }

    public boolean addDevice(Device device) {
        return deviceDao.addDevice(device);
    }

    public boolean updateDevice(Device device) {
        return deviceDao.updateDevice(device);
    }

    public void deleteDeviceById(int id) {
        deviceDao.deleteDeviceById(id);
    }
}
