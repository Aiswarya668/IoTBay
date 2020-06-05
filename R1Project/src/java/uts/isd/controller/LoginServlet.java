/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.iotbay.dao.*;
import uts.isd.model.*;

/**
 *
 * @author Kevin
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1- retrieve the current session
        HttpSession session = request.getSession();
        // 2- create an instance of the Validator class
        Validator validator = new Validator();
        // 3- capture the posted email
        String email = request.getParameter("Email");
        // 4- capture the posted password
        String password = request.getParameter("Password");
        // 5- retrieve the manager instance from session
        DBCustomerManager customerManager = 
                (DBCustomerManager) session.getAttribute("customerManager");
        DBApplicationLogsManager logsManager = 
                (DBApplicationLogsManager) session.getAttribute("logsManager");
        DBStaffManager staffManager = 
                (DBStaffManager) session.getAttribute("staffManager");

        Customer customer = null;
        validator.clear(session);

        if (email.equals("admin@iotbay.com") && password.equals("admin")) {
            session.setAttribute("sysadmin", true);
        }
        
        try {
            customer = customerManager.findCustomer(email);
            if (!customer.isActive()) {
                // set user is not active error to the session
                session.setAttribute("loginErr", "That account is no longer active");
                // redirect user back to the login.jsp
                request.getRequestDispatcher("login.jsp").include(request, response);
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!validator.validateEmail(email)) {
            // set incorrect email error to the session
            session.setAttribute("emailErr", "Error: Email format incorrect");
            // redirect user back to the login.jsp
            request.getRequestDispatcher("login.jsp").include(request, response);
        } 
        else if (!validator.validatePassword(password)) {
            // set incorrect password error to the session
            session.setAttribute("passErr", "Error: Password format incorrect: min. 4 characters");
            // redirect user back to the login.jsp
            request.getRequestDispatcher("login.jsp").include(request, response);
        } 
        else if (customer != null) {
            if (customer.getPassword().equals(password)) {
                // save the logged in user object to the session
                session.setAttribute("customer", customer);
                try {
                    logsManager.addCustomerLog(customer.getEmail(), "Login");
                }
                catch (SQLException ex) {
                    // set user does not exist error to the session
                    session.setAttribute("loginErr", "Error saving login entry");
                    // redirect user back to the login.jsp
                    request.getRequestDispatcher("login.jsp").include(request, response);
                    return;
                }
                // redirect user to the main.jsp
                request.getRequestDispatcher("welcome.jsp").include(request, response);
            }
            else {
                // set incorrect password error to the session
                session.setAttribute("passErr", "Error: Incorrect password");
                // redirect user back to the login.jsp
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
        } 
        else {
            // staff check
            try {
                Staff staff = staffManager.findStaff(email);
                if (!staff.isActive()) {
                    // set user is not active error to the session
                    session.setAttribute("loginErr", "That account is no longer active");
                    // redirect user back to the login.jsp
                    request.getRequestDispatcher("login.jsp").include(request, response);
                }
                else if (!staff.getPassword().equals(password)) {
                    // set incorrect password error to the session
                    session.setAttribute("passErr", "Error: Incorrect password");
                    // redirect user back to the login.jsp
                    request.getRequestDispatcher("login.jsp").include(request, response);
                }
                else {
                    // save the logged in user object to the session
                    session.setAttribute("staff", staff);
                    try {
                        logsManager.addStaffLog(staff.getEmail(), "Login");
                    }
                    catch (SQLException ex) {
                        // set user does not exist error to the session
                        session.setAttribute("loginErr", "Error saving login entry");
                        // redirect user back to the login.jsp
                        request.getRequestDispatcher("login.jsp").include(request, response);
                    }
                    // redirect user to the main.jsp
                    request.getRequestDispatcher("welcome.jsp").include(request, response);
                    }
                return;
                }
            catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            // set user does not exist error to the session
            session.setAttribute("loginErr", "User does not exist in the database");
            // redirect user back to the login.jsp
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }
}
