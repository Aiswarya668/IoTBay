package uts.isd.controller;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import uts.isd.model.Device;
import uts.isd.model.iotbay.dao.DB;
import uts.isd.model.iotbay.dao.DBConnector;
import uts.isd.model.iotbay.dao.DBDeviceManager;



/**
 *
 * @author aiswarya.r
 */
public class TestDeviceDB {
    
        private static Scanner in = new Scanner(System.in);
        private DBConnector connector;
        private Connection conn;
        private DBDeviceManager db;

        
    public static void main(String[] args) throws SQLException {
         (new TestDeviceDB()).runQueries();
    }

    public TestDeviceDB() {
        try {
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBDeviceManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDeviceDB.class.getName()).log(Level.SEVERE, null, ex);
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
       
        System.out.print("Device name: ");
        String deviceName = in.nextLine();
        System.out.print("Device type: ");
        String type = in.nextLine();
        System.out.print("Device cost: ");
        double cost = in.nextDouble();
        System.out.print("Device stock quantity: ");
        int stockQuantity = in.nextInt();
        System.out.print("Device description: ");
        String description = in.next();
        in.nextLine(); 
  
        try {
            db.addDevice(deviceName, type, cost, stockQuantity, description);
        } catch (SQLException ex) {
            Logger.getLogger(TestDeviceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Device is added to the database.");
    }
        
        
        
        private void testRead() throws SQLException {
        
        System.out.print("Device name: ");
        String deviceName = in.nextLine();
        System.out.print("Device type: ");
        String type = in.nextLine();
        
        Device device = db.findDevice(deviceName, type);
        
        if (device != null) {
            System.out.println("Device: " + device.getDeviceName() + " " + device.getType() + " exists in the database.");
        } else {
            System.out.println("Device does not exits.");
        }

    }
        
        
        
        private void testUpdate() {
        System.out.print("Device name: ");
        String deviceName = in.nextLine();
        System.out.print("Device type: ");
        String type = in.nextLine();
        
       
        try {
            Device device = db.findDevice(deviceName, type);
            if (device != null) {
                //System.out.print("Device name: ");
                //String deviceName = in.nextLine();
                //System.out.print("Device type: ");
                //String type = in.nextLine();
                System.out.print("Device cost: ");
                double cost = in.nextDouble();
                in.nextLine(); 
                System.out.print("Device stock quantity: ");
                int stockQuantity = in.nextInt();
                in.nextLine(); 
                System.out.print("Device description: ");
                String description = in.nextLine();
                
                db.updateDevice(deviceName, type, cost, stockQuantity, description);
                
            } else {
                System.out.println("Device does not exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDeviceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
          private void testDelete() {
        
        System.out.print("Device name: ");
        String deviceName = in.nextLine();
        System.out.print("Device type: ");
        String type = in.nextLine();
        System.out.print("Device id: ");
        int deviceID = in.nextInt();
        in.nextLine(); 
        
        
        try {
            if (db.findDevice(deviceName, type) != null) {
                db.deleteDevice(deviceID);
                System.out.println("Device " + deviceName + " was deleted from the database.");
            } else {
                System.out.println("Device does not exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDeviceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
        private void showAll() throws SQLException {
        try {
            ArrayList<Device> devices = db.fetchDevice();
            System.out.println("DEVICE TABLE: ");
            // lambda expression for every device show all device fields 
            devices.stream().forEach((device) -> {
                System.out.printf("%-20s %-20s %-20s %-20s %-10s %-100s \n", device.getDeviceID(), device.getDeviceName(), device.getType(), device.getCost(), device.getStockQuantity(), device.getDescription());
            });
            System.out.println();
         } catch (SQLException ex) {
            Logger.getLogger(TestDeviceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
