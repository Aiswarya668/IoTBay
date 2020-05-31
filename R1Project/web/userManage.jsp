<%-- 
    Document   : browseCatalogue
    Created on : 21/05/2020, 8:30:58 PM
    Author     : aiswarya.r
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="uts.isd.model.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.model.iotbay.dao.*"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
              rel="stylesheet">
        <title>User Management</title>
    </head>

    <body>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        <div class="maincolumn2-2">
            <div class="card">
                <%
                    DBConnector dbConnector = new DBConnector();
                    Connection conn = dbConnector.openConnection();
                    DBCustomerManager dbManager = new DBCustomerManager(conn);
                    List<Customer> customers = dbManager.fetchCustomers();
                    request.setAttribute("customers", customers);
                %>
                <div>
                    <h1>User Management</h1>
                    <i class="material-icons global-search-icon">&#xE8B6;</i>

                    <input type="text" id="inputFName" class="searchbox" onkeyup="myFunction()" placeholder="First name" title="Type in a name">
                    <input type="text" id="inputLName" class="searchbox" onkeyup="myFunction()" placeholder="Last name.." title="Type in a name">
                    <input type="text" id="inputPhone" class="searchbox" onkeyup="myFunction()" placeholder="Phone number" title="Type in a name">
                    <a class="button1" href="register.jsp">Add New</a>
                </div>

                <div class="table-wrapper">
                    <table id="userTable">
                        <thead>
                            <tr>
                                <th>Email</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Phone number</th>
                                <th>Password</th>
                                <th>Street address</th>
                                <th>Unit number</th>
                                <th>City</th>
                                <th>State</th>
                                <th>Postcode</th>
                                <th>Login status</th>
                                <th>Register date</th>
                                <th>Gender</th>
                                <th>Active</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${customers}" var="c">
                                <tr>
                                    <td>${c.getEmail()}</td>
                                    <td>${c.getFirstName()}</td>
                                    <td>${c.getLastName()}</td>
                                    <td>${c.getPhoneNumber()}</td>
                                    <td>${c.getPassword()}</td>
                                    <td>${c.getStreetAddress()}</td>
                                    <td>${c.getUnitNumber()}</td>
                                    <td>${c.getCity()}</td>
                                    <td>${c.getState()}</td>
                                    <td>${c.getPostcode()}</td>
                                    <td>${c.isLoginStatus()}</td>
                                    <td>${c.getDateRegistered()}</td>
                                    <td>${c.getGender()}</td>
                                    <td>${c.isActive()}</td>
                                    <!--                                    <td><a class="button2" href="edit.jsp"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a></td>
                                                                        <td><a class="button3" href="edit.jsp"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a></td>-->
                                    <td>
                                        <a href="#editEmployeeModal" class="edit"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="#deleteEmployeeModal" class="delete"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <script>
                    function myFunction() {
                        var input, filterFName, table, tr, tdFName, tdLName, tdPhone, i, txtFName;
                        input = document.getElementById("inputFName");
                        filterFName = input.value.toUpperCase();
                        input = document.getElementById("inputLName");
                        filterLName = input.value.toUpperCase();
                        input = document.getElementById("inputPhone");
                        filterPhone = input.value.toUpperCase();
                        table = document.getElementById("userTable");
                        tr = table.getElementsByTagName("tr");
                        for (i = 0; i < tr.length; i++) {
                            tdFName = tr[i].getElementsByTagName("td")[1];
                            tdLName = tr[i].getElementsByTagName("td")[2];
                            tdPhone = tr[i].getElementsByTagName("td")[3];
                            if (tdFName && tdLName) {
                                txtFName = tdFName.textContent || tdFName.innerText;
                                txtLName = tdLName.textContent || tdLName.innerText;
                                txtPhone = tdPhone.textContent || tdPhone.innerText;
                                if (txtFName.toUpperCase().indexOf(filterFName) > -1 && txtLName.toUpperCase().indexOf(filterLName) > -1 && txtPhone.toUpperCase().indexOf(filterPhone) > -1) {
                                    tr[i].style.display = "";
                                } else {
                                    tr[i].style.display = "none";
                                }
                            }
                        }
                    }
                </script>

            </div>
        </div>
    </body>
</html>
