package com.me.spring;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.crsh.shell.impl.command.system.repl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.me.spring.DAO.CategoryDAO;
import com.me.spring.DAO.CountryDAO;
import com.me.spring.DAO.CustomerDAO;
import com.me.spring.DAO.EcoSystemDAO;
import com.me.spring.DAO.EmployeeDAO;
import com.me.spring.DAO.InventoryDAO;
import com.me.spring.DAO.OrderDAO;
import com.me.spring.DAO.OrderItemDAO;
import com.me.spring.DAO.SubCategoryDAO;
import com.me.spring.DAO.SupplierDAO;
import com.me.spring.DAO.ZipcodeDAO;
import com.me.spring.DAO.ZipcodePositionDAO;
import com.me.spring.exception.AdException;
import com.me.spring.pojo.Category;
import com.me.spring.pojo.Country;
import com.me.spring.pojo.Customer;
import com.me.spring.pojo.EcoSystem;
import com.me.spring.pojo.Employee;
import com.me.spring.pojo.Inventory;
import com.me.spring.pojo.OrderCatalog;
import com.me.spring.pojo.OrderItem;
import com.me.spring.pojo.Supplier;
import com.me.spring.pojo.Zipcode;
import com.me.spring.pojo.ZipcodePosition;




/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController{
	
	
	
	
	/*
	
	@Autowired
	CustomerDAO customerDAO;
	@Autowired
	EmployeeDAO employeeDAO;
	@Autowired
	EcoSystemDAO ecoSystemDAO;
	@Autowired
	ZipcodePositionDAO zipcodePositionDAO; 
	*/
	
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
@RequestMapping(value = "/index", method = RequestMethod.GET)
public String index(Locale locale, Model model,HttpServletRequest request) throws AdException {
	   
/*	EcoSystemDAO systemDAO = new EcoSystemDAO();
	List systemList = systemDAO.list();	
	
	model.addAttribute("systemList", systemList );
	return "index";
	*/
	
//	return "fileUploadForm";
  	ZipcodePositionDAO zipcodePositionDAO = new ZipcodePositionDAO();
	List stateList = zipcodePositionDAO.stateList();
	
	HttpSession session = request.getSession();
	session.removeAttribute("account");
	session.removeAttribute("department");
	session.removeAttribute("state");
	session.removeAttribute("city");
	session.removeAttribute("zipcode");
	  
	model.addAttribute("stateList", stateList );
	
	return "index";

}



@RequestMapping(value="/workArea.htm")
public ModelAndView signIn(Locale locale, Model model,HttpServletRequest request) throws AdException
{	
	HttpSession session = request.getSession();
	
	String username =null;
	String password =null;
	int count = 0;
	
	if(session.getAttribute("account")!= null){
		
		
		if("EcoSystemAdmin"==(String)session.getAttribute("department")){
			EcoSystem es = (EcoSystem)session.getAttribute("account");
			username = es.getUsername();
			password = es.getPassword();
			
		} 
		else if("Customer"==(String)session.getAttribute("department")){
			Customer c = (Customer)session.getAttribute("account");
			username = c.getUsername();
			password = c.getPassword();
			
		} 	
		else if("SupplierAdmin"==(String)session.getAttribute("department") || "Inventory"==(String)session.getAttribute("department") || "Finance"==(String)session.getAttribute("department") || "Sales"==(String)session.getAttribute("department") || "Shipping"==(String)session.getAttribute("department") ){
			Employee e = (Employee)session.getAttribute("account");
			username = e.getUsername();
			password = e.getPassword();
			
		} 

		count++;
		
		
	}
	else{
	
	username = request.getParameter("usernameLogin");
    password = request.getParameter("passwordLogin");
	}
	
	
    CustomerDAO customerDAO = new CustomerDAO();
    EmployeeDAO employeeDAO = new EmployeeDAO();
    EcoSystemDAO ecoSystemDAO = new EcoSystemDAO();
    
    
    
  if(ecoSystemDAO.loginCheck(username, password)!=null){
	  
	  if(count==0){
	  session.setAttribute("account",ecoSystemDAO.loginCheck(username, password));
	  session.setAttribute("department","EcoSystemAdmin");
	  }
	  
	  ZipcodePositionDAO zipcodePositionDAO = new ZipcodePositionDAO();
	  List stateList = zipcodePositionDAO.stateList();
	  return new ModelAndView("adminWorkArea","stateList",stateList);
	  
	  
  }
  
  if(employeeDAO.loginCheck(username, password)!=null){
	  
	  Employee emp = employeeDAO.loginCheck(username, password);
	  
	  if(count==0){
		  session.setAttribute("account",employeeDAO.loginCheck(username, password));
	  }
	  
	
 
	  if(emp.getDepartment().equals("SupplierAdmin")){
		  if(count==0){
			  session.setAttribute("department","SupplierAdmin");  
		  }
		  return new ModelAndView("supplierAdminWorkArea");
		  
		  
	  }
	  
	  if(emp.getDepartment().equals("Inventory")){
		  if(count==0){
			  session.setAttribute("department","Inventory");
		  }
		  
		  CategoryDAO categoryDAO = new CategoryDAO();
		  List categoryList = categoryDAO.CategoryList();
		  return new ModelAndView("inventoryWorkArea","categoryList",categoryList);
		  
		  
		  
	  }

	  if(emp.getDepartment().equals("Finance")){
		  if(count==0){
			  session.setAttribute("department","Finance");  
		  }
		  
		  
		  
		  
		  Employee employee = (Employee) session.getAttribute("account");
		  Supplier supplier = employee.getSupplier();
		  
		  System.out.println("SupplierId - "+supplier.getSupplierId());
		  
		  OrderItemDAO orderItemDAO = new OrderItemDAO();
		  
		  
		  List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		  
		  listOfOrderItems = orderItemDAO.list();

		  model.addAttribute("listOfOrderItems", listOfOrderItems );
		  return new ModelAndView("financeWorkArea");
		  
	  }

	  if(emp.getDepartment().equals("Sales")){
		  if(count==0){
			  session.setAttribute("department","Sales");  
		  }
		  
		  
		  Employee employee = (Employee) session.getAttribute("account");
		  Supplier supplier = employee.getSupplier();
		  
		  System.out.println("SupplierId - "+supplier.getSupplierId());
		  
		  OrderItemDAO orderItemDAO = new OrderItemDAO();
		  
		  
		  List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		  
		  listOfOrderItems = orderItemDAO.list();

		  model.addAttribute("listOfOrderItems", listOfOrderItems );
		  return new ModelAndView("salesWorkArea");
		  
	  } 

	  if(emp.getDepartment().equals("Shipping")){
		  if(count==0){
			  session.setAttribute("department","Shipping");  
		  }

		  Employee employee = (Employee) session.getAttribute("account");
		  Supplier supplier = employee.getSupplier();
		  
		  System.out.println("SupplierId - "+supplier.getSupplierId());
		  
		  OrderItemDAO orderItemDAO = new OrderItemDAO();
		  
		  
		  List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		  
		  listOfOrderItems = orderItemDAO.list();

		  model.addAttribute("listOfOrderItems", listOfOrderItems );
		  return new ModelAndView("shippingWorkArea");

		  
		  
	  } 
	  
	  
	  
  }
  
  if(customerDAO.loginCheck(username, password)!=null){
	  
	  if(count==0){
	  session.setAttribute("account",customerDAO.loginCheck(username, password));
	  session.setAttribute("department","Customer");
	  }
	  
	  
	  Customer customer = (Customer) session.getAttribute("account");
	  
	  OrderDAO orderDAO = new OrderDAO();
	  OrderItemDAO orderItemDAO = new OrderItemDAO();
	  
	  List<OrderCatalog> listOfOrders = new ArrayList<OrderCatalog>();
	  listOfOrders = orderDAO.customerOrderList(customer.getCustomerId());
	  
	  
	  List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
	  
	  for(int i=0;i<listOfOrders.size();i++){
	  
		  listOfOrderItems.addAll(orderItemDAO.customerOrderItemList(listOfOrders.get(i).getOrderId()));
	  
	  }
	  

	  model.addAttribute("listOfOrderItems", listOfOrderItems );
	  model.addAttribute("listOfOrders", listOfOrders );
	  return new ModelAndView("customerWorkArea");
	  
  } 
	  
  ZipcodePositionDAO zipcodePositionDAO = new ZipcodePositionDAO();
	List stateList = zipcodePositionDAO.stateList();
	
	model.addAttribute("stateList", stateList );
	  return new ModelAndView("index","loginMessage","Invalid Username/Password");
	  
  
    
	
   
}




@RequestMapping(value = "/cityListFind", method = RequestMethod.GET)
public ModelAndView cityListFind(Locale locale, Model model,HttpServletRequest request) throws AdException {
	
	String state = request.getParameter("state");
    
	ZipcodePositionDAO zipcodePositionDAO = new ZipcodePositionDAO();
	List cityList = zipcodePositionDAO.cityList(state);
	
	List stateList = new ArrayList<String>();
	
	model.addAttribute("stateList", stateList );
	model.addAttribute("cityList", cityList );
	model.addAttribute("stateName", state );
	return new ModelAndView("adminWorkArea");
	
	
	
	
}



@RequestMapping(value = "/zipcodeListFind", method = RequestMethod.GET)
public ModelAndView zipcodeListFind(Locale locale, Model model,HttpServletRequest request) throws AdException {
	
	String state = request.getParameter("state");
	String city = request.getParameter("city");
    
	ZipcodePositionDAO zipcodePositionDAO = new ZipcodePositionDAO();
	List zipcodeList = zipcodePositionDAO.zipcodeList(state,city);
	
	List stateList = new ArrayList<String>();
	List cityList = new ArrayList<String>();
	
	model.addAttribute("stateList", stateList );
	model.addAttribute("cityList", cityList );
	model.addAttribute("zipcodeList", zipcodeList );
	model.addAttribute("stateName", state );
	model.addAttribute("cityName", city );
	return new ModelAndView("adminWorkArea");
	
	
	
	
}



@RequestMapping(value="/logout.htm")
public ModelAndView logout(HttpServletRequest request,Model model) throws AdException
{	
	HttpSession session = request.getSession();
	session.removeAttribute("account");
	session.removeAttribute("department");
	
  	ZipcodePositionDAO zipcodePositionDAO = new ZipcodePositionDAO();
	List stateList = zipcodePositionDAO.stateList();

	 model.addAttribute("stateList", stateList );

return new ModelAndView("index");
}

	




@RequestMapping(value = "/cityListFindIndex", method = RequestMethod.GET)
public ModelAndView cityListFindIndex(Locale locale, Model model,HttpServletRequest request) throws AdException {
	
	String state = request.getParameter("state");
    
	ZipcodePositionDAO zipcodePositionDAO = new ZipcodePositionDAO();
	List cityList = zipcodePositionDAO.cityList(state);
	
	List stateList = new ArrayList<String>();
	
	model.addAttribute("stateList", stateList );
	model.addAttribute("cityList", cityList );
	model.addAttribute("stateName", state );
	return new ModelAndView("index");
	
	
	
	
}


@RequestMapping(value = "/zipcodeListFindIndex", method = RequestMethod.GET)
public ModelAndView zipcodeListFindIndex(Locale locale, Model model,HttpServletRequest request) throws AdException {
	
	String state = request.getParameter("state");
	String city = request.getParameter("city");
    
	ZipcodePositionDAO zipcodePositionDAO = new ZipcodePositionDAO();
	List zipcodeList = zipcodePositionDAO.zipcodeList(state,city);
	
	List stateList = new ArrayList<String>();
	List cityList = new ArrayList<String>();
	
	model.addAttribute("stateList", stateList );
	model.addAttribute("cityList", cityList );
	model.addAttribute("zipcodeList", zipcodeList );
	model.addAttribute("stateName", state );
	model.addAttribute("cityName", city );
	return new ModelAndView("index");
	
	
}



@RequestMapping(value = "/browseProducts", method = RequestMethod.POST)
public ModelAndView browseProducts(Locale locale, Model model,HttpServletRequest request) throws AdException {

	HttpSession session = request.getSession();
	
	
	String state;
	String city;
	String zipcode;
	
	if(session.getAttribute("state")
			==null){
		
	state = request.getParameter("state");
	city = request.getParameter("city");
	zipcode = request.getParameter("zipcode");
	
	}else{
		
		 state = (String) session.getAttribute("state");
		 city = (String) session.getAttribute("city");
		 zipcode = (String) session.getAttribute("zipcode");
		
	}
	
	ZipcodeDAO zipcodeDAO = new ZipcodeDAO();
	List zipcodeListWithSuppliersPresent = zipcodeDAO.zipcodeWithSuppliersList();
	
	ZipcodePositionDAO zipcodePositionDAO = new ZipcodePositionDAO();

	System.out.print(" lat -"+zipcodePositionDAO.zipcodeLatitude(zipcode));
	
	double givenLatitude = Double.parseDouble(zipcodePositionDAO.zipcodeLatitude(zipcode));
	double givenLongitude = Double.parseDouble(zipcodePositionDAO.zipcodeLongitude(zipcode));
	
	double degreeToRadian = 3.14159265/180.0;
	int earthRadius = 3959;
	
	double lat1 = givenLatitude * degreeToRadian;
	double lon1 = givenLongitude * degreeToRadian;
	
	double lat2;
	double lon2;
	
	SupplierDAO supplierDAO = new SupplierDAO();
	
	Zipcode z = new Zipcode();
	Supplier s = new Supplier();
	
	List<Supplier> validSupplierList = new ArrayList<Supplier>();
	
	for(int i=0;i<zipcodeListWithSuppliersPresent.size();i++){
		
		lat2 = Double.parseDouble(zipcodePositionDAO.zipcodeLatitude(zipcodeListWithSuppliersPresent.get(i).toString())) * degreeToRadian;
		lon2 = Double.parseDouble(zipcodePositionDAO.zipcodeLongitude(zipcodeListWithSuppliersPresent.get(i).toString())) * degreeToRadian;
		
		double distance=Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1-lon2)) * earthRadius;

		//considering average speed of 40miles per hour for 100 miles it will take Approx 2 n half hour 
		// Order restricted till 6 PM. 
		// Office hours from 9AM to 9PM
		
		if(distance<120){
            
			z = zipcodeDAO.returnZipcodeObjectUsingZipcode(zipcodeListWithSuppliersPresent.get(i).toString());
			s = supplierDAO.returnSupplierObjectUsingZipcode(z.getZipcodeId());
			validSupplierList.add(s);
			
        }
		
		
	}
	
	
	session.setAttribute("validSupplierList", validSupplierList);
	session.setAttribute("state", state);
	session.setAttribute("city", city);
	session.setAttribute("zipcode", zipcode);
	
	
	if(request.getParameter("fromBrowsePage")==null){
		
	
	ArrayList<String> inventoryItemId = new ArrayList<String>();
	session.setAttribute("inventoryItemId",inventoryItemId);
	
	ArrayList<String> quantity = new ArrayList<String>();
	session.setAttribute("quantity",quantity);
	
	     }
	
	
	  CategoryDAO categoryDAO = new CategoryDAO();
	  List categoryList = categoryDAO.CategoryList();
	  
	  model.addAttribute("categoryList", categoryList );

	return new ModelAndView("browseProducts");
	
	
}





