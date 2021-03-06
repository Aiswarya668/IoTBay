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

        //3) Capture all the posted fields 
       
        //4) capture the posted deviceName    
        String deviceName = request.getParameter("DeviceName");

        //5) capture the posted deviceType  
        String type = request.getParameter("DeviceType");

        //6) capture the posted deviceCost - parse as a double as the input is a string in form at bottom
        String cost = (request.getParameter("DeviceCost"));

        //7) capture the posted stockQuantity - parse as a int as the input is a string in form at bottom 
        String stockQuantity = (request.getParameter("DeviceStock"));

        //8) capture the posted description   
        String description = request.getParameter("DeviceDescription");

        //9) retrieve the manager instance from session - ConnServlet            
        DBDeviceManager deviceManager = (DBDeviceManager) session.getAttribute("deviceManager");
        
        //set device to null to check if exsists 
        Device device = null;
        //clear session 
        validator.clear(session);

        //Check if device exsists first
        try {
            device = deviceManager.findDevice(deviceName, type);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        //check if device is not null (if null = new device) 
        if (device != null) {
            //device name & type already exsists error 
            session.setAttribute("exceptionErr", "Device with that name and type already exists in the database please try again");
            // redirect back to addDevice
            request.getRequestDispatcher("addDevice.jsp").include(request, response);
        } 
        
        //validators
        //if any fields are empty?
        else if(validator.checkDeviceFieldsEmpty(deviceName,type,cost,stockQuantity,description)){
             session.setAttribute("deviceEmptyErr", "Error: All fields are mandatory!");
             request.getRequestDispatcher("addDevice.jsp").include(request, response);
        }
        else if (!validator.validateDeviceName(deviceName)) {
            //1- set incorrect name error to the session 
            session.setAttribute("deviceNameErr", "Error: Device name format incorrect");
            session.setAttribute("deletedeviceNameErr", "Error: Device name format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("addDevice.jsp").include(request, response);
       
        } else if (!validator.validateDeviceType(type)) {
            //1- set incorrect type error to the session 
            session.setAttribute("typeErr", "Error: Device type format incorrect");
            session.setAttribute("deletetypeErr", "Error: Device type format incorrect - 'Type' 'Sensor' ");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("addDevice.jsp").include(request, response);
       
        } else if (!validator.validateDeviceCost(cost)) {
            //1- set incorrect type error to the session 
            session.setAttribute("priceErr", "Error: Device price format incorrect");
            session.setAttribute("deletepriceErr", "Error: Device price format incorrect - $0.00");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("addDevice.jsp").include(request, response);
        
        } else if (!validator.validateDeviceStock(stockQuantity)) {
            //1- set incorrect type error to the session 
            session.setAttribute("stockErr", "Error: Device stock format incorrect");
            session.setAttribute("deletestockErr", "Error: Device stock format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("addDevice.jsp").include(request, response);
        
        } else if (!validator.validateDeviceDesc(description)) {
            //1- set incorrect type error to the session 
            session.setAttribute("descriptionErr", "Error: Device description format incorrect");
             session.setAttribute("deletedescriptionErr", "Error: Device description format incorrect");
            //2- redirect staff back to the addDevice.jsp     
            request.getRequestDispatcher("addDevice.jsp").include(request, response);
        
        } //if all passess then add the device
        
        else {
            try {
                //addDevice CRUD operation
                deviceManager.addDevice(deviceName, type, Double.parseDouble(cost), Integer.parseInt(stockQuantity), description);
                //set session attribute
                request.setAttribute("device", device);
                //send to createdDevice.jsp
                session.setAttribute("devicecreatedMsg", "Device was successfully created!");
                request.getRequestDispatcher("addDevice.jsp").include(request, response);
                
            } catch (SQLException ex) {
                //catch any exception
                session.setAttribute("exceptionErr", "Creation failed");
                request.getRequestDispatcher("addDevice.jsp").include(request, response);
            }

        }

    }

}
