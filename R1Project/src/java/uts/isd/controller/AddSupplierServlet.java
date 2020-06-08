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
        
        //capture the posted active status 
        boolean active = (request.getParameter("active") != null);

        //retrieve the manager instance from session - ConnServlet            
        DBSupplierInformationManager supplierManager = (DBSupplierInformationManager) session.getAttribute("supplierManager");
               
        //set supplier to null to see if supplier exists
        Supplier supplier = null;
        
        //clear validators
        validator.clear(session);
        
        //check if supplier exists
        try {
            supplier = supplierManager.findSupplierPK(supplierEmail);
        } catch (SQLException ex) {
            Logger.getLogger(AddSupplierServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //if supplier is not null then the supplier already exists and a new record with the PK cannot be created
        if (supplier != null) {
            session.setAttribute("exceptionSupplierErr", "Supplier with point of contact already exists");
            request.getRequestDispatcher("addSupplier.jsp").include(request, response);
        }
        
        //validators
       
        //if any fields are empty
        else if(validator.checkSupplierEmpty(contactName,supplierName,supplierEmail,supplierAddress)){
             session.setAttribute("supplierEmptyErr", "Error: All fields are mandatory!");
             request.getRequestDispatcher("addSupplier.jsp").include(request, response);
        }
        
        //if it doesn't pass the contactName validation
        else if (!validator.validateContactName(contactName)) {
            //1- set incorrect contactName error to the session 
            session.setAttribute("contactNameErr", "Error: Contact name format incorrect");
            session.setAttribute("formatErr", "Error: Supplier name format incorrect");
            //2- redirect system admin back to the addSupplier.jsp     
            request.getRequestDispatcher("addSupplier.jsp").include(request, response);
       
        //if it doesn't pass the supplierName validation
        } else if (!validator.validateSupplierName(supplierName)) {
            //1- set incorrect supplierName error to the session 
            session.setAttribute("supplierNameErr", "Error: Company name type format incorrect");
            session.setAttribute("formatErr", "Error: Company name type format incorrect");
            //2- redirect system admin back to the addSupplier.jsp    
            request.getRequestDispatcher("addSupplier.jsp").include(request, response);
        
            //if it doesn't pass the supplierEmail validation
        } else if (!validator.validateSupplierEmail(supplierEmail)) {
            //1- set incorrect supplierEmail error to the session 
            session.setAttribute("supplierEmailErr", "Error: Supplier email format incorrect");
            session.setAttribute("formatErr", "Error: Supplier email format incorrect");
            //2- redirect system admin back to the addSupplier.jsp   
            request.getRequestDispatcher("addSupplier.jsp").include(request, response);
        
            //if it doesn't pass the supplierAddress validation
        } else if (!validator.validateSupplierAddress(supplierAddress)) {
            //1- set incorrect supplierAddress error to the session 
            session.setAttribute("supplierAddressErr", "Error: Supplier address format incorrect");
            session.setAttribute("formatErr", "Error: Supplier address format incorrect");
            //2- redirect system admin back to the addSupplier.jsp    
            request.getRequestDispatcher("addSupplier.jsp").include(request, response);
        }
        
        
        //if it passess all validations then add the supplier
        else {
            try {
                //addSupplier CRUD operation
                supplierManager.addSupplier(contactName, supplierName, supplierEmail, supplierAddress, active);
                //set request attribute
                request.setAttribute("supplier", supplier);
                //set session attribute - success message
                session.setAttribute("creationConfirmation", "Supplier with point of contact creation was successful!");
                //redirect system admin back to the addSupplier.jsp
                request.getRequestDispatcher("addSupplier.jsp").include(request, response);
            } catch (SQLException ex) {
                 //catch any exception and display error message
                session.setAttribute("exceptionSupplierErr", "Submission Failed");
                request.getRequestDispatcher("addSupplier.jsp").include(request, response);
            }

        }
        

    }
    
    

}