@RequestMapping(value = "/subCategoryListFindIndex", method = RequestMethod.GET)
public ModelAndView subCategoryListFunction(Locale locale, Model model,HttpServletRequest request) throws AdException {
	
	String categoryVar = request.getParameter("category");
    
	
	CategoryDAO categoryDAO = new CategoryDAO();
	Category categoryObj = categoryDAO.CategoryExist(categoryVar);
			
	SubCategoryDAO subCategoryDAO = new SubCategoryDAO();
	
	List subCategoryList = subCategoryDAO.subCategoryList(categoryObj.getCategoryId());
	
	System.out.println(subCategoryList);
	
	List categoryList = new ArrayList<String>();
	
	model.addAttribute("categoryList", categoryList );
	model.addAttribute("subCategoryList", subCategoryList );
	model.addAttribute("categoryName", categoryVar );
	return new ModelAndView("browseProducts");


}



@RequestMapping(value = "/viewProducts", method = RequestMethod.POST)
public ModelAndView viewProducts(Locale locale, Model model,HttpServletRequest request) throws AdException {

	
	
	String category = request.getParameter("category");
	String subCategory = request.getParameter("subCategory");
	
	InventoryDAO inventoryDAO = new InventoryDAO();
    
	
	HttpSession session = request.getSession();
	
	List<Supplier> validSupplierList = (List<Supplier>)session.getAttribute("validSupplierList");
	
	List<Inventory> productViewList = new ArrayList<Inventory>();
	List<Inventory> finalProductViewList = new ArrayList<Inventory>();
	
	for(int i=0;i<validSupplierList.size();i++){
	
	productViewList = inventoryDAO.productViewList(category, subCategory,validSupplierList.get(i).getSupplierId());
	
	for(int j=0;j<productViewList.size();j++){
		
		finalProductViewList.add(productViewList.get(j));
		
	}
	
	}
	
	model.addAttribute("finalProductViewList", finalProductViewList );
	return new ModelAndView("viewProducts");
}







