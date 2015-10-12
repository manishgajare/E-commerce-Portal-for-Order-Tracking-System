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
  
  
  
  
  function checkForm(){
  	
  	document.getElementById('category').style.borderColor = '#ececec';
  	document.getElementById('subCategory').style.borderColor = '#ececec';
  	
  	$('#error1').html('');
		$('#error2').html('');
		
  	
  	var errormessage = "";
  	
  	if(document.getElementById('category').value.trim()==""){
  		errormessage +="enter category \n";
  		document.getElementById('category').style.borderColor = 'red';
  		var error1 = "enter category";
  	}
  	
  	if(document.getElementById('subCategory').value.trim()==""){
  		errormessage +="enter subCategory \n";
  		document.getElementById('subCategory').style.borderColor = 'red';
  		var error2 = "enter subCategory";
  	}
  	
  	
  	
  	
  	if(errormessage != "")
  	{
  		$('#error1').html(error1);
  		$('#error2').html(error2);
  		
  		
  	}else{
  		submitForm();
  	}
  	
  	
  }
  
  
  function submitForm(){
	  
	  document.getElementById("viewProductsForm").submit();
	  
  }
  

  
  
  function subCategoryListFunction()
        {
      	  var category = $('#category').val();
      	  
      	  window.location="subCategoryListFindIndex.htm?category="+category;
      	  
        }
  function resetForm(){
	  
	  $('#myform').submit();
	  
  }
        
        
        </script>
  
</head>
<body>




<div id="doc" class="yui-t2">
  <div id="hd">
    <div id="header"><a href="index.htm" style="float:right; position:relative; top: 50px; left: -50px;" >Home</a>
     
    
    <br><br></div>
  </div>
  <div id="bd">
    <div id="yui-main">
      <div class="yui-b">
        <div class="content1">





<h2>Please select Location For Delivery: </h2><br><br>
<form action="viewProducts.htm" method="POST" name="viewProductsForm" id="viewProductsForm">
	<table class="gridtable">
				<tr>
 			<td>Category : </td>
 			<td>
 			<c:choose>
 			<c:when test="${not empty categoryList}">
 			<select name="category" id="category" onchange="subCategoryListFunction()">
 			<option>--SELECT--</option>
 			<c:forEach var="categoryListElement" items="${categoryList}">
			<option value="${categoryListElement}">${categoryListElement}</option>
			</c:forEach>
			</select>
			</c:when>
			<c:otherwise>
			<input type="hidden" name="category" id="category" value="${categoryName}">
			${categoryName}
			</c:otherwise>
			</c:choose>
 			</td>
 			</tr>
 			
			<tr>
 			<td>Sub Category : </td>
 			<td>
 			<c:choose>
 			<c:when test="${not empty subCategoryList}">
 			<select name="subCategory" id="subCategory" >
 			<c:forEach var="subCategoryListElement" items="${subCategoryList}">
			<option value="${subCategoryListElement}">${subCategoryListElement}</option>
			</c:forEach>
			</select>
			</c:when>
			</c:choose>
 			</td>
 			</tr>
 			
 		    <tr><td colspan="2"><input type="button" value="View Products" onclick="checkForm()"><br/></td></tr>
            
		
	</table>		
</form>
<br><br>
 <form method="POST" action="browseProducts.htm" id="myform">
 <input type="hidden" id="fromBrowsePage" name="fromBrowsePage" value="1" />
 <input type="submit" value="Reset" onClick="resetForm()">
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