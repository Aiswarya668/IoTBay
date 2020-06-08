
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
 * @author aiswarya.r
 */

//This servlet checks to see if the device exsists before allowing for the operation to delete 

public class DeleteDeviceServlet extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1) retrieve the current session
        HttpSession session = request.getSession();
        
        //2) create an instance of the Validator class
        Validator validator = new Validator();
        
        //3) capture the posted parameters/info fields 
        
        //capture deviceID field
        String deviceID = request.getParameter("DeviceID");
        
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
               
        //5) set device to null to see if device exists
        Device device = null;
        validator.clear(session);

        try {
             //Check if the ID is not a string as needs to be parsed in 
            if (validator.validateDeviceID(deviceID) == true) {
            device = deviceManager.findDeviceID(Integer.parseInt(deviceID));
             //If empty or still redirect back to delete.jsp
            }else{
                 session.setAttribute("deletedeviceIDErr", "Error: Device ID cannot be edited!");
            }
       
       //if device doesn't exsist after findDevice
        if (device == null) {
            //send device name + type already exsists error 
            session.setAttribute("exceptionErr", "Device with that name, type and ID does not exsist");
            //reload the deleteDevice.jsp
            request.getRequestDispatcher("deleteDevice.jsp").include(request, response);    
        } 
        
        //validators
       
        //if any fields are empty
        else if(validator.checkDeviceEmpty(deviceID, deviceName,type,cost,stockQuantity,description)){
             session.setAttribute("deviceEmptyErr", "Error: All fields are mandatory!");
             request.getRequestDispatcher("deleteDevice.jsp").include(request, response);
        }  else if (!validator.validateDeviceID(deviceID)) {
            //1- set incorrect name error to the session 
            session.setAttribute("deletedeviceIDErr", "Error: Device ID cannot be edited!");
            //2- redirect staff back to the editDevice   
            request.getRequestDispatcher("deleteDevice.jsp").include(request, response);
        }
        else if (!validator.validateDeviceName(deviceName)) {
            //1- set incorrect name error to the session 
            session.setAttribute("deletedeviceNameErr", "Error: Device name format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("deleteDevice.jsp").include(request, response);
       
        } else if (!validator.validateDeviceType(type)) {
            //1- set incorrect type error to the session 
            session.setAttribute("deletetypeErr", "Error: Device type format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("deleteDevice.jsp").include(request, response);
        
        } else if (!validator.validateDeviceCost(cost)) {
            //1- set incorrect type error to the session 
            session.setAttribute("deletepriceErr", "Error: Device price format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("deleteDevice.jsp").include(request, response);
        
        } else if (!validator.validateDeviceStock(stockQuantity)) {
            //1- set incorrect type error to the session 
            session.setAttribute("deletestockErr", "Error: Device stock format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("deleteDevice.jsp").include(request, response);
        
        } else if (!validator.validateDeviceDesc(description)) {
            //1- set incorrect type error to the session 
            session.setAttribute("deletedescriptionErr", "Error: Device description format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("deleteDevice.jsp").include(request, response);
        
        } //if all passess then delete the device
        
        else {
            // if every condition is met - deleting device
            deviceManager.deleteDevice(Integer.parseInt(deviceID));
            Device deleteddDevice = deviceManager.findDeviceID(Integer.parseInt(deviceID));
            session.setAttribute("device", deleteddDevice);
                // success message if updating customer successful
            session.setAttribute("deviceDeletedMsg", "Device was successfully deleted!");
                // redirect user to the edit.jsp
            request.getRequestDispatcher("deleteDevice.jsp").include(request, response);
                
            }
        }   catch (SQLException ex) {
                // exception message if updating customer fails
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }
    }

