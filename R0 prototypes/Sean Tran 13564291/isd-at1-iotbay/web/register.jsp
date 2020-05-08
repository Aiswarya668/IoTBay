<%-- 
    Document   : register
    Created on : 26/04/2020, 6:52:30 PM
    Author     : seant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/iotbay.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
        <title>Register</title>
    </head>
    <body>
        <div class="header">
            <jsp:include page="header.jsp" flush="true"/>
            <div class="navbar">
                <a href="index.jsp" style="float: left"><b>&lt;</b> Back to home</a>
            </div>
        </div>
        <div class="body">
            <h2>Register</h2>
            <div class="card">
                <form method="post" action="welcome.jsp">
                    <table>
                        <tr><td>First name</td><td><input type="text" placeholder="First name" name="fname"></td></tr>
                        <tr><td>Last name</td><td><input type="text" placeholder="Last name" name="lname"></td></tr>
                        <tr><td>Phone number</td><td><input type="text" placeholder="Phone number" name="phone"></td></tr>
                        <tr><td>Email</td><td><input type="text" placeholder="Email" name="email"></td></tr>
                        <tr><td>Password</td><td><input type="password" placeholder="Password" name="password"></td></tr>
                        <tr><td>Gender</td><td><div style="padding: 0.5em;">
                                <input type="radio" id="male" name="gender" value="Male">
                                <label for="male">Male</label><br>
                                <input type="radio" id="female" name="gender" value="Female">
                                <label for="female">Female</label><br>
                            </div></td></tr>
                        <tr><td>House/unit number</td><td><input type="text" placeholder="House/unit number" name="unitNo"></td></tr>
                        <tr><td>Street address</td><td><input type="text" placeholder="Street address" name="address"></td></tr>
                        <tr><td>City</td><td><input type="text" placeholder="City" name="city"></td></tr>
                        <tr><td>State</td><td>
                                <select id="state" name="state">
                                    <option value="NSW">NSW</option>
                                    <option value="NT">NT</option>
                                    <option value="QLD">QLD</option>
                                    <option value="SA">SA</option>
                                    <option value="TAS">TAS</option>
                                    <option value="VIC">VIC</option>
                                    <option value="WA">WA</option>
                                </select>
                        <tr><td>Postcode</td><td><input type="text" placeholder="Postcode" name="postcode"></td></tr>
                    </table>
                    <div style="float: right">
                        <button type="button" onclick="location.href = 'index.jsp'">Cancel</button> <input type="submit" value="Register">
                    </div>
                </form>
            </div>
        </div>
        <footer class="footer">
            Copyright © 2020 IoTBay. All rights reserved.<br><a id="topLink" href="./register.jsp#top">Back to Top</a>
        </footer>
    </body>
</html>
