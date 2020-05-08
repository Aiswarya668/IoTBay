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
public class CartItem implements Serializable {
    private User user;
    private Device device;
    private int quantity;

    public CartItem() {
    }

    public CartItem(User user, Device device, int quantity) {
        this.user = user;
        this.device = device;
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

}
