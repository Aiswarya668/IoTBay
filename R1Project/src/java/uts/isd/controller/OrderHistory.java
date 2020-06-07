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
            HttpSession session = request.getSession();
            // Get user from sessiom
            // User user = (User) session.getAttribute("loggedInUser");
            Customer loggedInCustomer = (Customer) session.getAttribute("customer");
            DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
            session.setAttribute("orderErrors", new ArrayList<>());

            String loggedInEmail = "";
            ArrayList<CustomerOrder> orders = new ArrayList<>();

            if (loggedInCustomer == null) {
                loggedInEmail = "Anonymous";
                if (session.getAttribute("allOrders") == null) {
                    session.setAttribute("allOrders", new ArrayList<CustomerOrder>());
                }
                System.out.println("------------Order Hisory-------------");
                orders = (ArrayList<CustomerOrder>) session.getAttribute("allOrders");
                System.out.println(orders);
            } else {
                loggedInEmail = loggedInCustomer.getEmail();
                orders = orderManager.getOrdersByUserEmail("minkpen4@comcast.net");
            }

            session.setAttribute("allOrders", orders);
            // for later searching
            session.setAttribute("searchErrors", new ArrayList<String>());
            RequestDispatcher view = request.getRequestDispatcher("orderHistory.jsp");
            view.forward(request, response);

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
