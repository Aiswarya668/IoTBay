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
public class SupportTicket implements Serializable {
    private String ticketID;
    private Staff staff;
    private Customer customer;
    private String description;
    private boolean resolvedState;
    private String ticketDate;

    public SupportTicket(String ticketID, Staff staff, Customer customer, String description, boolean resolvedState, String ticketDate) {
        this.ticketID = ticketID;
        this.staff = staff;
        this.customer = customer;
        this.description = description;
        this.resolvedState = resolvedState;
        this.ticketDate = ticketDate;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isResolvedState() {
        return resolvedState;
    }

    public void setResolvedState(boolean resolvedState) {
        this.resolvedState = resolvedState;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    
    
    
}
