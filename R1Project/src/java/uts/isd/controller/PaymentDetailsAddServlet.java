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
        //get session
        HttpSession session = request.getSession();
        //create validator
        Validator validator = new Validator();
        //get customer from the session
        Customer customer = (Customer) session.getAttribute("customer");
        //prepare DBmanager for CRUD operations
        DBPaymentDetailsManager paymentDetailsManager = (DBPaymentDetailsManager) session.getAttribute("paymentDetailManager");
        //if no customer in the session, set anonymous customer emailt to default customer email
        String customerEmail = (customer != null) ? customer.getEmail() : "anonymous@gmail.com";
        
        //get information from the parameter in preperation to make new payment details
        String methodOfPayment = request.getParameter("methodOfPayment");
        String hashedCreditedCardNumber = request.getParameter("hashedCreditedCardNumber");     
        String cardSecurityCode = request.getParameter("cardSecurityCode");
        String cardExpiryDate = request.getParameter("cardExpiryDate");
        
        //check if the request to change payment details is from an order or from the customer's management
        String isOrder = request.getParameter("isOrder");
        //clear errors via the validator
        validator.clear(session);
        
        //vaalidate the retrieved parameters 
        if (validator.checkPaymentDetailsEmpty(methodOfPayment,hashedCreditedCardNumber,cardSecurityCode,cardExpiryDate)) {
            
            session.setAttribute("paymentDetailsEmptyErr", "Error: All fields are mandatory!");
            //send new payment details and redirect to the correct site of origin
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validateMethodOfPayment(methodOfPayment)) {
            
            session.setAttribute("methodFieldErr", "Error: Method of Payment format incorrect. Only text allowed.");
            session.setAttribute("paymentDetailsEmptyErr", "Error: Fields are of the wrong format!");
            //send new payment details and redirect to the correct site of origin
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validatehashedCreditedCardNumber(hashedCreditedCardNumber)) {
            
            session.setAttribute("cardNumberFieldErr", "Error: Card Number format incorrect. Format example: 1234132412341234");
            session.setAttribute("paymentDetailsEmptyErr", "Error: Fields are of the wrong format!");
            //send new payment details and redirect to the correct site of origin
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validatecardSecurityCode(cardSecurityCode)) {

            session.setAttribute("cardCodeFieldErr", "Error: Card Security Code format incorrect. Format example: 123");
            session.setAttribute("paymentDetailsEmptyErr", "Error: Fields are of the wrong format!");
            //send new payment details and redirect to the correct site of origin
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validatecardExpiryDate(cardExpiryDate)) {   
            
            session.setAttribute("expiryDateFieldErr", "Error: Card Expiry Date format incorrect. Format example: YYYY-MM");
            session.setAttribute("paymentDetailsEmptyErr", "Error: Fields are of the wrong format!");
            //send new payment details and redirect to the correct site of origin
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else {
            try {
                
                PaymentDetails paymentDetails = null;
                //if a registered customer, make payment details associated to that customer and retrieve it
                if (!customerEmail.equals("anonymous@gmail.com")){
                    paymentDetailsManager.addPaymentDetails(customerEmail, methodOfPayment, hashedCreditedCardNumber, cardSecurityCode, cardExpiryDate);
                    paymentDetails = paymentDetailsManager.findPaymentDetails(customerEmail);
                } else {
                    //make a payment details object for anonymous users and prepare to send it back to site of origin
                    paymentDetails = new PaymentDetails("", methodOfPayment, hashedCreditedCardNumber, cardSecurityCode, cardExpiryDate);
                }
                
                //put the payment details on the request for site of origin 
                request.setAttribute("paymentDetails", paymentDetails);
                
                //send new payment details and redirect to the correct site of origin
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
