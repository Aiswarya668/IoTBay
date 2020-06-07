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
       
        //Create object of printwriter - and calling response of getWriter()
        //PrintWriter out =response.getWriter();
        HttpSession session = request.getSession();
        
        Customer customer = (Customer) session.getAttribute("customer");
        
        String customerEmail = customer.getEmail();
        
        DBPaymentSnapshotsManager paymentSnapshotsManager = (DBPaymentSnapshotsManager) session.getAttribute("paymentSnapshotsManager");
        
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("");
        
        try {
            ArrayList<CustomerOrder> orders = orderManager.getOrdersByUserEmail(customerEmail);
            
            if (orders != null) {
                session.setAttribute("paymentHistory", null);
                return;
            }
            
            ArrayList<Integer> payIDArray = new ArrayList<Integer>();
            
            for (int i = 0; i < orders.size(); i++) { 		      
                payIDArray.add(orders.get(i).getPaymentID());
            }    
            
            ArrayList<PaymentSnapshots> paymentHistory = paymentSnapshotsManager.findPaymentSnapshotsWithArray(payIDArray);
                   
            
            request.setAttribute("paymentHistory", paymentHistory);
            request.setAttribute("orders",orders);
            
            request.getRequestDispatcher("viewPaymentHistory.jsp").include(request, response);
            
            return;
            
       } catch (SQLException ex) {
           Logger.getLogger(PaymentHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
        
      
   }
}
