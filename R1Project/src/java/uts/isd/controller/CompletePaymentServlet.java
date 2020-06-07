/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Customer;
import uts.isd.model.CustomerOrder;
import uts.isd.model.Device;
import uts.isd.model.PaymentSnapshots;
import uts.isd.model.iotbay.dao.DBDeviceManager;
import uts.isd.model.iotbay.dao.DBOrderManager;
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
        
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        
        DBDeviceManager deviceManager = (DBDeviceManager) session.getAttribute("deviceManager");
        
        Device theDevice = (Device) session.getAttribute("buyDevice");
        
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
                long estDate = new Date().getTime() + 3600000*48;
                long depDate = new Date().getTime() + 3600000*24;
                
                paymentSnapshotsManager.addPaymentSnapshots(methodOfPayment, hashedCreditedCardNumber, cardSecurityCode, cardExpiryDate, payDate, amount);
                int paymentID = paymentSnapshotsManager.findPaymentID(methodOfPayment, hashedCreditedCardNumber, cardSecurityCode, cardExpiryDate, payDate, amount);
                orderManager.addOrder(customer.getEmail(), paymentID, order.getDeviceID(), order.getQuantity(), new Timestamp(new Date().getTime()), order.getTotalPrice(), new Timestamp(estDate) , new Timestamp(depDate), order.getSupplierEmail(), order.getShippingCost(), order.getShippingType(), order.getStatus(), order.getStreetAddress(), order.getUnitNumber(), order.getCity(), order.getState(), order.getPostalCode(), order.getPhoneNumber());
                
                //Also change stock
                double currentStock = theDevice.getStockQuantity();
                theDevice.setStockQuantity((int) (currentStock - amount));

                // update DB of device
                deviceManager.updateDevice(theDevice.getDeviceID(), theDevice.getDeviceName(), theDevice.getType(), theDevice.getCost(),
                        theDevice.getStockQuantity(), theDevice.getDescription());

                session.setAttribute("paymentCompleted", "Your payment has been completed!");
                
                response.sendRedirect("/OrderHistory");
                
            } catch (SQLException ex) {
                session.setAttribute("exceptionErr", "Payment Detail Creation Failed.");
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            }
        
        }
    }
}
