package uts.isd.controller;
import uts.isd.model.iotbay.dao.*;
import uts.isd.model.iotbay.dao.DB;
import uts.isd.model.iotbay.dao.DBConnector;
import uts.isd.model.iotbay.dao.DBDeviceManager;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.servlet.*;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;



/**
 *
 * @author aiswarya.r
 *  


public class DeviceServlet extends HttpServlet {
    
    //create a database connection
    private DBConnector db;
    //share a database manager - device
    private DBDeviceManager deviceManager;
    private Connection conn;
    
    
    //starts as soon as browseCatalogue is loaded and creates an instance of DBConnector
    @Override
    public void init(){
        try{
            //creates a database connection 
            db = new DBConnector();
        }
        catch (ClassNotFoundException | SQLException ex){
            Logger.getLogger(DeviceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // captures the current session 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        //creates connection to db
        conn = db.openConnection();
        try{
            //creates deviceDBmanager 
            deviceManager = new DBDeviceManager(conn);
        }
        catch (SQLException ex){
            Logger.getLogger(DeviceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("deviceManager", deviceManager);
    }
    
    @Override
    public void destroy(){
        try{
            db.closeConnection();
        }
        catch (SQLException ex){
            Logger.getLogger(DeviceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
 */