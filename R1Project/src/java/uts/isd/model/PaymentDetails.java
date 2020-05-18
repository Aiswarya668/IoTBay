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
public class PaymentDetails implements Serializable {
    private User user;
    private String methodOfPayment;
    private String hashedCreditedCardNumber;
    private int cardSecurityCode;
    private String cardExpiryDate;

    public PaymentDetails(User user, String methodOfPayment, String hashedCreditedCardNumber, int cardSecurityCode, String cardExpiryDate) {
        this.user = user;
        this.methodOfPayment = methodOfPayment;
        this.hashedCreditedCardNumber = hashedCreditedCardNumber;
        this.cardSecurityCode = cardSecurityCode;
        this.cardExpiryDate = cardExpiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public int getCardSecurityCode() {
        return cardSecurityCode;
    }

    public void setCardSecurityCode(int cardSecurityCode) {
        this.cardSecurityCode = cardSecurityCode;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    
    
}


