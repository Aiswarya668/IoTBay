
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

public class DeviceCreationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1) retrieve the current session
        HttpSession session = request.getSession();
        
        //2) create an instance of the Validator class to check inputs
        Validator validator = new Validator();
        
        //3) deviceID is defined for the creation of the device   
        int deviceID = 0; // Just default value assigned 
            try {
                deviceID = Integer.parseInt(request.getParameter("DeviceID"));
                } catch (NumberFormatException e) {
                    // log the error or ignore it
                }
            
        try {
        //4) capture the posted deviceName    
        String deviceName = request.getParameter("DeviceName");
        
        //5) capture the posted deviceType  
        String type = request.getParameter("DeviceType");
        
        //6) capture the posted deviceCost - parse as a double as the input is a string in form  
        String cost = (request.getParameter("DeviceCost"));
        //double cost = Double.parseDouble(request.getParameter("DeviceCost"));
        
        //7) capture the posted stockQuantity - parse as a int as the input is a string in form  
        String stockQuantity = (request.getParameter("DeviceStock"));
        //int stockQuantity = Integer.parseInt(request.getParameter("DeviceStock"));
        
        //8) capture the posted description   
        String description = request.getParameter("DeviceDescription");
                    
        //9) retrieve the manager instance from session - ConnServlet            
        DBDeviceManager deviceManager = (DBDeviceManager)session.getAttribute("deviceManager");
        validator.clear(session);
        
        if (!validator.validateDeviceName(deviceName)) {
            //1- set incorrect email error to the session 
            session.setAttribute("deviceNameErr", "Error: Device name format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("addDevice.jsp").include(request, response);
        } 
        else if (!validator.validateDeviceType(type)) {
            //1- set incorrect type error to the session 
            session.setAttribute("typeErr", "Error: Device type format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("addDevice.jsp").include(request, response);
        } 
        else if(!validator.validateDeviceCost(cost)) {
            //1- set incorrect type error to the session 
            session.setAttribute("priceErr", "Error: Device price format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("addDevice.jsp").include(request, response);
        } 
        else if(!validator.validateDeviceStock(stockQuantity)) {
            //1- set incorrect type error to the session 
            session.setAttribute("stockErr", "Error: Device stock format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("addDevice.jsp").include(request, response);
        } 
        else if(!validator.validateDeviceDesc(description)) {
            //1- set incorrect type error to the session 
            session.setAttribute("descriptionErr", "Error: Device description format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("addDevice.jsp").include(request, response);
        } 

        else {
        Device device = new Device(deviceID, deviceName, type, Double.parseDouble(cost), Integer.parseInt(stockQuantity), description );
        deviceManager.addDevice(deviceName, type, Double.parseDouble(cost), Integer.parseInt(stockQuantity), description);      
        request.getRequestDispatcher("createdDevice.jsp").include(request, response);
        session.setAttribute("device", device);
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

   

}