@RequestMapping(value="/addToCart.htm",method=RequestMethod.POST)
public @ResponseBody String addToCart(@ModelAttribute(value="customer") Customer customer, BindingResult result,HttpServletRequest request) throws AdException{
    String returnText="";
    
    int cost =0;
    
    InventoryDAO inventoryDAO = new InventoryDAO();
    Inventory inventory;
    
    String quantityVar = request.getParameter("quantity");
    String inventoryItemIdVar = request.getParameter("inventoryItemId");
   
    
    HttpSession session = request.getSession();
    
    ArrayList<String> inventoryItemId = (ArrayList<String>) session.getAttribute("inventoryItemId");
    ArrayList<String> quantity = (ArrayList<String>) session.getAttribute("quantity");
    
    if(inventoryItemId.contains(inventoryItemIdVar)){
    	
    	for(int i=0;i<inventoryItemId.size();i++){
    		if(inventoryItemId.get(i).equals(inventoryItemIdVar)){
    			
    			
    			inventory = inventoryDAO.getProductObject(Integer.parseInt(inventoryItemId.get(i)));
    			
    			int available = inventory.getQuantityAvailable();
    			int temp = Integer.parseInt(quantityVar);
    			int alreadyAdded = Integer.parseInt(quantity.get(i));
    			
    			System.out.println("available-"+available);
    			System.out.println("temp-"+temp);
    			System.out.println("alreadyAdded-"+alreadyAdded);
    			
    			
    			if(available>=temp+alreadyAdded){
    				
    				quantity.set(i,String.valueOf(temp+alreadyAdded));
    				
    				
    				
    				returnText = "Product Updated to Cart<br>";
    				
    			}
    			else{
    				
    				returnText = "Product cannot be Updated as It exceeds total available quantity<br>";
    			}
    			
    			
    		}
    	}
    	
    	
    	
    } else{
    	
    	inventoryItemId.add(inventoryItemIdVar);
    	quantity.add(quantityVar);
    	
    	returnText = "Product Added to Cart<br>";
    	
    }
	
    
    

    if(!result.hasErrors()){
    	
    	
    	
        returnText += "<hr>CART:<hr>"
        		+ "<table class=\"gridtable\">"
        		+ "<tr>"
        		+ "<th>Product</th>"
        		+ "<th>Quantity</th>"
        		+ "<th></th>"
        		+ "<tr>";
        
        for(int i=0;i<inventoryItemId.size();i++){
        if(!quantity.get(i).equals("0")){
        	System.out.println(" - "+inventoryItemId.get(i));
        	
        	inventory = inventoryDAO.getProductObject(Integer.parseInt(inventoryItemId.get(i)));
			
        	cost = cost + inventory.getCost()*Integer.parseInt(quantity.get(i));
        	
        	returnText += "<tr>"
        			+ "<td>"+ inventoryItemId.get(i) +"</td>"
        			+ "<td>"+ quantity.get(i) +"</td>"
        			+ "<td>"+ inventory.getCost()*Integer.parseInt(quantity.get(i)) +"</td>"
        			+ "<tr>";
        
        }
        }
        
        returnText +="</table>"
        		+ "Total Cost : "+cost;
        		
        
    }else{
        returnText = "<b>Sorry, an error has occur.<b>";
    }
    
    
    session.setAttribute("inventoryItemId", inventoryItemId);
    session.setAttribute("quantity", quantity);
    
    return returnText;
}





