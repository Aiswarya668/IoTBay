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
public class SupportTicket implements Serializable {
    private String ticketId;
    private Customer customer;
    private Staff staff;
    private String description;
    private boolean resolved;
    private Date date;

    public SupportTicket() {
    }

    public SupportTicket(String ticketId, Customer customer, Staff staff, String description, boolean resolved, Date date) {
        this.ticketId = ticketId;
        this.customer = customer;
        this.staff = staff;
        this.description = description;
        this.resolved = resolved;
        this.date = date;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
