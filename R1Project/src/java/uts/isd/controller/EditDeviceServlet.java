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
public class EditDeviceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1- retrieve the current session
        HttpSession session = request.getSession();
        
        //2- create an instance of the Validator class
        Validator validator = new Validator();
        
        //3- capture the posted parameters/info fields  
        int deviceID = 0; // Just default value assigned 
        try {
            deviceID = Integer.parseInt(request.getParameter("DeviceID"));
        } catch (NumberFormatException e) {
            // log the error or ignore it
        }
        //4) capture the posted deviceName    
        String deviceName = request.getParameter("DeviceName");

        //5) capture the posted deviceType  
        String type = request.getParameter("DeviceType");

        //6) capture the posted deviceCost - parse as a double as the input is a string in form at bottom
        String cost = request.getParameter("DeviceCost");

        //7) capture the posted stockQuantity - parse as a int as the input is a string in form at bottom 
        String stockQuantity = request.getParameter("DeviceStock");

        //8) capture the posted description   
        String description = request.getParameter("DeviceDescription");

        //9) retrieve the manager instance from session - ConnServlet            
        DBDeviceManager deviceManager = (DBDeviceManager) session.getAttribute("deviceManager");
               
        
        // retrieve old customer values from session
        //Device existingDevice = (Device) session.getAttribute("display");
        //String oldDeviceName = existingDevice.getDeviceName();
        
        //String oldDeviceType = existingDevice.getType();
        
        

        Device device = null;

        try {
            device = deviceManager.findDevice(deviceName, type);
       
       //if (!deviceName.equals(existingDevice.getDeviceName()) && !type.equals(existingDevice.getType())) {
        if (device != null) {
             //send device name + type already exsists error 
             session.setAttribute("device", device);
             // redirect back to addDevice
             request.getRequestDispatcher("editDevice.jsp").include(request, response);
             
        
        } else {
            // updating user
            session.setAttribute("exceptionErr", "Device with that name and type already exists in the database please try again");
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
                //deviceManager.addDevice(deviceName, type, Double.parseDouble(cost), Integer.parseInt(stockQuantity), description);      
                // redirect user to the edit.jsp
                //request.getRequestDispatcher("editDevice.jsp").include(request, response);
                
            }
        }   catch (SQLException ex) {
                // exception message if updating customer fails
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                
                
            }
        //request.getRequestDispatcher("editDevice.jsp").include(request, response);
        }
    }

