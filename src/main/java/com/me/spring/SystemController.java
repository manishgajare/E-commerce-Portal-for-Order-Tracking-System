package com.me.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.me.spring.DAO.CityDAO;
import com.me.spring.DAO.CountryDAO;
import com.me.spring.DAO.CustomerDAO;
import com.me.spring.DAO.EcoSystemDAO;
import com.me.spring.DAO.EmployeeDAO;
import com.me.spring.DAO.StateDAO;
import com.me.spring.DAO.SupplierDAO;
import com.me.spring.DAO.ZipcodeDAO;
import com.me.spring.DAO.ZipcodePositionDAO;
import com.me.spring.pojo.City;
import com.me.spring.pojo.Country;
import com.me.spring.pojo.Customer;
import com.me.spring.pojo.EcoSystem;
import com.me.spring.pojo.Employee;
import com.me.spring.pojo.State;
import com.me.spring.pojo.Supplier;
import com.me.spring.pojo.Zipcode;
import com.me.spring.exception.AdException;

@Controller
public class SystemController {

	public SystemController(){
	
	}
	
	


    @RequestMapping(value="/AddUser.htm",method=RequestMethod.POST)
    public @ResponseBody String addUser(@ModelAttribute(value="system") EcoSystem ecoSystem, BindingResult result,HttpServletRequest request) throws AdException{
        String returnText;
        
        
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        ecoSystem.setUsername(username);
        ecoSystem.setPassword(password);
        
        
        EcoSystemDAO ecoSystemDAO = new EcoSystemDAO();
    	ecoSystemDAO.save(ecoSystem);
    	
        if(!result.hasErrors()){
            returnText = "User has been added to the list.";
        }else{
            returnText = "Sorry, an error has occur. User has not been added to list.";
        }
        return returnText;
    }


    
    
    
    @RequestMapping(value="/registerSupplier.htm",method=RequestMethod.POST)
    public @ResponseBody String addSupplier(@ModelAttribute(value="customer") Customer customer, BindingResult result,HttpServletRequest request) throws AdException{
        String returnText=null;
    	
    	CountryDAO countryDAO = new CountryDAO();
    	StateDAO stateDAO = new StateDAO();
    	CityDAO cityDAO = new CityDAO();
    	ZipcodeDAO zipcodeDAO = new ZipcodeDAO();
    	SupplierDAO supplierDAO = new SupplierDAO();
    	EmployeeDAO employeeDAO = new EmployeeDAO();
    	CustomerDAO customerDAO = new CustomerDAO();
    	EcoSystemDAO ecoSystemDAO = new EcoSystemDAO();
    	
    	State state = new State();
    	City city = new City();
    	Zipcode zipcode = new Zipcode();
    	Supplier supplier = new Supplier();
    	Employee employee = new Employee();
    	
    	int countryId = Integer.parseInt(request.getParameter("countryId"));
    	int countryVar = countryId;
        Country countryObj = countryDAO.returnCountryObject(countryVar);
        
    	
    	
    	String stateVar = request.getParameter("state");
        
    	String cityVar = request.getParameter("city");
        
    	String zipcodeVar = request.getParameter("zipcode");
    	
    	String supplierVar = request.getParameter("supplierName");
    	
    	String address = request.getParameter("address");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String department = request.getParameter("department");
        
        
        
        
        
        if(employeeDAO.AccountExist(username)=="no" && customerDAO.AccountExist(username)=="no" && ecoSystemDAO.AccountExist(username)=="no")
        	{
       if(stateDAO.StateExist(stateVar)!=null){
    		state = stateDAO.StateExist(stateVar);
    	} else{
    		state.setCountry(countryObj);
    		state.setName(stateVar);
    		stateDAO.save(state);
     	}
    	
        
       if(cityDAO.CityExist(cityVar)!=null){
    		city = cityDAO.CityExist(cityVar);
    	} else{
    		city.setState(state);
    		city.setName(cityVar);
    		cityDAO.save(city);
    	}
        
        
       if(zipcodeDAO.ZipcodeExist(zipcodeVar)!=null){
        	zipcode = zipcodeDAO.ZipcodeExist(zipcodeVar);
    	} else{
    		zipcode.setCity(city);
    		zipcode.setZipcode(zipcodeVar);
    		zipcodeDAO.save(zipcode);
    	}
        
        
        supplier.setName(supplierVar);
        supplier.setZipcode(zipcode);
        supplierDAO.save(supplier);
        
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
        returnText = "Supplier Account is Successfully Created.";
    }else{
        returnText = "Sorry, an error has occur. Please try registering supplier Up again.";
    }
    
    
        	}
        	
        else{
       	returnText="Username Already Exists";
       }
        
        
        return returnText;
    
  
 

    } 
    
    
    
    
    
    
	
	
}
