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
public class OrderDeviceT implements Serializable {
    private CustomerOrder order;
    private Device device;
    private int quantity;
    private double price;

    public OrderDeviceT(CustomerOrder order, Device device, int quantity, double price) {
        this.order = order;
        this.device = device;
        this.quantity = quantity;
        this.price = price;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
    
}
