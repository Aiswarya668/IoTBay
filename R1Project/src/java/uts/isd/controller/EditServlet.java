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
import uts.isd.model.*;
import uts.isd.model.iotbay.dao.*;

/**
 *
 * @author Kevin
 */
public class EditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //1- retrieve the current session
        HttpSession session = request.getSession();
        //2- create an instance of the Validator class
        Validator validator = new Validator();
        //3- capture the posted parameters/info fields  
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String email = request.getParameter("Email");
        String password = request.getParameter("Password");
        String unitNumber = request.getParameter("UnitNumber");
        String streetAddress = request.getParameter("StreetAddress");
        String city = request.getParameter("City");
        String state = request.getParameter("State");
        String postCode = request.getParameter("PostCode");
        String phoneNumber = request.getParameter("PhoneNumber");
        
        // customer
        String gender = request.getParameter("Gender");
        
        // staff
        String manager = request.getParameter("Manager");
        String contractType = request.getParameter("ContractType");
        String payHr = request.getParameter("PayHr");
        
        //5- retrieve the manager instance from session
        DBCustomerManager customerManager = (DBCustomerManager)session.getAttribute("customerManager");
        DBStaffManager staffManager = (DBStaffManager)session.getAttribute("staffManager");
        
        // retrieve old customer values from session
        Customer existingCustomer = (Customer) session.getAttribute("customer");
        Staff existingStaff = (Staff) session.getAttribute("staff");
        
        // if user wants to deactivate account
        if (request.getParameter("Deactivate") != null && request.getParameter("Deactivate").equals("Deactivate")) {
            try {
                if (existingCustomer != null) {
                    customerManager.deactivateCustomer(existingCustomer.getEmail());
                }
                else if (existingStaff != null) {
                    staffManager.deactivateStaff(existingStaff.getEmail());
                }
                request.getRequestDispatcher("goodbye.jsp").include(request, response);
                
            }
            catch (SQLException ex) {
                session.setAttribute("updateMsg", "Failed to deactivate");
                request.getRequestDispatcher("edit.jsp").include(request, response);
            }
            return;
        }

        Customer customer = null;
        Staff staff = null;
        
        // reset
        validator.clear(session);

        try {
            customer = customerManager.findCustomer(email);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!validator.validateEmail(email)) {
            // set incorrect email error to the session 
            session.setAttribute("emailEditErr", "Error: Email format incorrect");
            // redirect user back to the edit.jsp     
            request.getRequestDispatcher("edit.jsp").include(request, response);
        } 
        else if (!validator.validatePassword(password)) {
            // set incorrect password error to the session 
            session.setAttribute("passEditErr", "Error: Must be at least 4 characters long");
            // redirect user back to the edit.jsp 
            request.getRequestDispatcher("edit.jsp").include(request, response);
        } 
        else if (!validator.validateSingleString(firstName)) {
            // set incorrect email error to the session 
            session.setAttribute("fNameEditErr", "Error: First name format incorrect");
            // redirect user back to the edit.jsp     
            request.getRequestDispatcher("edit.jsp").include(request, response);
        } 
        else if (!validator.validateSingleString(lastName)) {
            // set incorrect email error to the session 
            session.setAttribute("lNameEditErr", "Error: Last name format incorrect");
            // redirect user back to the edit.jsp     
            request.getRequestDispatcher("edit.jsp").include(request, response);
        } 
        else if (!validator.validateSingleInt(unitNumber)) {
            // set incorrect email error to the session 
            session.setAttribute("unitEditErr", "Error: Unit number format incorrect");
            // redirect user back to the edit.jsp     
            request.getRequestDispatcher("edit.jsp").include(request, response);
        } 
        else if (!validator.validateSentence(streetAddress)) {
            // set incorrect email error to the session 
            session.setAttribute("streetEditErr", "Error: Street address format incorrect");
            // redirect user back to the edit.jsp     
            request.getRequestDispatcher("edit.jsp").include(request, response);
        } 
        else if (!validator.validateSingleString(city)) {
            // set incorrect email error to the session 
            session.setAttribute("emailEditErr", "Error: City format incorrect");
            // redirect user back to the edit.jsp     
            request.getRequestDispatcher("edit.jsp").include(request, response);
        } 
        else if (!validator.validateSingleString(state)) {
            // set incorrect email error to the session 
            session.setAttribute("stateEditErr", "Error: State format incorrect");
            // redirect user back to the edit.jsp     
            request.getRequestDispatcher("edit.jsp").include(request, response);
        } 
        else if (!validator.validateSingleInt(postCode)) {
            // set incorrect email error to the session 
            session.setAttribute("postEditErr", "Error: Post code format incorrect");
            // redirect user back to the edit.jsp     
            request.getRequestDispatcher("edit.jsp").include(request, response);
        } 
        else if (phoneNumber == null) {
            // set incorrect phone number error to the session 
            session.setAttribute("phoneEditErr", "Error: Phone number is mandatory");
            // redirect user back to the edit.jsp     
            request.getRequestDispatcher("edit.jsp").include(request, response);
        }
        else if (!validator.validatePhone(phoneNumber)) {
            // set incorrect phone number error to the session 
            session.setAttribute("phoneEditErr", "Error: Phone number format is incorrect");
            // redirect user back to the edit.jsp     
            request.getRequestDispatcher("edit.jsp").include(request, response);
        } 
        // if customer
        else if (existingCustomer != null) {
            // customer-specific validation
            if (gender == null) {
                // set incorrect email error to the session 
                session.setAttribute("genderEditErr", "Error: Gender is mandatory");
                // redirect user back to the edit.jsp     
                request.getRequestDispatcher("edit.jsp").include(request, response);
                return;
            }

            //  and email has been changed
            if (!email.equals(existingCustomer.getEmail())) {
                // if user already exists with that email
                if (customer != null) {
                // set duplicate email error to the session 
                session.setAttribute("existEditErr", "Customer with that email already exists in the database");
                // redirect user to the edit.jsp to retry
                request.getRequestDispatcher("edit.jsp").include(request, response);
                }
                else {
                    // updating user if email is valid and empty
                    try {
                    customerManager.updateCustomer(email, firstName, lastName, 
                    password, gender, unitNumber, streetAddress, city, 
                    state, postCode, phoneNumber, true);
                    Customer updatedCustomer = customerManager.findCustomer(email);
                    session.setAttribute("customer", updatedCustomer);
                    // success message if updating customer successful
                    session.setAttribute("updateMsg", "Update was successful");
                    // redirect user to the edit.jsp
                    request.getRequestDispatcher("edit.jsp").include(request, response);
                    }
                    catch (SQLException ex) {
                        // exception message if updating customer fails
                        session.setAttribute("updateMsg", "Update was not successful");
                        request.getRequestDispatcher("edit.jsp").include(request, response);
                    }
                }
            }
            // email has not changed, update as normal
            else {
                try {
                customerManager.updateCustomer(email, firstName, lastName,
                password, gender, unitNumber, streetAddress, city, 
                state, postCode, phoneNumber, true);
                Customer updatedCustomer = customerManager.findCustomer(email);
                session.setAttribute("customer", updatedCustomer);
                // success message if updating customer successful
                session.setAttribute("updateMsg", "Update was successful");
                // redirect user to the edit.jsp
                request.getRequestDispatcher("edit.jsp").include(request, response);
                }
                catch (SQLException ex) {
                    // exception message if updating customer fails
                    session.setAttribute("updateMsg", "Update was not successful");
                    request.getRequestDispatcher("edit.jsp").include(request, response);
                }
            }
        }
        else if (existingStaff != null) {
            // staff-specific validation
            if (!validator.validateSingleString(manager)) {
                // set incorrect email error to the session 
                session.setAttribute("managerEditErr", "Error: Manager is mandatory");
                // redirect user back to the edit.jsp     
                request.getRequestDispatcher("edit.jsp").include(request, response);
            } 
            else if (!validator.validateSingleString(contractType)) {
                // set incorrect email error to the session 
                session.setAttribute("contractTypeEditErr", "Error: Contract type is mandatory");
                // redirect user back to the edit.jsp     
                request.getRequestDispatcher("edit.jsp").include(request, response);
            } 
            else if (!validator.validateSingleInt(payHr)) {
                // set incorrect email error to the session 
                session.setAttribute("payHrEditErr", "Error: Pay per hour is mandatory");
                // redirect user back to the edit.jsp     
                request.getRequestDispatcher("edit.jsp").include(request, response);
            } 

            if (!email.equals(existingStaff.getEmail())) {
                // if user already exists with that email
                if (staff != null) {
                // set duplicate email error to the session 
                session.setAttribute("existEditErr", "Staff with that email already exists in the database");
                // redirect user to the edit.jsp to retry
                request.getRequestDispatcher("edit.jsp").include(request, response);
                }
                else {
                    // updating user if email is valid and empty
                    try {
                    staffManager.updateStaff(email, firstName, lastName, 
                    phoneNumber, password, streetAddress, unitNumber, city, 
                    state, postCode, manager, contractType, Integer.parseInt(payHr), true);
                    Staff updatedStaff = staffManager.findStaff(email);
                    session.setAttribute("staff", updatedStaff);
                    // success message if updating customer successful
                    session.setAttribute("updateMsg", "Update was successful");
                    // redirect user to the edit.jsp
                    request.getRequestDispatcher("edit.jsp").include(request, response);
                    }
                    catch (SQLException ex) {
                        // exception message if updating customer fails
                        session.setAttribute("updateMsg", "Update was not successful");
                        request.getRequestDispatcher("edit.jsp").include(request, response);
                    }
                }
            }
            else {
                try {
                staffManager.updateStaff(email, firstName, lastName, 
                phoneNumber, password, streetAddress, unitNumber, city, 
                state, postCode, manager, contractType, Integer.parseInt(payHr), true);
                Staff updatedStaff = staffManager.findStaff(email);
                session.setAttribute("staff", updatedStaff);
                // success message if updating customer successful
                session.setAttribute("updateMsg", "Update was successful");
                // redirect user to the edit.jsp
                request.getRequestDispatcher("edit.jsp").include(request, response);
                }
                catch (SQLException ex) {
                    // exception message if updating customer fails
                    session.setAttribute("updateMsg", "Update was not successful");
                    request.getRequestDispatcher("edit.jsp").include(request, response);
                }
            }
        }
    }
}
