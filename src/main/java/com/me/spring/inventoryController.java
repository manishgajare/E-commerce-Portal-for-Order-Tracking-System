package com.me.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.me.spring.DAO.SubCategoryDAO;
import com.me.spring.DAO.SupplierDAO;
import com.me.spring.exception.AdException;
import com.me.spring.pojo.Category;
import com.me.spring.pojo.Country;
import com.me.spring.pojo.Customer;
import com.me.spring.pojo.Employee;
import com.me.spring.pojo.Inventory;
import com.me.spring.pojo.Supplier;

@Controller
public class inventoryController {




	@RequestMapping(value = "/subCategoryListFind", method = RequestMethod.GET)
	public ModelAndView subCategoryListFind(Locale locale, Model model,HttpServletRequest request) throws AdException {
		
		String categoryVar = request.getParameter("category");
	    
		
		CategoryDAO categoryDAO = new CategoryDAO();
		Category categoryObj = categoryDAO.CategoryExist(categoryVar);
				
		SubCategoryDAO subCategoryDAO = new SubCategoryDAO();
		
		List subCategoryList = subCategoryDAO.subCategoryList(categoryObj.getCategoryId());
		
		List categoryList = new ArrayList<String>();
		
		model.addAttribute("categoryList", categoryList );
		model.addAttribute("subCategoryList", subCategoryList );
		model.addAttribute("categoryName", categoryVar );
		return new ModelAndView("inventoryWorkArea");

	
	}
	
	
	
	@RequestMapping(value="/addProduct.htm",method=RequestMethod.POST)
    public @ResponseBody String addProduct(@ModelAttribute(value="customer") Customer customer, BindingResult result,HttpServletRequest request) throws AdException{
        String returnText;
        
        
        
        
        String name = request.getParameter("name");
        String subCategory = request.getParameter("subCategory");
        String productCode = request.getParameter("productCode");
        String category = request.getParameter("category");
        int cost = Integer.parseInt(request.getParameter("cost"));
        int quantityAvailable = Integer.parseInt(request.getParameter("quantityAvailable"));
        
        HttpSession session = request.getSession();
        
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = (Employee)session.getAttribute("account");
        
        SupplierDAO supplierDAO = new SupplierDAO();
        Supplier supplier = employee.getSupplier();
       
        InventoryDAO inventoryDAO = new InventoryDAO();
        
        
        Inventory inventory = new Inventory();
        
        
        inventory.setCost(cost);
        inventory.setName(name);
        inventory.setCategory(category);
        inventory.setProductCode(productCode);
        inventory.setQuantityAvailable(quantityAvailable);
        inventory.setSubCategory(subCategory);
        inventory.setSupplier(supplier);
        
       
inventoryDAO.save(inventory);

                
       

        if(!result.hasErrors()){
            returnText = "Product was added Successfully";
        }else{
            returnText = "Sorry, an error has occur. Please try adding product again";
        }
        return returnText;
    }

    
	
	
	
	
	
}
