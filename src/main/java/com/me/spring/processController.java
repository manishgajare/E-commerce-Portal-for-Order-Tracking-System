package com.me.spring;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.spring.DAO.OrderDAO;
import com.me.spring.DAO.OrderItemDAO;
import com.me.spring.exception.AdException;
import com.me.spring.pojo.Customer;
import com.me.spring.pojo.Employee;
import com.me.spring.pojo.OrderCatalog;
import com.me.spring.pojo.OrderItem;

@Controller
public class processController {

	
	@RequestMapping(value = "/financeApproval", method = RequestMethod.POST)
	public ModelAndView financeApproval(Locale locale, Model model,HttpServletRequest request) throws AdException {

		String returnMessage="";
		
		String orderItemId = request.getParameter("orderItemId");
	    
		
			OrderItemDAO orderItemDAO = new OrderItemDAO();
			OrderItem orderItemObject = new OrderItem();
		    orderItemObject = orderItemDAO.orderItemIdObject(Integer.parseInt(orderItemId));
		    
		    HttpSession session = request.getSession();
		    Employee emp = (Employee) session.getAttribute("account");
		    
		    if("Order Placed".equals(orderItemObject.getStatusOfOrderItem())){
			    
		    orderItemObject.setStatusOfOrderItem("Under Processing");
		    orderItemObject.setFinanceEmployee(emp);
		    orderItemDAO.update(orderItemObject);
		    }
		    else if("Products returned - Refund Approval".equals(orderItemObject.getStatusOfOrderItem())){
			    
		    orderItemObject.setStatusOfOrderItem("Money Refunded");
		    orderItemObject.setFinanceReturnManagementEmployee(emp);
		    orderItemDAO.update(orderItemObject);
		    }
		    else{
		    	returnMessage="You are not authorised to change status now";
			    	
		    }
		    
		    
		    List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		  
		    listOfOrderItems = orderItemDAO.list();

		  
		    model.addAttribute("returnMessage", returnMessage );
		    model.addAttribute("listOfOrderItems", listOfOrderItems );
		    return new ModelAndView("financeWorkArea");
		  
		  
	}



	
	
	@RequestMapping(value = "/salesApproval", method = RequestMethod.POST)
	public ModelAndView salesApproval(Locale locale, Model model,HttpServletRequest request) throws AdException {

		String returnMessage="";
		
		String orderItemId = request.getParameter("orderItemId");
	    
		HttpSession session = request.getSession();
	    Employee emp = (Employee) session.getAttribute("account");
	    
		
			OrderItemDAO orderItemDAO = new OrderItemDAO();
			OrderItem orderItemObject = new OrderItem();
		    orderItemObject = orderItemDAO.orderItemIdObject(Integer.parseInt(orderItemId));
		    
		    if("Under Processing".equals(orderItemObject.getStatusOfOrderItem())){
		    orderItemObject.setStatusOfOrderItem("Packaging Done");
		    orderItemObject.setSalesEmployee(emp);
		    orderItemDAO.update(orderItemObject);
		    }
		    else if("Returned - Refund Request".equals(orderItemObject.getStatusOfOrderItem())){
		    	
		    	orderItemObject.setStatusOfOrderItem("Products returned - Refund Approval");
			    orderItemObject.setSalesReturnManagementEmployee(emp);
			    orderItemDAO.update(orderItemObject);	
		    	
		    }
		    else{
		    	returnMessage="You are not authorised to change status now";
			    	
		    }
		    
		    List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		  
		    listOfOrderItems = orderItemDAO.list();

		  
		    model.addAttribute("returnMessage", returnMessage );
		    model.addAttribute("listOfOrderItems", listOfOrderItems );
		    return new ModelAndView("salesWorkArea");
		  
		  
	}


	

