/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import uts.isd.model.PaymentDetails;
import uts.isd.model.iotbay.dao.DBOrderManager;
import uts.isd.model.iotbay.dao.DBPaymentDetailsManager;

/**
 *
 * @author James
 */
@WebServlet(name = "OrderPaymentServlet", urlPatterns = {"/OrderPaymentServlet"})
public class OrderPaymentServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //get session
        HttpSession session = request.getSession();
        //get customer from session
        Customer customer = (Customer) session.getAttribute("customer");
        //if there is no customer and it is an anonymous customer, set customer email to none 
        String customerEmail = (customer != null) ? customer.getEmail() : null;
        //prepare dbmanager for CRUD operations later on
        DBPaymentDetailsManager paymentDetailsManager = (DBPaymentDetailsManager) session.getAttribute("paymentDetailManager");
        //get orderID from the request to find order for payment
        String orderID = request.getParameter("id");
        //prepare validator for validation
        Validator validator = new Validator();
        //clear errors on the session via the validator
        validator.clear(session);
        
        //if there is an orderID, retrieve the order from the database to be sent to purchase to finish payment
        //else just continue to use the order from the session prior
        if (orderID != null){
            try{
                DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
                CustomerOrder order = orderManager.getOrdersById(orderID).get(0);
                session.setAttribute("order",order);
                
            } catch(SQLException ex) {
                Logger.getLogger(PaymentHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }           
        //Find payment details for customer for payment
        try {
            PaymentDetails paymentDetails = paymentDetailsManager.findPaymentDetails(customerEmail);
                   
            request.setAttribute("paymentDetails", paymentDetails);     
            request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            
            return;
            
       } catch (SQLException ex) {
           Logger.getLogger(PaymentHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
       }    
   }
}
