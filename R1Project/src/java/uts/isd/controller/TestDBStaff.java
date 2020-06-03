/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import uts.isd.model.Staff;
import uts.isd.model.iotbay.dao.DBConnector;
import uts.isd.model.iotbay.dao.DBStaffManager;

public class TestDBStaff {

    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private DBStaffManager db;

    public static void main(String[] args) throws SQLException {
        (new TestDBStaff()).runQueries();
    }

    public TestDBStaff() {
        try {
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBStaffManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDBStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private char readChoice() {
        System.out.print("Operation CRUDS or * to exit: ");
        return in.nextLine().charAt(0);
    }

    private void runQueries() throws SQLException {
        char c;

        while ((c = readChoice()) != '*') {
            switch (c) {
                case 'C':
                    testAdd();
                    break;
                case 'R':
                    testRead();
                    break;
                case 'U':
                    testUpdate();
                    break;
                case 'D':
                    testDelete();
                    break;
                case 'S':
                    showAll();
                    break;
                case 'A':
                    testDeactivate();
                    break;
                default:
                    System.out.println("Unknown Command");
                    break;
            }
        }
    }

    private void testAdd() {
        System.out.print("Staff email: ");
        String email = in.nextLine();
        System.out.print("Staff first name: ");
        String fname = in.nextLine();
        System.out.print("Staff last name: ");
        String lname = in.nextLine();
        System.out.print("Staff phone number: ");
        String phone = in.nextLine();
        System.out.print("Staff password: ");
        String password = in.nextLine();
        System.out.print("Staff street address: ");
        String streetAddr = in.nextLine();
        System.out.print("Staff unit number: ");
        String unitNo = in.nextLine();
        System.out.print("Staff city: ");
        String city = in.nextLine();
        System.out.print("Staff state: ");
        String state = in.nextLine();
        System.out.print("Staff post code: ");
        String postCode = in.nextLine();
        System.out.print("Staff manager: ");
        String manager = in.nextLine();
        System.out.print("Staff contract type: ");
        String contractType = in.nextLine();
        System.out.print("Staff pay per hour: ");
        String payHr = in.nextLine();
        try {
            db.addStaff(email, fname, lname, phone, password, streetAddr, unitNo, city, state, postCode, manager, contractType, payHr);
        } catch (SQLException ex) {
            Logger.getLogger(TestDBStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Staff is added to the database.");
    }

    private void testRead() throws SQLException {
        System.out.print("Staff email: ");
        String email = in.nextLine();
        Staff staff = db.findStaff(email);
        if (staff != null) {
            System.out.println("Staff " + staff.getFirstName() + " " + staff.getLastName() + " exists in the database.");
        } else {
            System.out.println("Staff does not exits.");
        }
    }

    private void testUpdate() {
        System.out.print("Staff email: ");
        String email = in.nextLine();

        try {
            if (db.findStaff(email) != null) {
                System.out.print("Staff first name: ");
                String fname = in.nextLine();
                System.out.print("Staff last name: ");
                String lname = in.nextLine();
                System.out.print("Staff password: ");
                String password = in.nextLine();
                System.out.print("Staff phone number: ");
                String phone = in.nextLine();
                System.out.print("Staff street address: ");
                String streetAddr = in.nextLine();
                System.out.print("Staff unit number: ");
                String unitNo = in.nextLine();
                System.out.print("Staff city: ");
                String city = in.nextLine();
                System.out.print("Staff state: ");
                String state = in.nextLine();
                System.out.print("Staff post code: ");
                String postCode = in.nextLine();
                System.out.print("Staff manager: ");
                String manager = in.nextLine();
                System.out.print("Staff contract type: ");
                String contractType = in.nextLine();
                System.out.print("Staff pay per hour: ");
                int payHr = in.nextInt();
                in.nextLine(); // Consumer newline left-over from nextInt()
                System.out.print("Staff activated (true/false): ");
                boolean active = in.nextBoolean();
                in.nextLine(); // Consumer newline left-over from nextInt()
                db.updateStaff(email, fname, lname, phone, password, streetAddr, unitNo, city, state, postCode, manager, contractType, payHr, active);
            } else {
                System.out.println("Staff does not exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDBStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void testDelete() {
        System.out.print("Staff email: ");
        String email = in.nextLine();
        try {
            if (db.findStaff(email) != null) {
                db.deleteStaff(email);
                System.out.println("Staff " + email + " was deleted from the database.");
            } else {
                System.out.println("Staff does not exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDBStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        private void testDeactivate() {
        System.out.print("Staff email: ");
        String email = in.nextLine();
        try {
            if (db.findStaff(email) != null) {
                db.deactivateStaff(email);
                System.out.println("Staff " + email + " was deactivated in the database.");
            } else {
                System.out.println("Staff does not exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDBCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showAll() throws SQLException {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            ArrayList<Staff> staffs = db.fetchStaffs();
            System.out.println("STAFF TABLE: ");
            staffs.stream().forEach((staff) -> {
                System.out.printf("%-40s %-20s %-20s %-20s %-30s %-20s %-10s %-20s %-20s %-10s %-20s %-10s %-10s %-10s %-10s %-10s \n", staff.getEmail(), staff.getFirstName(), staff.getLastName(), staff.getPhoneNumber(), staff.getPassword(), staff.getStreetAddress(), staff.getUnitNumber(), staff.getCity(), staff.getState(), staff.getPostcode(), staff.getManager(), staff.isLoginStatus(), formatter.format(staff.getDateRegistered()), staff.getContractType(), staff.getHourlyPay(), staff.isActive());
            });
            System.out.println();
        } catch (SQLException ex) {
            Logger.getLogger(TestDBStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
