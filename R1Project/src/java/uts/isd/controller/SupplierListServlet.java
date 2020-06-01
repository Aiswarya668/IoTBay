/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.*;
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
public class SupplierListServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        
        //1) capture the posted contactName   
        String contactName = request.getParameter("contactName");
        
        //3) capture the posted supplierName 
        String supplierName = request.getParameter("supplierName");
        
        //4) capture the posted supplierEmail 
        String supplierEmail = request.getParameter("supplierEmail");

        //5) capture the posted supplierAddress
        String supplierAddress = request.getParameter("supplierAddress");
        
        //6) capture the posted active status - parse as a boolean as the input is a string in form  
        Boolean active = Boolean.parseBoolean(request.getParameter("active"));
                    
        try {
           //1) retrieve the current session 
            HttpSession session = request.getSession();
        
            //2) create an instance of the Validator class to check inputs
            Validator validator = new Validator();
        
            //9) retrieve the manager instance from session - ConnServlet            
             DBSupplierInformationManager supplierManager = (DBSupplierInformationManager)session.getAttribute("supplierManager");
        
            //validator.clear(session);
            
            
            ArrayList<Supplier> show = supplierManager.fetchSuppliers();
            
            request.setAttribute("show",show);     
            
            request.getRequestDispatcher("supplierList.jsp").include(request, response);
            
            
       } catch (SQLException ex) {
           Logger.getLogger(SupplierListServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
        
      
   }
}
