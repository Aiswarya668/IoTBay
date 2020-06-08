/*
 * Servlet used throughout IoTBay to display the devices 
 * Functionalities change is customer is registered or anonymous or if staff 
 */
package uts.isd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ViewDeviceServletUsers extends HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       //Capture all fields 
       
        //1) capture the posted deviceID    
        String deviceID = request.getParameter("DeviceID"); 
              
        //2) capture the posted deviceName    
        String deviceName = request.getParameter("DeviceName");
        
        //3) capture the posted deviceType  
        String type = request.getParameter("DeviceType");
        
        //4) capture the posted deviceCost - parse as a double as the input is a string in form  
        String cost = (request.getParameter("DeviceCost"));
        
        //5) capture the posted stockQuantity - parse as a int as the input is a string in form  
        String stockQuantity = (request.getParameter("DeviceStock"));
        
        //6) capture the posted description   
        String description = request.getParameter("DeviceDescription");
                    
        try {
           //7) retrieve the current session 
            HttpSession session = request.getSession();
            //8) create an instance of the Validator class to check inputs
            Validator validator = new Validator();
        
            //9) retrieve the manager instance from session - ConnServlet            
             DBDeviceManager deviceManager = (DBDeviceManager)session.getAttribute("deviceManager");
                    
            //10) fetch all devices from deviceManager and assign to ArrayList 
            ArrayList<Device> display = deviceManager.fetchDevice();
            
            //11) setAttribute as display to show on browseCatalogue 
            request.setAttribute("display",display);     
            
            //12) redirect to browseCatalogue jsp 
            request.getRequestDispatcher("browseCatalogueUsers.jsp").include(request, response);
            
       } catch (SQLException ex) {
           Logger.getLogger(ViewDeviceServletUsers.class.getName()).log(Level.SEVERE, null, ex);
       }
      
   }
   
}
