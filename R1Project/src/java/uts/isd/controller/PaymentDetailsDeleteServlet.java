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
        //get session
        HttpSession session = request.getSession();
        //create validator for later validation
        Validator validator = new Validator();
        //get customer from the session
        Customer customer = (Customer) session.getAttribute("customer");
        //prepare DBmanager for later CRUD operations
        DBPaymentDetailsManager paymentDetailsManager = (DBPaymentDetailsManager) session.getAttribute("paymentDetailManager");
        //if customer is anonymous, give the anonymous user a default anonymous email
        String customerEmail = (customer != null) ? customer.getEmail() : "anonymous@gmail.com";
        //prepare a paymentDetails object
        PaymentDetails paymentDetail = null;
        //check if the request is coming from an order or from the customer's management
        String isOrder = request.getParameter("isOrder");
        //clear errors on the session by using the validator
        validator.clear(session);
        
        try {
            //if anonymous user, delete payment details from the object session and then send a null object back to them for their order
            if (customerEmail.equals("anonymous@gmail.com")) {
                paymentDetail = null;
                request.setAttribute("paymentDetails", paymentDetail);
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
                return;
            }
            //find the customer's payment details
            paymentDetail = paymentDetailsManager.findPaymentDetails(customerEmail);
            
            //if they have no payment details, send back an error
            if (paymentDetail == null) {
                session.setAttribute("exceptionErr", "Error: Payment details do not exist for the user");
                //send new payment details and redirect to the correct site of origin
                if (isOrder.equals("true")) {
                    request.getRequestDispatcher("orderPayment.jsp").include(request, response);
                } else {
                    request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
                }
            }
            //delete the customer's payment information and send back a null payment details object
            paymentDetailsManager.deletePaymentDetails(customerEmail);
            paymentDetail = null;
            
            request.setAttribute("paymentDetails", paymentDetail);
            //send new payment details and redirect to the correct site of origin
            if (isOrder.equals("true")) {
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            
        } catch (SQLException ex) {
                session.setAttribute("exceptionErr", "Payment Detail Deletion Failed.");
                //send new payment details and redirect to the correct site of origin
                if (isOrder.equals("true")) {
                PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                request.setAttribute("paymentDetails",paymentDetails);
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
            }
            }
    }
}
