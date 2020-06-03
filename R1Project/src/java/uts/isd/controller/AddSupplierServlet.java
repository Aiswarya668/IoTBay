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
public class AddSupplierServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //retrieve the current session
        HttpSession session = request.getSession();
        
        //create an instance of the Validator class
        Validator validator = new Validator();

       //capture the posted contactName   
        String contactName = request.getParameter("contactName");
        
        //capture the posted supplierName 
        String supplierName = request.getParameter("supplierName");
        
        //capture the posted supplierEmail 
        String supplierEmail = request.getParameter("supplierEmail");

        //capture the posted supplierAddress
        String supplierAddress = request.getParameter("supplierAddress");
        
        //capture the posted active status - parse as a boolean as the input is a string in form  
        Boolean active = Boolean.parseBoolean(request.getParameter("active"));

        //4) retrieve the manager instance from session - ConnServlet            
        DBSupplierInformationManager supplierManager = (DBSupplierInformationManager) session.getAttribute("supplierManager");
               
        
        Supplier supplier = null;
        validator.clear(session);

        try {
            supplier = supplierManager.findSupplier(contactName, supplierName);
        } catch (SQLException ex) {
            Logger.getLogger(AddSupplierServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (supplier != null) {

            session.setAttribute("exceptionErr", "Supplier with point of contact already exists");
            request.getRequestDispatcher("addSupplier.jsp").include(request, response);
        } 
        
        
        else {
            try {
                supplierManager.addSupplier(contactName, supplierName, supplierEmail, supplierAddress, active);
                request.setAttribute("supplier", supplier);
                request.getRequestDispatcher("addedSupplier.jsp").include(request, response);
            } catch (SQLException ex) {
                session.setAttribute("exceptionErr", "Registration failed");
                request.getRequestDispatcher("addSupplier.jsp").include(request, response);
            }

        }
        

    }

}
