/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.iotbay.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import uts.isd.model.PaymentSnapshots;

/**
 *
 * @author James
 */
public class DBPaymentSnapshotsManager {
    private Statement st;
    private Connection conn;
    
    public DBPaymentSnapshotsManager(Connection conn) throws SQLException {
        st = conn.createStatement();
        this.conn = conn;
    }
    //find payment snapshots by ID
    public PaymentSnapshots findPaymentSnapshots(int paymentID) throws SQLException {
        String query = "SELECT * FROM IOTBAYUSER.PAYMENTSNAPSHOTS WHERE PAYMENTID='" + paymentID + "'";
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()){
            int foundPaymentID = rs.getInt(1);
            if (foundPaymentID == paymentID){
                String foundMethodOfPayment = rs.getString(2);
                String foundHashedCreditedCardNumber = rs.getString(3);
                String foundCardSecurityCode = rs.getString(4);
                String foundCardExpiryDate = rs.getString(5);
                String foundPayDate = rs.getString(6);
                double foundAmount = rs.getDouble(7);        
                //return payment snapshot object found from the query
                return new PaymentSnapshots(foundPaymentID, foundMethodOfPayment, foundHashedCreditedCardNumber,foundCardSecurityCode, foundCardExpiryDate, foundPayDate, foundAmount);
            }
        }
        
        throw new SQLException("No payments exist for this customer");
    }
    //Add payment snapshots to the database
    public void addPaymentSnapshots(String methodOfPayment, String hashedCreditedCardNumber, String cardSecurityCode, String cardExpiryDate, String payDate, double amountPaid) throws SQLException {
        String query = "INSERT INTO IOTBAYUSER.PaymentSnapshots (methodOfPayment, HashedCardNumber, CardSecurityCode, CardExpiryDate, PayDate, AmountPaid) VALUES (?,?,?,?,?,?)";
        //Putting parameters into the qeuery
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, methodOfPayment);
        stmt.setString(2, hashedCreditedCardNumber);
        stmt.setString(3, cardSecurityCode);
        stmt.setString(4, cardExpiryDate);
        stmt.setString(5, payDate);
        stmt.setDouble(6, amountPaid);
        
        stmt.executeUpdate();

     }
    //Update payment snapshots using ID and information passed through the parameter
    public void updatePaymentSnapshots(int paymentID, String methodOfPayment, String hashedCreditedCardNumber, String cardSecurityCode, String cardExpiryDate, String payDate, double amountPaid) throws SQLException{
        st.executeUpdate("UPDATE IOTBAYUSER.PAYMENTSNAPSHOTS SET METHODOFPAYMENT='" + methodOfPayment + "', HashedCardNumber='"+ hashedCreditedCardNumber + "', CARDSECURITYCODE='" + cardSecurityCode + "', CARDEXPIRYDATE='" + cardExpiryDate + "', PAYDATE='" + payDate + "', AMOUNTPAID='" + amountPaid + "' WHERE PAYMENTID='" + paymentID + "'");
    }
    //Delete payment snapshot using ID
    public void deletePaymentSnapshots(int paymentID) throws SQLException {
        st.executeUpdate("DELETE FROM IOTBAYUSER.PAYMENTSNAPSHOTS WHERE PAYMENTID=" + paymentID);
    }
    //Retrieve all payment snapshots in the database
    public ArrayList<PaymentSnapshots> fetchPaymentSnapshots()throws SQLException{
        
        String fetch = "SELECT * FROM IOTBAYUSER.PAYMENTSNAPSHOTS";
        ResultSet rs = st.executeQuery(fetch);
        //add all items found into result array of PaymentSnapshot objects and return it
        ArrayList<PaymentSnapshots> result = new ArrayList();
        
            while (rs.next()){
                int foundPaymentID = rs.getInt(1);
                String foundMethodOfPayment = rs.getString(2);
                String foundHashedCreditedCardNumber = rs.getString(3);
                String foundCardSecurityCode = rs.getString(4);
                String foundCardExpiryDate = rs.getString(5);
                String foundPayDate = rs.getString(6);
                double foundAmount = rs.getDouble(7);
                
                result.add(new PaymentSnapshots(foundPaymentID, foundMethodOfPayment, foundHashedCreditedCardNumber,foundCardSecurityCode, foundCardExpiryDate, foundPayDate, foundAmount));
            }
            
        return result;    }
    //Find payment snapshots that have an ID within the given array and return a list of payment snapshots
    public ArrayList<PaymentSnapshots> findPaymentSnapshotsWithArray(ArrayList<Integer> payIDArray) throws SQLException {
        //Prepare all paymentIDs in the array into query form
        String stString = "(" + payIDArray.get(0);
        
        for (int i = 1; i < payIDArray.size(); i++) {
            stString += ", " + payIDArray.get(i);
        }
        
        stString += ")";
        
        String query = "SELECT * FROM IOTBAYUSER.PAYMENTSNAPSHOTS WHERE PAYMENTID IN" + stString;
        
        ResultSet rs = st.executeQuery(query);
        //add all items found into result array of PaymentSnapshot objects and return it
        ArrayList<PaymentSnapshots> result = new ArrayList<PaymentSnapshots>();
        
        PaymentSnapshots paymentSnapshot = null;
        
        while (rs.next()){
            int foundPaymentID = rs.getInt(1);
            if (payIDArray.contains(foundPaymentID)){
                String foundMethodOfPayment = rs.getString(2);
                String foundHashedCreditedCardNumber = rs.getString(3);
                String foundCardSecurityCode = rs.getString(4);
                String foundCardExpiryDate = rs.getString(5);
                String foundPayDate = rs.getString(6);
                double foundAmount = rs.getDouble(7);        
                paymentSnapshot = new PaymentSnapshots(foundPaymentID, foundMethodOfPayment, foundHashedCreditedCardNumber,foundCardSecurityCode, foundCardExpiryDate, foundPayDate, foundAmount);
                result.add(paymentSnapshot);
            }
        }
        return result;
    }
    //Find payment snapshot using information passed in through the parameter and return the paymentID for the creation of an order to work
    public int findPaymentID(String methodOfPayment, String hashedCreditedCardNumber, String cardSecurityCode, String cardExpiryDate, String payDate, double amount) throws SQLException {
        String query = "SELECT * FROM IOTBAYUSER.PAYMENTSNAPSHOTS WHERE MethodOfPayment='" + methodOfPayment + "' AND HashedCardNumber='"+ hashedCreditedCardNumber + "' AND CardSecurityCode='" + cardSecurityCode + "' AND CardExpiryDate='" + cardExpiryDate + "' AND PayDate='" + payDate + "' AND AmountPaid=" + amount + "";
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()){
            String foundMethodOfPayment = rs.getString(2);
            String foundHashedCreditedCardNumber = rs.getString(3);
            String foundCardSecurityCode = rs.getString(4);
            String foundCardExpiryDate = rs.getString(5);
            String foundPayDate = rs.getString(6);
            double foundAmount = rs.getDouble(7);
            //confirm that the query results is correct
            if (foundMethodOfPayment.equals(methodOfPayment) && foundHashedCreditedCardNumber.equals(hashedCreditedCardNumber) && foundCardSecurityCode.equals(cardSecurityCode) && foundCardExpiryDate.equals(cardExpiryDate) && foundPayDate.equals(payDate) && foundAmount == amount){
                int foundPaymentID = rs.getInt(1);      
                
                return foundPaymentID;
            }
        }
        //return -1 if no paymentID could be found - EOF value
        return -1;
    }
}
