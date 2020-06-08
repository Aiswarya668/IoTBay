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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Customer;
import uts.isd.model.CustomerOrder;
import uts.isd.model.PaymentSnapshots;
import uts.isd.model.iotbay.dao.DBOrderManager;
import uts.isd.model.iotbay.dao.DBPaymentSnapshotsManager;

/**
 *
 * @author James
 */
@WebServlet(name = "PaymentHistoryServlet", urlPatterns = {"/PaymentHistoryServlet"})
public class PaymentHistoryServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //get session
        HttpSession session = request.getSession();
        //get customer from the session
        Customer customer = (Customer) session.getAttribute("customer");
        // if anonymous customer, give anonymous customer a default anonymous email
        String customerEmail = (customer != null) ? customer.getEmail() : "anonymous@gmail.com";
        //prepare DBmanager for later CRUD operations
        DBPaymentSnapshotsManager paymentSnapshotsManager = (DBPaymentSnapshotsManager) session.getAttribute("paymentSnapshotsManager");
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        
        try {
            //find payment history via email
            ArrayList<CustomerOrder> orders = orderManager.getOrdersByUserEmail(customerEmail);
            
            //if no results from the dbmanager, redirect back to page with no resultsa
            if (orders.size()==0) {
                session.setAttribute("paymentHistory", null);
                request.setAttribute("order", orders);
                request.getRequestDispatcher("viewPaymentHistory.jsp").include(request, response);
                return;
            }
            
            //if orders found, extract paymentIDs from the orders and put them into an ArrayList
            ArrayList<Integer> payIDArray = new ArrayList<Integer>();
            
            for (int i = 0; i < orders.size(); i++) { 		      
                payIDArray.add(orders.get(i).getPaymentID());
            }    
            
            //Find the relevant Payment Snapshot information using the payID array
            ArrayList<PaymentSnapshots> paymentHistory = paymentSnapshotsManager.findPaymentSnapshotsWithArray(payIDArray);
                   
            //send payments and relevant orders back to payment history jsp page
            request.setAttribute("paymentHistory", paymentHistory);
            request.setAttribute("order", orders);
            
            request.getRequestDispatcher("viewPaymentHistory.jsp").include(request, response);
            
            return;
            
       } catch (SQLException ex) {
           Logger.getLogger(PaymentHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
        
      
   }
}
