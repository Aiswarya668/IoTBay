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

        // get id of the order
        String id = request.getParameter("id");

        Customer cust = (Customer) session.getAttribute("customer");

        if (cust == null) {
            // get all orders from session
            ArrayList<CustomerOrder> allOrders = (ArrayList) session.getAttribute("allOrders");
            
            
            if (allOrders.size() > 0) {
                for (CustomerOrder c : allOrders) {
                    if (c.getOrderID().equals(id)) {
                      
                        allOrders.remove(c);
                        
                        break;
                    }
                }

            }
            session.setAttribute("allOrders", allOrders);
        } else {
            try {
                orderManager.deleteOrder(Integer.parseInt(id));
            } catch (SQLException ex) {
                Logger.getLogger(cancelOrder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        // if deleted get the Device and add a stock fro that order
        
        
        
        
        
        
        RequestDispatcher v = request.getRequestDispatcher("/orderHistory.jsp");
        v.forward(request, response);

    }
}
