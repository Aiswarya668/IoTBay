/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isd.iotbay.model;

/**
 *
 * @author seant
 */
public class User {
    private String userId;
    private String streetAddress;
    private int unitNo;
    private String city;
    private String state;
    private String postcode;
    private boolean loginStatus;
    private String phone;

    public User() {
    }

    public User(String userId, String streetAddress, int unitNo, String city, String state, String postcode, boolean loginStatus, String phone) {
        this.userId = userId;
        this.streetAddress = streetAddress;
        this.unitNo = unitNo;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.loginStatus = loginStatus;
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public int getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(int unitNo) {
        this.unitNo = unitNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
