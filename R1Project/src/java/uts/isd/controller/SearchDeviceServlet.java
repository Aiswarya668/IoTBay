
package uts.isd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.*;
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
public class SearchDeviceServlet extends HttpServlet {

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
      
        
        //Create object of printwriter - and calling response of getWriter()
        //PrintWriter out =response.getWriter();
       
         //1) deviceID is defined for the creation of the device   
        int deviceID = 0; // Just default value assigned 
            try {
                deviceID = Integer.parseInt(request.getParameter("DeviceID"));
                } catch (NumberFormatException e) {
                    // log the error or ignore it
                }
            
        
        //2) capture the posted deviceName    
        String deviceName = request.getParameter("DeviceName");
        
        //3) capture the posted deviceType  
        String type = request.getParameter("DeviceType");
        
        //4) capture the posted deviceCost - parse as a double as the input is a string in form  
        String cost = (request.getParameter("DeviceCost"));
        //double cost = Double.parseDouble(request.getParameter("DeviceCost"));
        
        //5) capture the posted stockQuantity - parse as a int as the input is a string in form  
        String stockQuantity = (request.getParameter("DeviceStock"));
        //int stockQuantity = Integer.parseInt(request.getParameter("DeviceStock"));
        
        //6) capture the posted description   
        String description = request.getParameter("DeviceDescription");
                    
        try {
           //1) retrieve the current session 
            HttpSession session = request.getSession();
        
            //2) create an instance of the Validator class to check inputs
            Validator validator = new Validator();
        
            //9) retrieve the manager instance from session - ConnServlet            
             DBDeviceManager deviceManager = (DBDeviceManager)session.getAttribute("deviceManager");
        
            //validator.clear(session);
            
            
            ArrayList<Device> search = deviceManager.fetchDevice();
            request.setAttribute("search",search);             
            //request.getRequestDispatcher("browseCatalogue.jsp").include(request, response);
            
            
       } catch (SQLException ex) {
           Logger.getLogger(ViewDeviceServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        
        
       
        
        //session.setAttribute("device", device);
   }
}