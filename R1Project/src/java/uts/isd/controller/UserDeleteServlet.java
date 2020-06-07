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
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "UserDeleteServlet", urlPatterns = {"/UserDeleteServlet"})
public class UserDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {//        processRequest(request, response);

        // 1- retrieve the current session
        HttpSession session = request.getSession();
        // 3- capture the posted email
        String customerEmail = request.getParameter("customerEmail");
        String staffEmail = request.getParameter("staffEmail");
        // 4- capture the posted password
//        String password = request.getParameter("Password");
        // 5- retrieve the manager instance from session
        DBCustomerManager customerManager = (DBCustomerManager) session.getAttribute("customerManager");
        DBStaffManager staffManager = (DBStaffManager) session.getAttribute("staffManager");

        Customer customer = null;
        Staff staff = null;
        try {
            if (customerManager.findCustomer(customerEmail) != null) {
                customerManager.deleteCustomer(customerEmail);
                session.setAttribute("deleteMsg", "Customer deleted (" + customerEmail + ")");
            } else {
                session.setAttribute("userDeleteErr", "Customer does not exist.");
            }
        } catch (SQLException ex) {
            session.setAttribute("userDeleteErr", "Error: " + ex.getMessage());
            Logger.getLogger(UserDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (staffManager.findStaff(staffEmail) != null) {
                staffManager.deleteStaff(staffEmail);
                session.setAttribute("deleteMsg", "Staff deleted (" + staffEmail + ")");
            } else {
                session.setAttribute("userDeleteErr", "Staff does not exist.");
            }
        } catch (SQLException ex) {
            session.setAttribute("userDeleteErr", "Error: " + ex.getMessage());
            Logger.getLogger(UserDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("UserListServlet");
    }

}
