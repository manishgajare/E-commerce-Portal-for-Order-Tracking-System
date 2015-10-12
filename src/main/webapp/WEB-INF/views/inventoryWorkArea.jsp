<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Inventory</title>
        
        <link rel="stylesheet" type="text/css" href="/spring/css/styles.css">






<script src="//code.jquery.com/jquery-1.9.1.js"></script>


        <script type="text/javascript">
        
        function resetForm()
        {
      	  window.location="workArea.htm";
        }

        
 function checkInventoryForm(){
        	
        	document.getElementById('category').style.borderColor = '#ececec';
        	document.getElementById('subCategory').style.borderColor = '#ececec';
        	document.getElementById('name').style.borderColor = '#ececec';
        	document.getElementById('productCode').style.borderColor = '#ececec';
        	document.getElementById('quantityAvailable').style.borderColor = '#ececec';
        	document.getElementById('cost').style.borderColor = '#ececec';


        	$('#error1').html('');
    		$('#error2').html('');
    		$('#error3').html('');
    		$('#error4').html('');
    		$('#error5').html('');
    		$('#error6').html('');
    		
        	
        	var errormessage = "";
        	
        	if(document.getElementById('category').value.trim()==""){
        		errormessage +="enter category \n";
        		document.getElementById('category').style.borderColor = 'red';
        		var error1 = "enter category";
        	}
        	
        	if(document.getElementById('subCategory').value.trim()==""){
        		errormessage +="enter subcategory \n";
        		document.getElementById('subCategory').style.borderColor = 'red';
        		var error2 = "enter subcategory";
        	}
        	
        	if(document.getElementById('name').value.trim()==""){
        		errormessage +="enter name \n";
        		document.getElementById('name').style.borderColor = 'red';
        		var error3 = "enter name";
        	}

        	if(document.getElementById('productCode').value.trim()==""){
        		errormessage +="enter productCode \n";
        		document.getElementById('productCode').style.borderColor = 'red';
        		var error4 = "enter productCode";
        	}
        	
        	
        	if(document.getElementById('quantityAvailable').value.trim()==""){
        		errormessage +="enter  quantityAvailable \n";
        		document.getElementById('quantityAvailable').style.borderColor = 'red';
        		var error5 = "enter  quantityAvailable";
        	}
        	
        	if(document.getElementById('cost').value.trim()==""){
        		errormessage +="enter cost \n";
        		document.getElementById('cost').style.borderColor = 'red';
        		var error6 = "enter  cost";
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
        		addProduct();
        	}
        	
        	
        }
        

        
        
        
        
        
        
        
        
        
        
        function addProduct() {
        // get the form values
        var name = $('#name').val();
        var productCode = $('#productCode').val();
        var quantityAvailable = $('#quantityAvailable').val();
        var cost = $('#cost').val();
        var category = $('#category').val();
        var subCategory = $('#subCategory').val();
        

        $.ajax({
        type: "POST",
        url: "addProduct.htm",
        data: "name="+name+"&productCode="+productCode+"&quantityAvailable="+quantityAvailable+"&cost="+cost+"&category="+category+"&subCategory="+subCategory,
        success: function(response){
        // we have the response
        $('#addProductMessage').html(response);
        $('#name').val('');
        $('#productCode').val('');
        $('#quantityAvailable').val('');
        $('#cost').val('');
        },
        error: function(e){
        alert('Please fill all the fields');
        }
        });
        }
        
        
        
        function subCategoryListFunction()
        {
      	  var category = $('#category').val();
      	  
      	  window.location="subCategoryListFind.htm?category="+category;
      	  
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




       <h1>Add Products To Inventory</h1>
       		<form>
            <table>
            
            
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
 			<td><font color="red"><div id="error1" ></div></font></td>
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
 			<td><font color="red"><div id="error2" ></div></font></td>
 			</tr>
 			<tr><td>Name : </td><td> <input type="text" name="name" id="name"><br/></td><td><font color="red"><div id="error3" ></div></font></td></tr>
            <tr><td>Product Code : </td><td> <input type="text" name="productCode" id="productCode"><br/></td><td><font color="red"><div id="error4" ></div></font></td></tr>
            <tr><td>Quantity Available : </td><td> <input type="text" name="quantityAvailable" id="quantityAvailable"><br/></td><td><font color="red"><div id="error5" ></div></font></td></tr>
            <tr><td>Cost : </td><td> <input type="text" name="cost" id="cost"><br/></td><td><font color="red"><div id="error6" ></div></font></td></tr>
            <tr><td colspan="2"><input type="button" value="Add Product" onclick="checkInventoryForm()"><br/></td></tr>
            <tr><td colspan="2"><td colspan="2"><div id="addProductMessage" ></div></td></tr>
            </table>
      		</form>
 
 <br><br>
 <input type="button" value="Reset" onclick="resetForm()" >
			


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