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
        
      
        function validateEmail(email) { 
            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        } 
        
        
        
        
        
        function checkRegistrationForm(){
        	
        	document.getElementById('firstName').style.borderColor = '#ececec';
        	document.getElementById('lastName').style.borderColor = '#ececec';
        	document.getElementById('phoneNumber').style.borderColor = '#ececec';
        	document.getElementById('email').style.borderColor = '#ececec';
        	document.getElementById('address').style.borderColor = '#ececec';
        	document.getElementById('username').style.borderColor = '#ececec';
        	document.getElementById('password').style.borderColor = '#ececec';
        	
        	$('#error1').html('');
    		$('#error2').html('');
    		$('#error3').html('');
    		$('#error4').html('');
    		$('#error5').html('');
    		$('#error6').html('');
    		$('#error7').html('');
        	
        	var errormessage = "";
        	
        	if(document.getElementById('firstName').value.trim()==""){
        		errormessage +="enter your first name \n";
        		document.getElementById('firstName').style.borderColor = 'red';
        		var error1 = "enter your first name";
        	}
        	
        	if(document.getElementById('lastName').value.trim()==""){
        		errormessage +="enter your last name \n";
        		document.getElementById('lastName').style.borderColor = 'red';
        		var error2 = "enter your last Name";
        	}
        	
        	if(document.getElementById('phoneNumber').value.trim()==""){
        		errormessage +="enter your phoneNumber \n";
        		document.getElementById('phoneNumber').style.borderColor = 'red';
        		var error3 = "enter your phone Number";
        	}

        	if(document.getElementById('email').value.trim()=="" || !validateEmail(document.getElementById('email').value.trim())){
        		errormessage +="enter your email \n";
        		document.getElementById('email').style.borderColor = 'red';
        		var error4 = "enter your email";
        	}
        	
        	
        	if(document.getElementById('address').value.trim()==""){
        		errormessage +="enter your address \n";
        		document.getElementById('address').style.borderColor = 'red';
        		var error5 = "enter your address";
        	}
        	
        	if(document.getElementById('username').value.trim()==""){
        		errormessage +="enter your username \n";
        		document.getElementById('username').style.borderColor = 'red';
        		var error6 = "enter your username";
        	}

        	if(document.getElementById('password').value.trim()==""){
        		errormessage +="enter your password \n";
        		document.getElementById('password').style.borderColor = 'red';
        		var error7 = "enter your password";
        	}

        	
        	
        	
        	
        	if(errormessage != "")
        	{
        		$('#error1').html(error1);
        		$('#error2').html(error2);
        		$('#error3').html(error3);
        		$('#error4').html(error4);
        		$('#error5').html(error5);
        		$('#error6').html(error6);
        		$('#error7').html(error7);
        		
        	}else{
        		document.getElementById("RegisterForm").submit();
        	}
        	
        	
        }
  
        
        
function checkLoginForm(){
        	
        	document.getElementById('usernameLogin').style.borderColor = '#ececec';
        	document.getElementById('passwordLogin').style.borderColor = '#ececec';
        	
        	$('#error8').html('');
    		$('#error9').html('');
    		
        	var errormessage = "";
        	
        	if(document.getElementById('usernameLogin').value==""){
        		errormessage +="enter your username \n";
        		document.getElementById('usernameLogin').style.borderColor = 'red';
        		var error8 = "enter your first name";
        	}
        	
        	if(document.getElementById('passwordLogin').value==""){
        		errormessage +="enter your password \n";
        		document.getElementById('passwordLogin').style.borderColor = 'red';
        		var error9 = "enter your password";
        	}
        	
        	
        	if(errormessage != "")
        	{
        		$('#error8').html(error8);
        		$('#error9').html(error9);
        		
        	}else{
        		document.getElementById("loginForm").submit();
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
        <div class="content2">

  
  
  



        <h3>New to This Website...SignUp</h3>
        <form action="placeOrderRegister.htm" method="post"  name="RegisterForm" id="RegisterForm">
        <table>
<tr><td>First Name : </td><td> <input type="text" id="firstName" name="firstName"><br/></td><td><font color="red"><div id="error1" ></div></font></td></tr>
            <tr><td>Last Name : </td><td> <input type="text" id="lastName" name="lastName"><br/></td><td><font color="red"><div id="error2" ></div></font></td></tr>
            <tr><td>Phone Number : </td><td> <input type="text" id="phoneNumber" name="phoneNumber"><br/></td><td><font color="red"><div id="error3" ></div></font></td></tr>
            <tr><td>Email : </td><td> <input type="text" id="email" name="email"><br/></td><td><font color="red"><div id="error4" ></div></font></td></tr>
            <tr><td>Address : </td><td> <input type="text" id="address" name="address"><br/></td><td><font color="red"><div id="error5" ></div></font></td></tr>
            <tr><td colspan="2"> <input type="hidden" id="countryId" name="countryId" value="1"><hr></td></tr>
            <tr><td>UserName : </td><td> <input type="text" id="username" name="username"><br/></td><td><font color="red"><div id="error6" ></div></font></td></tr>
            <tr><td>Password : </td><td> <input type="text" id="password" name="password"><br/></td><td><font color="red"><div id="error7" ></div></font></td></tr>
            <tr><td colspan="2"><input type="button" value="Register" onclick="checkRegistrationForm()"><br/></td></tr>
            <tr><td colspan="2">${registerCustomerMessage}</td></tr>
        </table>
      	</form>
      	
      	 <h3>Already a member...SignIn</h3>
      	<form action="placeOrderLogin.htm" method="post" name="loginForm" id="loginForm">
        <table>
        <tr><td>UserName : </td><td> <input type="text" id="usernameLogin" name="usernameLogin"><br/></td><td><font color="red"><div id="error8" ></div></font></td></tr>
        <tr><td>Password : </td><td> <input type="text" id="passwordLogin" name="passwordLogin"><br/></td><td><font color="red"><div id="error9" ></div></font></td></tr>
        <tr><td colspan="2"><input type="button" value="Login" onclick="checkLoginForm()"><br/></td></tr>
        <tr><td colspan="2">${loginCustomerMessage}</td></tr>
		</table>
        </form>
        
        

</div>
      </div>
    </div>
    <div class="yui-b">
      <div id="secondary2"></div>
    </div>
  </div>
  <div id="ft">
    <div id="footer"></div>
  </div>
</div>
        

</body>
</html>