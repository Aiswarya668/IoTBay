/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.iotbay.dao;

import uts.isd.model.Customer;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Kevin
 */
public class DBCustomerManager {

    private Statement st;
    private Connection conn;

    public DBCustomerManager(Connection conn) throws SQLException {
        st = conn.createStatement();
        this.conn = conn;
    }

    // Read (Find the Customer)
    public Customer findCustomer(String email) throws SQLException {
        String fetch = "select * from IOTBAYUSER.CUSTOMER where CUSTOMEREMAIL='" + email + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            String customerEmail = rs.getString(1);
            if (customerEmail.equals(email)) {
                String customerFname = rs.getString(2);
                String customerLname = rs.getString(3);
                String customerPhone = rs.getString(4);
                String customerPass = rs.getString(5);
                String customerSAdd = rs.getString(6);
                String customerUnit = rs.getString(7);
                String customerCity = rs.getString(8);
                String customerState = rs.getString(9);
                String customerPostC = rs.getString(10);
                boolean customerLoginStatus = rs.getBoolean(11);
                java.util.Date customerRegisterDate = rs.getDate(12);
                String customerGender = rs.getString(13);
                boolean customerActive = rs.getBoolean(14);
                return new Customer(customerFname, customerLname, customerEmail,
                        customerPass, customerGender, customerUnit, customerSAdd,
                        customerCity, customerState, customerPostC, customerPhone,
                        customerRegisterDate, customerLoginStatus, customerActive);
            }
        }
        throw new SQLException("No such customer exists");
    }

    // //Find customer by email and password in the database
    // public Customer findCustomer(String customerEmail, String customerPass)
    // throws SQLException {
    // //setup the select sql query string
    // String query = "SELECT * FROM IOTBAYUSER.CUSTOMER WHERE CUSTOMEREMAIL='" +
    // customerEmail + "' AND PASSWORD='" + customerPass + "'";
    // //execute this query using the statement field
    // //add the results to a ResultSet
    // ResultSet rs = st.executeQuery(query);
    // //search the ResultSet for a customer using the parameters
    // while (rs.next()) {
    // String customerEmail = rs.getString(1); // get string at index 2 (column 2)
    // String customerPass = rs.getString(5); // get string at index 3
    // if (customerEmail.equals(email) && customerPass.equals(password)) {
    // String customerFName = rs.getString(2);
    // String customerLName = rs.getString(3);
    // String customerPhone = rs.getString(4);
    // String customerStreetAddr = rs.getString(6);
    // String customerUnitNo = rs.getString(7);
    // String customerCity = rs.getString(8);
    // String customerState = rs.getString(9);
    // String customerPostCode = rs.getString(10);
    // boolean customerLoginStatus = rs.getBoolean(11);
    // java.util.Date customerRegisterDate = rs.getDate(12);
    // String customerGender = rs.getString(13);
    // return new Customer(customerFName, customerLName, customerEmail,
    // customerPass, customerGender, customerUnitNo,
    // customerStreetAddr, customerCity, customerState,
    // customerPostCode, customerPhone,
    // customerRegisterDate, customerLoginStatus);
    // }
    // }
    // return null;
    // }
    // Create (Add Customer data into the database)
    public void addCustomer(String customerFname, String customerLname, String customerEmail, String customerPass,
            String customerGender, String customerUnit, String customerSAdd, String customerCity, String customerState,
            String customerPostC, String customerPhone) throws SQLException {

        Timestamp date = new Timestamp(new java.util.Date().getTime());

//        String query = "INSERT INTO IOTBAYUSER.Customer" + " VALUES ('" + 
//                customerEmail + "', '" + customerFname + "', '" + customerLname +
//                "', '" + customerPhone + "', '" + customerPass + "', '" + 
//                customerSAdd + "', '" + customerUnit + "', '" + customerCity + 
//                "', '" + customerState + "', '" + customerPostC+ "', '"  + 
//                "true" + "', '" + date.toString() + "', '" + 
//                customerGender + "' )";
        String query = "INSERT INTO IOTBAYUSER.Customer VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, customerEmail);
        stmt.setString(2, customerFname);
        stmt.setString(3, customerLname);
        stmt.setString(4, customerPhone);
        stmt.setString(5, customerPass);
        stmt.setString(6, customerSAdd);
        stmt.setString(7, customerUnit);
        stmt.setString(8, customerCity);
        stmt.setString(9, customerState);
        stmt.setString(10, customerPostC);
        stmt.setBoolean(11, true);
        stmt.setTimestamp(12, date);
        stmt.setString(13, customerGender);
        stmt.setBoolean(14, true);

        stmt.executeUpdate();
        // st.executeUpdate(query);
    }

    //Update (Update a Customer's details in the database)
    public void updateCustomer(String customerEmail, String customerFname,
            String customerLname, String customerPass, String customerGender,
            String customerUnit, String customerSAdd, String customerCity,
            String customerState, String customerPostC, String customerPhone,
            boolean customerActive)
            throws SQLException {
        
        String query = "UPDATE IOTBAYUSER.CUSTOMER SET CUSTOMEREMAIL = ?, "
                + "FNAME = ?, LNAME = ?, PHONENUMBER = ?, PASSWORD = ?,"
                + "STREETADDRESS = ?, UNITNUMBER = ?, CITY = ?, STATE = ?,"
                + "POSTALCODE = ?, GENDER = ?, ACTIVE = ? WHERE CUSTOMEREMAIL=?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, customerEmail);
        stmt.setString(2, customerFname);
        stmt.setString(3, customerLname);
        stmt.setString(4, customerPhone);
        stmt.setString(5, customerPass);
        stmt.setString(6, customerSAdd);
        stmt.setString(7, customerUnit);
        stmt.setString(8, customerCity);
        stmt.setString(9, customerState);
        stmt.setString(10, customerPostC);
        stmt.setString(11, customerGender);
        stmt.setBoolean(12, customerActive);
        stmt.setString(13, customerEmail);

        stmt.executeUpdate();
    }

    // Delete (Delete a Customer from the database)
    public void deleteCustomer(String customerEmail) throws SQLException {
        String query = "DELETE FROM IOTBAYUSER.CUSTOMER WHERE CUSTOMEREMAIL = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, customerEmail);

        stmt.executeUpdate();
    }

    public ArrayList<Customer> fetchCustomers() throws SQLException {
        String query = "SELECT * FROM Customer";
        ResultSet rs = st.executeQuery(query);
        ArrayList<Customer> result = new ArrayList();

        while (rs.next()) {
            String customerEmail = rs.getString(1);
            String customerFname = rs.getString(2);
            String customerLname = rs.getString(3);
            String customerPhone = rs.getString(4);
            String customerPass = rs.getString(5);
            String customerSAdd = rs.getString(6);
            String customerUnit = rs.getString(7);
            String customerCity = rs.getString(8);
            String customerState = rs.getString(9);
            String customerPostC = rs.getString(10);
            boolean customerLoginStatus = rs.getBoolean(11);
            java.util.Date customerRegisterDate = rs.getDate(12);
            String customerGender = rs.getString(13);
<<<<<<< HEAD
            boolean customerActive = rs.getBoolean(14);
            result.add(new Customer(customerFname, customerLname, customerEmail,
                    customerPass, customerGender, customerUnit, customerSAdd,
                    customerCity, customerState, customerPostC, customerPhone,
                    customerRegisterDate, customerLoginStatus, customerActive));
=======
            result.add(new Customer(customerFname, customerLname, customerEmail, customerPass, customerGender,
                    customerUnit, customerSAdd, customerCity, customerState, customerPostC, customerPhone,
                    customerRegisterDate, customerLoginStatus));
>>>>>>> more on customer register validation
        }

        return result;
    }

    public boolean checkCustomer(String customerEmail) throws SQLException {
        String query = "SELECT * FROM IOTBAYUSER.Customer " + "where CUSTOMEREMAIL = " + customerEmail;
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String email = rs.getString(1);
            if (email.equals(customerEmail)) {
                return true;
            }
        }
        return false;
    }
}
