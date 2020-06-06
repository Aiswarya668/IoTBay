/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Customer;
import uts.isd.model.CustomerOrder;
import uts.isd.model.PaymentSnapshots;
import uts.isd.model.iotbay.dao.DBPaymentSnapshotsManager;

/**
 *
 * @author James
 */
public class CompletePaymentServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        Validator validator = new Validator();
        
        Customer customer = (Customer) session.getAttribute("customer");
        
        DBPaymentSnapshotsManager paymentSnapshotsManager = (DBPaymentSnapshotsManager) session.getAttribute("paymentSnapshotsManager");
        
        CustomerOrder order = (CustomerOrder) session.getAttribute("order");
        
        String methodOfPayment = request.getParameter("methodOfPayment");
        
        String hashedCreditedCardNumber = request.getParameter("hashedCreditedCardNumber");
                
        String cardSecurityCode = request.getParameter("cardSecurityCode");
        
        String cardExpiryDate = request.getParameter("cardExpiryDate");
        
        Date orderPayDate = order.getDateOrdered();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String payDate = dateFormat.format(orderPayDate); 
        
        double amount = order.getTotalPrice();
        
        validator.clear(session);
        
        if (validator.checkPaymentDetailsEmpty(methodOfPayment,hashedCreditedCardNumber,cardSecurityCode,cardExpiryDate)) {
            
            session.setAttribute("paymentDetailsEmptyErr", "Error: No Payment Details Exists!");
            request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            
        } else if (!validator.validateMethodOfPayment(methodOfPayment)) {
            
            session.setAttribute("methodFieldErr", "Error: Method of Payment format incorrect");    
            request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            
        } else if (!validator.validatehashedCreditedCardNumber(hashedCreditedCardNumber)) {
            
            session.setAttribute("cardNumberFieldErr", "Error: Card Number format incorrect");
            request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            
        } else if (!validator.validatecardSecurityCode(cardSecurityCode)) {

            session.setAttribute("cardCodeFieldErr", "Error: Card Security Code format incorrect");
            request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            
        } else if (!validator.validatecardExpiryDate(cardExpiryDate)) {   
            
            session.setAttribute("expiryDateFieldErr", "Error: Card Expiry Date format incorrect");
            request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            
        } else {
            try {
                paymentSnapshotsManager.addPaymentSnapshots(methodOfPayment, hashedCreditedCardNumber, cardSecurityCode, cardExpiryDate, payDate, amount);
                int paymentID = paymentSnapshotsManager.findPaymentID(methodOfPayment, hashedCreditedCardNumber, cardSecurityCode, cardExpiryDate, payDate, amount);
                request.setAttribute("paymentID",paymentID);
                request.getRequestDispatcher("CompletePayment.jsp").include(request, response);

            } catch (SQLException ex) {
                session.setAttribute("exceptionErr", "Payment Detail Creation Failed.");
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            }
        
        }
    }
}
