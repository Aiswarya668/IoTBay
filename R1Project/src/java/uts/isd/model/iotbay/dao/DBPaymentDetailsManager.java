/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.iotbay.dao;

import uts.isd.model.PaymentDetails;
import java.sql.*;
import java.util.*;
/**
 *
 * @author James
 */
public class DBPaymentDetailsManager {
    private Statement st;
    private Connection conn;
    
    public DBPaymentDetailsManager(Connection conn) throws SQLException {
        st = conn.createStatement();
        this.conn = conn;
    }
    //find payment details using information from the customer email
    public PaymentDetails findPaymentDetails(String CustomerEmail) throws SQLException {
        String query = "SELECT * FROM IOTBAYUSER.PAYMENTDETAILS WHERE CUSTOMEREMAIL='" + CustomerEmail + "'";
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()){
            String foundCustomerEmail = rs.getString(1);
            if (foundCustomerEmail.equals(CustomerEmail)){
                String foundMethodOfPayment = rs.getString(2);
                String foundHashedCreditedCardNumber = rs.getString(3);
                String foundCardSecurityCode = rs.getString(4);
                String foundCardExpiryDate = rs.getString(5);
                //return payment details object with results found from query
                return new PaymentDetails(foundCustomerEmail, foundMethodOfPayment, foundHashedCreditedCardNumber,foundCardSecurityCode, foundCardExpiryDate);
            }
        }
        //return null if nothing is found in the query
        return null;
    }
    //add payment details to the database
    public void addPaymentDetails(String CustomerEmail, String methodOfPayment, String hashedCreditedCardNumber, String cardSecurityCode, String cardExpiryDate) throws SQLException {
        String query = "INSERT INTO IOTBAYUSER.PAYMENTDETAILS (customeremail, MethodOfPayment, HashedCardNumber, CardSecurityCode, CardExpiryDate) VALUES (?,?,?,?,?)";
        //put parameters into the query
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, CustomerEmail);
        stmt.setString(2, methodOfPayment);
        stmt.setString(3, hashedCreditedCardNumber);
        stmt.setString(4, cardSecurityCode);
        stmt.setString(5, cardExpiryDate);
        
        stmt.executeUpdate();
        stmt.close();
    }
     //Update payment details using information from parameters on the customer email
    public void updatePaymentDetails(String CustomerEmail, String methodOfPayment, String hashedCreditedCardNumber, String cardSecurityCode, String cardExpiryDate) throws SQLException{
        st.executeUpdate("UPDATE IOTBAYUSER.PAYMENTDETAILS SET MethodOfPayment='" + methodOfPayment + "', HashedCardNumber='"+ hashedCreditedCardNumber + "', CardSecurityCode='" + cardSecurityCode + "', CardExpiryDate='" + cardExpiryDate + "' WHERE CUSTOMEREMAIL='" + CustomerEmail + "'");
    }
    //delete payment details using the customer email
    public void deletePaymentDetails(String CustomerEmail) throws SQLException {
        st.executeUpdate("DELETE FROM IOTBAYUSER.PAYMENTDETAILS WHERE CUSTOMEREMAIL='" + CustomerEmail + "'");
    }
    //retrieve all payment details from the database and return it in an array list of PaymentDetails
    public ArrayList<PaymentDetails> fetchPaymentDetails()throws SQLException{
        String fetch = "SELECT * FROM IOTBAYUSER.PAYMENTDETAILS";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<PaymentDetails> result = new ArrayList();
        
            while (rs.next()){
                String foundCustomerEmail = rs.getString(1);
                String foundMethodOfPayment = rs.getString(2);
                String foundHashedCreditedCardNumber = rs.getString(3);
                String foundCardSecurityCode = rs.getString(4);
                String foundCardExpiryDate = rs.getString(5);
                
                result.add(new PaymentDetails(foundCustomerEmail, foundMethodOfPayment, foundHashedCreditedCardNumber,foundCardSecurityCode, foundCardExpiryDate));
            }
            
        return result;
    }
    
    
}