@RequestMapping(value = "/editCart", method = RequestMethod.GET)
public ModelAndView editCart(Locale locale, Model model,HttpServletRequest request) throws AdException {

	String message="";
	
	
	HttpSession session = request.getSession();
	
	ArrayList<String> inventoryItemId = (ArrayList<String>) session.getAttribute("inventoryItemId");
	ArrayList<String> quantity = (ArrayList<String>) session.getAttribute("quantity");
	ArrayList<String> cost = new ArrayList<String>();

	
	InventoryDAO inventoryDAO = new InventoryDAO();
    Inventory inventory;
    
    session.setAttribute("inventoryItemId", inventoryItemId);
    session.setAttribute("quantity", quantity);

    
    for(int i=0;i<inventoryItemId.size();i++){
        
    	inventory = inventoryDAO.getProductObject(Integer.parseInt(inventoryItemId.get(i)));
    	 cost.add(String.valueOf(inventory.getCost()*Integer.parseInt(quantity.get(i))));
    	
    }
	
	
	
	model.addAttribute("cost", cost );
    model.addAttribute("message", message );
	
	
	return new ModelAndView("editCart");
	
	
	
	
}








@RequestMapping(value = "/removeFromCart", method = RequestMethod.GET)
public @ResponseBody ModelAndView removeFromCart(Locale locale, Model model,HttpServletRequest request) throws AdException {

	
	
	System.out.println("inventoryIdItem - "+request.getParameter("inventoryItemId"));
	System.out.println("quantity - "+request.getParameter("quantity"));
	
	String message="";
	
	
	HttpSession session = request.getSession();
	
	ArrayList<String> inventoryItemId = (ArrayList<String>) session.getAttribute("inventoryItemId");
	ArrayList<String> quantity = (ArrayList<String>) session.getAttribute("quantity");
	ArrayList<String> cost = new ArrayList<String>();
	
    InventoryDAO inventoryDAO = new InventoryDAO();
    Inventory inventory;
    
    
    
    for(int i=0;i<inventoryItemId.size();i++){
        
    if(inventoryItemId.get(i).equals(request.getParameter("inventoryItemId"))){
    	
    	if(Integer.parseInt(quantity.get(i))>Integer.parseInt(request.getParameter("quantity"))){
    		
    		quantity.set(i, String.valueOf(Integer.parseInt(quantity.get(i))-Integer.parseInt(request.getParameter("quantity"))));
    		break;
    	}
    	else if(Integer.parseInt(quantity.get(i))==Integer.parseInt(request.getParameter("quantity"))){
    		
    		quantity.set(i,"0");
        	break;
        	
    	}
    	else if(Integer.parseInt(quantity.get(i))<Integer.parseInt(request.getParameter("quantity"))){
    		
    		message = "Cannot Commit this action";
    		
    	}
    	
    	
    	
    }
    
    }
    
    
    session.setAttribute("inventoryItemId", inventoryItemId);
    session.setAttribute("quantity", quantity);
    
    
    
    for(int i=0;i<inventoryItemId.size();i++){
        
    	inventory = inventoryDAO.getProductObject(Integer.parseInt(inventoryItemId.get(i)));
    	 cost.add(String.valueOf(inventory.getCost()*Integer.parseInt(quantity.get(i))));
    	
    }
	
	
	  
    model.addAttribute("cost", cost );
    model.addAttribute("message", message );
	
	
	return new ModelAndView("editCart");
	

}



