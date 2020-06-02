/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        DBApplicationLogsManager logsManager = (DBApplicationLogsManager) session.getAttribute("logsManager");

        Customer customer = (Customer) session.getAttribute("customer");
        Staff staff = (Staff) session.getAttribute("staff");
        
        ArrayList<ApplicationAccessLogs> logs = new ArrayList<ApplicationAccessLogs>();
        try {
            if (customer != null) {
                logs = logsManager.findCustomerLogs(customer.getEmail());
            } else if (staff != null) {
                logs = logsManager.findStaffLogs(staff.getEmail());
            }
            // set logs to the session
            session.setAttribute("logs", logs);
        } catch (SQLException ex) {
            // show no logs error
            session.setAttribute("noLogsErr", "No logs for user exists");
        }
        request.getRequestDispatcher("userLogs.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // retrieve the current session
        HttpSession session = request.getSession();
        // retrieve the manager instance from session
        DBApplicationLogsManager logsManager = (DBApplicationLogsManager) session.getAttribute("logsManager");

        // retrieve customer/staff from session
        Customer customer = (Customer) session.getAttribute("customer");
        Staff staff = (Staff) session.getAttribute("staff");
        
        // get the dates posted
        String fromDate = request.getParameter("FromDate");
        String toDate = request.getParameter("ToDate");
        
        // reset error
        session.setAttribute("logResult", "");
        session.setAttribute("noLogsErr", "");
        
        // verify user actually wants to filter
        if (toDate != null && fromDate != null) {
            // convert to timestamp
            java.sql.Timestamp fromDateTimestamp = convertToTimeStamp(fromDate, session);
            java.sql.Timestamp toDateTimestamp = convertToTimeStamp(toDate, session);
            
            if (fromDateTimestamp == null || toDateTimestamp == null) {
                session.setAttribute("logResult", "Invalid date input");
                request.getRequestDispatcher("userLogs.jsp").include(request, response);
                return;
            }
            
            ArrayList<ApplicationAccessLogs> logs = new ArrayList<ApplicationAccessLogs>();
            try {
                if (customer != null) {
                    logs = logsManager.filterCustomerLogs(customer.getEmail(), fromDateTimestamp, toDateTimestamp);
                } else if (staff != null) {
                    logs = logsManager.filterStaffLogs(staff.getEmail(), fromDateTimestamp, toDateTimestamp);
                }

                // set logs to the session
                session.setAttribute("logs", logs);
                session.setAttribute("logResult", "Successful query");
            } catch (SQLException ex) {
                // show no logs error
                session.setAttribute("noLogsErr", "No logs for user exists");
            }
            request.getRequestDispatcher("userLogs.jsp").include(request, response);
        }
    }

    public java.sql.Timestamp convertToTimeStamp(String date, HttpSession session) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (Exception ex) {
            return null;
        }
    }
}
