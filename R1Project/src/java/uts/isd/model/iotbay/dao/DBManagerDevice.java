/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.iotbay.dao;

import uts.isd.model.Device;
import java.sql.*;
import java.util.*;


/**
 *
 * @author aiswarya.r
 */
public class DBManagerDevice {
    
    private Statement st;
    private Connection conn;
    
    public DBManagerDevice(Connection conn) throws SQLException{
        st = conn.createStatement();   
        this.conn = conn;
    }
    
    //Find a device by name + type in iotdb database
    public Device findDevice(String deviceName, String type) throws SQLException{
        
        //Searches device by ID and puts in result set - rs enables iterative reading 
        String query = "SELECT * FROM IOTBAYUSER.DEVICE WHERE DEVICENAME='" + deviceName + "' AND TYPE='" + type + "'";
        // results added to result set 
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()){
            String searchedDeviceName = rs.getString(2);
            String searchedType = rs.getString(3);
           
                if(searchedDeviceName.equals(deviceName) && searchedType.equals(type)){
                    int searchedDeviceID = rs.getInt(1);
                    //String searchedDeviceName = rs.getString(2);
                    //String searchedType = rs.getString(3);
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
    public void addDevice(String deviceName, String type, double cost, int stockQuantity, String description) throws SQLException{
       //st.executeUpdate("INSERT INTO IOTBAYUSER.DEVICE VALUES ('" + deviceName + "', '" + type + "', " + cost + ", " + stockQuantity +", '" + description + "')"); //note that cost and stockQuanity do not have '' as int/double
       String query = "INSERT INTO IOTBAYUSER.DEVICE VALUES "
                + "(?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, deviceName);
        stmt.setString(2, type);
        stmt.setDouble(3, cost);
        stmt.setInt(4, stockQuantity);
        stmt.setString(5, description);

        stmt.executeUpdate();
        st.executeUpdate(query);
    }
    
    //Update device details 
    public void updateDevice(String deviceName, String type, double cost,  int stockQuantity, String description) throws SQLException{
        st.executeUpdate("UPDATE IOTBAYUSER.DEVICE SET DEVICENAME='" + deviceName + "', TYPE='" + type + "', COST=" + cost + ", STOCKQUANTITY=" + stockQuantity + ", DESCRIPTION='" + description + "'");
    }
    
    //Delete device
    public void deleteDevice(int deviceID) throws SQLException{
        st.executeUpdate("DELETE FROM IOTBAYUSER.Device WHERE DEVICEID=" + deviceID + "");
    }
    
    //Fetch all devices 
    public ArrayList<Device> fetchDevice()throws SQLException{
        String fetch = "SELECT * FROM DEVICE";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Device> result = new ArrayList();
        
            while (rs.next()){
                int searcheddeviceID = rs.getInt(1);
                String searcheddeviceName = rs.getString(2);
                String searchedtype = rs.getString(3);
                double searchedcost = rs.getDouble(4);
                int searchedstockQuantity = rs.getInt(5);
                String searcheddescription = rs.getString(6);
                result.add(new Device(searcheddeviceID, searcheddeviceName, searchedtype, searchedcost, searchedstockQuantity, searcheddescription));
            }
        return result;
    }
    
    //check Device by name and type 
    public boolean checkDevice(String deviceName, String type)throws SQLException{
        String query = "select * from IOTBAYUSER.Device where NAME= '" + deviceName + "' and TYPE='" + type + "'";
        ResultSet rs = st.executeQuery(query);
        
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
    

