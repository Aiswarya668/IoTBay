/*
 * Edit Device Servlet
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

        //4)Set device to null to see if exsists 
        Device device = null;
        
        //clear session 
        validator.clear(session);

        //Check if device exsists first 
        try {
            //Check if the ID is not a string as needs to be parsed in 
            if (validator.validateDeviceID(deviceID)) {
            device = deviceManager.findDeviceID(Integer.parseInt(deviceID));
            //If empty or string then redirect back to editDevice
            }else{
                 session.setAttribute("deletedeviceIDErr", "Error: Device ID cannot be edited!");
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        //check if device is not null (if null means that it is a new device) 
        if (device == null) {
            //device name & type don't exsists error 
            session.setAttribute("exceptionErr", "Device with that name and type does not exist in the database please try again");
            // redirect back to editDevice
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
        } 
        
       //validators
       
        //if any fields are empty?
        else if(validator.checkDeviceEmpty(deviceID, deviceName,type,cost,stockQuantity,description)){
             session.setAttribute("deviceEmptyErr", "Error: All fields are mandatory!");
             request.getRequestDispatcher("editDevice.jsp").include(request, response);
        }
        else if (!validator.validateDeviceID(deviceID)) {
            //1- set incorrect name error to the session 
            session.setAttribute("deletedeviceIDErr", "Error: Device ID cannot be edited!");
        //    //2- redirect staff back to the editDevice   
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
        }else if (!validator.validateDeviceName(deviceName)) {
            //1- set incorrect name error to the session 
            session.setAttribute("deletedeviceNameErr", "Error: Device name format incorrect");
        //    //2- redirect staff back to the editDevice    
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
       
        } else if (!validator.validateDeviceType(type)) {
            //1- set incorrect type error to the session 
            session.setAttribute("deletetypeErr", "Error: Device type format incorrect");
            //2- redirect staff back to the editDevice
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
        
        } else if (!validator.validateDeviceCost(cost)) {
            //1- set incorrect type error to the session 
            session.setAttribute("deletepriceErr", "Error: Device price format incorrect");
            //2- redirect staff back to the editDevice 
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
        
        } else if (!validator.validateDeviceStock(stockQuantity)) {
            //1- set incorrect type error to the session 
            session.setAttribute("deletestockErr", "Error: Device stock format incorrect");
            //2- redirect staff back to the editDevice
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
        
        } else if (!validator.validateDeviceDesc(description)) {
            //1- set incorrect type error to the session 
            session.setAttribute("deletedescriptionErr", "Error: Device description format incorrect");
            //2- redirect staff back to the editDevice  
            request.getRequestDispatcher("editDevice.jsp").include(request, response);
        
        } //if all passess then edit the device
        else {
            try {
                //addDevice CRUD operation
                deviceManager.updateDevice(Integer.parseInt(deviceID), deviceName, type, Double.parseDouble(cost), Integer.parseInt(stockQuantity), description);
                //set session attribute
                Device updatedDevice = deviceManager.findDeviceID(Integer.parseInt(deviceID));
                session.setAttribute("device", updatedDevice);
                // success message if updating customer successful
                session.setAttribute("deviceupdateMsg", "Update was successful!");
                // redirect user to the editDevice
                request.getRequestDispatcher("editDevice.jsp").include(request, response);
                
            
          } catch (SQLException ex) {
                //catch any exception
                session.setAttribute("exceptionErr", "Registration failed");
                //redirec user to editDevice
                request.getRequestDispatcher("editDevice.jsp").include(request, response);
            }

        }

    }
 }