@RequestMapping(value = "/checkout", method = RequestMethod.POST)
public @ResponseBody ModelAndView checkout(Locale locale, Model model,HttpServletRequest request) throws AdException {

	HttpSession session = request.getSession();

	ArrayList<String> inventoryItemId = (ArrayList<String>) session.getAttribute("inventoryItemId");
	ArrayList<String> quantity = (ArrayList<String>) session.getAttribute("quantity");
	
	InventoryDAO inventoryDAO = new InventoryDAO();
    Inventory inventory;
	
    int count = 0;
    
	 for(int i=0;i<inventoryItemId.size();i++){
		 
		 if(Integer.parseInt(quantity.get(i))!=0){
			 count++;
		 }
     	
	    }
	
	if(count!=0){
	return new ModelAndView("checkout");
}else{
	return new ModelAndView("editCart");
}
	
}


@RequestMapping(value = "/placeOrderRegister", method = RequestMethod.POST)
public ModelAndView placeOrderRegister(Locale locale, Model model,HttpServletRequest request) throws AdException {



	HttpSession session = request.getSession();


		
		String address = request.getParameter("address");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        int countryId = 1;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println("username - "+username);
        System.out.println("password - "+password);
        
        System.out.println(address+email+firstName+lastName+phoneNumber+countryId+username+password);
        
        CountryDAO countryDAO = new CountryDAO();
        Country country = countryDAO.returnCountryObject(countryId);
        
        
       
        CustomerDAO customerDAO = new CustomerDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        EcoSystemDAO ecoSystemDAO = new EcoSystemDAO();
        
        if(employeeDAO.AccountExist(username).equals("no") && customerDAO.AccountExist(username).equals("no") && ecoSystemDAO.AccountExist(username).equals("no")){
        
        Customer customer = new Customer();	
        	
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhoneNumber(phoneNumber);
        customer.setCountry(country);

        customerDAO.save(customer);
        
        ArrayList<String> inventoryItemId = (ArrayList<String>) session.getAttribute("inventoryItemId");
    	ArrayList<String> quantity = (ArrayList<String>) session.getAttribute("quantity");
    	ArrayList<String> cost = new ArrayList<String>();

    	
    	InventoryDAO inventoryDAO = new InventoryDAO();
        Inventory inventory;
        
        session.setAttribute("inventoryItemId", inventoryItemId);
        session.setAttribute("quantity", quantity);

        int totalCost = 0;
        
        for(int i=0;i<inventoryItemId.size();i++){
            
        	inventory = inventoryDAO.getProductObject(Integer.parseInt(inventoryItemId.get(i)));
        	 cost.add(String.valueOf(inventory.getCost()*Integer.parseInt(quantity.get(i))));
        	
         	 totalCost = totalCost + inventory.getCost()*Integer.parseInt(quantity.get(i));
	    }
        
      
        session.setAttribute("account",customerDAO.loginCheck(username, password));
  	    session.setAttribute("department","Customer");
  	  
		System.out.println("account - "+customerDAO.loginCheck(username, password).getFirsName());
  	    
	    model.addAttribute("cost", cost );
	    model.addAttribute("totalCost", totalCost );
	           return new ModelAndView("placeOrder");
        
        }
		
		
		 return new ModelAndView("checkout","registerCustomerMessage","Username Already Used! Please try some other username.");
		 
		
	

	 
}




