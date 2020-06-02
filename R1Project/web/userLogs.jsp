<%-- 
    Document   : edit
    Created on : Apr 30, 2020, 12:01:31 AM
    Author     : aiswaryarajeev
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="uts.isd.model.ApplicationAccessLogs"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>User Logs Page</title>
</head>

<body>
        <%
            Customer customer = (Customer) session.getAttribute("customer");
            Staff staff = (Staff) session.getAttribute("staff");
            String logResult = (String) session.getAttribute("logResult");
            String noLogsErr = (String) session.getAttribute("noLogsErr");
            
            String name = "";
            
            if (customer != null) {
                name = customer.getFirstName();
            }
            else if (staff != null) {
                name = staff.getFirstName();
            }
            
            ArrayList<ApplicationAccessLogs> logs = 
                    (ArrayList<ApplicationAccessLogs>) session.getAttribute("logs");
        %>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left" />
        <p class="right"> <a class="button21" href="main.jsp">Main</a> </p>
        <p class="right"> <a class="button21" href="logout.jsp">Logout</a> </p>
        <div id="logsmaincolumn">
                <div class="card">
                        <h1>Logs for <%= name %></h1>
                        <form method="post" method="get" action="UserLogsServlet">
                                <label>
                                        Search from:
                                <input type="date" name="FromDate">
                                <label>
                                        to:
                                <input type="date" name="ToDate">
                                <input class="button3" type="submit" value="Filter" </a>
                                <p><%= (logResult != null ? logResult : "") %></p>
                                <table>
                                    <% if (logs != null && (noLogsErr == null || noLogsErr.equals(""))) { %>
                                        <thead>
                                                <tr>
                                                        <th>ID</th>
                                                        <% if (customer != null) { %>
                                                        <th>Customer Email</th>
                                                        <% } else if (staff != null) { %>
                                                        <th>Staff Email</th>
                                                        <% } %>
                                                        <th>Timestamp</th>
                                                        <th>Log Description</th>
                                                </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${logs}" var="log">
                                                    <tr>
                                                            <td>${log.getAccessLogID()}</td>
                                                            <% if (customer != null) { %>
                                                            <td>${log.getCustomerEmail()}</td>
                                                            <% } else if (staff != null) { %>
                                                            <td>${log.getStaffEmail()}</td>
                                                            <% } %>
                                                            <td>${log.getTimestamp()}</td>
                                                            <td>${log.getLogDescription()}</td>
                                                    </tr>
                                            </c:forEach>
                                        </tbody>
                                        <% }
                                    else { %>
                                        <p><%=(noLogsErr != null ? noLogsErr : "")%></p>
                                        <% } %>
                                </table>
                        </form>
                </div>
        </div>
</body>

</html>