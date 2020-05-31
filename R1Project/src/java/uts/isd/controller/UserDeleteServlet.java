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
import uts.isd.model.iotbay.dao.DBCustomerManager;

/**
 *
 * @author seant
 */
@WebServlet(name = "UserDeleteServlet", urlPatterns = {"/UserDeleteServlet"})
public class UserDeleteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserDeleteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserDeleteServlet at " + request.getParameter("customerEmail") + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        // 1- retrieve the current session
        HttpSession session = request.getSession();
        // 3- capture the posted email
        String email = request.getParameter("Email");
        // 4- capture the posted password
        String password = request.getParameter("Password");
        // 5- retrieve the manager instance from session
        DBCustomerManager customerManager = (DBCustomerManager) session.getAttribute("customerManager");

        Customer customer = null;

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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
