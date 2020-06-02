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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1) retrieve the current session
        HttpSession session = request.getSession();

        //2) create an instance of the Validator class to check inputs
        Validator validator = new Validator();

        //3) Capture all the posted fields 
        // capture deviceID - noted it is string and needs to be parsed 
        String deviceID = request.getParameter("DeviceID"); // Just default value assigned 

        //capture the posted deviceName    
        String deviceName = request.getParameter("DeviceName");

        //capture the posted deviceType  
        String type = request.getParameter("DeviceType");

        //capture the posted deviceCost - parse as a double as the input is a string in form at bottom
        String cost = (request.getParameter("DeviceCost"));

        //capture the posted stockQuantity - parse as a int as the input is a string in form at bottom 
        String stockQuantity = (request.getParameter("DeviceStock"));

        //capture the posted description   
        String description = request.getParameter("DeviceDescription");

        //retrieve the manager instance from session - ConnServlet            
        DBDeviceManager deviceManager = (DBDeviceManager) session.getAttribute("deviceManager");

        
        Device device = null;
        
        validator.clear(session);

        //Check if device exsists first
        try {
            device = deviceManager.findDeviceID(Integer.parseInt(deviceID));
           
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        //check if device is not null (if null means that it is a new device) 
        if (device == null) {
            //device name & type don't exsists error 
            session.setAttribute("exceptionErr", "Device with that name and type does not exist in the database please try again");
            // redirect back to addDevice
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
        } 
        
        //validators
       
        //if any fields are empty?
        else if(validator.checkDeviceEmpty(deviceName,type,cost,stockQuantity,description)){
             session.setAttribute("deviceEmptyErr", "Error: All fields are mandatory!");
             request.getRequestDispatcher("editDevice.jsp").include(request, response);
        }
        else if (!validator.validateDeviceName(deviceName)) {
            //1- set incorrect name error to the session 
            session.setAttribute("deviceNameErr", "Error: Device name format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
       
        } else if (!validator.validateDeviceType(type)) {
            //1- set incorrect type error to the session 
            session.setAttribute("typeErr", "Error: Device type format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
        
        } else if (!validator.validateDeviceCost(cost)) {
            //1- set incorrect type error to the session 
            session.setAttribute("priceErr", "Error: Device price format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
        
        } else if (!validator.validateDeviceStock(stockQuantity)) {
            //1- set incorrect type error to the session 
            session.setAttribute("stockErr", "Error: Device stock format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
        
        } else if (!validator.validateDeviceDesc(description)) {
            //1- set incorrect type error to the session 
            session.setAttribute("descriptionErr", "Error: Device description format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
        
        } //if all passess then add the device
        else {
            try {
                //addDevice CRUD operation
                deviceManager.updateDevice(Integer.parseInt(deviceID), deviceName, type, Double.parseDouble(cost), Integer.parseInt(stockQuantity), description);
                //set session attribute
                request.setAttribute("device", device);
                //send to createdDevice.jsp
                
                request.getRequestDispatcher("updateDeviceConfirmation.jsp").include(request, response);
            
          } catch (SQLException ex) {
                //catch any exception
                session.setAttribute("exceptionErr", "Registration failed");
                request.getRequestDispatcher("editDevice.jsp").include(request, response);
            }

        }

    }
    }

