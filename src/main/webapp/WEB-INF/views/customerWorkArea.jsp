<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Amazon</title>
  <script src="//code.jquery.com/jquery-1.9.1.js"></script>



 <link rel="stylesheet" type="text/css" href="/spring/css/styles.css">


<script>
function returnRefundRequest(){
	  
	  $('#returnRefundRequest').submit();
	  
}
</script>


</head>
<body>





<div id="doc" class="yui-t2">
  <div id="hd">
    <div id="header"><a href="logout.htm" style="float:right; position:relative; top: 50px; left: -50px;" >Logout</a>
     
    
    <br><br></div>
  </div>
  <div id="bd">
    <div id="yui-main">
      <div class="yui-b">
        <div class="content1">



<table  class="gridtable">
<tr>
<td>Order Id</td>
<td>Expected Delivery Date</td>
<td>Confirmation Code</td>
<td>OrderItems</td>
</tr>

<c:forEach var="listOfOrdersElement" items="${listOfOrders}" varStatus="OrderIdLoopStatus">


<tr>
<td>${listOfOrdersElement.orderId}<br></td>
<td>${listOfOrdersElement.orderToBeDeliveredTime}</td>

<td>
<c:set var="flag" value="0"/>
<c:forEach var="listOfOrderItemsElement" items="${listOfOrderItems}" varStatus="listOfOrderItemsLoopStatus">

<c:if test="${listOfOrderItemsElement.orderCatalog.orderId == listOfOrdersElement.orderId}">

<c:if test="${flag==0}">
${listOfOrderItemsElement.confirmationCode}
<c:set var="flag" value="1"/>
</c:if>

</c:if>

</c:forEach>
</td>


<td>

<table>

<tr>
<td>Name</td>
<td>Quantity</td>
<td>Cost</td>
<td>Status</td>
</tr>

<c:forEach var="listOfOrderItemsElement" items="${listOfOrderItems}" varStatus="listOfOrderItemsLoopStatus">

<c:if test="${listOfOrderItemsElement.orderCatalog.orderId == listOfOrdersElement.orderId}">


<tr>
<td>${listOfOrderItemsElement.inventory.name}</td>
<td>${listOfOrderItemsElement.quantity}</td>
<td>${listOfOrderItemsElement.cost}</td>
<td>${listOfOrderItemsElement.statusOfOrderItem}</td>

<c:if test="${listOfOrderItemsElement.statusOfOrderItem eq 'Delivered'}">
<td>
<form method="POST" action="returnRefundRequest.htm" id="returnRefundRequest">
 <input type="hidden" id="orderItemId" name="orderItemId" value="${listOfOrderItemsElement.orderItemId}" />
 <input type="submit" value="Return" onClick="returnRefundRequest()">
</form>
</td>
</c:if>
</tr>

</c:if>

</c:forEach>

</table>
<br><br>
</td>

</tr>

</c:forEach>

</table>




</div>
      </div>
    </div>
    <div class="yui-b">
      <div id="secondary"></div>
    </div>
  </div>
  <div id="ft">
    <div id="footer"></div>
  </div>
</div>















</body>
</html>