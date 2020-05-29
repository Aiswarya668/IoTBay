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
public class Supplier implements Serializable {
    private String supplierEmail;
    private String supplierName;
    private String contactName;
    private String supplierAddress;
    private boolean active;

    public Supplier(String supplierEmail, String supplierName, String contactName, String supplierAddress, Boolean active) {
        this.supplierEmail = supplierEmail;
        this.supplierName = supplierName;
        this.contactName = contactName;
        this.supplierAddress = supplierAddress;
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    
    public String getSupplierEmail() {
        return supplierEmail;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
}