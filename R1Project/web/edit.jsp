<%-- 
    Document   : edit
    Created on : Apr 30, 2020, 12:01:31 AM
    Author     : aiswaryarajeev
--%>

<%@page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Edit Page</title>
    </head>

    <body>
        <%
            String existErr = (String) session.getAttribute("existEditErr");
            String emailErr = (String) session.getAttribute("emailEditErr");
            String passErr = (String) session.getAttribute("passEditErr");
            String fNameErr = (String) session.getAttribute("fNameEditErr");
            String lNameErr = (String) session.getAttribute("lNameEditErr");
            String genderErr = (String) session.getAttribute("genderEditErr");
            String phoneErr = (String) session.getAttribute("phoneEditErr");
            String unitErr = (String) session.getAttribute("unitEditErr");
            String streetErr = (String) session.getAttribute("streetEditErr");
            String cityErr = (String) session.getAttribute("cityEditErr");
            String stateErr = (String) session.getAttribute("stateEditErr");
            String postErr = (String) session.getAttribute("postEditErr");
            String managerErr = (String) session.getAttribute("managerEditErr");
            String contractTypeErr = (String) session.getAttribute("contractTypeEditErr");
            String payHrErr = (String) session.getAttribute("payHrEditErr");

            boolean sysadmin = (session.getAttribute("sysadmin") != null);
            Customer customer = (Customer) session.getAttribute("customer");
            Staff staff = (Staff) session.getAttribute("staff");
            Staff s = null; // JSTL core tag to Java
            // common attributes amongst staff and customer
            String firstName = "";
            String lastName = "";
            String email = "";
            String password = "";
            String phoneNumber = "";
            String unitNumber = "";
            String streetAddress = "";
            String city = "";
            String state = "";
            String postCode = "";

            // customer specific
            String gender = "";

            // staff specific
            String manager = "";
            String contractType = "";
            String payHr = "";

            if (customer != null) {
                firstName = customer.getFirstName();
                lastName = customer.getLastName();
                email = customer.getEmail();
                password = customer.getPassword();
                gender = customer.getGender();
                phoneNumber = customer.getPhoneNumber();
                unitNumber = customer.getUnitNumber();
                streetAddress = customer.getStreetAddress();
                city = customer.getCity();
                state = customer.getState();
                postCode = customer.getPostcode();
            } else if (staff != null) {
                firstName = staff.getFirstName();
                lastName = staff.getLastName();
                email = staff.getEmail();
                password = staff.getPassword();
                phoneNumber = staff.getPhoneNumber();
                unitNumber = staff.getUnitNumber();
                streetAddress = staff.getStreetAddress();
                city = staff.getCity();
                state = staff.getState();
                postCode = staff.getPostcode();
                manager = staff.getManager();
                contractType = staff.getContractType();
                payHr = Integer.toString(staff.getHourlyPay());
            }
        %>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left" />
        <p class="right"> <a class="button21" href="main.jsp">Main</a> </p>
        <p class="right"> <a class="button21" href="logout.jsp">Logout</a> </p>
        <div class="maincolumn2">
            <div class="card">
                <h1>Edit profile</h1>
                <p><%= (existErr != null) ? existErr : ""%></p>
                <p><%=(emailErr != null ? emailErr : "")%></p>
                <p><%=(passErr != null ? passErr : "")%></p>
                <p><%=(fNameErr != null ? fNameErr : "")%></p>
                <p><%=(lNameErr != null ? lNameErr : "")%></p>
                <p><%=(phoneErr != null ? phoneErr : "")%></p>
                <p><%=(genderErr != null ? genderErr : "")%></p>
                <p><%=(unitErr != null ? unitErr : "")%></p>
                <p><%=(streetErr != null ? streetErr : "")%></p>
                <p><%=(cityErr != null ? cityErr : "")%></p>
                <p><%=(stateErr != null ? stateErr : "")%></p>
                <p><%=(postErr != null ? postErr : "")%></p>
                <p><%=(managerErr != null ? managerErr : "")%></p>
                <p><%=(contractTypeErr != null ? contractTypeErr : "")%></p>
                <p><%=(payHrErr != null ? payHrErr : "")%></p>
                <form method="post" method="get" action="EditServlet">
                    <table>
                        <tr>
                            <td>Email</td>
                            <td><input type="text" value="<%= email%>" name="Email"></td>
                        </tr>
                        <tr>
                            <td>First Name</td>
                            <td><input type="text" value="<%= firstName%>" name="FirstName">
                            </td>
                        </tr>
                        <tr>
                            <td>Last Name</td>
                            <td><input type="text" value="<%= lastName%>" name="LastName">
                            </td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password" value="<%= password%>" name="Password">
                            </td>
                        </tr>
                        <tr>
                            <td>Phone Number</td>
                            <td><input type="text" value="<%= phoneNumber%>"
                                       name="PhoneNumber"></td>
                        </tr>
                        <tr>
                            <td>Unit Number</td>
                            <td><input type="text" value="<%= unitNumber%>" name="UnitNumber">
                            </td>
                        </tr>
                        <tr>
                            <td>Street Address</td>
                            <td><input type="text" value="<%= streetAddress%>"
                                       name="StreetAddress"></td>
                        </tr>
                        <tr>
                            <td>City</td>
                            <td><input type="text" value="<%= city%>" name="City"></td>
                        </tr>
                        <tr>
                            <td>State</td>
                            <td><input type="text" value="<%= state%>" name="State"></td>
                        </tr>
                        <tr>
                            <td>Postal Code</td>
                            <td><input type="text" value="<%= postCode%>" name="PostCode"></td>   
                        </tr>
                        <% if (customer != null) { %>
                        <tr>
                            <td>Gender</td>
                            <td>
                                <input type="radio" id="Male" value="Male"
                                       name="Gender" value="Male" 
                                       <% if (customer.getGender().toLowerCase().equals("male")) { %>
                                       checked
                                       <% } %>
                                       <label for="Male">Male</label>
                                <br>
                                <input type="radio" id="Female" 
                                       <% if (customer.getGender().toLowerCase().equals("female")) { %>
                                       checked
                                       <% } %>
                                       name="Gender" value="Female">
                                <label for="Female">Female</label><br></td>
                        </tr>
                        <% } else if (staff != null) {%>
                        <tr>
                            <td>Manager</td>
                            <td>
                                <select name="Manager">
                                    <c:forEach items="${staffs}" var="s">
                                        <% s = (Staff) pageContext.getAttribute("s"); %>
                                        <option value="${s.getEmail()}" <% if (s.getEmail().equals(staff.getManager())) {%> selected <% }%>>${s.getEmail()}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Contract Type</td>
                            <td>
                                <select name="ContractType">
                                    <option value="Full Time" <% if (staff.getContractType().equals("Full Time")) {%> selected <% }%>>Full Time</option>
                                    <option value="Part Time" <% if (staff.getContractType().equals("Part Time")) {%> selected <% }%>>Part Time</option>
                                    <option value="Casual" <% if (staff.getContractType().equals("Casual")) {%> selected <% }%>>Casual</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Pay Per Hour</td> 
                            <td><input type="text" value="<%= payHr%>" name="PayHr"></td>
                        </tr>
                        <% }%>
                    </table>
                    <div>
                        <%
                            if (customer != null) {
                                if (customer.isActive()) {
                        %>
                        <input class="button21" type="submit" name="Deactivate"
                               value="Deactivate" </a>
                        <%
                        } else if (sysadmin) {
                        %>
                        <input class="button21" type="submit" name="Activate"
                               value="Activate" </a>
                        <%
                            }
                        } else if (staff != null) {
                            if (staff.isActive()) {
                        %>    
                        <input class="button21" type="submit" name="Deactivate"
                               value="Deactivate" </a>
                        <%
                        } else if (sysadmin) {
                        %>     
                        <input class="button21" type="submit" name="Activate"
                               value="Activate" </a>
                        <%
                                }
                            }
                        %>

                        <input class="button3" type="submit" value="Update" </a>
                    </div>
                </form>
            </div>
        </div>
    </body>

</html>