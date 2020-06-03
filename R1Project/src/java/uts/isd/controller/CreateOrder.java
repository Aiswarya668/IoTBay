/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
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
        try {
            // get session
            HttpSession session = request.getSession();
            // device manager to get details of the device
            DBDeviceManager deviceManager = (DBDeviceManager) session.getAttribute("deviceManager");

            // for setting up orders errors
            session.setAttribute("orderErrors", new ArrayList<String>());

            // get id of the device that is to be ordered
            int deviceID = Integer.parseInt(request.getParameter("id"));

            Device theDevice = deviceManager.findDeviceID(deviceID);

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
                }
            } else {
                response.sendRedirect("/listDevices.jsp");
            }

        } catch (SQLException error) {
            System.out.println(error);
            error.getSQLState();
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
            HttpSession session = request.getSession();
            DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");

            DBDeviceManager deviceManager = (DBDeviceManager) session.getAttribute("deviceManager");

            Device theDevice = (Device) session.getAttribute("buyDevice");

            // if there is no device , please redirect it 
            if (theDevice == null) {
                response.sendRedirect("/");
            }

            // for tracking errors
            ArrayList<String> orderErrors = new ArrayList<>();

            // create a actual order
            CustomerOrder order = new CustomerOrder();

            // get all data from order form
            int amount = Integer.parseInt(request.getParameter("amount"));
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

            if (theDevice.getStockQuantity() < 0) {
                orderErrors.add("You can not buy more than the stock available.");
            }

            if (amount == 0) {
                orderErrors.add("Please enter atleast 1 amount.");
            }

            // set errors if any
            session.setAttribute("orderErrors", orderErrors);

            // if contains error
            if (orderErrors.size() > 0) {
                RequestDispatcher view = request.getRequestDispatcher("createOrder.jsp");
                view.forward(request, response);
            } else {

                // set status of the device
                String action
                        = request.getParameter("action");
                String savedStatus = "SAVED";
                String submittedStatus = "SUBMITTED";
                String orderStatus = "";

                if (savedStatus.equalsIgnoreCase(action)) {
                    orderStatus = savedStatus;
                } else if (submittedStatus.equalsIgnoreCase(action)) {
                    orderStatus = submittedStatus;
                }

                // Make data to be saved to Customer order ready
                String orderID = "" + (new Random()).nextInt(999999);
                // get customer from session
                Customer loggedInCustomer = (Customer) request.getAttribute("customer");

                String userEmail = "";

                if (loggedInCustomer == null) {
                    userEmail = "Anynomous User Email";
                } else {
                    userEmail = loggedInCustomer.getEmail();
                }
                // Current Date
                Timestamp dateOrdered = new Timestamp(new Date().getTime());

                // Estimated Arrival Date 
                // TODO:
                Timestamp estArrivalDate = new Timestamp(new Date().getTime());
                Timestamp departureDate = new Timestamp(new Date().getTime());

                // Get supplier Email Address
                // TODO: Just fakeing it as there is no way to link device with supplier
                String supplierEmail = "";
                // Also same for shipment Price
                double shipmentPrice = totalCost + 10;
                // Same for shipment Type
                String shipmentType = "Air";

                // Also change stock
                if (orderStatus.equalsIgnoreCase("SUBMITTED")) {
                    double currentStock = theDevice.getStockQuantity();
                    theDevice.setStockQuantity((int) (currentStock - amount));

                    // update DB of device
                    deviceManager.updateDevice(theDevice.getDeviceID(), theDevice.getDeviceName(), theDevice.getType(), theDevice.getCost(),
                            theDevice.getStockQuantity(), theDevice.getDescription());
                }

                // addOrder(String customerEmail, String dateOrdered, double totalPrice,
                // String estArrivalDate, String departureDate, String supplierEmail, double shipmentPrice,
                // String shipmentType, String status, String streetAddress, String unitNumber, String city,
                // String state, String postalCode,  String phoneNumber
                // Add all these data to DB
                orderManager.addOrder(userEmail, dateOrdered,
                        totalCost, estArrivalDate, departureDate, supplierEmail, shipmentPrice,
                        shipmentType, orderStatus, streetAddress, unitNumber, city,
                        state, postcode, phoneNumber);

                // Redirection to list of orders
                response.sendRedirect("/OrderHistory");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
