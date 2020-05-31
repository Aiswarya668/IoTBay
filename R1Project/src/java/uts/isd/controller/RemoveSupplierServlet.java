/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Supplier;
import uts.isd.model.iotbay.dao.DBSupplierInformationManager;

/**
 *
 * @author Anastasia
 */
public class RemoveSupplierServlet extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1- retrieve the current session
        HttpSession session = request.getSession();
        
        //2- create an instance of the Validator class
        Validator validator = new Validator();
        
        //3- capture the posted parameters/info fields 
        //capture contactName field
        String contactName = request.getParameter("contactName");

        //capture supplierName field
        String supplierName = request.getParameter("supplierName");

        //4 retrieve the manager instance from session - ConnServlet            
        DBSupplierInformationManager supplierManager = (DBSupplierInformationManager) session.getAttribute("supplier");
               
        
        Supplier supplier = null;

        try {
            supplier = supplierManager.findSupplier(contactName, supplierName);
       
       //if (!deviceName.equals(existingDevice.getDeviceName()) && !type.equals(existingDevice.getType())) {
        if (supplier != null) {
             session.setAttribute("exceptionErr", "Device with that name, type and ID does not exsist");
            request.getRequestDispatcher("deleteDevice.jsp").include(request, response);
             
        
        } else {
            // deleting device
            session.setAttribute("supplier", supplier);
             // redirect back to addDevice
             request.getRequestDispatcher("removeSupplier.jsp").include(request, response);
             supplierManager.deleteSupplier(contactName, supplierName);
                //deviceManager.addDevice(deviceName, type, Double.parseDouble(cost), Integer.parseInt(stockQuantity), description);      
                // redirect user to the edit.jsp
                //request.getRequestDispatcher("editDevice.jsp").include(request, response);
                
            }
        }   catch (SQLException ex) {
                // exception message if updating customer fails
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                
                
            }
        //request.getRequestDispatcher("editDevice.jsp").include(request, response);
        }
    }


