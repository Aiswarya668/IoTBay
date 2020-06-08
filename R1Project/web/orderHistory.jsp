<%-- 
    Document   : orderHistory
    Created on : 30/05/2020, 4:22:18 PM
    Author     : sanyadua
--%>

<%@page import="uts.isd.model.CustomerOrder"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // get all orders
    
    ArrayList<CustomerOrder> orders = (ArrayList) session.getAttribute("allOrders");

    // Search
     // errors
    ArrayList<String> searchErrors = (ArrayList<String>) session.getAttribute("searchErrors");
    String paymentCompleted = (String) session.getAttribute("paymentCompleted");
    String orderID = (String) session.getAttribute("orderID");
    session.setAttribute("orderID", null);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IoTBay | Order History</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>        
        <!--<link href="css/webpage.css" rel="stylesheet" type="text/css"/>-->


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
             <a href="ViewDeviceServletUsers">Go Back</a>   
            <h3 class="iot-center">Your Order History</h3>
            <p><%=(paymentCompleted != null ? paymentCompleted : "")%></p>
            <p><%=(orderID == null ? "" : "This is your Order ID: " + orderID)%></p>
            
             <%for (String error : searchErrors) {%><p class="alert alert-danger"><%=error%></p><%}%>
            <br />
            <!-- Search Form-->
            <form action="/OrderHistorySearch" method="GET">
                <div class="iot-center">
                    <div class="col-sm-3">
                        <input class="form-control" type="search" name="searchId" placeholder="Enter ID">       
                    </div>
                   
                    <div class="col-sm-2">
                        <input type="submit" class="btn btn-primary btn-block" value="Search">        
                    </div>
                </div>
            </form>

            <div class="iot-center" style="margin:40px;">
                <table class="table table-hover"> 
                    <thead>
                        <tr>
                            <th>OrderID</th>
                            
                            <th>Est Arrival Time</th>
                            <th>Shipping Cost</th>
                            <th>Total Price</th>
                            <th>Status</th>
                            <th>Shipping Type</th>
                            <th>Date</th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody>
                        <% if(orders!=null) {%>
                        
                        <% for (CustomerOrder o : orders) {%>

                    <tr>
                        <td><%= o.getOrderID() %></td>
                        
                        <td><%= o.getEstimatedArrivalDate() %></td>
                        <td><%= o.getShippingCost() %></td>
                        <td><%= o.getTotalPrice()%></td>
                        <td><%= o.getStatus() %></td>
                        <td><%= o.getShippingType() %></td>
                        <td><%= o.getDateOrdered() %></td>

                        <td>
                        <%if(o.getStatus().equalsIgnoreCase("SAVED")){%>
                        <a href="/cancelOrder?id=<%=o.getOrderID()%>" class="btn btn-xs btn-danger">Cancel</a>
                        <a href="/updateOrder?id=<%=o.getOrderID()%>" class="btn btn-xs btn-primary">Update</a>
                        <a href="/OrderPaymentServlet?id=<%=o.getOrderID()%>" class="btn btn-xs btn-danger">Purchase</a>
                        <%}%>

                        </td>
                    </tr>
                    <%}%><%}%>

                    </tbody>
                </table> 

            </div>

        </div>
        <% 
            session.setAttribute("paymentCompleted","");
        %>
    </body>
</html>

