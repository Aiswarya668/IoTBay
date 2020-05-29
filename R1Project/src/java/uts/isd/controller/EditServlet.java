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
import uts.isd.model.Customer;
import uts.isd.model.iotbay.dao.DBCustomerManager;

/**
 *
 * @author Kevin
 */
public class EditServlet extends HttpServlet {
    
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
            session.setAttribute("existErr", "Customer does not exist in the database");
            // redirect user back to the login.jsp
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }
}