@RequestMapping(value = "/placeOrderLogin", method = RequestMethod.POST)
public ModelAndView placeOrderLogin(Locale locale, Model model,HttpServletRequest request) throws AdException {


	
	
	HttpSession session = request.getSession();
	
	
	String username = request.getParameter("usernameLogin");
    String password = request.getParameter("passwordLogin");

	CustomerDAO customerDAO = new CustomerDAO();
    	    

  
  if(customerDAO.loginCheck(username, password)!=null){
	  
	  session.setAttribute("account",customerDAO.loginCheck(username, password));
	  session.setAttribute("department","Customer");
	  
	  
	  
	    ArrayList<String> inventoryItemId = (ArrayList<String>) session.getAttribute("inventoryItemId");
		ArrayList<String> quantity = (ArrayList<String>) session.getAttribute("quantity");
		ArrayList<String> cost = new ArrayList<String>();

		
		InventoryDAO inventoryDAO = new InventoryDAO();
	    Inventory inventory;
	    
	    session.setAttribute("inventoryItemId", inventoryItemId);
	    session.setAttribute("quantity", quantity);

	    int totalCost = 0;
	    for(int i=0;i<inventoryItemId.size();i++){
	         
	    	inventory = inventoryDAO.getProductObject(Integer.parseInt(inventoryItemId.get(i)));
	    	 cost.add(String.valueOf(inventory.getCost()*Integer.parseInt(quantity.get(i))));
	    	 
	    	 totalCost = totalCost + inventory.getCost()*Integer.parseInt(quantity.get(i));
	    }
		
		
		
	    model.addAttribute("cost", cost );
	    model.addAttribute("totalCost", totalCost );
	    return new ModelAndView("placeOrder");
	  
	  
  } 
	  
	  return new ModelAndView("checkout","loginCustomerMessage","Invalid Username/Password");
	 
}





