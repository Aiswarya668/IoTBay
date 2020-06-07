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
import uts.isd.model.Supplier;
import uts.isd.model.User;
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
    public void addOrder(String customerEmail, Timestamp dateOrdered, double totalPrice,
            Timestamp estArrivalDate, Timestamp departureDate, String supplierEmail, double shipmentPrice,
            String shipmentType, String status, String streetAddress, String unitNumber, String city,
            String state, String postalCode, String phoneNumber) throws SQLException {
        try {
            // create order then
            String query = "insert into customerorder (customeremail, dateordered, totalprice, estarrivaldate, departuredate, supplierEmail, shipmentprice, shippingtype, status, streetaddress, unitnumber, city, state, postalcode, phonenumber) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            System.out.println(query);
            PreparedStatement pStatement = connection.prepareStatement(query);

            pStatement.setString(1, customerEmail);
            pStatement.setTimestamp(2, dateOrdered);
            pStatement.setDouble(3, totalPrice);
            pStatement.setTimestamp(4, estArrivalDate);
            pStatement.setTimestamp(5, departureDate);
            pStatement.setString(6, supplierEmail);
            pStatement.setDouble(7, shipmentPrice);
            pStatement.setString(8, shipmentType);
            pStatement.setString(9, status);
            pStatement.setString(10, streetAddress);
            pStatement.setString(11, unitNumber);
            pStatement.setString(12, city);
            pStatement.setString(13, state);
            pStatement.setString(14, postalCode);
            pStatement.setString(15, phoneNumber);
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
            String orderID = rSet.getString("ORDERID");
            Date dateOrdered = rSet.getDate("DATEORDERED");
            double totalPrice = rSet.getDouble("TOTALPRICE");
            String estArrivalDate = rSet.getString("ESTARRIVALDATE");
            String dateTimeDeparture = rSet.getString("DEPARTUREDATE");
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

            // get user details from email
            String userSql = "";

            // get current logged in user
            User user = new User(
                    "Fake Name",
                    "Fake LastName",
                    "minkpen4@comcast.net",
                    "admin123",
                    "Male",
                    unitNumber,
                    "Morisson Street",
                    city,
                    state,
                    "2222"
            );

            Supplier supplier = new Supplier(supplierEmail, supplierEmail, postCode, "", true);

            customerOrders.add(
                    new CustomerOrder(
                            orderID,
                            user,
                            dateOrdered,
                            totalPrice,
                            estArrivalDate,
                            supplier,
                            shippingCost,
                            dateTimeDeparture,
                            shippingType,
                            status
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
            String orderID = rSet.getString("ORDERID");

            Date dateOrdered = rSet.getDate("DATEORDERED");
            double totalPrice = rSet.getDouble("TOTALPRICE");
            String estArrivalDate = rSet.getString("ESTARRIVALDATE");
            String dateTimeDeparture = rSet.getString("DEPARTUREDATE");
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

            // get user details from email
            String userSql = "";

            // get current logged in user
            User user = new User(
                    "Fake Name",
                    "Fake LastName",
                    "minkpen4@comcast.net",
                    "admin123",
                    "Male",
                    unitNumber,
                    "Morisson Street",
                    city,
                    state,
                    "2222"
            );
            // String supplierEmail, String supplierName, String contactName, String supplierAddress, Boolean active
            Supplier supplier = new Supplier(supplierEmail, supplierEmail, postCode, streetAddress, true);

            customerOrders.add(
                    new CustomerOrder(
                            orderID,
                            user,
                            dateOrdered,
                            totalPrice,
                            estArrivalDate,
                            supplier,
                            shippingCost,
                            dateTimeDeparture,
                            shippingType,
                            status
                    )
            );
        }
        return customerOrders;
    }

    //Update device details 
    public void updateCustomerOrder(String deviceName, String type, double cost, int stockQuantity, String description) throws SQLException {
        statement.executeUpdate("UPDATE IOTBAYUSER.CUSTOMERORDER SET ='" + deviceName + "', TYPE='" + type + "', COST=" + cost + ", STOCKQUANTITY=" + stockQuantity + ", DESCRIPTION='" + description + "' WHERE DEVICENAME='" + deviceName + "'");
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