	@RequestMapping(value = "/shippingApproval", method = RequestMethod.POST)
	public ModelAndView shippingApproval(Locale locale, Model model,HttpServletRequest request) throws AdException {

		String returnMessage="";
		
		String orderItemId = request.getParameter("orderItemId");
	    
		
		HttpSession session = request.getSession();
	    Employee emp = (Employee) session.getAttribute("account");
	    
		
			OrderItemDAO orderItemDAO = new OrderItemDAO();
			OrderItem orderItemObject = new OrderItem();
		    orderItemObject = orderItemDAO.orderItemIdObject(Integer.parseInt(orderItemId));
		   
		    if("Packaging Done".equals(orderItemObject.getStatusOfOrderItem())){
		    orderItemObject.setStatusOfOrderItem("Dispatched For Delivery");
		    orderItemObject.setShippingEmployee(emp);
		    orderItemDAO.update(orderItemObject);
		    returnMessage="Order Status Changed Successfully";
		    }
		    else{
		    	returnMessage="You are not authorised to change status now";
			    	
		    }
		    
		    List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		  
		    listOfOrderItems = orderItemDAO.list();

		  
		    model.addAttribute("returnMessage", returnMessage );
		    model.addAttribute("listOfOrderItems", listOfOrderItems );
		    return new ModelAndView("shippingWorkArea");
		  
		  
	}

	
	
	@RequestMapping(value = "/shippingDone", method = RequestMethod.POST)
	public ModelAndView shippingDone(Locale locale, Model model,HttpServletRequest request) throws AdException {

		String returnMessage="";
		
		String orderItemId = request.getParameter("orderItemId");
		String confirmationCode = request.getParameter("confirmationCode");
	    
		
		HttpSession session = request.getSession();
	    Employee emp = (Employee) session.getAttribute("account");
	    
		
			OrderItemDAO orderItemDAO = new OrderItemDAO();
			OrderItem orderItemObject = new OrderItem();
		    orderItemObject = orderItemDAO.orderItemIdObject(Integer.parseInt(orderItemId));
		    
		    
		    if(orderItemObject.getConfirmationCode().equals(confirmationCode) && "Dispatched For Delivery".equals(orderItemObject.getStatusOfOrderItem())  &&  orderItemObject.getShippingEmployee().getEmployeeId()==emp.getEmployeeId()){
		    orderItemObject.setStatusOfOrderItem("Delivered");
		    
		    Calendar cal = Calendar.getInstance();
		    
		    Date date = new java.sql.Date(cal.getTimeInMillis());
		    
		    orderItemObject.setActualDeliveryTime(date);
		    
		    if(date.equals(orderItemObject.getOrderCatalog().getOrderToBeDeliveredTime())  || date.before(orderItemObject.getOrderCatalog().getOrderToBeDeliveredTime()) ){
		    	
		    	orderItemObject.setPenaltyFlag(String.valueOf(Integer.parseInt(orderItemObject.getCost())*0.1));
		    	
		    }else{
		    	
		    	orderItemObject.setPenaltyFlag("No Penalty");
		    	
		    }
		    
		    orderItemDAO.update(orderItemObject);
		    returnMessage="Order Status Changed Successfully";
		    }
		    else{
		    	
		    	
		    	
		    		returnMessage="Confirmation Code did not match / You are not authorised to change status now";
		    	
			    	
		    }
		    
		    List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		  
		    listOfOrderItems = orderItemDAO.list();

		  
		    model.addAttribute("returnMessage", returnMessage );
		    model.addAttribute("listOfOrderItems", listOfOrderItems );
		    return new ModelAndView("shippingWorkArea");
		  
		  
	}

	
	
	
	
	@RequestMapping(value = "/returnRefundRequest", method = RequestMethod.POST)
	public ModelAndView returnRefundRequest(Locale locale, Model model,HttpServletRequest request) throws AdException {

		String returnMessage="";
		
		String orderItemId = request.getParameter("orderItemId");
	    
		
		HttpSession session = request.getSession();
	    Customer cus = (Customer) session.getAttribute("account");
	    
		
			OrderItemDAO orderItemDAO = new OrderItemDAO();
			OrderItem orderItemObject = new OrderItem();
		    orderItemObject = orderItemDAO.orderItemIdObject(Integer.parseInt(orderItemId));
		   
		    if("Delivered".equals(orderItemObject.getStatusOfOrderItem())){
		    orderItemObject.setStatusOfOrderItem("Returned - Refund Request");
		    orderItemDAO.update(orderItemObject);
		    returnMessage="Order Status Changed Successfully";
		    }
		    else{
		    	returnMessage="You are not authorised to change status now";
			    	
		    }
		    
		   

			  Customer customer = (Customer) session.getAttribute("account");
			  
			  OrderDAO orderDAO = new OrderDAO();
			  
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

	
	
	
	
	
	
	
	
}
