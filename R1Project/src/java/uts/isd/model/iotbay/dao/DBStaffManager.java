/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.iotbay.dao;

import uts.isd.model.Staff;
import java.sql.*;
import java.util.*;

/**
 *
 * @author seant
 */
public class DBStaffManager {

    private Statement st;
    private Connection conn;

    public DBStaffManager(Connection conn) throws SQLException {
        st = conn.createStatement();
        this.conn = conn;
    }

    //Find staff by email and password in the database   
    public Staff findStaff(String email) throws SQLException {
        //setup the select sql query string       
        String query = "SELECT * FROM IOTBAYUSER.STAFF WHERE STAFFEMAIL='" + email + "'";
        //execute this query using the statement field
        //add the results to a ResultSet       
        ResultSet rs = st.executeQuery(query);
        //search the ResultSet for a staff using the parameters               
        while (rs.next()) {
            String staffEmail = rs.getString(1);
            if (staffEmail.equals(email)) {
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
                boolean staffActive = rs.getBoolean(16);
                return new Staff(staffFName, staffLName, staffEmail, staffPass,
                        staffUnitNo, staffStreetAddr, staffCity, staffState,
                        staffPostCode, staffRegisterDate, staffLoginStatus,
                        staffPhone, staffContractType, staffPayHr, staffManager,
                        staffActive);
            }
        }
        throw new SQLException("No such staff exists");
    }

    //Add a staff-data into the database   
    public void addStaff(String staffEmail, String staffFname, String staffLname,
            String staffPhone, String staffPass, String staffSAdd,
            String staffUnit, String staffCity, String staffState,
            String staffPostC, String staffManager, String staffContractType,
            String staffPayHr)
            throws SQLException {
        //code for add-operation
        Timestamp date = new Timestamp(new java.util.Date().getTime());

        String query = "INSERT INTO IOTBAYUSER.STAFF VALUES "
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, staffEmail);
        stmt.setString(2, staffFname);
        stmt.setString(3, staffLname);
        stmt.setString(4, staffPhone);
        stmt.setString(5, staffPass);
        stmt.setString(6, staffSAdd);
        stmt.setString(7, staffUnit);
        stmt.setString(8, staffCity);
        stmt.setString(9, staffState);
        stmt.setString(10, staffPostC);
        stmt.setString(11, staffManager);
        stmt.setBoolean(12, true);
        stmt.setTimestamp(13, date);
        stmt.setString(14, staffContractType);
        stmt.setString(15, staffPayHr);
        stmt.setBoolean(16, true);

        stmt.executeUpdate();
        stmt.close();
    }

    //update a staff details in the database   
    public void updateStaff(String staffEmail, String staffFname,
            String staffLname, String staffPhone, String staffPass,
            String staffSAdd, String staffUnit, String staffCity,
            String staffState, String staffPostC, String staffManager,
            String staffContractType, int staffPayHr, boolean staffActive)
            throws SQLException {;
        //code for update-operation
        String query = "UPDATE IOTBAYUSER.STAFF SET STAFFEMAIL = ?, "
                + "FNAME = ?, LNAME = ?, PHONENUMBER = ?, PASSWORD = ?, "
                + "STREETADDRESS = ?, UNITNUMBER = ?, CITY = ?, STATE = ?, "
                + "POSTALCODE = ?, MANAGER = ?, CONTRACTTYPE = ?, PAYHR = ?, "
                + "ACTIVE = ? WHERE STAFFEMAIL = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, staffEmail);
        stmt.setString(2, staffFname);
        stmt.setString(3, staffLname);
        stmt.setString(4, staffPhone);
        stmt.setString(5, staffPass);
        stmt.setString(6, staffSAdd);
        stmt.setString(7, staffUnit);
        stmt.setString(8, staffCity);
        stmt.setString(9, staffState);
        stmt.setString(10, staffPostC);
        stmt.setString(11, staffManager);
        stmt.setString(12, staffContractType);
        stmt.setInt(13, staffPayHr);
        stmt.setBoolean(14, staffActive);
        stmt.setString(15, staffEmail);

        stmt.executeUpdate();
        stmt.close();
    }

    //delete a staff from the database   
    public void deleteStaff(String staffEmail) throws SQLException {
        //code for delete-operation   
        String query = "DELETE FROM IOTBAYUSER.STAFF WHERE STAFFEMAIL = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, staffEmail);

        stmt.executeUpdate();
        stmt.close();
    }

    // Fetch all staff accounts
    public ArrayList<Staff> fetchStaffs() throws SQLException {
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
            boolean staffActive = rs.getBoolean(16);
            temp.add(new Staff(staffFName, staffLName, staffEmail, staffPass,
                    staffUnitNo, staffStreetAddr, staffCity, staffState,
                    staffPostCode, staffRegisterDate, staffLoginStatus,
                    staffPhone, staffContractType, staffPayHr, staffManager,
                    staffActive));
        }
        return temp;
    }

    // Check if staff exists in database
    public boolean checkStaff(String staffEmail) throws SQLException {
        String query = "SELECT * FROM IOTBAYUSER.STAFF "
                + "where STAFFEMAIL = " + staffEmail;
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String email = rs.getString(1);
            if (email.equals(staffEmail)) {
                return true;
            }
        }
        return false;
    }
    
    // deactivate a staff - set their active status to false
    public void deactivateStaff(String staffEmail) throws SQLException {
        String query = "UPDATE IOTBAYUSER.STAFF SET ACTIVE = ? WHERE STAFFEMAIL = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setBoolean(1, false);
        stmt.setString(2, staffEmail);
        
        stmt.executeUpdate();
        stmt.close();
    }
    // activate a staff - set their active status to true
    public void activateStaff(String staffEmail) throws SQLException {
        String query = "UPDATE IOTBAYUSER.STAFF SET ACTIVE = ? WHERE STAFFEMAIL = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setBoolean(1, true);
        stmt.setString(2, staffEmail);
        
        stmt.executeUpdate();
        stmt.close();
    }
}
