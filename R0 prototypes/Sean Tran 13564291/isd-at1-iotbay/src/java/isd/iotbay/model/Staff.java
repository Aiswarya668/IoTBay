/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isd.iotbay.model;
import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author seant
 */
public class Staff implements Serializable {
    private String userId;
    private String fname;
    private String lname;
    private String phone;
    private String email;
    private String password;
    private String gender;
    private int unitNo;
    private String address;
    private String city;
    private String state;
    private int postcode;
    private boolean loginStatus;
    private Date registerDate;
    private Staff manager;
    private String contractType;
    private int hourlyPay;

    public Staff() {
        
    }

    public Staff(String userId, String fname, String lname, String phone, String email, String password, String gender, int unitNo, String address, String city, String state, int postcode, boolean loginStatus, Date registerDate, Staff manager, String contractType, int hourlyPay) {
        this.userId = userId;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.unitNo = unitNo;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.loginStatus = loginStatus;
        this.registerDate = registerDate;
        this.manager = manager;
        this.contractType = contractType;
        this.hourlyPay = hourlyPay;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(int unitNo) {
        this.unitNo = unitNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Staff getManager() {
        return manager;
    }

    public void setManager(Staff manager) {
        this.manager = manager;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public int getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(int hourlyPay) {
        this.hourlyPay = hourlyPay;
    }
        
}
