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
               
        
        Supplier supplier;
        
        try {
            supplier = supplierManager.findSupplier(contactName, supplierName);
       
        if (supplier != null) {
            session.setAttribute("supplier", supplier);
             request.getRequestDispatcher("removeSupplier.jsp").include(request, response);
             supplierManager.deleteSupplier(contactName, supplierName);
             
            
        
        } 
        }   catch (SQLException ex) {

                session.setAttribute("exceptionErr", "Supplier doesn't exist");
                 request.getRequestDispatcher("removeSupplier.jsp").include(request, response);
                
                
            }
        }
    }


