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
import uts.isd.model.Customer;
import uts.isd.model.iotbay.dao.DBConnector;
import uts.isd.model.iotbay.dao.DBCustomerManager;

public class TestDBCustomer {

    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private DBCustomerManager db;

    public static void main(String[] args) throws SQLException {
        (new TestDBCustomer()).runQueries();
    }

    public TestDBCustomer() {
        try {
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBCustomerManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDBCustomer.class.getName()).log(Level.SEVERE, null, ex);
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
                default:
                    System.out.println("Unknown Command");
                    break;
            }
        }
    }

    private void testAdd() {
        System.out.print("Customer email: ");
        String email = in.nextLine();
        System.out.print("Customer first name: ");
        String fname = in.nextLine();
        System.out.print("Customer last name: ");
        String lname = in.nextLine();
        System.out.print("Customer phone number: ");
        String phone = in.nextLine();
        System.out.print("Customer password: ");
        String password = in.nextLine();
        System.out.print("Customer street address: ");
        String streetAddr = in.nextLine();
        System.out.print("Customer unit number: ");
        String unitNo = in.nextLine();
        System.out.print("Customer city: ");
        String city = in.nextLine();
        System.out.print("Customer state: ");
        String state = in.nextLine();
        System.out.print("Customer post code: ");
        String postCode = in.nextLine();
        System.out.print("Customer gender: ");
        String gender = in.nextLine();
        try {
            db.addCustomer(email, fname, lname, phone, password, streetAddr, unitNo, city, state, postCode, gender);
        } catch (SQLException ex) {
            Logger.getLogger(TestDBCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Customer is added to the database.");
    }

    private void testRead() throws SQLException {
        System.out.print("Customer email: ");
        String email = in.nextLine();
        Customer customer = db.findCustomer(email);
        if (customer != null) {
            System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " exists in the database.");
        } else {
            System.out.println("Customer does not exits.");
        }

    }

    private void testUpdate() {
        System.out.print("Customer email: ");
        String email = in.nextLine();

        try {
            if (db.findCustomer(email) != null) {
                System.out.print("Customer first name: ");
                String fname = in.nextLine();
                System.out.print("Customer last name: ");
                String lname = in.nextLine();
                System.out.print("Customer password: ");
                String password = in.nextLine();
                System.out.print("Customer phone number: ");
                String phone = in.nextLine();
                System.out.print("Customer street address: ");
                String streetAddr = in.nextLine();
                System.out.print("Customer unit number: ");
                String unitNo = in.nextLine();
                System.out.print("Customer city: ");
                String city = in.nextLine();
                System.out.print("Customer state: ");
                String state = in.nextLine();
                System.out.print("Customer post code: ");
                String postCode = in.nextLine();
                System.out.print("Customer gender: ");
                String gender = in.nextLine();
                db.updateCustomer(email, fname, lname, password, gender, unitNo, streetAddr, city, state, postCode, phone);;
            } else {
                System.out.println("Customer does not exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDBCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void testDelete() {
        System.out.print("Customer email: ");
        String email = in.nextLine();
        try {
            if (db.findCustomer(email) != null) {
                db.deleteCustomer(email);
                System.out.println("Customer " + email + " was deleted from the database.");
            } else {
                System.out.println("Customer does not exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDBCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showAll() throws SQLException {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            ArrayList<Customer> customers = db.fetchCustomers();
            System.out.println("CUSTOMERS TABLE: ");
            customers.stream().forEach((customer) -> {
                System.out.printf("%-40s %-20s %-20s %-20s %-30s %-20s %-10s %-20s %-20s %-10s %-10s %-20s %-10s \n", customer.getEmail(), customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber(), customer.getPassword(), customer.getStreetAddress(), customer.getUnitNumber(), customer.getCity(), customer.getState(), customer.getPostcode(), customer.isLoginStatus(), formatter.format(customer.getDateRegistered()), customer.getGender());
            });
            System.out.println();
        } catch (SQLException ex) {
            Logger.getLogger(TestDBCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
