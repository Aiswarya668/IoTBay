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
public class CustomerOrder implements Serializable {
    private String orderId;
    private User user;
    private Date dateOrdered;
    private double totalPrice;
    private Date estArrival;
    private ShippingCompany shippingCompany;
    private double shippingCost;
    private String shippingType;
    private String status;
    private Date departure;

    public CustomerOrder() {
    }

    public CustomerOrder(String orderId, User user, Date dateOrdered, double totalPrice, Date estArrival, ShippingCompany shippingCompany, double shippingCost, String shippingType, String status, Date departure) {
        this.orderId = orderId;
        this.user = user;
        this.dateOrdered = dateOrdered;
        this.totalPrice = totalPrice;
        this.estArrival = estArrival;
        this.shippingCompany = shippingCompany;
        this.shippingCost = shippingCost;
        this.shippingType = shippingType;
        this.status = status;
        this.departure = departure;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Date getEstArrival() {
        return estArrival;
    }

    public void setEstArrival(Date estArrival) {
        this.estArrival = estArrival;
    }

    public ShippingCompany getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(ShippingCompany shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
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

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }
    
}
