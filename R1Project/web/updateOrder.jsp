<%-- 
    Document   : createOrder
    Created on : 30/05/2020, 4:22:02 PM
    Author     : sanyadua
--%>

<%@page import="uts.isd.model.CustomerOrder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.Device"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    // Get device that is to buy

    // Get current device
    System.out.println("Ayo------------>s");
    CustomerOrder updateOrder = (CustomerOrder) session.getAttribute("updateOrder");
    System.out.println("Update-------------------->" + updateOrder.toString());
    ArrayList<String> errors = (ArrayList<String>) session.getAttribute("orderErrors");
    String orderIDToBeupdated = (String) session.getAttribute("orderIdTobeUpdated");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IoTBay | Update Order</title>

        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/btns.css" rel="stylesheet" type="text/css"/>
        <style>
            .iot-center {
                display:flex; 
                align-items: center; 
                justify-content: center;
            }
        </style>
    </head>
    <body>
        <div class="">

            <a href="/">
                <img src="images/Logo.png" alt="LOGO" style="width:15%; height:10%" class="left"/>
            </a>


            <hr/>
            <div class="container">
                <p class="lead">Update Your Order Details </p>
                <a href="/orderHistory.jsp">Go Back</a>
                <%for (String error : errors) {%><p class="alert alert-danger"><%=error%></p><%}%>

                <form method="post">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>Amount</label>
                                <input class="form-control" type="text" name="amount" value="">
                            </div>


                            <p class="lead">Delivery Address Details</p>
                            <hr/>
                            <div class="row">
                                <div class="col-md-4">
                                    <label>Unit Number</label>
                                    <input class="form-control" 
                                           type="text" 
                                           name="unitnumber" value="<%= updateOrder.getUnitNumber()%>" 
                                           />
                                </div>
                                           
                                           <input type="hidden" name="id" value="<%= orderIDToBeupdated %>" />
                                <div class="col-md-8">
                                    <label>Street Address</label>
                                    <input 
                                        class="form-control" 
                                        type="text" 
                                        name="streetaddress" 
                                        value="<%= updateOrder.getStreetAddress()%>" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <label>City</label>
                                    <input class="form-control" 
                                           type="text" 
                                           name="city" value="<%= updateOrder.getCity()%>" 
                                           />
                                </div>
                                <div class="col-md-4">
                                    <label>State</label>
                                    <input 
                                        class="form-control" 
                                        type="text" 
                                        name="state" 
                                        value="<%= updateOrder.getState()%>" />
                                </div>
                                <div class="col-md-4">
                                    <label>Post Code</label>
                                    <input 
                                        class="form-control" 
                                        type="text" 
                                        name="postcode" 
                                        value="<%= updateOrder.getPostalCode()%>" />
                                </div>
                            </div>
                        </div>
                    </div> 
                    <div  style="display: flex; align-items:center; justify-content: flex-end; margin-top:2rem;">
                        <input name="id" type="hidden" value="${request.getAttribtue("id")}"/>
                        <input name="action" type="submit" value="Update"  class="btn btn-success"/>
                    </div>
                </form>
            </div>
        </div>

    </body>
</html>
