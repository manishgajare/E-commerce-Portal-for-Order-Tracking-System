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

       <script type="text/javascript">
       
       
       
       function checkLoginForm(){
       	
    	   document.getElementById('streetAddress').style.borderColor = '#ececec';
          	document.getElementById('creditDebitCardNo').style.borderColor = '#ececec';
          	document.getElementById('accHolderName').style.borderColor = '#ececec';
           	document.getElementById('securityCode').style.borderColor = '#ececec';
           	document.getElementById('expirationYear').style.borderColor = '#ececec';
           	document.getElementById('expirationMonth').style.borderColor = '#ececec';
           	
       	$('#error1').html('');
   		$('#error2').html('');
   		$('#error3').html('');
   		$('#error4').html('');
   		$('#error5').html('');
   		$('#error6').html('');


       	var errormessage = "";

       	if(document.getElementById('streetAddress').value==""){
       		errormessage +="enter your streetAddress \n";
       		document.getElementById('streetAddress').style.borderColor = 'red';
       		var error1 = "enter your streetAddress";
       	}
       	
       	if(document.getElementById('creditDebitCardNo').value==""){
       		errormessage +="enter your creditDebitCardNo \n";
       		document.getElementById('creditDebitCardNo').style.borderColor = 'red';
       		var error2 = "enter your creditDebitCardNo";
       	}
       	

       	if(document.getElementById('accHolderName').value==""){
       		errormessage +="enter your accHolderName \n";
       		document.getElementById('accHolderName').style.borderColor = 'red';
       		var error3 = "enter your accHolderName";
       	}
       	
       	if(document.getElementById('securityCode').value==""){
       		errormessage +="enter your securityCode \n";
       		document.getElementById('securityCode').style.borderColor = 'red';
       		var error4 = "enter your securityCode";
       	}
       	

       	if(document.getElementById('expirationYear').value==""){
       		errormessage +="enter your expirationYear \n";
       		document.getElementById('expirationYear').style.borderColor = 'red';
       		var error5 = "enter your expirationYear";
       	}
       	
       	if(document.getElementById('expirationMonth').value==""){
       		errormessage +="enter your expirationMonth \n";
       		document.getElementById('expirationMonth').style.borderColor = 'red';
       		var error6 = "enter your expirationMonth";
       	}
       	
       	
       	if(errormessage != "")
       	{
       		$('#error1').html(error1);
       		$('#error2').html(error2);
       		$('#error3').html(error3);
       		$('#error4').html(error4);
       		$('#error5').html(error5);
       		$('#error6').html(error6);
       		
       	}else{
       		placeFinalOrder();
       	}
       	
       	
       }

       
       
       
        function placeFinalOrder() {
        // get the form values
        var streetAddress = $('#streetAddress').val();
        var creditDebitCardNo = $('#creditDebitCardNo').val();
        var accHolderName = $('#accHolderName').val();
        var securityCode = $('#securityCode').val();
        var expirationYear = $('#expirationYear').val();
        var expirationMonth = $('#expirationMonth').val();
        
        $.ajax({
        type: "POST",
        url: "placeFinalOrder.htm",
        data: "streetAddress=" + streetAddress + "&creditDebitCardNo=" + creditDebitCardNo + "&accHolderName=" + accHolderName + "&securityCode=" + securityCode + "&expirationYear=" + expirationYear + "&expirationMonth=" + expirationMonth,
        success: function(response){
        // we have the response
        $('#placeOrderMessage').html(response);
        $("#toBeHidden").hide();
        },
        error: function(e){
        alert('Please fill the form with proper values');
        }
        });
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
        <div class="content3">









<div id="toBeHidden" >

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
<c:out value="${quantityElement}"/>
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


</tr>
</table>

<br>
<b>Total Cost : ${totalCost}</b>


	 
      	<form>
        <table>
        <tr><td>Country : </td><td>USA</td></tr>
        <tr><td>State : </td><td>${state}</td></tr>
        <tr><td>City : </td><td>${city}</td></tr>
        <tr><td>Zipcode : </td><td>${zipcode}</td></tr>
        <tr><td>Street Address : </td><td> <input type="text" id="streetAddress" name="streetAddress"><br/></td><td><font color="red"><div id="error1" ></div></font></td></tr>
        <tr><td>Credit/Debit Card No. : </td><td> <input type="text" id="creditDebitCardNo" name="creditDebitCardNo"><br/></td><td><font color="red"><div id="error2" ></div></font></td></tr>
        <tr><td>Acc. Holder Name : </td><td> <input type="text" id="accHolderName" name="accHolderName"><br/></td><td><font color="red"><div id="error3" ></div></font></td></tr>
        <tr><td>Security Code : </td><td> <input type="text" id="securityCode" name="securityCode"><br/></td><td><font color="red"><div id="error4" ></div></font></td></tr>
        <tr><td>Expiration Month : </td><td> <input type="text" id="expirationYear" name="expirationYear"><br/></td><td><font color="red"><div id="error5" ></div></font></td></tr>
        <tr><td>Expiration Year : </td><td> <input type="text" id="expirationMonth" name="expirationMonth"><br/></td><td><font color="red"><div id="error6" ></div></font></td></tr>
        <tr><td colspan="2"><input type="button" value="Place order" onclick="checkLoginForm()" ><br/></td></tr>
        </table>
        </form>


</div>

<div id="placeOrderMessage" ></div>





</div>
      </div>
    </div>
    <div class="yui-b">
      <div id="secondary3"></div>
    </div>
  </div>
  <div id="ft">
    <div id="footer"></div>
  </div>
</div>



</body>
</html>