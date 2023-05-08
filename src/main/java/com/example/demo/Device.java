package com.example.demo;
public class Device {
    private int id;
    private String name;
    private String macAddress;
    private int userId;

    public Device(int id, String name, String macAddress, int userId) {
        this.id = id;
        this.name = name;
        this.macAddress = macAddress;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", userId=" + userId +
                '}';
    }
}
