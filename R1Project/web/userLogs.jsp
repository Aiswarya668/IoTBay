<%-- 
    Document   : edit
    Created on : Apr 30, 2020, 12:01:31 AM
    Author     : aiswaryarajeev
--%>

<%@page import="uts.isd.model.ApplicationAccessLogs"%>
<%@page import="java.util.ArrayList"%>
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
        <jsp:include page="./UserLogsServlet" flush="true" />
        <%
            Customer customer = (Customer) session.getAttribute("customer");
            String noLogsErr = (String) session.getAttribute("noLogsErr");
            ArrayList<ApplicationAccessLogs> logs = 
                    (ArrayList<ApplicationAccessLogs>) session.getAttribute("logs");
        %>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left" />
        <p class="right"> <a class="button21" href="main.jsp">Main</a> </p>
        <p class="right"> <a class="button21" href="logout.jsp">Logout</a> </p>
        <div class="maincolumn2">
                <div class="card">
                        <h1>Logs for ${customer.firstName}</h1>
                        <input type="search" placeholder="Search..">
                        <form method="post" action="UserLogsServlet">
                                <table>
                                        <tr>
                                                <td>ID</td>
                                                <td>Customer Email</td>
                                                <td>Staff Email</td>
                                                <td>Timestamp</td>
                                                <td>Log Description</td>
                                        </tr>
                                        <% if (logs != null && !logs.isEmpty()) {
                                        for (ApplicationAccessLogs log : logs) { %>
                                        <tr>
<!--                                                <td>${log.getAccessLogID()}</td>
                                                <td>${log.getCustomerEmail()}</td>
                                                <td>${log.getStaffEmail()}</td>
                                                <td>${log.getTimestamp()}</td>
                                                <td>${log.getLogDescription()}</td>-->
                                            <td><% log.getAccessLogID(); %></td>
                                            <td><% log.getCustomerEmail(); %></td>
                                            <td><% log.getStaffEmail(); %></td>
                                            <td><% log.getTimestamp().toString(); %></td>
                                            <td><% log.getLogDescription(); %></td>
                                        </tr>
                                        <% } } 
                                    else { %>
                                        <p><%=(noLogsErr != null ? noLogsErr : "")%></p>
                                        <% } %>
                                </table>
<!--                                <div>
                                        <input class="button21" type="submit" name="Deactivate"
                                                onclick="return confirm('Are you sure you want to deacivate your account?')"
                                                value="Deactivate" </a>
                                        <input class="button3" type="submit" value="Update" </a>
                                </div>-->
                        </form>
                </div>
        </div>
</body>

</html>