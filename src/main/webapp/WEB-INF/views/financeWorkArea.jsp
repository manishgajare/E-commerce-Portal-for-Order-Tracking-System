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
function financeApproval(){
	  
	  $('#financeApproval').submit();
	  
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




<b>${returnMessage}</b>
<br>
<table class="gridtable">
<tr>
<td>Order Id</td>
<td>Product Code</td>
<td>Quantity</td>
<td>Cost</td>
<td>Commision</td>
<td>Penalty</td>
<td>Status</td>
<td>Approve Process</td>
</tr>



<c:forEach var="listOfOrderItemsElement" items="${listOfOrderItems}" varStatus="OrderItemIdLoopStatus">


<c:if test="${listOfOrderItemsElement.inventory.supplier.supplierId==account.supplier.supplierId}" >

<tr>
<td>${listOfOrderItemsElement.orderCatalog.orderId}<br></td>
<td>${listOfOrderItemsElement.inventory.productCode}</td>
<td>${listOfOrderItemsElement.quantity}</td>
<td>${listOfOrderItemsElement.cost}</td>
<td>${listOfOrderItemsElement.commision}</td>
<td>${listOfOrderItemsElement.penaltyFlag}</td>
<td>${listOfOrderItemsElement.statusOfOrderItem}</td>
<td>
<form method="POST" action="financeApproval.htm" id="financeApproval">
 <input type="hidden" id="orderItemId" name="orderItemId" value="${listOfOrderItemsElement.orderItemId}" />
 <input type="submit" value="Approve" onClick="financeApproval()">
</form>
</td>
<tr>

</c:if>

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