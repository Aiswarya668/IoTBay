/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import uts.isd.model.ApplicationAccessLogs;
import uts.isd.model.iotbay.dao.DBConnector;
import uts.isd.model.iotbay.dao.DBApplicationLogsManager;

public class TestDBApplicationLogs {

    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private DBApplicationLogsManager db;

    public static void main(String[] args) throws SQLException {
        (new TestDBApplicationLogs()).runQueries();
    }

    public TestDBApplicationLogs() {
        try {
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBApplicationLogsManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDBApplicationLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private char readChoice() {
        System.out.println("Operation C (add customer log)");
        System.out.println("Operation S (add staff log)");
        System.out.println("Operation X (read customer log)");
        System.out.println("Operation Y (read staff log)");
        System.out.println("Operation * to exit");
        return in.nextLine().charAt(0);
    }

    private void runQueries() throws SQLException {
        char c;

        while ((c = readChoice()) != '*') {
            switch (c) {
                case 'C':
                    testAddCustomer();
                    break;
                case 'S':
                    testAddStaff();
                    break;
                case 'X':
                    testReadCustomer();
                    break;
                case 'Y':
                    testReadStaff();
                    break;
                default:
                    System.out.println("Unknown Command");
                    break;
            }
        }
    }

    private void testAddCustomer() {
        System.out.print("Customer Email: ");
        String email = in.nextLine();
        System.out.print("Timestamp: ");
        String time = in.nextLine();
        try {
            db.addCustomerLog(email, time);
        } catch (SQLException ex) {
            Logger.getLogger(TestDBApplicationLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Customer log is added to the database.");
    }

    private void testAddStaff() {
        System.out.print("Staff Email: ");
        String email = in.nextLine();
        System.out.print("Timestamp: ");
        String time = in.nextLine();
        try {
            db.addStaffLog(email, time);
        } catch (SQLException ex) {
            Logger.getLogger(TestDBApplicationLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Staff log is added to the database.");
    }

    private void testReadCustomer() throws SQLException {
        System.out.print("Customer email: ");
        String email = in.nextLine();
        ArrayList<ApplicationAccessLogs> logs = db.findCustomerLogs(email);
        if (!logs.isEmpty()) {
            for (ApplicationAccessLogs log : logs) {
                System.out.println(log.toString());
            }
        } else {
            System.out.println("Customer logs does not exits.");
        }
    }

    private void testReadStaff() throws SQLException {
        System.out.print("Staff email: ");
        String email = in.nextLine();
        ArrayList<ApplicationAccessLogs> logs = db.findStaffLogs(email);
        if (!logs.isEmpty()) {
            for (ApplicationAccessLogs log : logs) {
                System.out.println(log.toString());
            }
        } else {
            System.out.println("Staff logs does not exits.");
        }
    }
}
