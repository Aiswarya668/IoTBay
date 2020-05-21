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
public class ShippingCompany implements Serializable {
    private String companyEmail;
    private String companyName;
    private String contactName;
    private String companyAddress;

    public ShippingCompany(String companyEmail, String companyName, String contactName, String companyAddress) {
        this.companyEmail = companyEmail;
        this.companyName = companyName;
        this.contactName = contactName;
        this.companyAddress = companyAddress;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    
    
}
