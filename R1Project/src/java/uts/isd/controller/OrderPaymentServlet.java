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
       
        HttpSession session = request.getSession();
        
        Customer customer = (Customer) session.getAttribute("customer");
       
        String customerEmail = (customer != null) ? customer.getEmail() : null;
        
        DBPaymentDetailsManager paymentDetailsManager = (DBPaymentDetailsManager) session.getAttribute("paymentDetailManager");
        
        String orderID = request.getParameter("id");
        
        Validator validator = new Validator();
        
        validator.clear(session);
        
        if (orderID != null){
            try{
                DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
                CustomerOrder order = orderManager.getOrdersById(orderID).get(0);
                session.setAttribute("order",order);
                
            } catch(SQLException ex) {
                Logger.getLogger(PaymentHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }           
        
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
