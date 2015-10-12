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
  
  
  
  
    
  function removeFromCart(inventoryItemId)
        {
	  var  quantity =  $('#quantity'+inventoryItemId).val();
		 
      	  window.location="removeFromCart.htm?quantity="+quantity+"&inventoryItemId="+inventoryItemId;
      	  
        }
  
  
function mainMenuForm(){
	  
	  $('#mainMenuForm').submit();
	  
  }
  
function checkoutForm(){
	  
	  $('#checkoutForm').submit();
	  
}
  
  </script>
  
  
  
</head>
<body>




<div id="doc" class="yui-t2">
  <div id="hd">
    <div id="header"><a href="editCart.htm" style="float:right; position:relative; top: 50px; left: -50px;" >View Cart</a>
     
    
    <br><br></div>
  </div>
  <div id="bd">
    <div id="yui-main">
      <div class="yui-b">
        <div class="content1">







<table class="gridtable">
<tr>

<td>
<table>
<tr>
<th>ID</th>
</tr>

<c:forEach var="inventoryItemIdElement" items="${inventoryItemId}" varStatus="inventoryItemIdLoopStatus">

<c:forEach var="quantityElement" items="${quantity}" varStatus="quantityLoopStatus">
<c:if test="${quantityLoopStatus.index == inventoryItemIdLoopStatus.index}">

<c:if test="${quantityElement == '0'}">
<c:set var="flag" value="0"/>
</c:if>
<c:if test="${quantityElement != '0'}">
<c:set var="flag" value="1"/>
</c:if>
</c:if>
</c:forEach>

<c:if test="${flag == '1'}">
<tr>
<td><c:out value="${inventoryItemIdElement}" /></td>
</tr>
</c:if>
</c:forEach>
</table>
</td>

<td>
<table>
<tr>
<th>Quantity</th>
</tr>
<c:forEach var="quantityElement" items="${quantity}" varStatus="quantityLoopStatus">
<tr>
<td>
<c:forEach var="inventoryItemIdElement" items="${inventoryItemId}" varStatus="inventoryItemIdLoopStatus">
<c:if test="${quantityLoopStatus.index == inventoryItemIdLoopStatus.index}">
<c:if test="${quantityElement == '0'}">
<c:set var="flag" value="0"/>
</c:if>
<c:if test="${quantityElement != '0'}">
<c:set var="flag" value="1"/>
</c:if>
<c:set var="iii" value="${inventoryItemIdElement}"/>
</c:if>
</c:forEach>
<c:if test="${flag == '1'}">
<select name="quantity<c:out value="${iii}"/>" id="quantity<c:out value="${iii}"/>">
<c:forEach begin="1" end="${quantityElement}" var="val">
    <option value="<c:out value="${val}"/>" ><c:out value="${val}"/></option>
</c:forEach>
</select>
</c:if>
</td>
</tr>
</c:forEach>
</table>
</td>

<td>
<table>
<tr>
<th>Cost</th>
</tr>
<c:forEach var="costElement" items="${cost}" varStatus="costLoopStatus">


<c:forEach var="quantityElement" items="${quantity}" varStatus="quantityLoopStatus">
<c:if test="${quantityLoopStatus.index == costLoopStatus.index}">

<c:if test="${quantityElement == '0'}">
<c:set var="flag" value="0"/>
</c:if>
<c:if test="${quantityElement != '0'}">
<c:set var="flag" value="1"/>
</c:if>
</c:if>
</c:forEach>

<c:if test="${flag == '1'}">
<tr>
<td><c:out value="${costElement}" /></td>
</tr>
</c:if>

</c:forEach>
</table>
</td>


<td>
<table>
<tr>
<th>Remove</th>
</tr>
<c:forEach var="inventoryItemIdElement" items="${inventoryItemId}" varStatus="inventoryItemIdLoopStatus">

<c:forEach var="quantityElement" items="${quantity}" varStatus="quantityLoopStatus">
<c:if test="${quantityLoopStatus.index == inventoryItemIdLoopStatus.index}">

<c:if test="${quantityElement == '0'}">
<c:set var="flag" value="0"/>
</c:if>
<c:if test="${quantityElement != '0'}">
<c:set var="flag" value="1"/>
</c:if>
</c:if>
</c:forEach>

<c:if test="${flag == '1'}">
<tr>
<td><button  value="${inventoryItemIdElement}" id="inventoryItemId" onClick="removeFromCart(this.value)">Remove to Cart</button></td>
</tr>
</c:if>


</c:forEach>
</table>
</td>

</tr>
</table>

<div id="RemoveFromCartMessage"></div>

<br><br><br><br>
<form method="POST" action="browseProducts.htm" id="mainMenuForm">
 <input type="hidden" id="fromBrowsePage" name="fromBrowsePage" value="1" />
 <input type="submit" value="Main Menu" onClick="mainMenuForm()">
</form>


<br>

<form method="POST" action="checkout.htm" id="checkoutForm">
 <input type="submit" value="Checkout" onClick="checkoutForm()">
</form>









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