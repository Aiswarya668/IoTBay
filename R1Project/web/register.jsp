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

    <title>Registration Page</title>
    </head>
    <body>
         <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
         <p class="right"> Already registered? <a class ="button21" href="login.jsp">Login</a> </p>
                <div class="maincolumn2">
                     <div class="card">
        <h1>Create an account</h1>
        <form method="post" method="get" action="welcome.jsp">
            <table>
                <tr><td>Email</td><td><input type="text" placeholder="Enter email" name="Email"></td></tr>
                <tr><td>First Name</td><td><input type="text" placeholder="Enter first name" name="FirstName"></td></tr>
                <tr><td>Last Name</td><td><input type="text" placeholder="Enter last name" name="LastName"></td></tr>
                <tr><td>Password</td><td><input type="password" placeholder="Enter password" name="Password"></td></tr>
                <tr><td>Gender</td><td><input type="radio" id="male" name="gender" value="male"> <label for="male">Male</label><br> <input type="radio" id="female" name="gender" value="female"><label for="female">Female</label><br></td></tr>
                <tr><td>Phone Number</td><td><input type="text" placeholder="Enter phone number" name="PhoneNumber"></td></tr>   
                <tr><td>Unit Number</td><td><input type="text" placeholder="Enter unit number" name="UnitNumber"></td></tr>
                <tr><td>Street Address</td><td><input type="text" placeholder="Enter street address" name="StreetAddress"></td></tr>
                <tr><td>City</td><td><input type="text" placeholder="Enter city" name="City"></td></tr>
                <tr><td>State</td><td><input type="text" placeholder="Enter state" name="State"></td></tr>
                <tr><td>Postal Code</td><td><input type="text" placeholder="Enter post code" name="PostCode"></td></tr>
            </table>
            <input type="hidden" name="NewAccount" value="true" />
            <div>
                <input class ="button4" type="submit" value="Sign Up">
                 <a class ="button3" href="index.jsp">Cancel</a>
            </div>
        </form>
        
                </div>
            </div>
    </body>
</html>