/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.CustomerOrder;
import uts.isd.model.Device;
import uts.isd.model.iotbay.dao.DBDeviceManager;
import uts.isd.model.iotbay.dao.DBOrderManager;

/**
 *
 * @author sanyadua
 */
public class updateOrder extends HttpServlet {

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
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");

        // for setting up orders errors
        session.setAttribute("orderErrors", new ArrayList<String>());

        CustomerOrder foundOrder = orderManager.getOrdersById(request.getParameter("id")).get(0);
        
        request.setAttribute("orderIdTobeUpdated", request.getParameter("id"));
        request.setAttribute("updateOrder", foundOrder);
        
        RequestDispatcher v = request.getRequestDispatcher("/updateOrder.jsp");
        v.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(cancelOrder.class.getName()).log(Level.SEVERE, null, ex);
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

            CustomerOrder foundOrder = orderManager.getOrdersById(request.getParameter("id")).get(0);
            int deviceID = foundOrder.getDeviceID();
            
            Device theDevice = deviceManager.findDeviceByID(deviceID);
            double deviceCost = theDevice.getCost();

            // for tracking errors
            ArrayList<String> orderErrors = new ArrayList<>();

            // get all data from order form
            String amountFromInput = request.getParameter("amount");
            if (amountFromInput.equals("")) {
                amountFromInput = "0";
            }
            int amount = Integer.parseInt(amountFromInput);
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

            if (theDevice.getStockQuantity() < amount) {
                orderErrors.add("You can not buy more than the stock available.");
            }

            if (amount == 0) {
                orderErrors.add("Please enter atleast 1 amount.");
            }

            // set errors if any
            session.setAttribute("orderErrors", orderErrors);

            if (session.getAttribute("allOrders") == null) {
                session.setAttribute("allOrders", new ArrayList<CustomerOrder>());
            }

            // if contains error
            if (orderErrors.size() > 0) {

                RequestDispatcher view = request.getRequestDispatcher("updateOrder.jsp");
                view.forward(request, response);
            } else {

                // set status of the device
                String orderStatus = "SAVED";  
                // deleteOrderFrist(orderActualID, session); 
                // Make data to be saved to Customer order ready
                // String orderID = "" + (new Random()).nextInt(999999);
                // get customer from session
                
                orderManager.updateCustomerOrder(Integer.parseInt(request.getParameter("id")),foundOrder.getCustomerEmail(), -1, foundOrder.getDeviceID(), amount, foundOrder.getDateOrdered(),
                            deviceCost*amount, null, null, foundOrder.getSupplierEmail(), (deviceCost*amount)+10,
                            foundOrder.getShippingType(), orderStatus, streetAddress, unitNumber, city,
                            state, postcode, phoneNumber);
                
                
                // Redirection to list of orders
                
                session.setAttribute("updateSucess", "Order details successfully updated!");
                RequestDispatcher v = request.getRequestDispatcher("/orderHistory.jsp");
                v.forward(request, response);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    
    
//    public void deleteOrderFrist(int orderID, HttpSession session){
//        if(session.getAttribute("customer") == null){
//            // if the cutsomer is not logged in
//            ArrayList<CustomerOrder> allOrdersFromSession = (ArrayList) session.getAttribute("allOrders");
//            
//            for(CustomerOrder o: allOrdersFromSession){
//                if(Integer.parseInt(o.getOrderID()) == orderID){
//                   allOrdersFromSession.remove(o);
//                   break;
//                }
//            }
//            
//            session.setAttribute("allOrders", allOrdersFromSession);
//            
//        } else {
//            // if the customer is logged in
//            DBOrderManager manager = (DBOrderManager) session.getAttribute("orderManager");
//            try {
//                manager.deleteOrder(orderID);
//            } catch(SQLException e){
//                System.out.println(e);
//            }
//            
//            
//        }
//        
//    }
}
