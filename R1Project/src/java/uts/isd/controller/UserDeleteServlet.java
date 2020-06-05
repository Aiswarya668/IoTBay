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
                System.out.println("Customer " + customerEmail + " was deleted from the database.");
            } else {
                System.out.println("Customer does not exist.");
            }
        } catch (SQLException ex) {
            session.setAttribute("userDeleteErr", ex);
            Logger.getLogger(UserDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (staffManager.findStaff(staffEmail) != null) {
                staffManager.deleteStaff(staffEmail);
                System.out.println("Staff" + staffEmail + " was deleted from the database.");
            } else {
                System.out.println("Staff does not exist.");
            }
        } catch (SQLException ex) {
            session.setAttribute("userDeleteErr", ex);
            Logger.getLogger(UserDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("UserListServlet");
//        try {
//            customerManager.deleteCustomer(customerEmail);
////            if (!customer.isActive()) {
////                // set user is not active error to the session
////                session.setAttribute("loginErr", "That account is no longer active");
////                // redirect user back to the login.jsp
////                return;
//            request.getRequestDispatcher("userManage.jsp").include(request, response);
////            }
//        } catch (SQLException ex) {
//            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        if (!validator.validateEmail(email)) {
//            // set incorrect email error to the session
//            session.setAttribute("emailErr", "Error: Email format incorrect");
//            // redirect user back to the login.jsp
//            request.getRequestDispatcher("login.jsp").include(request, response);
//        } else if (!validator.validatePassword(password)) {
//            // set incorrect password error to the session
//            session.setAttribute("passErr", "Error: Password format incorrect: min. 4 characters");
//            // redirect user back to the login.jsp
//            request.getRequestDispatcher("login.jsp").include(request, response);
//        } else if (customer != null) {
//            if (customer.getPassword().equals(password)) {
//                // save the logged in user object to the session
//                session.setAttribute("customer", customer);
//                // redirect user to the main.jsp
//                request.getRequestDispatcher("welcome.jsp").include(request, response);
//            } else {
//                // set incorrect password error to the session
//                session.setAttribute("passErr", "Error: Incorrect password");
//                // redirect user back to the login.jsp
//                request.getRequestDispatcher("login.jsp").include(request, response);
//            }
//        } else {
//            // set user does not exist error to the session
//            session.setAttribute("loginErr", "Customer does not exist in the database");
//            // redirect user back to the login.jsp
//            request.getRequestDispatcher("login.jsp").include(request, response);
//        }
    }

}
