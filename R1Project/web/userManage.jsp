<%-- 
    Document   : browseCatalogue
    Created on : 21/05/2020, 8:30:58 PM
    Author     : aiswarya.r
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.model.Device"%>
<%@page import="uts.isd.model.iotbay.dao.*"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>User Management</title>

    <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
    <div class="maincolumn2">
        <div class="card">

            </head>
            <body>

                <%
                    DBConnector dbConnector = new DBConnector();
                    Connection conn = dbConnector.openConnection();
                    DBDeviceManager dbManager = new DBDeviceManager(conn);
                    dbManager.fetchDevice();

                %>

                <h1>User Management</h1>

                <%-- 
                Note that this is a test 
                
                
                <%!
                    public class Device {
                     String url = "jdbc:derby://localhost:1527/iot_db";
                     String dbuser = "iotbayUser";
                     String dbpass = "Group27";

             Connection conn = null;
             PreparedStatement sd = null;
             ResultSet rs = null;

             public Device(){
             
                try {
                    conn = DriverManager.getConnection(url, dbuser, dbpass);
                    sd = conn.prepareStatement("SELECT deviceID, deviceName, type, cost, stockQuantity, description from DEVICE");

                } catch(SQLException e){
                    e.printStackTrace();
                }
            
                }
              
                
                
            }
        %>
                --%>

                <form method="post" method="get">
                    <table>
                        <tr>
                            <td>Device Name</td>
                            <td>Type</td>
                            <td>Cost</td>
                            <td>Stock</td>
                            <td>Description</td>
                        </tr>
                    </table>
                </form>
            </body>
        </div>
    </div>
</html>
