<%-- 
    Document   : edit
    Created on : Apr 30, 2020, 12:01:31 AM
    Author     : aiswaryarajeev
--%>

<%@page import="uts.isd.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Edit Page</title>
</head>

<body>
        <%
            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
            String fNameErr = (String) session.getAttribute("fNameErr");
            String lNameErr = (String) session.getAttribute("lNameErr");
            String genderErr = (String) session.getAttribute("genderErr");
            String phoneErr = (String) session.getAttribute("phoneErr");
            String unitErr = (String) session.getAttribute("unitErr");
            String streetErr = (String) session.getAttribute("streetErr");
            String cityErr = (String) session.getAttribute("cityErr");
            String stateErr = (String) session.getAttribute("stateErr");
            String postErr = (String) session.getAttribute("postErr");
            String exceptionErr = (String) session.getAttribute("exceptionErr");
            String updatedMsg = request.getParameter("updatedMsg");
            
            Customer customer = (Customer) session.getAttribute("customer");
        %>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left" />
        <p class="right"> <a class="button21" href="main.jsp">Main</a> </p>
        <p class="right"> <a class="button21" href="logout.jsp">Logout</a> </p>
        <div class="maincolumn2">
                <div class="card">
                        <h1>Edit profile <span> <%= (updatedMsg != null) ? updatedMsg : ""%> </span></h1>
                        <form method="post" method="get" action="EditServlet">
                                <table>
                                        <tr>
                                                <td>Email</td>
                                                <td><input type="text" value="${customer.email}" name="Email"></td>
                                        </tr>
                                        <tr>
                                                <td>First Name</td>
                                                <td><input type="text" value="${customer.firstName}" name="FirstName">
                                                </td>
                                        </tr>
                                        <tr>
                                                <td>Last Name</td>
                                                <td><input type="text" value="${customer.lastName}" name="LastName">
                                                </td>
                                        </tr>
                                        <tr>
                                                <td>Password</td>
                                                <td><input type="password" value="${customer.password}" name="Password">
                                                </td>
                                        </tr>
                                        <tr>
                                                <td>Gender</td>
                                                <td>
                                                        <input type="radio" id="Male" value="${customer.gender}"
                                                                name="Gender" value="Male">
                                                        <label for="Male">Male</label>
                                                        <br>
                                                        <input type="radio" id="Female" name="gender" value="Female">
                                                        <label for="Female">Female</label><br></td>
                                        </tr>
                                        <tr>
                                                <td>Phone Number</td>
                                                <td><input type="text" value="${customer.phoneNumber}"
                                                                name="PhoneNumber"></td>
                                        </tr>
                                        <tr>
                                                <td>Unit Number</td>
                                                <td><input type="text" value="${customer.unitNumber}" name="UnitNumber">
                                                </td>
                                        </tr>
                                        <tr>
                                                <td>Street Address</td>
                                                <td><input type="text" value="${customer.streetAddress}"
                                                                name="StreetAddress"></td>
                                        </tr>
                                        <tr>
                                                <td>City</td>
                                                <td><input type="text" value="${customer.city}" name="City"></td>
                                        </tr>
                                        <tr>
                                                <td>State</td>
                                                <td><input type="text" value="${customer.state}" name="State"></td>
                                        </tr>
                                        <tr>
                                                <td>Postal Code</td>
                                                <td><input type="text" value="${customer.postcode}" name="PostCode">
                                                </td>
                                        </tr>
                                </table>
                                <div>
                                        <input class="button21" type="submit" name="Deactivate"
                                                onclick="return confirm('Are you sure you want to deacivate your account?')"
                                                value="Deactivate" </a>
                                        <input class="button3" type="submit" value="Update" </a>
                                </div>
                        </form>
                </div>
        </div>

</body>

</html>