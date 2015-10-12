package com.me.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.spring.DAO.CityDAO;
import com.me.spring.DAO.CountryDAO;
import com.me.spring.DAO.CustomerDAO;
import com.me.spring.DAO.EcoSystemDAO;
import com.me.spring.DAO.EmployeeDAO;
import com.me.spring.DAO.StateDAO;
import com.me.spring.DAO.SupplierDAO;
import com.me.spring.DAO.ZipcodeDAO;
import com.me.spring.exception.AdException;
import com.me.spring.pojo.City;
import com.me.spring.pojo.Country;
import com.me.spring.pojo.Customer;
import com.me.spring.pojo.Employee;
import com.me.spring.pojo.State;
import com.me.spring.pojo.Supplier;
import com.me.spring.pojo.Zipcode;

@Controller
public class EmployeeController {

	public EmployeeController(){
		
	}
	
	
	
	 @RequestMapping(value="/registerEmployee.htm",method=RequestMethod.POST)
	    public @ResponseBody String addSupplier(@ModelAttribute(value="customer") Customer customer, BindingResult result,HttpServletRequest request) throws AdException{
	        String returnText=null;
	    	
	    	SupplierDAO supplierDAO = new SupplierDAO();
	    	EmployeeDAO employeeDAO = new EmployeeDAO();
	    	CustomerDAO customerDAO = new CustomerDAO();
	    	EcoSystemDAO ecoSystemDAO = new EcoSystemDAO();
	    	
	    	
	    	HttpSession session = request.getSession();
	    	
	    	Employee supplierAdmin = (Employee)session.getAttribute("account");
	    	Supplier supplier = supplierAdmin.getSupplier();
	    	Employee employee = new Employee();
	    	
	    	String address = request.getParameter("address");
	        String email = request.getParameter("email");
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        String phoneNumber = request.getParameter("phoneNumber");
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String department = request.getParameter("department");
	        
	        
	        
	        
	        
	        if(employeeDAO.AccountExist(username).equals("no") && customerDAO.AccountExist(username).equals("no") && ecoSystemDAO.AccountExist(username).equals("no"))
	        	{


	        
	        employee.setAddress(address);
	        employee.setDepartment(department);
	        employee.setEmail(email);
	        employee.setFirstName(firstName);
	        employee.setLastName(lastName);
	        employee.setPassword(password);
	        employee.setPhoneNumber(phoneNumber);
	        employee.setSupplier(supplier);
	        employee.setUsername(username);
	        
	        employeeDAO.save(employee);
	        
	        
	      
	    	


	    if(!result.hasErrors()){
	        returnText = "Employee Account is Successfully Created.";
	    }else{
	        returnText = "Sorry, an error has occur. Please try registering employee again.";
	    }
	    
	    
	        	}
	        	
	        else{
	       	returnText="Username Already Exists";
	       }
	        
	        
	        return returnText;
	    
	  
	 

	    } 
	    
	    
	
	
	
	
	
	
}
