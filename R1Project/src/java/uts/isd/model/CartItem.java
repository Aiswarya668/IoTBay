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
public class CartItem implements Serializable {
    private User user;
    private String deviceID;
    private int deviceQuality;

    public CartItem(User user, String deviceID, int deviceQuality) {
        this.user = user;
        this.deviceID = deviceID;
        this.deviceQuality = deviceQuality;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public int getDeviceQuality() {
        return deviceQuality;
    }

    public void setDeviceQuality(int deviceQuality) {
        this.deviceQuality = deviceQuality;
    }
    
    
    
}
