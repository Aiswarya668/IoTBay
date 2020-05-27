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
    
    public DBSupplierInformationManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }
    
    //Read (Find the Supplier)
    public Supplier findSupplier(String contactName, String supplierName) throws SQLException {
        String fetch ="select * from IOTBAYUSER.ShippingCompany where CONTACTNAME = '" + contactName + " ' and COMPANYNAME='" + supplierName + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            String supplierContactName = rs.getString(3); 
            String supplierCompanyName = rs.getString(2);
            if (supplierContactName.equals(contactName) && supplierCompanyName.equals(supplierName)) {
                String supplierEmail = rs.getString(1);
                String supplierAddress = rs.getString(4);
                return new Supplier(contactName, supplierName, supplierEmail, supplierAddress);   
            }
        }
        return null;
    }
    
    //Create (Add Supplier data into the database)
    public void addSupplier(String supplierEmail, String supplierName, String contactName, String supplierAddress) throws SQLException {
        st.executeUpdate("INSERT INTO IOTBAYUSER.Supplier" + "VALUES ('" + supplierEmail +"', '" + supplierName + "', '" + contactName + "', '"+ supplierAddress +"' )");
    }
    
    //Update (Update a Supplier's details in the database)
    public void updateSupplier(String supplierEmail, String supplierName, String contactName, String supplierAddress) throws SQLException {
        st.executeUpdate("UPDATE IOTBAYUSER.Supplier SET COMPANYNAME='" + supplierName + "', CONTACTNAME='" + contactName + "', COMPANYADDRESS='" + supplierAddress +"' WHERE COMPANYEMAIL='"+ supplierEmail +"' )");
    }
    
    //Delete (Delete a Supplier from the database)
    public void deleteSupplier(String supplierEmail) throws SQLException {
        st.executeUpdate("DELETE FROM IOTBAYUSER.Supplier WHERE EMAIL='" + supplierEmail + "'");
    }
    
    public ArrayList<Supplier> fectSupplier() throws SQLException {
    String fetch = "select * from SUPPLIER";
    ResultSet rs = st.executeQuery(fetch);
    ArrayList<Supplier> temp = new ArrayList();
    
    while (rs.next()) {
        String contactName = rs.getString(3);
        String supplierName = rs.getString(2);
        String supplierEmail = rs.getString(1);
        String supplierAddress = rs.getString(4);
        temp.add(new Supplier(supplierEmail, supplierName, contactName, supplierAddress));
    }
    return temp;
}
    
    public boolean checkSupplier(String contactName, String supplierName) throws SQLException {
        String fetch = "select * from IOTBAYUSER.Supplier where CONTACTNAME = '" + contactName + "' and SUPPLIERNAME='" + supplierName + "'";
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
