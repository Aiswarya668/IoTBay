/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uts.isd.model.iotbay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Date;
import uts.isd.model.CustomerOrder;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author sanyadua
 */
public class DBOrderManager {

    private final Statement statement;
    private final Connection connection;

    /**
     * Default Constructor of DBOrderManager
     *
     * @param connection
     * @throws SQLException
     */
    public DBOrderManager(Connection connection) throws SQLException {
        this.connection = connection;
        statement = connection.createStatement();
    }

    /**
     * Create Order into the database
     *
     * @param customerEmail
     * @param paymentID
     * @param dateOrdered
     * @param totalPrice
     * @param estArrivalDate
     * @param departureDate
     * @param supplierEmail
     * @param shipmentPrice
     * @param status
     * @param streetAddress
     * @param unitNumber
     * @param city
     * @param phoneNumber
     * @throws SQLException
     */
    public void addOrder(String customerEmail, int paymentID, int deviceID, int quantity, Timestamp dateOrdered, double totalPrice,
            Timestamp estArrivalDate, Timestamp departureDate, String supplierEmail, double shipmentPrice,
            String shipmentType, String status, String streetAddress, String unitNumber, String city,
            String state, String postalCode, String phoneNumber) throws SQLException {
        try {
            // create order then
            String query = "insert into customerorder (customeremail, paymentID, deviceID, quantity, dateordered, totalprice, estarrivaldate, departuredate, supplierEmail, shipmentprice, shippingtype, status, streetaddress, unitnumber, city, state, postalcode, phonenumber) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            System.out.println(query);
            PreparedStatement pStatement = connection.prepareStatement(query);

            pStatement.setString(1, customerEmail);
            pStatement.setInt(2, paymentID);
            pStatement.setInt(3, deviceID);
            pStatement.setInt(4, quantity);
            pStatement.setTimestamp(5, dateOrdered);
            pStatement.setDouble(6, totalPrice);
            pStatement.setTimestamp(7, estArrivalDate);
            pStatement.setTimestamp(8, departureDate);
            pStatement.setString(9, supplierEmail);
            pStatement.setDouble(10, shipmentPrice);
            pStatement.setString(11, shipmentType);
            pStatement.setString(12, status);
            pStatement.setString(13, streetAddress);
            pStatement.setString(14, unitNumber);
            pStatement.setString(15, city);
            pStatement.setString(16, state);
            pStatement.setString(17, postalCode);
            pStatement.setString(18, phoneNumber);
            pStatement.executeUpdate();

            // Also save this to device T
            //Strind deviceTQry = "";
        } catch (SQLException e) {
            e.getSQLState();
            System.out.println(e);
        }
    }

    /**
     * Search DB for order of given userEmail
     *
     * @param userEmail Email of logged in user
     * @return ArrayList of CustomerOrder
     * @throws SQLException
     */
    public ArrayList<CustomerOrder> getOrdersByUserEmail(String userEmail) throws SQLException {
        // Email of current logged in user
        // TODO: get email of logged in user
        //String email = "minkpen4@comcast.net";

        String customerOrderQry = "SELECT * FROM IOTBAYUSER.CUSTOMERORDER WHERE CUSTOMEREMAIL = '"
                + userEmail + "'";

        ArrayList<CustomerOrder> customerOrders = new ArrayList<>();

        ResultSet rSet = statement.executeQuery(customerOrderQry);

        while (rSet.next()) {
            // add all data to above array list
            int orderID = rSet.getInt("ORDERID");
            int paymentID = rSet.getInt("PAYMENTID");
            int deviceID = rSet.getInt("DEVICEID");
            int quantity = rSet.getInt("QUANTITY");
            Timestamp dateOrdered = rSet.getTimestamp("DATEORDERED");
            double totalPrice = rSet.getDouble("TOTALPRICE");
            Timestamp estArrivalDate = rSet.getTimestamp("ESTARRIVALDATE");
            Timestamp dateTimeDeparture = rSet.getTimestamp("DEPARTUREDATE");
            String supplierEmail = rSet.getString("SUPPLIEREMAIL");
            double shippingCost = rSet.getDouble("SHIPMENTPRICE");
            String shippingType = rSet.getString("SHIPPINGTYPE");
            String status = rSet.getString("STATUS");

            String streetAddress = rSet.getString("STREETADDRESS");
            String unitNumber = rSet.getString("UNITNUMBER");
            String city = rSet.getString("CITY");
            String state = rSet.getString("STATE");
            String postCode = rSet.getString("POSTALCODE");
            String phoneNumber = rSet.getString("PHONENUMBER");

            customerOrders.add(
                    new CustomerOrder(
                            orderID,
                            userEmail,
                            paymentID,
                            deviceID, 
                            quantity,
                            dateOrdered,
                            totalPrice,
                            estArrivalDate,
                            supplierEmail,
                            shippingCost,
                            dateTimeDeparture,
                            shippingType,
                            status,
                            streetAddress,
                            unitNumber,
                            city,
                            state,
                            postCode,
                            phoneNumber
                    )
            );
        }

        return customerOrders;
    }

