/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Customer;
import uts.isd.model.CustomerOrder;
import uts.isd.model.Device;
import uts.isd.model.Supplier;
import uts.isd.model.User;
import uts.isd.model.iotbay.dao.DBDeviceManager;
import uts.isd.model.iotbay.dao.DBOrderManager;

/**
 *
 *
 */
public class CreateOrder extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get session
        HttpSession session = request.getSession();
        // device manager to get details of the device
        DBDeviceManager deviceManager = (DBDeviceManager) session.getAttribute("deviceManager");
        // for setting up orders errors
        session.setAttribute("orderErrors", new ArrayList<String>());
        // get id of the device that is to be ordered
        int deviceID = Integer.parseInt(request.getParameter("id"));
        System.out.println("DEvice iD = " + deviceID);
        Device theDevice = deviceManager.findDeviceByID(deviceID);
        System.out.println("Device =" + theDevice.toString());
        if (theDevice != null) {
            // check if the device is in stock or not
            if (theDevice.getStockQuantity() > 0) {
                // set current device in session
                session.setAttribute("buyDevice", theDevice);
                // Emoty all error if have
                session.setAttribute("orderErrors", new ArrayList<>());

                // returns the view
                RequestDispatcher v = request.getRequestDispatcher("/createOrder.jsp");
                v.forward(request, response);
             //if no stock left then send back tot he catalogue
            } else if (theDevice.getStockQuantity() == 0) {
                response.sendRedirect("ViewDeviceServletUsers");
                session.setAttribute("quantityErr", "Error: Not enough stock!");
            }
        } else {
            response.sendRedirect("/listDevices.jsp");
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //get session
            HttpSession session = request.getSession();
            //get db managers ready for CRUD operations
            DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");

            DBDeviceManager deviceManager = (DBDeviceManager) session.getAttribute("deviceManager");
            //retrieve the device from the deviceID passed in from the request
            Device theDevice = (Device) session.getAttribute("buyDevice");

            // if there is no device , please redirect it 
            if (theDevice == null) {
                response.sendRedirect("/");
            }

            // for tracking errors
            ArrayList<String> orderErrors = new ArrayList<>();

            // get all data from order form
            String amountFromInput = request.getParameter("amount");
            if (amountFromInput.equals("")) {
                amountFromInput = "0";
            }
            int amount = 0;
            // check if amount is integer
            try {
                amount = Integer.parseInt(amountFromInput);
            } catch (NumberFormatException e) {
                amount = -1;
                orderErrors.add("Please enter an integer for amount.");
            }
            // delivery address details
            String unitNumber = request.getParameter("unitnumber");
            String streetAddress = request.getParameter("streetaddress");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String postcode = request.getParameter("postcode");
            String phoneNumber = request.getParameter("phonenumber");
            double costOfDevice = theDevice.getCost();

            // calculate total cost of the device
            double totalCost = costOfDevice * amount;

            // form field validation
            if (unitNumber.equals("") || unitNumber == null) {
                orderErrors.add("Please enter a unit number.");
            }
            if (streetAddress.equals("") || streetAddress == null) {;
                orderErrors.add("Please enter a street address.");
            }
            if (city.equals("") || city == null) {
                orderErrors.add("Please enter a city.");
            }
            if (state.equals("") || state == null) {
                orderErrors.add("Please enter a state.");
            }
            if (postcode.equals("") || postcode == null || postcode.length() != 4) {
                orderErrors.add("Please enter a 4 digit post code.");
            } else {
                try {
                    Integer.parseInt(postcode);
                } catch (NumberFormatException e) {
                    orderErrors.add("Please enter a 4 digit post code.");
                }
            }
            if (phoneNumber.equals("") || phoneNumber == null) {;
                orderErrors.add("Please enter a phone number.");
            }

            // check if enough stock
            if (theDevice.getStockQuantity() < 0) {
                orderErrors.add("You can not buy more than the stock available.");
            }

            if (theDevice.getStockQuantity() < amount) {
                orderErrors.add("You can not buy more than the stock available.");
            }

            if (amount == 0) {
                orderErrors.add("Please enter an amount.");
            }

            // set errors if any
            session.setAttribute("orderErrors", orderErrors);

            if (session.getAttribute("allOrders") == null) {
                session.setAttribute("allOrders", new ArrayList<CustomerOrder>());
            }

            // if contains error
            if (orderErrors.size() > 0) {

                RequestDispatcher view = request.getRequestDispatcher("createOrder.jsp");
                view.forward(request, response);
            } else {

                // set status of the device
                String action = request.getParameter("action");
                System.out.println(action);
                String savedStatus = "SAVED";
                String submittedStatus = "SUBMITED";
                String orderStatus = "";

                if (savedStatus.equalsIgnoreCase(action)) {
                    orderStatus = savedStatus;
                } else if (submittedStatus.equalsIgnoreCase(action)) {
                    orderStatus = submittedStatus;
                }

                // Make data to be saved to Customer order ready
                // String orderID = "" + (new Random()).nextInt(999999);
                // get customer from session
                Customer loggedInCustomer = (Customer) session.getAttribute("customer");

                String userEmail = "";

                // Get supplier Email Address
                // TODO: Just fakeing it as there is no way to link device with supplier
                String supplierEmail = "fkaines28@mozilla.com";
                // Also same for shipment Price
                double shipmentPrice = totalCost + 10;
                // Same for shipment Type
                String shipmentType = "Air";
                Timestamp timeNow = new Timestamp(new Date().getTime());
                
                if (loggedInCustomer == null) {

                    // Make everything Anynomous
                    userEmail = "anonymous@gmail.com";
                    //If order is to be saved, commit it to the database with missing information about payments and dates
                    if (orderStatus.equals("SAVED")) {
                        orderManager.addOrder(userEmail, -1, theDevice.getDeviceID(), amount, timeNow,
                                totalCost, null, null, supplierEmail, shipmentPrice,
                                shipmentType, orderStatus, streetAddress, unitNumber, city,
                                state, postcode, phoneNumber);
                        
                        //find orderID from the above made database commit and pass it onto the orderHistory page
                        int ID = orderManager.findOrderID(userEmail, -1, theDevice.getDeviceID(), amount, timeNow,
                                totalCost, null, null, supplierEmail, shipmentPrice,
                                shipmentType, orderStatus, streetAddress, unitNumber, city,
                                state, postcode, phoneNumber);

                        String orderID = Integer.toString(ID);
                        session.setAttribute("orderID", orderID);
                        response.sendRedirect("/OrderHistory");
                        return;
                    }
                    //anonymous user makes a customer order object to send to order payment page
                    CustomerOrder aOrder = new CustomerOrder(-1, userEmail, -1, theDevice.getDeviceID(), amount, timeNow,
                            totalCost, null, supplierEmail, shipmentPrice, null,
                            shipmentType, orderStatus, streetAddress, unitNumber, city,
                            state, postcode, phoneNumber);

//                    if (session.getAttribute("allOrders") == null) {
//                        session.setAttribute("allOrders", new ArrayList<CustomerOrder>());
//                    }
//                    ArrayList<CustomerOrder> custOrders = (ArrayList) session.getAttribute("allOrders");
//                    custOrders.add(aOrder);
//                    System.out.println(custOrders);
//                    session.setAttribute("allOrders", custOrders);
                    session.setAttribute("order", aOrder);

                    response.sendRedirect("/OrderPaymentServlet");
                    return;

                } else {
                    userEmail = loggedInCustomer.getEmail();

                    // addOrder(String customerEmail, String dateOrdered, double totalPrice,
                    // String estArrivalDate, String departureDate, String supplierEmail, double shipmentPrice,
                    // String shipmentType, String status, String streetAddress, String unitNumber, String city,
                    // String state, String postalCode,  String phoneNumber
                    // Add all these data to DB   
                    
                    ////If order is to be saved, commit it to the database with missing information about payments and dates
                    if (orderStatus.equals("SAVED")) {
                        orderManager.addOrder(userEmail, -1, theDevice.getDeviceID(), amount, timeNow,
                                totalCost, null, null, supplierEmail, shipmentPrice,
                                shipmentType, orderStatus, streetAddress, unitNumber, city,
                                state, postcode, phoneNumber);
                        //no need for orderId because orders can be found via customerEmail
                        
                        response.sendRedirect("/OrderHistory");
                        return;

                    }

                    //Customer makes a customer order object to send to order payment page
                    CustomerOrder aOrder = new CustomerOrder(-1, userEmail, -1, theDevice.getDeviceID(), amount, timeNow,
                            totalCost, null, supplierEmail, shipmentPrice, null,
                            shipmentType, orderStatus, streetAddress, unitNumber, city,
                            state, postcode, phoneNumber);

                    session.setAttribute("order", aOrder);

                    response.sendRedirect("/OrderPaymentServlet");
                    return;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
