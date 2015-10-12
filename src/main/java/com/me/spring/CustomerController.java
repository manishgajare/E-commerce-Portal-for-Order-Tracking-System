package com.me.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.me.spring.DAO.CountryDAO;
import com.me.spring.DAO.CustomerDAO;
import com.me.spring.DAO.EcoSystemDAO;
import com.me.spring.DAO.EmployeeDAO;
import com.me.spring.exception.AdException;
import com.me.spring.pojo.Country;
import com.me.spring.pojo.Customer;

@Controller
public class CustomerController {

	@RequestMapping(value="/registerCustomer.htm",method=RequestMethod.POST)
    public @ResponseBody String addUser(@ModelAttribute(value="customer") Customer customer, BindingResult result,HttpServletRequest request) throws AdException{
        String returnText;
        
        
        
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        int countryId = Integer.parseInt(request.getParameter("countryId"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        CountryDAO countryDAO = new CountryDAO();
        Country country = countryDAO.returnCountryObject(countryId);
        
        
       
        CustomerDAO customerDAO = new CustomerDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        EcoSystemDAO ecoSystemDAO = new EcoSystemDAO();
        
        if(employeeDAO.AccountExist(username).equals("no") && customerDAO.AccountExist(username).equals("no") && ecoSystemDAO.AccountExist(username).equals("no")){
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhoneNumber(phoneNumber);
        customer.setCountry(country);
        
        
        customerDAO.save(customer);
        }
        else{
        	return "<b>Account Name Already Exists. Please slecet other Username.<b>";
        }

        if(!result.hasErrors()){
            returnText = "<b>Customer Account is Successfully Created.<b>";
        }else{
            returnText = "<b>Sorry, an error has occur. Please try Signing Up again.</b>";
        }
        return returnText;
    }

    

	
	
	
	
	
	
	
	
	
	
}
