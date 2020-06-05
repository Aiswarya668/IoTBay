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
import uts.isd.model.Customer;
import uts.isd.model.Staff;
import uts.isd.model.iotbay.dao.DBCustomerManager;
import uts.isd.model.iotbay.dao.DBStaffManager;

/**
 *
 * @author seant
 */
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //retrieve the current session 
            HttpSession session = request.getSession();

            //retrieve the manager instance from session - ConnServlet            
            DBCustomerManager customerManager = (DBCustomerManager) session.getAttribute("customerManager");
            DBStaffManager staffManager = (DBStaffManager) session.getAttribute("staffManager");

            List<Customer> customers = customerManager.fetchCustomers();
            List<Staff> staffs = staffManager.fetchStaffs();

            request.setAttribute("customers", customers);
            request.setAttribute("staffs", staffs);

            request.getRequestDispatcher("userManage.jsp").include(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(UserListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
