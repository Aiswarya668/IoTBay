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
import uts.isd.model.Device;
import uts.isd.model.iotbay.dao.DBDeviceManager;

/**
 *
 * @author aiswarya
 */
public class UpdateDeviceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1- retrieve the current session
        HttpSession session = request.getSession();

        //2- create an instance of the Validator class
        Validator validator = new Validator();

        //3- capture the posted parameters/info fields 
        //capture deviceID field 
        String deviceID = request.getParameter("DeviceID"); // Just default value assigned 

        //capture deviceName field
        String deviceName = request.getParameter("DeviceName");

        //capture deviceType field
        String type = request.getParameter("DeviceType");

        //capture deviceCost field - noted it is a string and must be parsed in as a double
        String cost = request.getParameter("DeviceCost");

        //capture stockQuantity field - noted it is a string and must be parsed in as a int
        String stockQuantity = request.getParameter("DeviceStock");

        //capture devicedescription field
        String description = request.getParameter("DeviceDescription");

        //4) retrieve the manager instance from session - ConnServlet            
        DBDeviceManager deviceManager = (DBDeviceManager) session.getAttribute("deviceManager");

        Device device = null;

        try {
            device = deviceManager.findDevice(deviceName, type);

            //if device doesn't exsist after
            if (device == null) {
                //send device name + type already exsists error 
                session.setAttribute("exceptionErr", "Device with that name, type and ID does not exsist");
                request.getRequestDispatcher("editDevice.jsp").include(request, response);

                //validators
            } else {
                // editing device
                session.setAttribute("device", device);
                // redirect back to editDevice
                request.getRequestDispatcher("editDevice.jsp").include(request, response);

            }
        } catch (SQLException ex) {
            // exception message if updating customer fails
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
