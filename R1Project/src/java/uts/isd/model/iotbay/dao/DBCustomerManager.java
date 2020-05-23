/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.iotbay.dao;

import uts.isd.model.Customer;
import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author seant
 */
public class DBCustomerManager {

    private Statement st;

    public DBCustomerManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    //Find customer by email and password in the database   
    public Customer findCustomer(String email, String password) throws SQLException {
        //setup the select sql query string       
        String query = "SELECT * FROM IOTBAYUSER.CUSTOMER WHERE CUSTOMEREMAIL='" + email + "' AND PASSWORD='" + password + "'";
        //execute this query using the statement field
        //add the results to a ResultSet       
        ResultSet rs = st.executeQuery(query);
        //search the ResultSet for a customer using the parameters               
        while (rs.next()) {
            String customerEmail = rs.getString(1); // get string at index 2 (column 2)
            String customerPass = rs.getString(5); // get string at index 3
            if (customerEmail.equals(email) && customerPass.equals(password)) {
                String customerFName = rs.getString(2);
                String customerLName = rs.getString(3);
                String customerPhone = rs.getString(4);
                String customerStreetAddr = rs.getString(6);
                String customerUnitNo = rs.getString(7);
                String customerCity = rs.getString(8);
                String customerState = rs.getString(9);
                String customerPostCode = rs.getString(10);
                boolean customerLoginStatus = rs.getBoolean(11);
                java.util.Date customerRegisterDate = rs.getDate(12);
                String customerGender = rs.getString(13);;
                return new Customer(customerFName, customerLName, customerEmail, customerPass, customerGender, customerUnitNo, customerStreetAddr, customerCity, customerState, customerPostCode, customerPhone, customerRegisterDate, customerLoginStatus);
            }
        }
        return null;
    }

    //Add a customer-data into the database   
    public void addCustomer(String email, String fname, String lname, String phone, String password, String streetAddr, String unitNo, String city, String state, String postCode, String gender) throws SQLException {;
        //code for add-operation
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = formatter.format(new java.util.Date());
        st.executeUpdate("INSERT INTO IOTBAYUSER.CUSTOMER VALUES ('" + email + "', '" + fname + "', '" + lname + "', '" + phone + "', '" + password + "', '" + streetAddr + "', '" + unitNo + "', '" + city + "', '" + state + "', '" + postCode + "', '" + "false" + "', '" + currentDate + "', '" + gender + "')");
    }

    //update a customer details in the database   
    public void updateCustomer(String email, String fname, String lname, String phone, String password, String streetAddr, String unitNo, String city, String state, String postCode, String gender) throws SQLException {
        //code for update-operation
        st.executeUpdate("UPDATE IOTBAYUSER.CUSTOMER SET CUSTOMEREMAIL='" + email + "', FNAME='" + fname + "', LNAME='" + lname + "', PHONENUMBER='" + phone + "', PASSWORD='" + password + "', STREETADDRESS='" + streetAddr + "', UNITNUMBER='" + unitNo + "', CITY='" + city + "', STATE='" + state + "', POSTALCODE='" + postCode + "', GENDER'" + gender + "' WHERE CUSTOMEREMAIL='" + email + "'");
    }

    //delete a customer from the database   
    public void deleteCustomer(String email) throws SQLException {
        //code for delete-operation   
        st.executeUpdate("DELETE FROM IOTBAYUSER.CUSTOMER WHERE CUSTOMEREMAIL='" + email + "'");
    }

    public ArrayList<Customer> fetchAll() throws SQLException {
        String fetch = "SELECT * FROM CUSTOMER";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Customer> temp = new ArrayList();

        while (rs.next()) {
            String customerEmail = rs.getString(1); // get string at index 2 (column 2)
            String customerFName = rs.getString(2);
            String customerLName = rs.getString(3);
            String customerPhone = rs.getString(4);
            String customerPass = rs.getString(5); // get string at index 3
            String customerStreetAddr = rs.getString(6);
            String customerUnitNo = rs.getString(7);
            String customerCity = rs.getString(8);
            String customerState = rs.getString(9);
            String customerPostCode = rs.getString(10);
            boolean customerLoginStatus = rs.getBoolean(11);
            java.util.Date customerRegisterDate = rs.getDate(12);
            String customerGender = rs.getString(13);
            temp.add(new Customer(customerFName, customerLName, customerEmail, customerPass, customerGender, customerUnitNo, customerStreetAddr, customerCity, customerState, customerPostCode, customerPhone, customerRegisterDate, customerLoginStatus));
        }
        return temp;
    }
}
