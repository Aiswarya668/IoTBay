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
@WebServlet(name = "PaymentDetailsUpdateServlet", urlPatterns = {"/PaymentDetailsUpdateServlet"})
public class PaymentDetailsUpdateServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get Session
        HttpSession session = request.getSession();
        //create validator for later validation
        Validator validator = new Validator();
        //get customer from the session
        Customer customer = (Customer) session.getAttribute("customer");
        //prepare DBmanager for later crud operations
        DBPaymentDetailsManager paymentDetailsManager = (DBPaymentDetailsManager) session.getAttribute("paymentDetailManager");
        //if the customer is anonymous, give them a default anonymous user email
        String customerEmail = (customer != null) ? customer.getEmail() : "anonymous@gmail.com";
        
        //prepare parameters in the request for later payment detail updating
        String methodOfPayment = request.getParameter("methodOfPayment");
        String hashedCreditedCardNumber = request.getParameter("hashedCreditedCardNumber");    
        String cardSecurityCode = request.getParameter("cardSecurityCode");
        String cardExpiryDate = request.getParameter("cardExpiryDate");
        
        //Check if the request is coming from an order or from the customer's management
        String isOrder = request.getParameter("isOrder");
        
        //clear errors in the session via the validator
        validator.clear(session);
        //perform validation checks
        if (validator.checkPaymentDetailsEmpty(methodOfPayment,hashedCreditedCardNumber,cardSecurityCode,cardExpiryDate)) {
            
            session.setAttribute("paymentDetailsEmptyErr", "Error: All fields are mandatory!");
            //redirect to appropriate page
            if (isOrder.equals("true")) {
                PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                request.setAttribute("paymentDetails",paymentDetails);
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                request.setAttribute("paymentDetails",paymentDetails);
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validateMethodOfPayment(methodOfPayment)) {
            
            session.setAttribute("methodFieldErr", "Error: Method of Payment format incorrect. Only text allowed.");
            session.setAttribute("paymentDetailsEmptyErr", "Error: Fields are of the wrong format!");
            //redirect to appropriate page
            if (isOrder.equals("true")) {
                PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                request.setAttribute("paymentDetails",paymentDetails);
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                request.setAttribute("paymentDetails",paymentDetails);
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validatehashedCreditedCardNumber(hashedCreditedCardNumber)) {
            
            session.setAttribute("cardNumberFieldErr", "Error: Card Number format incorrect. Format example: 1234132412341234");
            session.setAttribute("paymentDetailsEmptyErr", "Error: Fields are of the wrong format!");
            //redirect to appropriate page
            if (isOrder.equals("true")) {
                PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                request.setAttribute("paymentDetails",paymentDetails);
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                request.setAttribute("paymentDetails",paymentDetails);
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validatecardSecurityCode(cardSecurityCode)) {

            session.setAttribute("cardCodeFieldErr", "Error: Card Security Code format incorrect. Format example: 123");
            session.setAttribute("paymentDetailsEmptyErr", "Error: Fields are of the wrong format!");
            //redirect to appropriate page
            if (isOrder.equals("true")) {
                PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                request.setAttribute("paymentDetails",paymentDetails);
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                request.setAttribute("paymentDetails",paymentDetails);
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else if (!validator.validatecardExpiryDate(cardExpiryDate)) {   
            
            session.setAttribute("expiryDateFieldErr", "Error: Card Expiry Date format incorrect. Format example: YYYY-MM");
            session.setAttribute("paymentDetailsEmptyErr", "Error: Fields are of the wrong format!");
            //redirect to appropriate page
            if (isOrder.equals("true")) {
                PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                request.setAttribute("paymentDetails",paymentDetails);
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                request.setAttribute("paymentDetails",paymentDetails);
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } else {
            try {
                //if anonymous user, update the payment details object instead of updating the database
                if (customerEmail.equals("anonymous@gmail.com")) {
                    PaymentDetails paymentDetails = new PaymentDetails("",methodOfPayment, hashedCreditedCardNumber, cardSecurityCode, cardExpiryDate);
                    request.setAttribute("paymentDetails",paymentDetails);
                    session.setAttribute("updateSucess", "Your payment details were successfully updated!");
                    request.getRequestDispatcher("orderPayment.jsp").include(request, response);
                    return;
                }
                //if a customer, update payment details by finding them on the database
                paymentDetailsManager.updatePaymentDetails(customerEmail, methodOfPayment, hashedCreditedCardNumber, cardSecurityCode, cardExpiryDate);
                
                PaymentDetails paymentDetails = paymentDetailsManager.findPaymentDetails(customerEmail);
                
                request.setAttribute("paymentDetails", paymentDetails);
                
                session.setAttribute("updateSucess", "Your payment details were successfully updated!");
                //redirect to appropriate page
                if (isOrder.equals("true")) {
                    request.getRequestDispatcher("orderPayment.jsp").include(request, response);
                } else {
                    request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                session.setAttribute("exceptionErr", "Payment Detail Creation Failed.");
                //redirect to appropriate page
                if (isOrder.equals("true")) {
                    PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                    request.setAttribute("paymentDetails",paymentDetails);
                    request.getRequestDispatcher("orderPayment.jsp").include(request, response);
                } else {
                    PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                    request.setAttribute("paymentDetails",paymentDetails);
                    request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
                }
            }
        
        }
    }
}
