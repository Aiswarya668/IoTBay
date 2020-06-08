<%-- 
    Document   : createOrder
    Created on : 30/05/2020, 4:22:02 PM
    Author     : sanyadua
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.Device"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    // Get device that is to buy

    // Get current device
    Device theDevice = (Device) session.getAttribute("buyDevice");
    
    System.out.println("Ayyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
    // errors
    ArrayList<String> errors = (ArrayList<String>) session.getAttribute("orderErrors");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IoTBay | Create History</title>
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
                <a class="btn btn-xs btn-primary" href="ViewDeviceServletUsers">Go Back</a>
                <br>
                <br>
                <%for (String error : errors) {%><p class="alert alert-danger"><%=error%></p><%}%>

                <form method="post">
                    <div class="row">
                        <div class="col-md-6">
                            <p class="lead">Device Details</p>
                            <hr/>
                            <div class="form-group">
                                <label><b>Device Title</b></label>
                                <input 
                                    class="form-control" 
                                    type="text" 
                                    hidden="true"
                                    name="title" 
                                    value="<%= theDevice.getDeviceName()%>" 
                                    />
                                <p><%= theDevice.getDeviceName()%></p>
                            </div>
                            <div class="form-group">
                                <label><b>Device Description</b></label>
                                <input 
                                    class="form-control" 
                                    type="text" 
                                    name="description"
                                    hidden="true"                               
                                    />
                                <p><%= theDevice.getDescription()%></p>
                            </div>


                        </div>
                        <div class="col-md-6">
                            <p class="lead">Device Details</p>
                            <hr/>
                            <div class="row">
                                <div class="col">
                                    <label>Cost</label>
                                    <input 
                                        class="form-control" 
                                        type="text" 
                                        name="price" 
                                        value="$<%= theDevice.getCost()%>" 
                                        readonly>
                                </div>
                                <div class="col">
                                    <label>Stock Quantity Left</label>
                                    <input 
                                        class="form-control" 
                                        type="text" name="stock" 
                                        value="<%= theDevice.getStockQuantity()%>" 
                                        readonly>
                                </div>
                            </div>
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
                                           name="unitnumber" value="" 
                                           />
                                </div>
                                <div class="col-md-8">
                                    <label>Street Address</label>
                                    <input 
                                        class="form-control" 
                                        type="text" 
                                        name="streetaddress" 
                                        value="" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <label>City</label>
                                    <input class="form-control" 
                                           type="text" 
                                           name="city" value="" 
                                           />
                                </div>
                                <div class="col-md-4">
                                    <label>State</label>
                                    <input 
                                        class="form-control" 
                                        type="text" 
                                        name="state" 
                                        value="" />
                                </div>
                                <div class="col-md-4">
                                    <label>Post Code</label>
                                    <input 
                                        class="form-control" 
                                        type="text" 
                                        name="postcode" 
                                        value="" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Phone Number</label>
                                <input class="form-control" type="text" name="phonenumber" value="">
                            </div>
                        </div>
                    </div> 
                    <div  style="display: flex; align-items:center; justify-content: flex-end; margin-top:2rem;">
                        <input name="action" type="submit" value="Saved" 
                               style="margin-right:1em;" class="btn btn-warning" />
                        <input name="action" type="submit" value="Submited"  class="btn btn-success"/>
                    </div>
                </form>
            </div>
        </div>

    </body>
</html>
