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
        // active = Boolean.parseBoolean(request.getParameter("active"));
        //boolean active = (request.getParameter("active").equals("on") || request.getParameter("active").equals("null"));
        boolean active = (request.getParameter("active") != null);

        //4) retrieve the manager instance from session - ConnServlet            
        DBSupplierInformationManager supplierManager = (DBSupplierInformationManager) session.getAttribute("supplierManager");
               
        
        Supplier supplier = null;
        validator.clear(session);
        
        try {
            supplier = supplierManager.findSupplierPK(supplierEmail);
        } catch (SQLException ex) {
            Logger.getLogger(AddSupplierServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (supplier != null) {

            session.setAttribute("exceptionSupplierErr", "Supplier with point of contact already exists");
            request.getRequestDispatcher("addSupplier.jsp").include(request, response);
        }
        
        //validators
       
        //if any fields are empty?
        else if(validator.checkSupplierEmpty(contactName,supplierName,supplierEmail,supplierAddress)){
             session.setAttribute("supplierEmptyErr", "Error: All fields are mandatory!");
             request.getRequestDispatcher("addSupplier.jsp").include(request, response);
        }
        
        else if (!validator.validateContactName(contactName)) {
            //1- set incorrect contactName error to the session 
            session.setAttribute("contactNameErr", "Error: Contact name format incorrect");
            session.setAttribute("formatErr", "Error: Supplier name format incorrect");
            //2- redirect system admin back to the addSupplier.jsp     
            request.getRequestDispatcher("addSupplier.jsp").include(request, response);
       
        } else if (!validator.validateSupplierName(supplierName)) {
            //1- set incorrect supplierName error to the session 
            session.setAttribute("supplierNameErr", "Error: Company name type format incorrect");
            session.setAttribute("formatErr", "Error: Company name type format incorrect");
            //2- redirect system admin back to the addSupplier.jsp    
            request.getRequestDispatcher("addSupplier.jsp").include(request, response);
        
        } else if (!validator.validateSupplierEmail(supplierEmail)) {
            //1- set incorrect type error to the session 
            session.setAttribute("supplierEmailErr", "Error: Supplier email format incorrect");
            session.setAttribute("formatErr", "Error: Supplier email format incorrect");
            //2- redirect system admin back to the addSupplier.jsp   
            request.getRequestDispatcher("addSupplier.jsp").include(request, response);
        
        } else if (!validator.validateSupplierAddress(supplierAddress)) {
            //1- set incorrect type error to the session 
            session.setAttribute("supplierAddressErr", "Error: Supplier address format incorrect");
            session.setAttribute("formatErr", "Error: Supplier address format incorrect");
            //2- redirect system admin back to the addSupplier.jsp    
            request.getRequestDispatcher("addSupplier.jsp").include(request, response);
        }
        
        
        
        else {
            try {
                supplierManager.addSupplier(contactName, supplierName, supplierEmail, supplierAddress, active);
                request.setAttribute("supplier", supplier);
                session.setAttribute("creationConfirmation", "Supplier creation was successful");
                request.getRequestDispatcher("addSupplier.jsp").include(request, response);
            } catch (SQLException ex) {
                session.setAttribute("exception2Err", "Submission Failed");
                request.getRequestDispatcher("addSupplier.jsp").include(request, response);
            }

        }
        

    }
    
    

}
