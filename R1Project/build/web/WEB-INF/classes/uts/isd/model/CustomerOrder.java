/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author aiswaryarajeev
 */
public class CustomerOrder implements Serializable {
    private String orderID;
    private User user;
    private Date dateOrdered;
    private double totalPrice;
    private String estimatedArrivalDate;
    private Supplier supplier;
    private double shippingCost;
    private String dateTimeDeparture;
    private String shippingType;
    private String status;

    public CustomerOrder(String orderID, User user, Date dateOrdered, double totalPrice, String estimatedArrivalDate, Supplier supplier, double shippingCost, String dateTimeDeparture, String shippingType, String status) {
        this.orderID = orderID;
        this.user = user;
        this.dateOrdered = dateOrdered;
        this.totalPrice = totalPrice;
        this.estimatedArrivalDate = estimatedArrivalDate;
        this.supplier = supplier;
        this.shippingCost = shippingCost;
        this.dateTimeDeparture = dateTimeDeparture;
        this.shippingType = shippingType;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getEstimatedArrivalDate() {
        return estimatedArrivalDate;
    }

    public void setEstimatedArrivalDate(String estimatedArrivalDate) {
        this.estimatedArrivalDate = estimatedArrivalDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getDateTimeDeparture() {
        return dateTimeDeparture;
    }

    public void setDateTimeDeparture(String dateTimeDeparture) {
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

    
    
}
