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

        HttpSession session = request.getSession();
        
        Validator validator = new Validator();
        
        String contactName = request.getParameter("contactName");

        String supplierName = request.getParameter("supplierName");
         
        DBSupplierInformationManager supplierManager = (DBSupplierInformationManager) session.getAttribute("supplierManager");
               
        
        Supplier supplier = null;
        validator.clear(session);
        
         try {

            supplier = supplierManager.findSupplier(contactName, supplierName);
       
         if(validator.checkSearchEmpty(contactName,supplierName)){
             session.setAttribute("supplierEmptyErr", "Error: All fields are mandatory!");
             request.getRequestDispatcher("removeSupplier.jsp").include(request, response);
        }
        
        else if (!validator.validateContactName(contactName)) {
            //1- set incorrect contactName error to the session 
            session.setAttribute("contactNameErr", "Error: Contact name format incorrect");
            session.setAttribute("formatErr", "Error: Supplier name format incorrect");
            //2- redirect system admin back to the addSupplier.jsp     
            request.getRequestDispatcher("removeSupplier.jsp").include(request, response);
       
        } else if (!validator.validateSupplierName(supplierName)) {
            //1- set incorrect supplierName error to the session 
            session.setAttribute("supplierNameErr", "Error: Company name type format incorrect");
            session.setAttribute("formatErr", "Error: Company name type format incorrect");
            //2- redirect system admin back to the addSupplier.jsp    
            request.getRequestDispatcher("removeSupplier.jsp").include(request, response);
        
     
                
        } else if (supplier == null) {
             session.setAttribute("exceptionErr", "Supplier with point of contact does not exist");
            request.getRequestDispatcher("removeSupplier.jsp").include(request, response);    
        }
        
        else {

            request.getRequestDispatcher("removeSupplier.jsp").include(request, response);
            session.setAttribute("creationConfirmation", "Supplier with point of contact has been successfully deleted");
            supplierManager.deleteSupplier(contactName, supplierName);
            
                
            }
        }   catch (SQLException ex) {

                Logger.getLogger(RemoveSupplierServlet.class.getName()).log(Level.SEVERE, null, ex);
                
                
            }
        }
    }
