/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 *
 * @author aiswaryarajeev
 */
public class CustomerOrder implements Serializable {
    private int orderID;
    private String customerEmail;
    private int paymentID;
    private int deviceID;
    private int quantity;
    private Timestamp dateOrdered;
    private double totalPrice;
    private Timestamp estimatedArrivalDate;
    private String supplierEmail;
    private double shippingCost;
    private Timestamp dateTimeDeparture;
    private String shippingType;
    private String status;
    private String StreetAddress;
    private String UnitNumber;
    private String City;
    private String State;
    private String PostalCode;
    private String PhoneNumber ;

    public CustomerOrder(int orderID, String customerEmail, int paymentID, int deviceID, int quantity, Timestamp dateOrdered, double totalPrice, Timestamp estimatedArrivalDate, String supplierEmail, double shippingCost, Timestamp dateTimeDeparture, String shippingType, String status, String StreetAddress, String UnitNumber, String City, String State, String PostalCode, String PhoneNumber) {
        this.orderID = orderID;
        this.customerEmail = customerEmail;
        this.paymentID = paymentID;
        this.deviceID = deviceID;
        this.quantity = quantity;
        this.dateOrdered = dateOrdered;
        this.totalPrice = totalPrice;
        this.estimatedArrivalDate = estimatedArrivalDate;
        this.supplierEmail = supplierEmail;
        this.shippingCost = shippingCost;
        this.dateTimeDeparture = dateTimeDeparture;
        this.shippingType = shippingType;
        this.status = status;
        this.StreetAddress = StreetAddress;
        this.UnitNumber = UnitNumber;
        this.City = City;
        this.State = State;
        this.PostalCode = PostalCode;
        this.PhoneNumber = PhoneNumber;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Timestamp dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getEstimatedArrivalDate() {
        return estimatedArrivalDate;
    }

    public void setEstimatedArrivalDate(Timestamp estimatedArrivalDate) {
        this.estimatedArrivalDate = estimatedArrivalDate;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Timestamp getDateTimeDeparture() {
        return dateTimeDeparture;
    }

    public void setDateTimeDeparture(Timestamp dateTimeDeparture) {
        this.dateTimeDeparture = dateTimeDeparture;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStreetAddress() {
        return StreetAddress;
    }

    public void setStreetAddress(String StreetAddress) {
        this.StreetAddress = StreetAddress;
    }

    public String getUnitNumber() {
        return UnitNumber;
    }

    public void setUnitNumber(String UnitNumber) {
        this.UnitNumber = UnitNumber;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String PostalCode) {
        this.PostalCode = PostalCode;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    

    
}
