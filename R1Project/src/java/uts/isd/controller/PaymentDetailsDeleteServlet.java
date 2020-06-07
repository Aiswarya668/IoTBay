/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "PaymentDetailsDeleteServlet", urlPatterns = {"/PaymentDetailsDeleteServlet"})
public class PaymentDetailsDeleteServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        Validator validator = new Validator();
        
        Customer customer = (Customer) session.getAttribute("customer");
        
        DBPaymentDetailsManager paymentDetailsManager = (DBPaymentDetailsManager) session.getAttribute("paymentDetailManager");
        
        String customerEmail = customer.getEmail();
        
        PaymentDetails paymentDetail = null;
        
        String isOrder = request.getParameter("isOrder");

        validator.clear(session);
        try {
            paymentDetail = paymentDetailsManager.findPaymentDetails(customerEmail);
            
            if (paymentDetail == null) {
                session.setAttribute("exceptionErr", "Error: Payment details do not exist for the user");
                if (isOrder.equals("true")) {
                    request.getRequestDispatcher("orderPayment.jsp").include(request, response);
                } else {
                    request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
                }
            }
            
            paymentDetailsManager.deletePaymentDetails(customerEmail);
            paymentDetail = null;
            
            request.setAttribute("paymentDetails", paymentDetail);
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } catch (SQLException ex) {
                session.setAttribute("exceptionErr", "Payment Detail Deletion Failed.");
                if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            }
    }
}
