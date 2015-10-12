package com.me.spring.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.Category;
import com.me.spring.pojo.OrderCatalog;

public class OrderDAO extends DAO{

	


	// CREATE

	public void save(OrderCatalog orderCatalog) throws AdException {
        try {
            begin();
            getSession().save(orderCatalog);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Failed to Create in OrderCatalog - "+e);
        }
    }
	
	
	
// READ 
	 public List list() throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from OrderCatalog"); 
	            List list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not list the Orders", e);
	        }
	    }


	    
// UPDATE	    
	    public void update(OrderCatalog orderCatalog) throws AdException
		{
			try
			{
				begin();
				getSession().update(orderCatalog);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Could not update the OrderCatalog - "+e);
				rollback();
			}
		}
	
// DELETE	
	
	    public void remove(OrderCatalog orderCatalog) throws AdException
		{
			try
			{
				begin();
				getSession().delete(orderCatalog);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Failed to Delete Order - "+e);
				rollback();
			}
		}
	
	    
	    
	 // ORDERS OF CUSTOMER 
		 public List customerOrderList(int customerId) throws AdException {
		        try {
		            begin();
		            Query q = getSession().createQuery("from OrderCatalog where customerId = :customerId ");
		            q.setParameter("customerId",customerId);
					List list = q.list();
		            commit();
		            return list;
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Could not list the Orders of customer", e);
		        }
		    }
	

		
	

		 
		 
}
