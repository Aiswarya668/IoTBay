/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.iotbay.dao;

import uts.isd.model.ApplicationAccessLogs;
import uts.isd.model.Customer;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Kevin
 */
public class DBApplicationLogsManager {

    private Statement st;
    private Connection conn;

    public DBApplicationLogsManager(Connection conn) throws SQLException {
        st = conn.createStatement();
        this.conn = conn;
    }

    // Read (Find the logs for a customer email)
    public ArrayList<ApplicationAccessLogs> findCustomerLogs(String email) throws SQLException {
        String query = "select * from IOTBAYUSER.APPLICATIONACCESSLOGS where CUSTOMEREMAIL=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        ArrayList<ApplicationAccessLogs> logs = new  ArrayList<ApplicationAccessLogs>();
        
        while (rs.next()) {
            String logId = rs.getString(1);
            String customerEmail = rs.getString(2);
            String staffEmail = rs.getString(3);
            Timestamp timestamp = rs.getTimestamp(4);
            String logDescription = rs.getString(5);
            logs.add(new ApplicationAccessLogs(logId, customerEmail, staffEmail, timestamp, logDescription));
        }
        if (logs.isEmpty()) {
            throw new SQLException("No logs for customer exists");
        }
        else {
            return logs;
        }
    }
    
    // Read (Find the logs for a staff email)
    public ArrayList<ApplicationAccessLogs> findStaffLogs(String email) throws SQLException {
        String query = "select * from IOTBAYUSER.APPLICATIONACCESSLOGS where STAFFEMAIL=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        ArrayList<ApplicationAccessLogs> logs = new  ArrayList<ApplicationAccessLogs>();
        
        while (rs.next()) {
            String logId = rs.getString(1);
            String customerEmail = rs.getString(2);
            String staffEmail = rs.getString(3);
            Timestamp timestamp = rs.getTimestamp(4);
            String logDescription = rs.getString(5);
            logs.add(new ApplicationAccessLogs(logId, customerEmail, staffEmail, timestamp, logDescription));
        }
        if (logs.isEmpty()) {
            throw new SQLException("No logs for staff exists");
        }
        else {
            return logs;
        }
    }

    // Create (Add Customer data into the logs)
        public void addCustomerLog(String customerEmail,
            String logDescription) throws SQLException {

        String query = "INSERT INTO IOTBAYUSER.ApplicationAccessLogs "
                + "(CUSTOMEREMAIL, TIMESTAMP, LOGDESCRIPTION) "
                + "VALUES (?,?,?)";
        Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, customerEmail);
        stmt.setTimestamp(2, timestamp);
        stmt.setString(3, logDescription);
        
        stmt.executeUpdate();
    }
    
    // Create (Add Staff data into the logs)
    public void addStaffLog(String staffEmail, 
            String logDescription) throws SQLException {

        String query = "INSERT INTO IOTBAYUSER.ApplicationAccessLogs "
                + "(STAFFEMAIL, TIMESTAMP, LOGDESCRIPTION) "
                + "VALUES (?,?,?)";
        Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, staffEmail);
        stmt.setTimestamp(2, timestamp);
        stmt.setString(3, logDescription);
        
        stmt.executeUpdate();
    }

//    //Update (Update a Customer's details in the database)
//    public void updateCustomer(String customerEmail, String customerFname,
//            String customerLname, String customerPass, String customerGender,
//            String customerUnit, String customerSAdd, String customerCity,
//            String customerState, String customerPostC, String customerPhone,
//            boolean customerActive)
//            throws SQLException {
//        
//        String query = "UPDATE IOTBAYUSER.CUSTOMER SET CUSTOMEREMAIL = ?, "
//                + "FNAME = ?, LNAME = ?, PHONENUMBER = ?, PASSWORD = ?,"
//                + "STREETADDRESS = ?, UNITNUMBER = ?, CITY = ?, STATE = ?,"
//                + "POSTALCODE = ?, GENDER = ?, ACTIVE = ? WHERE CUSTOMEREMAIL=?";
//
//        PreparedStatement stmt = conn.prepareStatement(query);
//        stmt.setString(1, customerEmail);
//        stmt.setString(2, customerFname);
//        stmt.setString(3, customerLname);
//        stmt.setString(4, customerPhone);
//        stmt.setString(5, customerPass);
//        stmt.setString(6, customerSAdd);
//        stmt.setString(7, customerUnit);
//        stmt.setString(8, customerCity);
//        stmt.setString(9, customerState);
//        stmt.setString(10, customerPostC);
//        stmt.setString(11, customerGender);
//        stmt.setBoolean(12, customerActive);
//        stmt.setString(13, customerEmail);
//
//        stmt.executeUpdate();
//    }

    // Delete (Delete a Customer from the database)
//    public void deleteCustomer(String customerEmail) throws SQLException {
//        String query = "DELETE FROM IOTBAYUSER.CUSTOMER WHERE CUSTOMEREMAIL = ?";
//        PreparedStatement stmt = conn.prepareStatement(query);
//        stmt.setString(1, customerEmail);
//
//        stmt.executeUpdate();
//    }

//    public ArrayList<Customer> fetchCustomers() throws SQLException {
//        String query = "SELECT * FROM Customer";
//        ResultSet rs = st.executeQuery(query);
//        ArrayList<Customer> result = new ArrayList<Customer>();
//
//        while (rs.next()) {
//            String customerEmail = rs.getString(1);
//            String customerFname = rs.getString(2);
//            String customerLname = rs.getString(3);
//            String customerPhone = rs.getString(4);
//            String customerPass = rs.getString(5);
//            String customerSAdd = rs.getString(6);
//            String customerUnit = rs.getString(7);
//            String customerCity = rs.getString(8);
//            String customerState = rs.getString(9);
//            String customerPostC = rs.getString(10);
//            boolean customerLoginStatus = rs.getBoolean(11);
//            java.util.Date customerRegisterDate = rs.getDate(12);
//            String customerGender = rs.getString(13);
//            boolean customerActive = rs.getBoolean(14);
//            result.add(new Customer(customerFname, customerLname, customerEmail,
//                    customerPass, customerGender, customerUnit, customerSAdd,
//                    customerCity, customerState, customerPostC, customerPhone,
//                    customerRegisterDate, customerLoginStatus, customerActive));
//        }
//
//        return result;
//    }

    public boolean checkCustomer(String customerEmail) throws SQLException {
        String query = "SELECT * FROM IOTBAYUSER.Customer WHERE CUSTOMEREMAIL = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, customerEmail);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String email = rs.getString(1);
            if (email.equals(customerEmail)) {
                return true;
            }
        }
        return false;
    }

    // deactivate a customer - set their active status to false
    public void deactivateCustomer(String customerEmail) throws SQLException {
        String query = "UPDATE IOTBAYUSER.CUSTOMER SET ACTIVE = ? WHERE CUSTOMEREMAIL = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setBoolean(1, false);
        stmt.setString(2, customerEmail);
        
        stmt.executeUpdate();
    }
}
