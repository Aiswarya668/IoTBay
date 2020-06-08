/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;
import java.util.Date;

/**
 *
 * @author James
 */
public class PaymentSnapshots {
    
    private int paymentID;
    private String methodOfPayment;
    private String hashedCreditedCardNumber;
    private String cardSecurityCode;
    private String cardExpiryDate;
    private String payDate;
    private double amountPaid;

    public PaymentSnapshots(int paymentID, String methodOfPayment, String hashedCreditedCardNumber, String cardSecurityCode, String cardExpiryDate, String payDate, double amountPaid) {
        this.paymentID = paymentID;
        this.methodOfPayment = methodOfPayment;
        this.hashedCreditedCardNumber = hashedCreditedCardNumber;
        this.cardSecurityCode = cardSecurityCode;
        this.cardExpiryDate = cardExpiryDate;
        this.payDate = payDate;
        this.amountPaid = amountPaid;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getMethodOfPayment() {
        return methodOfPayment;
    }

    public void setMethodOfPayment(String methodOfPayment) {
        this.methodOfPayment = methodOfPayment;
    }

    public String getHashedCreditedCardNumber() {
        return hashedCreditedCardNumber;
    }

    public void setHashedCreditedCardNumber(String hashedCreditedCardNumber) {
        this.hashedCreditedCardNumber = hashedCreditedCardNumber;
    }

    public String getCardSecurityCode() {
        return cardSecurityCode;
    }

    public void setCardSecurityCode(String cardSecurityCode) {
        this.cardSecurityCode = cardSecurityCode;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    
}
