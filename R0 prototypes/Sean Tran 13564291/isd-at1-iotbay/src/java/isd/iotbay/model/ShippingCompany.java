/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isd.iotbay.model;

import java.io.Serializable;

/**
 *
 * @author seant
 */
public class ShippingCompany implements Serializable {
    private String companyEmail;
    private String name;
    private String phone;

    public ShippingCompany() {
    }

    public ShippingCompany(String companyEmail, String name, String phone) {
        this.companyEmail = companyEmail;
        this.name = name;
        this.phone = phone;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
