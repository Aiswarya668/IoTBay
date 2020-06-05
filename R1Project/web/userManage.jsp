<%-- 
    Document   : browseCatalogue
    Created on : 21/05/2020, 8:30:58 PM
    Author     : aiswarya.r
--%>

<%@page import="uts.isd.model.Staff"%>
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
    <%
        SQLException userDeleteErr = (SQLException) session.getAttribute("userDeleteErr");
    %>

    <body>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        <div class="maincolumn2-2">
            <div class="card">
                <h1>User Management</h1>
                <span>${userDeleteErr}</span>
                <div>
                    <i class="material-icons global-search-icon">&#xE8B6;</i> 
                    <select id="inputType" onchange="filterTable()" >
                        <option value="all">All</option>
                        <option value="customer">Customers only</option>
                        <option value="staff">Staff only</option>
                    </select>
                    <input type="text" id="inputFName" class="searchbox" onkeyup="filterTable()" placeholder="First name" title="Type in a name">
                    <input type="text" id="inputLName" class="searchbox" onkeyup="filterTable()" placeholder="Last name.." title="Type in a name">
                    <input type="text" id="inputPhone" class="searchbox" onkeyup="filterTable()" placeholder="Phone number" title="Type in a name">
                    <form method="get" action="RegisterServlet">
                        <input type="hidden" name="userType" value="customer" />
                        <button class="button4" type="submit">Add New Customer</button>
                    </form>
                    <form method="get" action="RegisterServlet">
                        <input type="hidden" name="userType" value="staff" />
                        <button class="button4" type="submit">Add New Staff</button>
                    </form>
                </div>
                <div class="table-wrapper">
                    <table id="userTable">
                        <thead>
                            <tr><td><h2>Customers</h2></td></tr>
                            <tr>
                                <th>Type</th>
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
                                    <td>Customer</td>
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
                                    <td>
                                        <form method="get" action="EditServlet">
                                            <input type="hidden" name="userEmail" value="${c.getEmail()}" />
                                            <input type="hidden" name="userType" value="customer" />
                                            <!--<input class="button4" type="submit" value="Delete">-->
                                            <button type="submit"><i class="material-icons">&#xE254;</i></button>
                                        </form>
                                    </td>
                                    <td>
                                        <form method="post" action="UserDeleteServlet">
                                            <input type="hidden" name="customerEmail" value="${c.getEmail()}" />
                                            <!--<input class="button4" type="submit" value="Delete">-->
                                            <button type="submit"><i class="material-icons">&#xE872;</i></button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <thead>
                            <tr><td><h2>Staff</h2></td></tr>
                            <tr>
                                <th>Type</th>
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
                                <th>Active</th>
                                <th>Manager</th>
                                <th>Contract Type</th>
                                <th>Pay per hour</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${staffs}" var="s">
                                <tr>
                                    <td>Staff</td>
                                    <td>${s.getEmail()}</td>
                                    <td>${s.getFirstName()}</td>
                                    <td>${s.getLastName()}</td>
                                    <td>${s.getPhoneNumber()}</td>
                                    <td>${s.getPassword()}</td>
                                    <td>${s.getStreetAddress()}</td>
                                    <td>${s.getUnitNumber()}</td>
                                    <td>${s.getCity()}</td>
                                    <td>${s.getState()}</td>
                                    <td>${s.getPostcode()}</td>
                                    <td>${s.isLoginStatus()}</td>
                                    <td>${s.getDateRegistered()}</td>
                                    <td>${s.isActive()}</td>
                                    <td>${s.getManager()}</td>
                                    <td>${s.getContractType()}</td>
                                    <td>${s.getHourlyPay()}</td>
                                    <td>
                                        <form method="get" action="EditServlet">
                                            <input type="hidden" name="userEmail" value="${s.getEmail()}" />
                                            <input type="hidden" name="userType" value="staff" />
                                            <!--<input class="button4" type="submit" value="Edit">-->
                                            <button type="submit"><i class="material-icons">&#xE254;</i></button>
                                        </form>
                                    </td>
                                    <td>
                                        <form method="post" action="UserDeleteServlet">
                                            <input type="hidden" name="staffEmail" value="${s.getEmail()}" />
                                            <!--<input class="button3" type="submit" value="Delete">-->

                                            <button type="submit"><i class="material-icons">&#xE872;</i></button>
                                        </form>
                                        <!--<td><a class="button2" href="edit.jsp"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a></td>-->
                                        <!--<td><a class="button3" href="edit.jsp"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a></td>-->
                                        <!--<a href="#editEmployeeModal" class="edit"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>-->
                                        <!--<a href="#deleteEmployeeModal" class="delete"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>-->
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <script>
                    function filterTable() {
                        var input, filterFName, filterLName, filterPhone, filterType, table, tr, tdFName, tdLName, tdPhone, tdType, i, txtFName;
                        input = document.getElementById("inputFName");
                        filterFName = input.value.toUpperCase();
                        input = document.getElementById("inputLName");
                        filterLName = input.value.toUpperCase();
                        input = document.getElementById("inputPhone");
                        filterPhone = input.value.toUpperCase();
                        input = document.getElementById("inputType");
                        filterType = input.value.toUpperCase();
                        table = document.getElementById("userTable");
                        tr = table.getElementsByTagName("tr");
                        for (i = 0; i < tr.length; i++) {
                            tdType = tr[i].getElementsByTagName("td")[0];
                            tdFName = tr[i].getElementsByTagName("td")[2];
                            tdLName = tr[i].getElementsByTagName("td")[3];
                            tdPhone = tr[i].getElementsByTagName("td")[4];
                            if (tdFName && tdLName) {
                                txtType = tdType.textContent || tdType.innerText;
                                txtFName = tdFName.textContent || tdFName.innerText;
                                txtLName = tdLName.textContent || tdLName.innerText;
                                txtPhone = tdPhone.textContent || tdPhone.innerText;
                                if (filterType === "CUSTOMER" || filterType === "STAFF") {
                                    if (txtType.toUpperCase().indexOf(filterType) > -1 && txtFName.toUpperCase().indexOf(filterFName) > -1 && txtLName.toUpperCase().indexOf(filterLName) > -1 && txtPhone.toUpperCase().indexOf(filterPhone) > -1) {
                                        tr[i].style.display = "";
                                    } else {
                                        tr[i].style.display = "none";
                                    }
                                } else {
                                    if (txtFName.toUpperCase().indexOf(filterFName) > -1 && txtLName.toUpperCase().indexOf(filterLName) > -1 && txtPhone.toUpperCase().indexOf(filterPhone) > -1) {
                                        tr[i].style.display = "";
                                    } else {
                                        tr[i].style.display = "none";
                                    }
                                }
                            }
                        }
                    }
                </script>
            </div>
        </div>
    </body>
</html>
