/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.iotbay.dao;

import uts.isd.model.Staff;
import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author seant
 */
public class DBStaffManager {

    private Statement st;

    public DBStaffManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    //Find staff by email and password in the database   
    public Staff findStaff(String email, String password) throws SQLException {
        //setup the select sql query string       
        String query = "SELECT * FROM IOTBAYUSER.STAFF WHERE STAFFEMAIL='" + email + "' AND PASSWORD='" + password + "'";
        //execute this query using the statement field
        //add the results to a ResultSet       
        ResultSet rs = st.executeQuery(query);
        //search the ResultSet for a staff using the parameters               
        while (rs.next()) {
            String staffEmail = rs.getString(1);
            String staffPass = rs.getString(5);
            if (staffEmail.equals(email) && staffPass.equals(password)) {
                String staffFName = rs.getString(2);
                String staffLName = rs.getString(3);
                String staffPhone = rs.getString(4);
                String staffStreetAddr = rs.getString(6);
                String staffUnitNo = rs.getString(7);
                String staffCity = rs.getString(8);
                String staffState = rs.getString(9);
                String staffPostCode = rs.getString(10);
                String staffManager = rs.getString(11);
                boolean staffLoginStatus = rs.getBoolean(12);
                java.util.Date staffRegisterDate = rs.getDate(13);
                String staffContractType = rs.getString(14);
                int staffPayHr = rs.getInt(15);
                return new Staff(staffFName, staffLName, staffEmail, staffPass, staffUnitNo, staffStreetAddr, staffCity, staffState, staffPostCode, staffRegisterDate, staffLoginStatus, staffPhone, staffContractType, staffPayHr, staffManager);
            }
        }
        return null;
    }

    //Add a staff-data into the database   
    public void addStaff(String email, String fname, String lname, String phone, String password, String streetAddr, String unitNo, String city, String state, String postCode, String manager, String contractType, String payHr) throws SQLException {
        //code for add-operation
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = formatter.format(new java.util.Date());
        st.executeUpdate("INSERT INTO IOTBAYUSER.STAFF VALUES ('" + email + "', '" + fname + "', '" + lname + "', '" + phone + "', '" + password + "', '" + streetAddr + "', '" + unitNo + "', '" + city + "', '" + state + "', '" + postCode + "', '" + manager + "', '" + "false" + "', '" + currentDate + "', '" + contractType + "', " + payHr + ")"); // payHr is type int (does not have quotation marks)
    }

    //update a staff details in the database   
    public void updateStaff(String email, String fname, String lname, String phone, String password, String streetAddr, String unitNo, String city, String state, String postCode, String manager, String contractType, String payHr) throws SQLException {
        //code for update-operation
        st.executeUpdate("UPDATE IOTBAYUSER.STAFF SET STAFFEMAIL='" + email + "', FNAME='" + fname + "', LNAME='" + lname + "', PHONENUMBER='" + phone + "', PASSWORD='" + password + "', STREETADDRESS='" + streetAddr + "', UNITNUMBER='" + unitNo + "', CITY='" + city + "', STATE='" + state + "', POSTALCODE='" + postCode + "', MANAGER='" + manager + "', CONTRACTTYPE='" + contractType + "', PAYHR=" + payHr + " WHERE STAFFEMAIL='" + email + "'");
    }

    //delete a staff from the database   
    public void deleteStaff(String email) throws SQLException {
        //code for delete-operation   
        st.executeUpdate("DELETE FROM IOTBAYUSER.STAFF WHERE STAFFEMAIL='" + email + "'");
    }

    public ArrayList<Staff> fetchAll() throws SQLException {
        String fetch = "SELECT * FROM STAFF";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Staff> temp = new ArrayList();

        while (rs.next()) {
            String staffEmail = rs.getString(1);
            String staffFName = rs.getString(2);
            String staffLName = rs.getString(3);
            String staffPhone = rs.getString(4);
            String staffPass = rs.getString(5);
            String staffStreetAddr = rs.getString(6);
            String staffUnitNo = rs.getString(7);
            String staffCity = rs.getString(8);
            String staffState = rs.getString(9);
            String staffPostCode = rs.getString(10);
            String staffManager = rs.getString(11);
            boolean staffLoginStatus = rs.getBoolean(12);
            java.util.Date staffRegisterDate = rs.getDate(13);
            String staffContractType = rs.getString(14);
            int staffPayHr = rs.getInt(15);
            temp.add(new Staff(staffFName, staffLName, staffEmail, staffPass, staffUnitNo, staffStreetAddr, staffCity, staffState, staffPostCode, staffRegisterDate, staffLoginStatus, staffPhone, staffContractType, staffPayHr, staffManager));
        }
        return temp;
    }
}
