/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.iotbay.dao;

import uts.isd.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

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
    
    //Read (Find the Customer)
    public Customer findCustomer(String email) throws SQLException {
        String fetch ="select * from IOTBAYUSER.Customer where EMAIL = '" + email + "';";
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
//                String customerLogin = rs.getString(11);
//                String customerRegD = rs.getString(12);
                String customerGender = rs.getString(13);
                return new Customer(customerFname, customerLname, customerEmail,
                    customerPass, customerGender,  customerUnit, customerSAdd, 
                    customerCity, customerState, customerPostC, customerPhone);
//                    customerLogin,customerRegD);
            }
        }
        return null;
    }
    
    //Create (Add Customer data into the database)
    public void addCustomer(String customerFname, String customerLname, 
            String customerEmail, String customerPass, String customerGender,
            String customerUnit, String customerSAdd, String customerCity, 
            String customerState, String customerPostC, String customerPhone)
            throws SQLException {
        
        Timestamp date = new Timestamp(new Date().getTime());
        
//        String query = "INSERT INTO IOTBAYUSER.Customer" + " VALUES ('" + 
//                customerEmail + "', '" + customerFname + "', '" + customerLname +
//                "', '" + customerPhone + "', '" + customerPass + "', '" + 
//                customerSAdd + "', '" + customerUnit + "', '" + customerCity + 
//                "', '" + customerState + "', '" + customerPostC+ "', '"  + 
//                "true" + "', '" + date.toString() + "', '" + 
//                customerGender + "' )";
        String query = "INSERT INTO IOTBAYUSER.Customer VALUES "
                + "(?,?,?,?,?,?,?,?,?,?,?,?,? )";
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
        stmt.setBoolean(11, "true");
        stmt.setTimestamp(12, date);
        stmt.setString(13, customerGender);
        
        stmt.executeUpdate();
    st.executeUpdate(query);
    }
    
    //Update (Update a Customer's details in the database)
    public void updateCustomer(String contactName, String CustomerName, String CustomerEmail, String CustomerAddress) throws SQLException {
        st.executeUpdate("UPDATE IOTBAYUSER.Customer SET CONTACTNAME='" + contactName + "', COMPANYNAME='" + CustomerName + "', COMPANYADDRESS='" + CustomerAddress +"' WHERE COMPANYEMAIL='"+ CustomerEmail +"' )");
    }
    
    //Delete (Delete a Customer from the database)
    public void deleteCustomer(String CustomerEmail) throws SQLException {
        st.executeUpdate("DELETE FROM IOTBAYUSER.Customer WHERE EMAIL='" + CustomerEmail + "'");
    }
    
    public ArrayList<Customer> fectCustomer() throws SQLException {
    String fetch = "select * from Customer";
    ResultSet rs = st.executeQuery(fetch);
    ArrayList<Customer> temp = new ArrayList();
    
    while (rs.next()) {
        String contactName = rs.getString(1);
        String CustomerName = rs.getString(2);
        String CustomerEmail = rs.getString(3);
        String CustomerAddress = rs.getString(4);
//        temp.add(new Customer(contactName, CustomerName, CustomerEmail, CustomerAddress));
    }
    return temp;
}
    
    public boolean checkCustomer(String contactName, String CustomerName) throws SQLException {
        String fetch = "select * from IOTBAYUSER.Customer where CONTACTNAME = '" + contactName + "' and CustomerNAME='" + CustomerName + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while(rs.next()) {
            String CustomerContactName = rs.getString(1);
            String CCustomerName = rs.getString(2);
            if (CustomerContactName.equals(contactName) && CCustomerName.equals(CustomerName)) {
                return true;
            }
        }
        return false;
    }
}
