/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
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
public class PaymentDetailsUpdateServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        Validator validator = new Validator();
        
        Customer customer = (Customer) session.getAttribute("customer");
        
        DBPaymentDetailsManager paymentDetailsManager = (DBPaymentDetailsManager) session.getAttribute("paymentDetailManager");
        
        String customerEmail = customer.getEmail();
        
        String methodOfPayment = request.getParameter("methodOfPayment");
        
        String hashedCreditedCardNumber = request.getParameter("hashedCreditedCardNumber");
                
        String cardSecurityCode = request.getParameter("cardSecurityCode");
        
        String cardExpiryDate = request.getParameter("cardExpiryDate");
        
        String isOrder = request.getParameter("isOrder");
        
        validator.clear(session);
        
        if (validator.checkPaymentDetailsEmpty(methodOfPayment,hashedCreditedCardNumber,cardSecurityCode,cardExpiryDate)) {
            
            session.setAttribute("paymentDetailsEmptyErr", "Error: All fields are mandatory!");
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validateMethodOfPayment(methodOfPayment)) {
            
            session.setAttribute("methodFieldErr", "Error: Method of Payment format incorrect");    
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validatehashedCreditedCardNumber(hashedCreditedCardNumber)) {
            
            session.setAttribute("cardNumberFieldErr", "Error: Card Number format incorrect");
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validatecardSecurityCode(cardSecurityCode)) {

            session.setAttribute("cardCodeFieldErr", "Error: Card Security Code format incorrect");
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validatecardExpiryDate(cardExpiryDate)) {   
            
            session.setAttribute("expiryDateFieldErr", "Error: Card Expiry Date format incorrect");
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else {
            try {
                paymentDetailsManager.updatePaymentDetails(customerEmail, methodOfPayment, hashedCreditedCardNumber, cardSecurityCode, cardExpiryDate);
                
                PaymentDetails paymentDetails = paymentDetailsManager.findPaymentDetails(customerEmail);
                
                request.setAttribute("paymentDetails", paymentDetails);
                
                if (isOrder.equals("true")) {
                    request.getRequestDispatcher("orderPayment.jsp").include(request, response);
                } else {
                    request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                session.setAttribute("exceptionErr", "Payment Detail Creation Failed.");
                if (isOrder.equals("true")) {
                    request.getRequestDispatcher("orderPayment.jsp").include(request, response);
                } else {
                    request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
                }
            }
        
        }
    }
}
