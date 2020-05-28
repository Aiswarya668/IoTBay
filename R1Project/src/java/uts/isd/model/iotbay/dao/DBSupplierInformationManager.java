/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.iotbay.dao;

import uts.isd.model.Supplier;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Anastasia
 */
public class DBSupplierInformationManager {
    private Statement st;
    private Connection conn;
    
    public DBSupplierInformationManager(Connection conn) throws SQLException {
        st = conn.createStatement();
        this.conn = conn;
    }
   
    
    //Read (Find the Supplier)
    public Supplier findSupplier(String contactName, String supplierName) throws SQLException {
        String fetch ="select * from IOTBAYUSER.Supplier where CONTACTNAME = '" + contactName + " ' and SUPPLIERNAME='" + supplierName + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            String supplierContactName = rs.getString(3); 
            String supplierCompanyName = rs.getString(2);
            if (supplierContactName.equals(contactName) && supplierCompanyName.equals(supplierName)) {
                String supplierEmail = rs.getString(1);
                String supplierAddress = rs.getString(4);
                Boolean active = rs.getBoolean(5);
                return new Supplier( supplierEmail, supplierName, contactName, supplierAddress, active);   
            }
        }
        return null;
    }
    
    //Create (Add Supplier data into the database)
    public void addSupplier(String supplierEmail, String supplierName, String contactName, String supplierAddress, Boolean active) throws SQLException {
        //st.executeUpdate("INSERT INTO IOTBAYUSER.SUPPLIER" + "VALUES ('" + supplierEmail +"', '" + supplierName + "', '" + contactName + "', '"+ supplierAddress +"', " + active + ")");
    
        String query = "INSERT INTO IOTBAYUSER.Supplier (supplierEmail, supplierName, contactName, supplierAddress, active) VALUES " + "(?,?,?,?,?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, supplierEmail);
        stmt.setString(2, supplierName);
        stmt.setString(3, contactName);
        stmt.setString(4, supplierAddress);
        stmt.setBoolean(5, active);

        stmt.executeUpdate();
    }
    
    //Update (Update a Supplier's details in the database)
    public void updateSupplier(String supplierEmail, String supplierName, String contactName, String supplierAddress, Boolean active, String oldSupplierEmail) throws SQLException {
        st.executeUpdate("UPDATE IOTBAYUSER.SUPPLIER SET SUPPLIERNAME='" + supplierName + "', CONTACTNAME='" + contactName + "', SUPPLIEREMAIL='" + supplierEmail + "', SUPPLIERADDRESS='" + supplierAddress +"', ACTIVE= "+ active +" WHERE SUPPLIEREMAIL='"+ oldSupplierEmail + "'");
        
    }
    
    //Delete (Delete a Supplier from the database)
    public void deleteSupplier(String contactName, String supplierName) throws SQLException {
        st.executeUpdate("DELETE FROM IOTBAYUSER.SUPPLIER WHERE CONTACTNAME='" + contactName + "' AND SUPPLIERNAME='" + supplierName + "'");
    }
    
    public ArrayList<Supplier> fetchSuppliers() throws SQLException {
    String fetch = "select * from SUPPLIER";
    ResultSet rs = st.executeQuery(fetch);
    ArrayList<Supplier> temp = new ArrayList();
    
    while (rs.next()) {
        String contactName = rs.getString(3);
        String supplierName = rs.getString(2);
        String supplierEmail = rs.getString(1);
        String supplierAddress = rs.getString(4);
        Boolean active = rs.getBoolean(5);
        temp.add(new Supplier(supplierEmail, supplierName, contactName, supplierAddress, active));
    }
    return temp;
}
    
    public boolean checkSupplier(String contactName, String supplierName) throws SQLException {
        String fetch = "select * from IOTBAYUSER.SUPPLIER where CONTACTNAME = '" + contactName + "' and SUPPLIERNAME='" + supplierName + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while(rs.next()) {
            String SupplierContactName = rs.getString(3);
            String SupplierSupplierName = rs.getString(2);
            if (SupplierContactName.equals(contactName) && SupplierSupplierName.equals(supplierName)) {
                return true;
            }
        }
        return false;
    }
}
