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
import uts.isd.model.Customer;
import uts.isd.model.iotbay.dao.*;

/**
 *
 * @author Kevin
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1- retrieve the current session
        HttpSession session = request.getSession();
        //2- create an instance of the Validator class
        Validator validator = new Validator();
        //3- capture the posted parameters/info fields  
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String email = request.getParameter("Email");
        String password = request.getParameter("Password");
        String gender = request.getParameter("gender");
        String unitNumber = request.getParameter("UnitNumber");
        String streetAddress = request.getParameter("StreetAddress");
        String city = request.getParameter("City");
        String state = request.getParameter("State");
        String postCode = request.getParameter("PostCode");
        String phoneNumber = request.getParameter("PhoneNumber");
        //5- retrieve the manager instance from session
        DBCustomerManager customerManager = 
                (DBCustomerManager)session.getAttribute("customerManager");
        DBApplicationLogsManager logsManager = 
                (DBApplicationLogsManager) session.getAttribute("logsManager");
        Customer customer = null;
        validator.clear(session);
        
        try {
            customer = customerManager.findCustomer(email);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (customer != null) {
             // set duplicate email error to the session 
            session.setAttribute("exceptionErr", "Customer with that email already exists in the database");
            // redirect user to the login.jsp to retry
            request.getRequestDispatcher("register.jsp").include(request, response);
        }
        else if (!validator.validateEmail(email)) {
            // set incorrect email error to the session 
            session.setAttribute("emailErr", "Error: Email format incorrect");
            // redirect user back to the login.jsp     
            request.getRequestDispatcher("register.jsp").include(request, response);
        } 
        else if (!validator.validatePassword(password)) {
            // set incorrect password error to the session 
            session.setAttribute("passErr", "Error: Must be at least 4 characters long");
            // redirect user back to the login.jsp 
            request.getRequestDispatcher("register.jsp").include(request, response);
        } 
        else if (!validator.validateSingleString(firstName)) {
            // set incorrect email error to the session 
            session.setAttribute("fNameErr", "Error: First name is mandatory");
            // redirect user back to the login.jsp     
            request.getRequestDispatcher("register.jsp").include(request, response);
        } 
        else if (!validator.validateSingleString(lastName)) {
            // set incorrect email error to the session 
            session.setAttribute("lNameErr", "Error: Last name is mandatory");
            // redirect user back to the login.jsp     
            request.getRequestDispatcher("register.jsp").include(request, response);
        } 
        else if (!validator.validateSingleInt(unitNumber)) {
            // set incorrect email error to the session 
            session.setAttribute("unitErr", "Error: Unit number is mandatory");
            // redirect user back to the login.jsp     
            request.getRequestDispatcher("register.jsp").include(request, response);
        } 
        else if (gender == null) {
            // set incorrect email error to the session 
            session.setAttribute("genderErr", "Error: Gender is mandatory");
            // redirect user back to the login.jsp     
            request.getRequestDispatcher("register.jsp").include(request, response);
        }
        else if (!validator.validateSentence(streetAddress)) {
            // set incorrect email error to the session 
            session.setAttribute("streetErr", "Error: Street address is mandatory");
            // redirect user back to the login.jsp     
            request.getRequestDispatcher("register.jsp").include(request, response);
        } 
        else if (!validator.validateSingleString(city)) {
            // set incorrect email error to the session 
            session.setAttribute("emailErr", "Error: City is mandatory");
            // redirect user back to the login.jsp     
            request.getRequestDispatcher("register.jsp").include(request, response);
        } 
        else if (!validator.validateSingleString(state)) {
            // set incorrect email error to the session 
            session.setAttribute("stateErr", "Error: State is mandatory");
            // redirect user back to the login.jsp     
            request.getRequestDispatcher("register.jsp").include(request, response);
        } 
        else if (!validator.validateSingleInt(postCode)) {
            // set incorrect email error to the session 
            session.setAttribute("postErr", "Error: Post code is mandatory");
            // redirect user back to the login.jsp     
            request.getRequestDispatcher("register.jsp").include(request, response);
        } 
        else if (phoneNumber == null) {
            // set incorrect phone number error to the session 
            session.setAttribute("phoneErr", "Error: Phone number is mandatory");
            // redirect user back to the login.jsp     
            request.getRequestDispatcher("register.jsp").include(request, response);
        }
        else if (!validator.validatePhone(phoneNumber)) {
            // set incorrect phone number error to the session 
            session.setAttribute("phoneErr", "Error: Phone number format is incorrect");
            // redirect user back to the login.jsp     
            request.getRequestDispatcher("register.jsp").include(request, response);
        } 
        else {
            try {
                // create new user
                customerManager.addCustomer(firstName, lastName, email, 
                password, gender, unitNumber, streetAddress, city, 
                state, postCode, phoneNumber);
                // add login log
                logsManager.addCustomerLog(customer.getEmail(), "Login");
            }
            catch (SQLException ex) {
                // exception message if adding customer fails
                session.setAttribute("exceptionErr", "Registration failed");
                request.getRequestDispatcher("login.jsp").include(request, response);
                return;
            }
            // redirect new user to the welcome.jsp
            request.getRequestDispatcher("welcome.jsp").include(request, response);
        }
    }
}
