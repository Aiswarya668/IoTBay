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
        DBCustomerManager customerManager = (DBCustomerManager) session.getAttribute("customerManager");

        Customer customer = null;
        validator.clear(session);

        try {
            customer = customerManager.findCustomer(email);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!validator.validateEmail(email)) {
            // 8-set incorrect email error to the session
            session.setAttribute("emailErr", "Error: Email format incorrect");
            // 9- redirect user back to the login.jsp
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            // 11-set incorrect password error to the session
            session.setAttribute("passErr", "Error: Password format incorrect");
            // 12- redirect user back to the login.jsp
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (customer != null) {
            // 13-save the logged in user object to the session
            session.setAttribute("customer", customer);
            // 14- redirect user to the main.jsp
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else {
            // 15-set user does not exist error to the session
            session.setAttribute("existErr", "Customer does not exist in the database");
            // 16- redirect user back to the login.jsp
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }
}
