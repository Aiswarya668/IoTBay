/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isd.iotbay.model;

import java.io.Serializable;

/**
 *
 * @author seant
 */
public class Device implements Serializable{
    private String deviceId;
    private String name;
    private double price;
    private int stock;

    public Device() {
    }

    public Device(String deviceId, String name, double price, int stock) {
        this.deviceId = deviceId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
}
