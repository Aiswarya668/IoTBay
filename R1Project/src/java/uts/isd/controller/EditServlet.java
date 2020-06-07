/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //retrieve the current session
        HttpSession session = request.getSession();

        //create an instance of the Validator class
        Validator validator = new Validator();

        Customer customer = (Customer) session.getAttribute("customer");
        Staff staff = (Staff) session.getAttribute("staff");
        // sysadmin user management - capture current userEmail
        String userEmail = "";
        String userType = "";
        if (request.getParameter("userEmail") != null && request.getParameter("userType") != null) {
            userEmail = request.getParameter("userEmail");
            userType = request.getParameter("userType");
        } else if (staff != null) {
            userEmail = staff.getEmail();
            userType = "staff";
            // sysadmin user management - capture the posted userType
        } else if (customer != null) {
            userEmail = customer.getEmail();
            userType = "customer";
        }

        boolean sysadmin = (session.getAttribute("sysadmin") != null);
        // hold sysadmin credentials while editing another user
        if (sysadmin) {
            Staff editor = (Staff) session.getAttribute("staff");
            session.setAttribute("editor", editor);
        }

        if ((sysadmin && userType.equals("staff")) || (!sysadmin && staff != null)) {
            // retrieve the manager instance from session - ConnServlet            
            DBStaffManager staffManager = (DBStaffManager) session.getAttribute("staffManager");

            staff = null;
            // sysadmin reset when editing another user
            session.setAttribute("customer", null);
            session.setAttribute("staff", null);
            try {
                staff = staffManager.findStaff(userEmail);
                session.setAttribute("staff", staff);
                session.setAttribute("customer", null); // staff and customer cannot be in session simultaneously

                // get staff list for datalist dropdown
                List<Staff> staffs = staffManager.fetchStaffs();
                request.setAttribute("staffs", staffs);

                request.getRequestDispatcher("edit.jsp").include(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.getRequestDispatcher("main.jsp").include(request, response);
            }
        } else if ((sysadmin && userType.equals("customer")) || (!sysadmin && customer != null)) {
            // retrieve the manager instance from session - ConnServlet            
            DBCustomerManager customerManager = (DBCustomerManager) session.getAttribute("customerManager");

            customer = null;
            session.setAttribute("customer", null);
            session.setAttribute("staff", null);

            try {
                customer = customerManager.findCustomer(userEmail);
                session.setAttribute("customer", customer);
                session.setAttribute("staff", null); // staff and customer cannot be in session simultaneously
                request.getRequestDispatcher("edit.jsp").include(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.getRequestDispatcher("main.jsp").include(request, response);
            }
        }
    }

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
        DBCustomerManager customerManager = (DBCustomerManager) session.getAttribute("customerManager");
        DBStaffManager staffManager = (DBStaffManager) session.getAttribute("staffManager");
        boolean sysadmin = (session.getAttribute("sysadmin") != null);

        // retrieve old customer values from session
        Customer existingCustomer = (Customer) session.getAttribute("customer");
        Staff existingStaff = (Staff) session.getAttribute("staff");

        // if user wants to deactivate account
        if (request.getParameter("Deactivate") != null && request.getParameter("Deactivate").equals("Deactivate")) {
            try {
                if (existingCustomer != null) {
                    customerManager.deactivateCustomer(existingCustomer.getEmail());
                    // success message if updating customer successful
                    session.setAttribute("updateMsg", "Customer deactivated (" + existingCustomer.getEmail() + ")");
                } else if (existingStaff != null) {;
                    staffManager.deactivateStaff(existingStaff.getEmail());
                    // success message if updating staff successful
                    session.setAttribute("updateMsg", "Staff deactivated (" + existingStaff.getEmail() + ")");
                }
                // reset staff session if sysadmin was editing another user
                Staff editor = (session.getAttribute("editor") != null) ? (Staff) session.getAttribute("editor") : null;
                if (editor != null) {
                    session.setAttribute("staff", editor);
                    session.setAttribute("customer", null); // staff and customer cannot be in session simultaneously
                    session.setAttribute("editor", null);
                }
                // redirect user
                if (sysadmin) {
                    response.sendRedirect("UserListServlet");
                } else {
                    request.getRequestDispatcher("goodbye.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                session.setAttribute("updateMsg", "Failed to deactivate");
                response.sendRedirect("EditServlet");
            }
            return;
        } else if (request.getParameter("Activate") != null && request.getParameter("Activate").equals("Activate")) {
            try {
                if (existingCustomer != null) {
                    customerManager.activateCustomer(existingCustomer.getEmail());
                    // success message if updating customer successful
                    session.setAttribute("updateMsg", "Customer activated (" + existingCustomer.getEmail() + ")");
                } else if (existingStaff != null) {;
                    staffManager.activateStaff(existingStaff.getEmail());
                    // success message if updating staff successful
                    session.setAttribute("updateMsg", "Staff activated (" + existingStaff.getEmail() + ")");
                }
                // reset staff session if sysadmin was editing another user
                Staff editor = (session.getAttribute("editor") != null) ? (Staff) session.getAttribute("editor") : null;
                if (editor != null) {
                    session.setAttribute("staff", editor);
                    session.setAttribute("customer", null); // staff and customer cannot be in session simultaneously
                    session.setAttribute("editor", null);
                }
                // redirect user
                if (sysadmin) {
                    response.sendRedirect("UserListServlet");
                } else {
                    request.getRequestDispatcher("goodbye.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                session.setAttribute("updateMsg", "Failed to activate");
                response.sendRedirect("EditServlet");
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
            Logger.getLogger(LoginServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        if (!validator.validateEmail(email)) {
            // set incorrect email error to the session 
            session.setAttribute("emailEditErr", "Error: Email format incorrect");
            // redirect user back to the edit.jsp     
            response.sendRedirect("EditServlet");
        } else if (!validator.validatePassword(password)) {
            // set incorrect password error to the session 
            session.setAttribute("passEditErr", "Error: Must be at least 4 characters long");
            // redirect user back to the edit.jsp 
            response.sendRedirect("EditServlet");
        } else if (!validator.validateSingleString(firstName)) {
            // set incorrect email error to the session 
            session.setAttribute("fNameEditErr", "Error: First name format incorrect");
            // redirect user back to the edit.jsp     
            response.sendRedirect("EditServlet");
        } else if (!validator.validateSingleString(lastName)) {
            // set incorrect email error to the session 
            session.setAttribute("lNameEditErr", "Error: Last name format incorrect");
            // redirect user back to the edit.jsp     
            response.sendRedirect("EditServlet");
        } else if (!validator.validateSingleInt(unitNumber)) {
            // set incorrect email error to the session 
            session.setAttribute("unitEditErr", "Error: Unit number format incorrect");
            // redirect user back to the edit.jsp     
            response.sendRedirect("EditServlet");
        } else if (!validator.validateSentence(streetAddress)) {
            // set incorrect email error to the session 
            session.setAttribute("streetEditErr", "Error: Street address format incorrect");
            // redirect user back to the edit.jsp     
            response.sendRedirect("EditServlet");
        } else if (!validator.validateSingleString(city)) {
            // set incorrect email error to the session 
            session.setAttribute("emailEditErr", "Error: City format incorrect");
            // redirect user back to the edit.jsp     
            response.sendRedirect("EditServlet");
        } else if (!validator.validateSingleString(state)) {
            // set incorrect email error to the session 
            session.setAttribute("stateEditErr", "Error: State format incorrect");
            // redirect user back to the edit.jsp     
            response.sendRedirect("EditServlet");
        } else if (!validator.validateSingleInt(postCode)) {
            // set incorrect email error to the session 
            session.setAttribute("postEditErr", "Error: Post code format incorrect");
            // redirect user back to the edit.jsp     
            response.sendRedirect("EditServlet");
        } else if (phoneNumber == null) {
            // set incorrect phone number error to the session 
            session.setAttribute("phoneEditErr", "Error: Phone number is mandatory");
            // redirect user back to the edit.jsp     
            response.sendRedirect("EditServlet");
        } else if (!validator.validatePhone(phoneNumber)) {
            // set incorrect phone number error to the session 
            session.setAttribute("phoneEditErr", "Error: Phone number format is incorrect");
            // redirect user back to the edit.jsp     
            response.sendRedirect("EditServlet");
        } // if customer
        else if (existingCustomer != null) {
            // customer-specific validation
            if (gender == null) {
                // set incorrect email error to the session 
                session.setAttribute("genderEditErr", "Error: Gender is mandatory");
                // redirect user back to the edit.jsp     
                response.sendRedirect("EditServlet");
                return;
            }

            //  and email has been changed
            if (!email.equals(existingCustomer.getEmail())) {
                // if user already exists with that email
                if (customer != null) {
                    // set duplicate email error to the session 
                    session.setAttribute("existEditErr", "Customer with that email already exists in the database");
                    // redirect user to the edit.jsp to retry
                    response.sendRedirect("EditServlet");
                } else {
                    // updating user if email is valid and empty
                    try {
                        customerManager.updateCustomer(email, firstName, lastName,
                                password, gender, unitNumber, streetAddress, city,
                                state, postCode, phoneNumber, true);
                        Customer updatedCustomer = customerManager.findCustomer(email);
                        session.setAttribute("customer", updatedCustomer);
                        session.setAttribute("staff", null); // staff and customer cannot be in session simultaneously
                        // success message if updating customer successful
                        session.setAttribute("updateMsg", "Customer updated (" + email + ")");
                        // reset staff session if sysadmin was editing another user
                        Staff editor = (session.getAttribute("editor") != null) ? (Staff) session.getAttribute("editor") : null;
                        if (editor != null) {
                            session.setAttribute("staff", editor);
                            session.setAttribute("customer", null); // staff and customer cannot be in session simultaneously
                            session.setAttribute("editor", null);
                        }
                        // redirect user
                        if (sysadmin) {
                            response.sendRedirect("UserListServlet");
                        } else {
                            request.getRequestDispatcher("main.jsp").include(request, response);
                        }
                    } catch (SQLException ex) {
                        // exception message if updating customer fails
                        session.setAttribute("updateMsg", "Update was not successful");
                        response.sendRedirect("EditServlet");
                    }
                }
            } // email has not changed, update as normal
            else {
                try {
                    customerManager.updateCustomer(email, firstName, lastName,
                            password, gender, unitNumber, streetAddress, city,
                            state, postCode, phoneNumber, true);
                    Customer updatedCustomer = customerManager.findCustomer(email);
                    session.setAttribute("customer", updatedCustomer);
                    session.setAttribute("staff", null); // staff and customer cannot be in session simultaneously
                    // reset staff session if sysadmin was editing another user
                    Staff editor = (session.getAttribute("editor") != null) ? (Staff) session.getAttribute("editor") : null;
                    if (editor != null) {
                        session.setAttribute("staff", editor);
                        session.setAttribute("customer", null); // staff and customer cannot be in session simultaneously
                        session.setAttribute("editor", null);
                    }
                    // success message if updating customer successful
                    session.setAttribute("updateMsg", "Customer updated (" + email + ")");
                    // redirect user
                    if (sysadmin) {
                        response.sendRedirect("UserListServlet");
                    } else {
                        request.getRequestDispatcher("main.jsp").include(request, response);
                    }
                } catch (SQLException ex) {
                    // exception message if updating customer fails
                    session.setAttribute("updateMsg", "Update was not successful");
                    response.sendRedirect("EditServlet");
                }
            }
        } else if (existingStaff != null) {
            // staff-specific validation
            if (manager == null || manager.equals("")) {
                // set incorrect email error to the session 
                session.setAttribute("managerEditErr", "Error: Manager is mandatory");
                // redirect user back to the edit.jsp     
                response.sendRedirect("EditServlet");
            } else if (contractType == null) {
                // set incorrect email error to the session 
                session.setAttribute("contractTypeEditErr", "Error: Contract type is mandatory");
                // redirect user back to the edit.jsp     
                response.sendRedirect("EditServlet");
            } else if (!validator.validateSingleInt(payHr)) {
                // set incorrect email error to the session 
                session.setAttribute("payHrEditErr", "Error: Pay per hour is mandatory");
                // redirect user back to the edit.jsp     
                response.sendRedirect("EditServlet");
            }

            if (!email.equals(existingStaff.getEmail())) {
                // if user already exists with that email
                if (staff != null) {
                    // set duplicate email error to the session 
                    session.setAttribute("existEditErr", "Staff with that email already exists in the database");
                    // redirect user to the edit.jsp to retry
                    response.sendRedirect("EditServlet");
                } else {
                    // updating user if email is valid and empty
                    try {
                        staffManager.updateStaff(email, firstName, lastName,
                                phoneNumber, password, streetAddress, unitNumber, city,
                                state, postCode, manager, contractType, Integer.parseInt(payHr), true);
                        Staff updatedStaff = staffManager.findStaff(email);
                        session.setAttribute("staff", updatedStaff);
                        session.setAttribute("customer", null); // staff and customer cannot be in session simultaneously
                        // success message if updating customer successful
                        session.setAttribute("updateMsg", "Staff updated (" + email + ")");
                        // reset staff session if sysadmin was editing another user
                        Staff editor = (session.getAttribute("editor") != null) ? (Staff) session.getAttribute("editor") : null;
                        if (editor != null) {
                            session.setAttribute("staff", editor);
                            session.setAttribute("customer", null); // staff and customer cannot be in session simultaneously
                            session.setAttribute("editor", null);
                        }
                        // redirect user
                        if (sysadmin) {
                            response.sendRedirect("UserListServlet");
                        } else {
                            request.getRequestDispatcher("main.jsp").include(request, response);
                        }
                    } catch (SQLException ex) {
                        // exception message if updating customer fails
                        session.setAttribute("updateMsg", "Update was not successful");
                        response.sendRedirect("EditServlet");
                    }
                }
            } else {
                try {
                    staffManager.updateStaff(email, firstName, lastName,
                            phoneNumber, password, streetAddress, unitNumber, city,
                            state, postCode, manager, contractType, Integer.parseInt(payHr), true);
                    Staff updatedStaff = staffManager.findStaff(email);
                    session.setAttribute("staff", updatedStaff);
                    session.setAttribute("customer", null); // staff and customer cannot be in session simultaneously
                    // success message if updating customer successful
                    session.setAttribute("updateMsg", "Staff updated (" + email + ")");
                    // reset staff session if sysadmin was editing another user
                    Staff editor = (session.getAttribute("editor") != null) ? (Staff) session.getAttribute("editor") : null;
                    if (editor != null) {
                        session.setAttribute("staff", editor);
                        session.setAttribute("customer", null); // staff and customer cannot be in session simultaneously
                        session.setAttribute("editor", null);
                    }
                    // redirect user
                    if (sysadmin) {
                        response.sendRedirect("UserListServlet");
                    } else {
                        request.getRequestDispatcher("main.jsp").include(request, response);
                    }
                } catch (SQLException ex) {
                    // exception message if updating customer fails
                    session.setAttribute("updateMsg", "Update was not successful");
                    response.sendRedirect("EditServlet");
                }
            }
        }
    }
}
