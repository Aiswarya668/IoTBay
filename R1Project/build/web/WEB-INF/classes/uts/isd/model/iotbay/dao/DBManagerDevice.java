/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.iotbay.dao;

import uts.isd.model.Device;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author aiswarya.r
 */
public class DBManagerDevice {
    
    private Statement st;
    
    public DBManagerDevice(Connection conn) throws SQLException{
        st = conn.createStatement();
    }
    
    //Find a device by name in iotdb database 
    public Device findDevice(int deviceID, String deviceName, String type, double cost,  int stockQuantity, String description) throws SQLException{
        
        //Searches device by ID and puts in result set - rs enables iterative reading 
        String fetch = "select * from IOTBAYUSER.Device where deviceName='" + deviceName + "' and type='" + type + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()){
            String searchedDeviceName = rs.getString(2);
            String searchedType = rs.getString(3);
                if(searchedDeviceName.equals(deviceName) && searchedType.equals(type)){
                    int searchedDeviceID = rs.getInt(1);
                    double searchedDeviceCost = rs.getDouble(4);
                    int searchedDeviceStock = rs.getInt(5);
                    String searchedDeviceDescription = rs.getString(6);

                    //return device searched from query 
                    return new Device (searchedDeviceID, searchedDeviceName, searchedType, searchedDeviceCost, searchedDeviceStock, searchedDeviceDescription);
                }
            }
        //if not found - return null
        return null;
        }
        
    //Add a device into iotdb
    public void addDevice(String deviceName, String type, double cost,  int stockQuantity, String description) throws SQLException{
       st.executeUpdate("insert into IOTBAYUSER.Device " + 
               "VALUES ('" + deviceName + "', '" + type + "', '" + cost + "', '" + stockQuantity + "','" + description + "', '" + "' )");
        
    }
    
    //Update device details 
    public void updateDevice(String deviceName, String type, double cost,  int stockQuantity, String description) throws SQLException{
        st.executeUpdate("update IOTBAYUSER.Device set name='" + deviceName + "', type='" + type + "', cost='" + cost + "', stockquantity='" + stockQuantity + "', description='" + description + ")");
    }
    
    //Delete device
    public void deleteDevice(int deviceID) throws SQLException{
        st.executeUpdate("delete from IOTBAYUSER.Device where DEVICEID='" + deviceID + "' ");
    }
    
    //Fetch all devices 
    public ArrayList<Device> fetchDevice()throws SQLException{
        String fetch = "select * from DEVICES";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Device> temp = new ArrayList();
        
            while (rs.next()){
                int deviceID = rs.getInt(1);
                String deviceName = rs.getString(2);
                String type = rs.getString(3);
                double cost = rs.getDouble(4);
                int stockQuantity = rs.getInt(5);
                String description = rs.getString(6);
                temp.add(new Device(deviceID, deviceName, type, cost, stockQuantity, description));
            }
        return temp;
    }
    
    //check Device by name and type 
    public boolean checkDevice(String deviceName, String type)throws SQLException{
        String fetch = "select * from IOTBAYUSER.Device where NAME= '" + deviceName + "' and TYPE='" + type + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()){
            String searchedDeviceName = rs.getString(2);
            String searchedType = rs.getString(3);
            if(searchedDeviceName.equals(deviceName) && searchedType.equals(type)){
                return true;
            }
        }
        return false;
    }

}
    