    /**
     * Get Customer Order by ID (helpful in search functionality)
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public ArrayList<CustomerOrder> getOrdersById(String id) throws SQLException {
        String customerOrderQry = "SELECT * FROM IOTBAYUSER.CUSTOMERORDER WHERE ORDERID = "
                + Integer.parseInt(id) + "";

        ArrayList<CustomerOrder> customerOrders = new ArrayList<>();

        ResultSet rSet = statement.executeQuery(customerOrderQry);

        while (rSet.next()) {
            // add all data to above array list
            int orderID = rSet.getInt("ORDERID");
            String customerEmail = rSet.getString("CUSTOMEREMAIL");
            int paymentID = rSet.getInt("PAYMENTID");
            int deviceID = rSet.getInt("DEVICEID");
            int quantity = rSet.getInt("QUANTITY");
            Timestamp dateOrdered = rSet.getTimestamp("DATEORDERED");
            double totalPrice = rSet.getDouble("TOTALPRICE");
            Timestamp estArrivalDate = rSet.getTimestamp("ESTARRIVALDATE");
            Timestamp dateTimeDeparture = rSet.getTimestamp("DEPARTUREDATE");
            String supplierEmail = rSet.getString("SUPPLIEREMAIL");
            double shippingCost = rSet.getDouble("SHIPMENTPRICE");
            String shippingType = rSet.getString("SHIPPINGTYPE");
            String status = rSet.getString("STATUS");

            String streetAddress = rSet.getString("STREETADDRESS");
            String unitNumber = rSet.getString("UNITNUMBER");
            String city = rSet.getString("CITY");
            String state = rSet.getString("STATE");
            String postCode = rSet.getString("POSTALCODE");
            String phoneNumber = rSet.getString("PHONENUMBER");

            customerOrders.add(
                    new CustomerOrder(
                            orderID,
                            customerEmail,
                            paymentID,
                            deviceID,
                            quantity,
                            dateOrdered,
                            totalPrice,
                            estArrivalDate,
                            supplierEmail,
                            shippingCost,
                            dateTimeDeparture,
                            shippingType,
                            status,
                            streetAddress,
                            unitNumber,
                            city,
                            state,
                            postCode,
                            phoneNumber
                    )
            );
        }
        return customerOrders;
    }

    //Update device details 
    public void updateCustomerOrder(int orderID, String customerEmail, int paymentID, int deviceID, int quantity, Timestamp dateOrdered, double totalPrice,
            Timestamp estArrivalDate, Timestamp departureDate, String supplierEmail, double shipmentPrice,
            String shipmentType, String status, String streetAddress, String unitNumber, String city,
            String state, String postalCode, String phoneNumber) throws SQLException {
        
        String query = "UPDATE IOTBAYUSER.CUSTOMERORDER SET CUSTOMEREMAIL=? , PAYMENTID=?, DEVICEID=?, QUANTITY=?, DATEORDERED=?, TOTALPRICE=?, ESTARRIVAL=?, DEPARTURE=?, SUPPLIEREMAIL=?, SHIPMENTPRICE=?, SHIPMENTTYPE=?, STATUS=?, STREETADDRESS=?, UNITNUMBER=?, CITY=?, STATE=?, POSTALCODE=?, PHONENUMBER=? WHERE ORDERID=?";
        PreparedStatement pStatement = connection.prepareStatement(query);

            pStatement.setString(1, customerEmail);
            pStatement.setInt(2, paymentID);
            pStatement.setInt(3, deviceID);
            pStatement.setInt(4, quantity);
            pStatement.setTimestamp(5, dateOrdered);
            pStatement.setDouble(6, totalPrice);
            pStatement.setTimestamp(7, estArrivalDate);
            pStatement.setTimestamp(8, departureDate);
            pStatement.setString(9, supplierEmail);
            pStatement.setDouble(10, shipmentPrice);
            pStatement.setString(11, shipmentType);
            pStatement.setString(12, status);
            pStatement.setString(13, streetAddress);
            pStatement.setString(14, unitNumber);
            pStatement.setString(15, city);
            pStatement.setString(16, state);
            pStatement.setString(17, postalCode);
            pStatement.setString(18, phoneNumber);
            pStatement.setInt(19, orderID);
            
            pStatement.executeUpdate();
            pStatement.close();
    }

    /**
     * Delete the order by orderID
     *
     * @param orderID
     * @throws SQLException
     */
    public void deleteOrder(int orderID) throws SQLException {
        statement.executeUpdate("DELETE FROM IOTBAYUSER.CUSTOMERORDER WHERE ORDERID=" + orderID + "");
    }

}