/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
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
public class OrderHistorySearch extends HttpServlet {

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
        ArrayList<CustomerOrder> orders = new ArrayList<>();
        //get session
        HttpSession session = request.getSession();
        //prepare DBmanager for CRUD operations later
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        // get id from url
        String id = request.getParameter("searchId");
        
        if(id == null){
            id = "";
        }
        //if no id was inputtd into the filter, redirect back to order history and show an error
        if (id.equals("")) {
            ArrayList<String> searchErrors = new ArrayList<>();
            searchErrors.add("Enter valid search input");

            session.setAttribute("searchErrors", searchErrors);
            
        } else {
           
            try {
                // get Orders by the id of that esp. user
                // if user is logged in search DB
                orders = orderManager.getOrdersById(id);
                Customer loggedInCustomer = (Customer)session.getAttribute("customer");
                
                //if and loops prevent other customers from looking at the orders of other customers by verifying against their customer email 
                if(loggedInCustomer == null){
                    // id not search session
                    ArrayList<CustomerOrder> foundOrders = new ArrayList<CustomerOrder>();
                    for (CustomerOrder o: orders) {
                        if(!o.getCustomerEmail().equals("anonymous@gmail.com")) {
                             foundOrders.add(o);
                        }
                    }    
                    for (CustomerOrder o: foundOrders) {
                        orders.remove(o);
                    }
                    
                }  else {
                    ArrayList<CustomerOrder> foundOrders = new ArrayList<CustomerOrder>();
                    for (CustomerOrder o: orders) {
                        if (!o.getCustomerEmail().equals(loggedInCustomer.getEmail())) {
                            foundOrders.add(o);
                        }
                    }
                    for (CustomerOrder o: foundOrders) {
                        orders.remove(o);
                    }
                }

                //if no orders are found, send an error
                if (orders.size() == 0){
                    session.setAttribute("notFoundError", "No orders could be found.");
                }
                //put all found orders with the filter onto the session to be displayed on orderhistory
                session.setAttribute("allOrders", orders);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        
        RequestDispatcher view = request.getRequestDispatcher("orderHistory.jsp");
        view.forward(request, response);
    }
}
