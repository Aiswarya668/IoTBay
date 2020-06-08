/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
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
 * @author sanyadua
 */
public class cancelOrder extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        // device manager to get details of the device
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        
        DBDeviceManager deviceManager = (DBDeviceManager) session.getAttribute("deviceManager");

        // get id of the order
        int id = Integer.parseInt(request.getParameter("id"));

        Customer cust = (Customer) session.getAttribute("customer");
        
        try{
            CustomerOrder foundOrder = orderManager.getOrdersById(request.getParameter("id")).get(0);
            
            int deviceID = foundOrder.getDeviceID();
            int amount = foundOrder.getQuantity();
            
            Device theDevice = deviceManager.findDeviceByID(deviceID);
            
            //Also change stock
            double currentStock = theDevice.getStockQuantity();
            theDevice.setStockQuantity((int) (currentStock - amount));

            // update DB of device
            deviceManager.updateDevice(theDevice.getDeviceID(), theDevice.getDeviceName(), theDevice.getType(), theDevice.getCost(),
                    theDevice.getStockQuantity(), theDevice.getDescription());
            
            orderManager.deleteOrder(id);
            
            ArrayList<CustomerOrder> orders = (ArrayList) session.getAttribute("allOrders");
            CustomerOrder found = null;
            for (CustomerOrder o : orders) {
                if (o.getOrderID() == id) {
                    found = o;
                }
            }
            orders.remove(found);
            session.setAttribute("allOrders",orders);
            
        } catch (SQLException ex) {
            Logger.getLogger(cancelOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

        // if deleted get the Device and add a stock for that order
        
        session.setAttribute("CancelSuccess", "Order successfully canceled!");
        
        RequestDispatcher v = request.getRequestDispatcher("/orderHistory.jsp");
        v.forward(request, response);

    }
}
