package com.me.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.me.spring.DAO.InventoryDAO;



@Controller
@RequestMapping(value="/")
public class MyPdfController {
	
	
	@RequestMapping(value="/distMyOrder.htm")
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception {
		 
	
	 	InventoryDAO inventoryDAO = new InventoryDAO();

	 	List inventoryItemList = inventoryDAO.list();
		
		
			return new ModelAndView("PdfDist","inventoryItemList",inventoryItemList);
			
		
		
		}
	
	
	

}
