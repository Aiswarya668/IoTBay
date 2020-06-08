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
import uts.isd.model.PaymentDetails;
import uts.isd.model.iotbay.dao.DBPaymentDetailsManager;

/**
 *
 * @author James
 */
@WebServlet(name = "PaymentDetailsServlet", urlPatterns = {"/PaymentDetailsServlet"})
public class PaymentDetailsServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //get session
        HttpSession session = request.getSession();
        //get customer from the session
        Customer customer = (Customer) session.getAttribute("customer");
        //get the customer's email
        String customerEmail = customer.getEmail();
        //prepare DBmanager for later CRUD operations
        DBPaymentDetailsManager paymentDetailsManager = (DBPaymentDetailsManager) session.getAttribute("paymentDetailManager");
        
        try {
            //find customer's payment details
            PaymentDetails paymentDetails = paymentDetailsManager.findPaymentDetails(customerEmail);
            
            //give the found payment details back to the JSP page to be shown       
            request.setAttribute("paymentDetails", paymentDetails);     
            request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            
            return;
            
       } catch (SQLException ex) {
           Logger.getLogger(PaymentHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
       }    
   }
}
