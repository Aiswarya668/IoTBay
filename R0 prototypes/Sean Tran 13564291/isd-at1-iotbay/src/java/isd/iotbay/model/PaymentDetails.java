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
public class PaymentDetails implements Serializable {
    private String customerId;
    private String paymentMethod;
    private String cardNo;
    private int securityCode;
    private Date cardExp;

    public PaymentDetails() {
    }

    public PaymentDetails(String customerId, String paymentMethod, String cardNo, int securityCode, Date cardExp) {
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
        this.cardNo = cardNo;
        this.securityCode = securityCode;
        this.cardExp = cardExp;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public Date getCardExp() {
        return cardExp;
    }

    public void setCardExp(Date cardExp) {
        this.cardExp = cardExp;
    }
    
    
}