@RequestMapping(value="/placeFinalOrder.htm",method=RequestMethod.POST)
public @ResponseBody String placeFinalOrder(@ModelAttribute(value="customer") Customer customer, BindingResult result,HttpServletRequest request) throws AdException{
    String returnText="";
    
    
    
    
	HttpSession session = request.getSession();

	ArrayList<String> inventoryItemId = (ArrayList<String>) session.getAttribute("inventoryItemId");
	ArrayList<String> quantity = (ArrayList<String>) session.getAttribute("quantity");
	
	String country = "USA";
	String state = (String) session.getAttribute("state");
	String city = (String) session.getAttribute("city");
	String zipcode = (String) session.getAttribute("zipcode");
	
	
	String streetAddress = request.getParameter("streetAddress");
	String creditDebitCardNo = request.getParameter("creditDebitCardNo");
	String accHolderName = request.getParameter("accHolderName");
	String securityCode = request.getParameter("securityCode");
	String expirationYear = request.getParameter("expirationYear");
	String expirationMonth = request.getParameter("expirationMonth");
    
	String addressVar = streetAddress+", "+city+", "+state+", "+", "+country+". Zipcode - "+zipcode;
	
	OrderCatalog orderCatalog = new OrderCatalog();
	OrderDAO orderCatalogDAO = new OrderDAO();
	
	

	
	Customer customer1 = (Customer) session.getAttribute("account");
	
	
orderCatalog.setAction("delivery");
orderCatalog.setAddress(addressVar);
orderCatalog.setCustomer(customer1);





Date date = new Date();





Calendar cal = Calendar.getInstance();

Calendar cal1 = Calendar.getInstance();


cal1.set(Calendar.HOUR_OF_DAY, 18);           
cal1.set(Calendar.MINUTE, 0);                 
cal1.set(Calendar.SECOND, 0);                 
cal1.set(Calendar.MILLISECOND, 0); 

Calendar cal2;
cal2 = Calendar.getInstance();


if(cal.before(cal1)){

           
cal2.set(Calendar.HOUR_OF_DAY, 23);            
cal2.set(Calendar.MINUTE, 59);                 
cal2.set(Calendar.SECOND, 59);                 
cal2.set(Calendar.MILLISECOND, 59);            
orderCatalog.setOrderToBeDeliveredTime(new java.sql.Date(cal2.getTimeInMillis()));             
     
 }else{

     
cal2.add (Calendar.DAY_OF_YEAR, 1); 
cal2.set(Calendar.HOUR_OF_DAY, 23);            
cal2.set(Calendar.MINUTE, 59);                 
cal2.set(Calendar.SECOND, 59);                 
cal2.set(Calendar.MILLISECOND, 59);            
orderCatalog.setOrderToBeDeliveredTime(new java.sql.Date(cal2.getTimeInMillis()));             
     
 }


System.out.println(addressVar);
System.out.println(customer1);


orderCatalog.setOrderTime(new java.sql.Date(cal.getTimeInMillis()));             


orderCatalogDAO.save(orderCatalog);






OrderItemDAO orderItemDAO = new OrderItemDAO();

Inventory inventory = new Inventory();
InventoryDAO inventoryDAO = new InventoryDAO();

int invoiceId;

int x = (int)(Math.random() * 999999)+100000 ;
String confirmationCode = String.valueOf(x);

int cost=0;


int count=0;
for(int i=0;i<inventoryItemId.size();i++){
	System.out.println(count);
	count++;
	if(Integer.parseInt(quantity.get(i))!=0){
		
	inventory = inventoryDAO.getProductObject(Integer.parseInt(inventoryItemId.get(i)));
	cost = Integer.parseInt(quantity.get(i))*inventory.getCost();
	
	OrderItem orderItem = new OrderItem();
	
	orderItem.setStatusOfOrderItem("Order Placed");
	orderItem.setOrderCatalog(orderCatalog);
	orderItem.setPenaltyFlag("-");
	orderItem.setCommision(String.valueOf(cost*0.1));
	orderItem.setQuantity(quantity.get(i));
	orderItem.setCost(String.valueOf(cost));
	orderItem.setConfirmationCode(confirmationCode);
	orderItem.setInventory(inventory);
	orderItemDAO.save(orderItem);
	
	inventory.setQuantityAvailable(inventory.getQuantityAvailable()-Integer.parseInt(quantity.get(i)));
	
	inventoryDAO.save(inventory);
	
	
	}
		
}

    if(!result.hasErrors()){
        returnText = "Order Placed Successfully.<br><br><a href=\"index.htm\">Home</a>";
    }else{
        returnText = "<b>Sorry, an error has occur. Please try giving order again.</b>";
    }
    return returnText;
}
   














	
	
	
}
