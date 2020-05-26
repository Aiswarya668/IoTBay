/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;
import java.io.Serializable;


/**
 *
 * @author aiswaryarajeev
 */
public class Device implements Serializable {
    private int deviceID;
    private String deviceName;
    private String type;
    private double cost;
    private int stockQuantity;
    private String description;

    public Device(int deviceID, String deviceName, String type, double cost, int stockQuantity, String description) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.type = type;
        this.cost = cost;
        this.stockQuantity = stockQuantity;
        this.description = description;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
