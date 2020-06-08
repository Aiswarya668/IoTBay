/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Customer;
import uts.isd.model.CustomerOrder;
import uts.isd.model.iotbay.dao.DBOrderManager;

/**
 *
 * @author sanyadua
 */
public class OrderHistory extends HttpServlet {

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
            //get session
            HttpSession session = request.getSession();
            // Get user from sessiom
            // User user = (User) session.getAttribute("loggedInUser");
            Customer loggedInCustomer = (Customer) session.getAttribute("customer");
            //prepare DBmanager for CRUD operations
            DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
            
            //get errors related to the orde
            session.setAttribute("orderErrors", new ArrayList<>());
            
            String loggedInEmail = "";
            ArrayList<CustomerOrder> orders = new ArrayList<>();

            //if customer is anonymous, don't show any orders on order history by default
            if (loggedInCustomer == null) {
                orders = null;
            } else {
                //else if a customer, find all orders related to them via their customer email
                orders = orderManager.getOrdersByUserEmail(loggedInCustomer.getEmail());
            }
            
            //put orders on session to be shown on orderHistory page
            session.setAttribute("allOrders", orders);
            session.setAttribute("searchErrors", new ArrayList<String>());
            RequestDispatcher view = request.getRequestDispatcher("orderHistory.jsp");
            view.forward(request, response);

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
    
}
