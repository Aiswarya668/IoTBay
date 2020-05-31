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
    public ArrayList<ApplicationAccessLogs> findCustomerLogs(String email) 
    throws SQLException {
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
            logs.add(new ApplicationAccessLogs(logId, customerEmail, staffEmail, 
            timestamp, logDescription));
        }

        if (logs.isEmpty()) {
            throw new SQLException("No logs for customer exists");
        }
        else {
            return logs;
        }
    }
    
    // Read (Find the logs for a staff email)
    public ArrayList<ApplicationAccessLogs> findStaffLogs(String email) 
    throws SQLException {
        String query = "select * from IOTBAYUSER.APPLICATIONACCESSLOGS where STAFFEMAIL=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        ArrayList<ApplicationAccessLogs> logs = 
        new ArrayList<ApplicationAccessLogs>();
        
        while (rs.next()) {
            String logId = rs.getString(1);
            String customerEmail = rs.getString(2);
            String staffEmail = rs.getString(3);
            Timestamp timestamp = rs.getTimestamp(4);
            String logDescription = rs.getString(5);
            logs.add(new ApplicationAccessLogs(logId, customerEmail, 
            staffEmail, timestamp, logDescription));
        }
        if (logs.isEmpty()) {
            throw new SQLException("No logs for staff exists");
        }
        else {
            return logs;
        }
    }

    // Create (Add Customer data into the logs)
        public void addCustomerLog(String customerEmail, String logDescription) 
        throws SQLException {

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
    public void addStaffLog(String staffEmail, String logDescription) 
    throws SQLException {

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

    // Filter/read customer logs based on two dates
    public ArrayList<ApplicationAccessLogs> filterCustomerLogs(String customerEmail, 
    Timestamp fromDate, Timestamp toDate) throws SQLException {

        String query = "SELECT * FROM IOTBAYUSER.ApplicationAccessLogs " + 
        "WHERE TIMESTAMP >= ? AND TIMESTAMP <= ? AND CUSTOMEREMAIL = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setTimestamp(1, fromDate);
        stmt.setTimestamp(2, toDate);
        stmt.setString(3, customerEmail);
        ResultSet rs = stmt.executeQuery();
        ArrayList<ApplicationAccessLogs> logs = 
        new ArrayList<ApplicationAccessLogs>();
        
        while (rs.next()) {
            String logId = rs.getString(1);
            String cusEmail = rs.getString(2);
            String staffEmail = rs.getString(3);
            Timestamp timestamp = rs.getTimestamp(4);
            String logDescription = rs.getString(5);
            logs.add(new ApplicationAccessLogs(logId, cusEmail, staffEmail, 
            timestamp, logDescription));
        }

        if (logs.isEmpty()) {
            throw new SQLException("No logs for customer exists");
        }
        else {
            return logs;
        }
    }

    // Filter/read customer logs based on two dates
    public ArrayList<ApplicationAccessLogs> filterStaffLogs(String staffEmail, 
    Timestamp fromDate, Timestamp toDate) throws SQLException {

        String query = "SELECT * FROM IOTBAYUSER.ApplicationAccessLogs" + 
        "WHERE TIMESTAMP >= ? AND TIMESTAMP <= ? AND STAFFEMAIL = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setTimestamp(1, fromDate);
        stmt.setTimestamp(2, toDate);
        stmt.setString(3, staffEmail);
        ResultSet rs = stmt.executeQuery();
        ArrayList<ApplicationAccessLogs> logs = 
        new ArrayList<ApplicationAccessLogs>();
        
        while (rs.next()) {
            String logId = rs.getString(1);
            String customerEmail = rs.getString(2);
            String staEmail = rs.getString(3);
            Timestamp timestamp = rs.getTimestamp(4);
            String logDescription = rs.getString(5);
            logs.add(new ApplicationAccessLogs(logId, customerEmail, staEmail, 
            timestamp, logDescription));
        }

        if (logs.isEmpty()) {
            throw new SQLException("No logs for customer exists");
        }
        else {
            return logs;
        }
    }
}
