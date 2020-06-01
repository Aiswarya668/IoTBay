/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.iotbay.dao.*;

/**
 *
 * @author Kevin
 */
public class ConnServlet extends HttpServlet {

    private DBConnector db;
    private DBCustomerManager customerManager;
    private DBDeviceManager deviceManager;
    private DBApplicationLogsManager logsManager;
    private DBStaffManager staffManager;
    private Connection conn;

    @Override // Create and instance of DBConnector for the deployment session
    public void init() {
        try {
            db = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override // Add the DBConnector, DBManager, Connection instances to the session
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        conn = db.openConnection();
        try {
            // instantiate new customer manager
            customerManager = new DBCustomerManager(conn);
            // instantiate new DeviceManager
            deviceManager = new DBDeviceManager(conn);
            // instantiate new logs manager
            logsManager = new DBApplicationLogsManager(conn);
            // instantiate new staff manager
            staffManager = new DBStaffManager(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        // export the DB manager(s) to the view-session (JSPs)
        session.setAttribute("customerManager", customerManager);
        session.setAttribute("deviceManager", deviceManager);
        session.setAttribute("logsManager", logsManager);
        session.setAttribute("staffManager", staffManager);
    }
    
    // Destroy the servlet and release the resources of the application (terminate
    // also the db connection)
    @Override
    public void destroy() {
        try {
            db.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
