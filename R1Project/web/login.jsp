<%-- 
    Document   : register
    Created on : 12 Apr 2020, 12:52:48 pm
    Author     : aiswarya.r
--%>
<%@page import="uts.isd.controller.ConnServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Login Page</title>
    </head>
    <%
        String emailErr = (String) session.getAttribute("emailErr");
        String passErr = (String) session.getAttribute("passErr");
        String existErr = (String) session.getAttribute("existErr");
    %>
    <body>
        <img src="images/Logo.png" alt="LOGO" style="width:15%; height:10%" class="left"/>
        <div class="maincolumn1">
            <div class="card">
                <h1>Login</h1>
                <form method="post" method="get" action="LoginServlet">
                    <table>
                        <tr><td>Email</td><td>
                            <input type="text" 
                                placeholder="<%=(emailErr != null ? emailErr : "Enter email")%>"
                                name="Email"></td></tr>
                        <tr><td>Password</td><td>
                            <input type="password" 
                                placeholder="<%=(passErr != null ? passErr : "Enter password")%>"
                                name="Password"></td></tr>             
                    </table>
                    <input type="hidden" name="NewAccount" value="false" />
                    <div>
                        <a class ="button3" href="index.jsp">Cancel</a> 
                        <input class ="button4" type="submit" value="Sign in">
                    </div>
                    <p><%=(existErr != null ? existErr : "")%></p>
                </form>
            </div>
        </div>
    </body>
</html>