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

        //retrieve the current session
        HttpSession session = request.getSession();
        
        //create an instance of the Validator class
        Validator validator = new Validator();
        
        //capture the posted contactName   
        String contactName = request.getParameter("contactName");

        //capture the posted supplierName 
        String supplierName = request.getParameter("supplierName");
         
        //retrieve the manager instance from session - ConnServlet
        DBSupplierInformationManager supplierManager = (DBSupplierInformationManager) session.getAttribute("supplierManager");
               
        //set supplier to null to see if supplier exists
        Supplier supplier = null;
        
        //clear validators
        validator.clear(session);
        
        //check if supplier exists
         try {
            supplier = supplierManager.findSupplier(contactName, supplierName);
         
            //validators
            //if any fields are empty
         if(validator.checkSearchEmpty(contactName,supplierName)){
             session.setAttribute("supplierEmptyErr", "Error: All fields are mandatory!");
             request.getRequestDispatcher("removeSupplier.jsp").include(request, response);
        }
        
         //if it doesn't pass the contactName validation
        else if (!validator.validateContactName(contactName)) {
            //1- set incorrect contactName error to the session 
            session.setAttribute("contactNameErr", "Error: Contact name format incorrect");
            session.setAttribute("formatErr", "Error: Supplier name format incorrect");
            //2- redirect system admin back to the removeSupplier.jsp     
            request.getRequestDispatcher("removeSupplier.jsp").include(request, response);
       
            //if it doesn't pass the supplierName validation
        } else if (!validator.validateSupplierName(supplierName)) {
            //1- set incorrect supplierName error to the session 
            session.setAttribute("supplierNameErr", "Error: Company name type format incorrect");
            session.setAttribute("formatErr", "Error: Company name type format incorrect");
            //2- redirect system admin back to the removeSupplier.jsp    
            request.getRequestDispatcher("removeSupplier.jsp").include(request, response);
        
     
          //if supplier is null then the supplier doesn't exists and a therefore cannot be removed
        } else if (supplier == null) {
            //1- set error message to the session 
             session.setAttribute("exceptionSupplierErr", "Supplier with point of contact does not exist");
             //2- redirect system admin back to the removeSupplier.jsp  
            request.getRequestDispatcher("removeSupplier.jsp").include(request, response);    
        }
        
        //if it passess all validations and conditions then delete the supplier
        else {
            //deleteSupplier CRUD operation
            supplierManager.deleteSupplier(contactName, supplierName);
             //set session attribute - success message
            session.setAttribute("creationConfirmation", "Supplier with point of contact has been successfully deleted!");
            //redirect system admin back to the removeSupplier.jsp
            request.getRequestDispatcher("removeSupplier.jsp").include(request, response);
            
                
            }
        }   catch (SQLException ex) {
                // exception message if updating customer fails
                Logger.getLogger(RemoveSupplierServlet.class.getName()).log(Level.SEVERE, null, ex);
                
                
            }
        }
    }
