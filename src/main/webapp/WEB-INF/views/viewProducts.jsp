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
function mainMenuForm(){
	  
	  $('#mainMenuForm').submit();
	  
  }
  
function editCart(){
	  
	  $('#mainMenuForm').submit();
	  
}
  
  
  function addToCart(inventoryItemId){

	  var  quantity =  $('#quantity'+inventoryItemId).val();
	 
	  
	  $.ajax({
	        type: "POST",
	        url: "addToCart.htm",
	        data: "quantity="+quantity+"&inventoryItemId="+inventoryItemId,
	        success: function(response){
	        // we have the response
	        $('#addToCartMessage').html(response);
	        },
	        error: function(e){
	        alert('Please fill all the fields');
	        }
	        });
	  
	  
	  function removeFromCart(inventoryItemIdToRemove)
	  {
	  alert(inventoryItemIdToRemove);
	  }
	  
	  
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
<th>Name</th>
<th>Product Code</th>
<th>Quantity Available</th>
<th>Cost</th>
<th>ID</th>
<th>Add Product</th>
</tr>
<c:forEach var="finalProductViewListElement" items="${finalProductViewList}">
<tr>
<td>${finalProductViewListElement.name}</td>
<td>${finalProductViewListElement.productCode}</td>
<td>
<select name="quantity${finalProductViewListElement.inventoryItemId}" id="quantity${finalProductViewListElement.inventoryItemId}">
<c:forEach begin="1" end="${finalProductViewListElement.quantityAvailable}" var="val">
    <option value="<c:out value="${val}"/>" ><c:out value="${val}"/></option>
</c:forEach>
</select>
</td>
<td>${finalProductViewListElement.cost}</td>
<td>${finalProductViewListElement.inventoryItemId}</td>
<td><button  value="${finalProductViewListElement.inventoryItemId}" id="inventoryItemId" onClick="addToCart(this.value)">Add to Cart</button></td>
</tr>
</c:forEach>
</table>

<br><br><br>

<div id="addToCartMessage"></div>


<form method="POST" action="browseProducts.htm" id="mainMenuForm">
 <input type="hidden" id="fromBrowsePage" name="fromBrowsePage" value="1" />
 <input type="submit" value="Go Back" onClick="mainMenuForm()">
</form>

<br>








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