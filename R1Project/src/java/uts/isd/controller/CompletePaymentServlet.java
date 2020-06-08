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
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Customer;
import uts.isd.model.CustomerOrder;
import uts.isd.model.Device;
import uts.isd.model.PaymentDetails;
import uts.isd.model.iotbay.dao.DBDeviceManager;
import uts.isd.model.iotbay.dao.DBOrderManager;
import uts.isd.model.iotbay.dao.DBPaymentSnapshotsManager;

/**
 *
 * @author James
 */
@WebServlet(name = "CompletePaymentServlet", urlPatterns = {"/CompletePaymentServlet"})
public class CompletePaymentServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get session
        HttpSession session = request.getSession();
        //get validator
        Validator validator = new Validator();
        //get customer from the session
        Customer customer = (Customer) session.getAttribute("customer");
        //if no cusstomer in the session, set the email to a default anonymous email
        String customerEmail = (customer != null) ? customer.getEmail() : "anonymous@gmail.com";
        //prepare dbmanagers for CRUD operations later on
        DBPaymentSnapshotsManager paymentSnapshotsManager = (DBPaymentSnapshotsManager) session.getAttribute("paymentSnapshotsManager");
        
        DBOrderManager orderManager = (DBOrderManager) session.getAttribute("orderManager");
        
        DBDeviceManager deviceManager = (DBDeviceManager) session.getAttribute("deviceManager");
        //retrieve the device that is being bought in the session
        Device theDevice = (Device) session.getAttribute("buyDevice");
        //retireve the order from createOrder page that needs to be paid for, from the session
        CustomerOrder order = (CustomerOrder) session.getAttribute("order");
        
        //retrieve parameters for payment details
        String methodOfPayment = request.getParameter("methodOfPayment");
        String hashedCreditedCardNumber = request.getParameter("hashedCreditedCardNumber");   
        String cardSecurityCode = request.getParameter("cardSecurityCode");
        String cardExpiryDate = request.getParameter("cardExpiryDate");
        
        //format date
        Date orderPayDate = order.getDateOrdered();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String payDate = dateFormat.format(orderPayDate); 
        
        double amount = order.getTotalPrice();
        
        int quantity = order.getQuantity();
        
        PaymentDetails details = (PaymentDetails) session.getAttribute("paymentDetail");
        
        //clear errors in the validators
        validator.clear(session);
        //if no payment details could be found, then send the user back to make payment details for the order
        if (details == null) {
            session.setAttribute("paymentDetailsEmptyErr", "Error: No Payment Details Exists!");
            request.getRequestDispatcher("orderPayment.jsp").include(request, response);   
        } else {
            try {
                //calculate time for date ordered, the estimated date of arrival and the estimate date of departure
                Timestamp dateNow = new Timestamp(new Date().getTime());
                long estDate = new Date().getTime() + 3600000*48;
                long depDate = new Date().getTime() + 3600000*24;
                
                //create a payment snapshot so users can view transaction information
                paymentSnapshotsManager.addPaymentSnapshots(methodOfPayment, hashedCreditedCardNumber, cardSecurityCode, cardExpiryDate, payDate, amount);
                int paymentID = paymentSnapshotsManager.findPaymentID(methodOfPayment, hashedCreditedCardNumber, cardSecurityCode, cardExpiryDate, payDate, amount);
                //if order was not saved prior and is a new order
                if (order.getOrderID() == -1) {
                    
                    orderManager.addOrder(customerEmail, paymentID, order.getDeviceID(), order.getQuantity(), dateNow, order.getTotalPrice(), new Timestamp(estDate) , new Timestamp(depDate), order.getSupplierEmail(), order.getShippingCost(), order.getShippingType(), order.getStatus(), order.getStreetAddress(), order.getUnitNumber(), order.getCity(), order.getState(), order.getPostalCode(), order.getPhoneNumber());
                    int ID = orderManager.findOrderID(customerEmail, paymentID, order.getDeviceID(), order.getQuantity(), dateNow, order.getTotalPrice(), new Timestamp(estDate) , new Timestamp(depDate), order.getSupplierEmail(), order.getShippingCost(), order.getShippingType(), order.getStatus(), order.getStreetAddress(), order.getUnitNumber(), order.getCity(), order.getState(), order.getPostalCode(), order.getPhoneNumber());
                    //Also change stock
                    double currentStock = theDevice.getStockQuantity();
                    theDevice.setStockQuantity((int) (currentStock - quantity));

                    // update DB of device
                    deviceManager.updateDevice(theDevice.getDeviceID(), theDevice.getDeviceName(), theDevice.getType(), theDevice.getCost(),
                            theDevice.getStockQuantity(), theDevice.getDescription());
                    //put id on the session so that the customer knows their order id when reaching orderHistory
                    String orderID = Integer.toString(ID);
                    session.setAttribute("orderID", orderID); 
                } else {
                    //else update the order that was saved before and is now being purchased later on
                    orderManager.updateCustomerOrder(order.getOrderID(), customerEmail, paymentID, order.getDeviceID(), order.getQuantity(), dateNow, order.getTotalPrice(), new Timestamp(estDate), new Timestamp(depDate), order.getSupplierEmail(), order.getShippingCost(), order.getShippingType(), "SUBMITED", order.getStreetAddress(), order.getUnitNumber(), order.getCity(), order.getState(), order.getPostalCode(), order.getPhoneNumber());
                    
                    double currentStock = theDevice.getStockQuantity();
                    theDevice.setStockQuantity((int) (currentStock - quantity));

                    // update DB of device
                    deviceManager.updateDevice(theDevice.getDeviceID(), theDevice.getDeviceName(), theDevice.getType(), theDevice.getCost(),
                            theDevice.getStockQuantity(), theDevice.getDescription());
                    //put id on the session so that the customer knows their order id when reaching orderHistory        
                    session.setAttribute("orderID", "" + order.getOrderID()); 
                }
                
                session.setAttribute("paymentCompleted", "Your payment has been completed!");
                response.sendRedirect("/OrderHistory");
                
            } catch (SQLException ex) {
                session.setAttribute("exceptionErr", "Payment Failed.");
                PaymentDetails paymentDetails = (PaymentDetails) session.getAttribute("paymentDetail");
                request.setAttribute("paymentDetails",paymentDetails);
                request.getRequestDispatcher("orderPayment.jsp").include(request, response);
            }
        
        }
    }
}
