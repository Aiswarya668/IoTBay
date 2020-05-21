/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.iotbay.dao;

import uts.isd.model.ShippingCompany;
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
    public ShippingCompany findSupplier(String contactName, String companyName) throws SQLException {
        String fetch ="select * from IOTBAYUSER.ShippingCompany where CONTACTNAME = '" + contactName + " ' and COMPANYNAME='" + companyName + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            String shippingCompanyContactName = rs.getString(1); 
            String shippingCompanyCompanyName = rs.getString(2);
            if (shippingCompanyContactName.equals(contactName) && shippingCompanyCompanyName.equals(companyName)) {
                String companyEmail = rs.getString(3);
                String companyAddress = rs.getString(4);
                return new ShippingCompany(contactName, companyName, companyEmail, companyAddress);   
            }
        }
        return null;
    }
    
    //Create (Add Supplier data into the database)
    public void addShippingCompany(String contactName, String companyName, String companyEmail, String companyAddress) throws SQLException {
        st.executeUpdate("INSERT INTO IOTBAYUSER.ShippingCompany" + "VALUES ('" + contactName + "', '" + companyName + "', '" + companyEmail +"', '"+ companyAddress +"' )");
    }
    
    //Update (Update a Supplier's details in the database)
    public void updateShippingCompany(String contactName, String companyName, String companyEmail, String companyAddress) throws SQLException {
        st.executeUpdate("UPDATE IOTBAYUSER.ShippingCompany SET CONTACTNAME='" + contactName + "', COMPANYNAME='" + companyName + "', COMPANYADDRESS='" + companyAddress +"' WHERE COMPANYEMAIL='"+ companyEmail +"' )");
    }
    
    //Delete (Delete a Supplier from the database)
    public void deleteShippingCompany(String companyEmail) throws SQLException {
        st.executeUpdate("DELTE FROM IOTBAYUSER.ShippingCompany WHERE EMAIL='" + companyEmail + "'");
    }
    
    public ArrayList<ShippingCompany> fectShipppingCompany() throws SQLException {
    String fetch = "select * from SHIPPINGCOMPANY";
    ResultSet rs = st.executeQuery(fetch);
    ArrayList<ShippingCompany> temp = new ArrayList();
    
    while (rs.next()) {
        String contactName = rs.getString(1);
        String companyName = rs.getString(2);
        String companyEmail = rs.getString(3);
        String companyAddress = rs.getString(4);
        temp.add(new ShippingCompany(contactName, companyName, companyEmail, companyAddress));
    }
    return temp;
}
}
