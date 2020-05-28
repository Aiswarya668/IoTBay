/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import uts.isd.model.Supplier;
import uts.isd.model.iotbay.dao.DBConnector;
import uts.isd.model.iotbay.dao.DBSupplierInformationManager;
import java.text.Format;

/**
 *
 * @author Anastasia
 */
public class TestDBSupplier {
    
    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private DBSupplierInformationManager db;

    public static void main(String[] args) throws SQLException {
        (new TestDBSupplier()).runQueries();
    }

    public TestDBSupplier() {
        try {
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBSupplierInformationManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDBSupplier.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.print("Supplier email: ");
        String supplierEmail = in.nextLine();
        System.out.print("Supplier name: ");
        String supplierName = in.nextLine();
        System.out.print("Contact name: ");
        String contactName = in.nextLine();
        System.out.print("Supplier Address: ");
        String supplierAddress = in.nextLine();
        try {db.addSupplier(supplierEmail, supplierName, contactName, supplierAddress);
        } catch (SQLException ex) {
            Logger.getLogger(TestDBSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Supplier with point of contact is added to the database.");
    }
    
    private void testRead() throws SQLException {
        System.out.print("Contact name: ");
        String contactName = in.nextLine();
        System.out.print("Supplier name: ");
        String supplierName = in.nextLine();
        Supplier supplier = db.findSupplier(contactName, supplierName);
        if (supplier != null) {
            System.out.println("Supplier " + supplier.getSupplierName() + " with point of contact " + supplier.getContactName() + " exists in the database.");
        } 
        else {
            System.out.println("Supplier with point of contact does not exist.");
        }

    }
    
    private void testUpdate() {
        System.out.print("Contact name: ");
        String contactName = in.nextLine();
        System.out.print("Supplier name: ");
        String supplierName = in.nextLine();

        try {
            if (db.findSupplier(contactName, supplierName) != null) {
                System.out.print("Supplier email: ");
                String supplierEmail = in.nextLine();
                System.out.print("Supplier name: ");
                String newSupplierName = in.nextLine();
                System.out.print("Contact name: ");
                String newContactName = in.nextLine();
                System.out.print("Supplier Address: ");
                String supplierAddress = in.nextLine();
                db.updateSupplier(supplierEmail, newSupplierName, newContactName, supplierAddress);
            } else {
                System.out.println("Supplier with point of contact does not exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDBSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void testDelete() {
        System.out.print("Contact Name: ");
        String contactName = in.nextLine();
        System.out.print("Supplier Name: ");
        String supplierName = in.nextLine();
        
        try {
            if (db.findSupplier(contactName, supplierName) != null) {
              db.deleteSupplier(contactName, supplierName);
                System.out.println("Supplier " + supplierName + " with person of contact " + contactName + "was deleted from the database.");
            } else {
                System.out.println("Supplier with person of contact does not exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDBSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    private void showAll() throws SQLException {
        try {
            ArrayList<Supplier> suppliers = db.fetchSuppliers();
            System.out.println("SUPPLIERS TABLE: ");
            suppliers.stream().forEach((supplier) -> {
                System.out.printf("%-20s %-20s %-20s %-20s \n", supplier.getSupplierEmail(), supplier.getSupplierName(), supplier.getContactName(), supplier.getSupplierAddress());
            });
            System.out.println();
        } 
        catch (SQLException ex) {
            Logger.getLogger(TestDBCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
    
}
