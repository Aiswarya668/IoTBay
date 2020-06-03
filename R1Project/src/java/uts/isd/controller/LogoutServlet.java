/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Customer;
import uts.isd.model.Staff;
import uts.isd.model.iotbay.dao.DBApplicationLogsManager;

/**
 *
 * @author Kevin
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // retrieve the current session
        HttpSession session = request.getSession();
        // retrieve the manager instance from session
        DBApplicationLogsManager logsManager = 
        (DBApplicationLogsManager) session.getAttribute("logsManager");
        
        Customer customer = (Customer) session.getAttribute("customer");
        Staff staff = (Staff) session.getAttribute("staff");
        
        try {
            // add logout log to db
            if (customer != null) {
                logsManager.addCustomerLog(customer.getEmail(), "Logout");
            }
            else if (staff != null) {
                logsManager.addStaffLog(staff.getEmail(), "Logout");
            }
        } 
        catch (SQLException ex) {
            // show no logs error
            session.setAttribute("logErr", "Error: Issue adding logout log");
        }
        session.invalidate();
    }
}
