/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.*;
import uts.isd.model.iotbay.dao.DBApplicationLogsManager;

/**
 *
 * @author Kevin
 */
public class UserLogsServlet extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // retrieve the current session
        HttpSession session = request.getSession();
        // retrieve the manager instance from session
        DBApplicationLogsManager logsManager = 
                (DBApplicationLogsManager) session.getAttribute("logsManager");

        Customer customer = (Customer) session.getAttribute("customer");
        
        try {
            ArrayList<ApplicationAccessLogs> logs = 
                    logsManager.findCustomerLogs(customer.getEmail());
            // set logs to the session
            session.setAttribute("logs", logs);
        } 
        catch (SQLException ex) {
            // show no logs error
            session.setAttribute("noLogsErr", "No logs for user exists");
        }
    }
    
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
        DBApplicationLogsManager logsManager = (DBApplicationLogsManager) session.getAttribute("logsManager");

        Customer customer = null;
        validator.clear(session);

        try {
            ArrayList<ApplicationAccessLogs> logs = 
                    logsManager.findCustomerLogs(customer.getEmail());
            // set user is not active error to the session
            session.setAttribute("logs", logs);
            // redirect user back to the login.jsp
            request.getRequestDispatcher("userLogs.jsp").include(request, response);
        } 
        catch (SQLException ex) {
            session.setAttribute("noLogsErr", "No logs for specified range exists");
            // redirect user back to the userLogs.jsp
            request.getRequestDispatcher("userLogs.jsp").include(request, response);
        }

        if (!validator.validateEmail(email)) {
            // set incorrect email error to the session
            session.setAttribute("emailErr", "Error: Email format incorrect");
            // redirect user back to the login.jsp
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            // set incorrect password error to the session
            session.setAttribute("passErr", "Error: Password format incorrect: min. 4 characters");
            // redirect user back to the login.jsp
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (customer != null) {
            if (customer.getPassword().equals(password)) {
                // save the logged in user object to the session
                session.setAttribute("customer", customer);
                // redirect user to the main.jsp
                request.getRequestDispatcher("welcome.jsp").include(request, response);
            }
            else {
                // set incorrect password error to the session
                session.setAttribute("passErr", "Error: Incorrect password");
                // redirect user back to the login.jsp
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
        } else {
            // set user does not exist error to the session
            session.setAttribute("loginErr", "Customer does not exist in the database");
            // redirect user back to the login.jsp
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }
}
