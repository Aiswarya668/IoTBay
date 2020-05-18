<%-- 
    Document   : register
    Created on : 12 Apr 2020, 12:52:48 pm
    Author     : aiswarya.r
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Login Page</title>
    </head>
    <body>
         <img src="images/Logo.png" alt="LOGO" style="width:15%; height:10%" class="left"/>
                 <div class="maincolumn1">
                     <div class="card">
        <h1>Login</h1>
            <form method="post" method="get" action="welcome.jsp">
            <table>
                <tr><td>Email</td><td><input type="text" placeholder="Enter email" name="Email"></td></tr>
                <tr><td>Password</td><td><input type="password" placeholder="Enter password" name="Password"></td></tr>             
            </table>
            <div>
                <a class ="button3" href="index.jsp">Cancel</a> 
                <input class ="button4" type="submit" value="Sign in">
            </div>
        </form>
                     </div>
              </div>
    </body>
</html>