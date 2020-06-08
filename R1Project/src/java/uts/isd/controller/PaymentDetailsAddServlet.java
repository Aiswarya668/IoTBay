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
import uts.isd.model.PaymentDetails;
import uts.isd.model.iotbay.dao.DBPaymentDetailsManager;

/**
 *
 * @author James
 */
@WebServlet(name = "PaymentDetailsAddServlet", urlPatterns = {"/PaymentDetailsAddServlet"})
public class PaymentDetailsAddServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        Validator validator = new Validator();
        
        Customer customer = (Customer) session.getAttribute("customer");
        
        DBPaymentDetailsManager paymentDetailsManager = (DBPaymentDetailsManager) session.getAttribute("paymentDetailManager");
        
        String customerEmail = (customer != null) ? customer.getEmail() : "anonymous@gmail.com";
        
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
            
            session.setAttribute("methodFieldErr", "Error: Method of Payment format incorrect. Only text allowed.");
            session.setAttribute("paymentDetailsEmptyErr", "Error: Fields are of the wrong format!");
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validatehashedCreditedCardNumber(hashedCreditedCardNumber)) {
            
            session.setAttribute("cardNumberFieldErr", "Error: Card Number format incorrect. Format example: 1234132412341234");
            session.setAttribute("paymentDetailsEmptyErr", "Error: Fields are of the wrong format!");
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validatecardSecurityCode(cardSecurityCode)) {

            session.setAttribute("cardCodeFieldErr", "Error: Card Security Code format incorrect. Format example: 123");
            session.setAttribute("paymentDetailsEmptyErr", "Error: Fields are of the wrong format!");
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validatecardExpiryDate(cardExpiryDate)) {   
            
            session.setAttribute("expiryDateFieldErr", "Error: Card Expiry Date format incorrect. Format example: YYYY-MM");
            session.setAttribute("paymentDetailsEmptyErr", "Error: Fields are of the wrong format!");
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else {
            try {
                PaymentDetails paymentDetails = null;
                if (!customerEmail.equals("anonymous@gmail.com")){
                    paymentDetailsManager.addPaymentDetails(customerEmail, methodOfPayment, hashedCreditedCardNumber, cardSecurityCode, cardExpiryDate);
                    paymentDetails = paymentDetailsManager.findPaymentDetails(customerEmail);
                } else {
                    paymentDetails = new PaymentDetails("", methodOfPayment, hashedCreditedCardNumber, cardSecurityCode, cardExpiryDate);
                }
  
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
