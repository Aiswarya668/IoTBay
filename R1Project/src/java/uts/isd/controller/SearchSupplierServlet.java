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
public class SearchSupplierServlet extends HttpServlet {

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        
        Validator validator = new Validator();
        
        String contactName = request.getParameter("contactName");

        String supplierName = request.getParameter("supplierName");
        
        DBSupplierInformationManager supplierManager = (DBSupplierInformationManager) session.getAttribute("supplierManager");
               
        Supplier supplier = null;
                    
        try {
            supplier = supplierManager.findSupplier(contactName, supplierName);
            

        request.setAttribute("supplier", supplier);
        request.getRequestDispatcher("searchResultSupplier.jsp").include(request, response);
        
            
            
       } catch (SQLException ex) {
           Logger.getLogger(SearchSupplierServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        
        
   }
}
    

