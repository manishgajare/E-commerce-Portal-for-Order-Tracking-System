package com.me.spring.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.spring.exception.AdException;

import com.me.spring.pojo.OrderItem;

public class OrderItemDAO extends DAO{
	
	
	// CREATE

	public void save(OrderItem orderItem) throws AdException {
        try {
            begin();
            getSession().save(orderItem);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Failed to Create OrderItem - "+e);
        }
    }
	
	
	
// READ 
	 public List list() throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from OrderItem"); 
	            List list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not list the OrderItems", e);
	        }
	    }


	    
// UPDATE	    
	    public void update(OrderItem orderItem) throws AdException
		{
			try
			{
				begin();
				getSession().update(orderItem);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Could not update the OrdeItem - "+e);
				rollback();
			}
		}
	
// DELETE	
	
	    public void remove(OrderItem orderItem) throws AdException
		{
			try
			{
				begin();
				getSession().delete(orderItem);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Failed to Delete OrderItem - "+e);
				rollback();
			}
		}
		 

	    
		 // ORDER ITEMS OF CUSTOMER BY ORDERID 
		 public List customerOrderItemList(int orderId) throws AdException {
		        try {
		            begin();
		            Query q = getSession().createQuery("from OrderItem where orderId = :orderId ");
		            q.setParameter("orderId",orderId);
					List list = q.list();
		            commit();
		            return list;
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Could not list the OrderItems of customer", e);
		        }
		    }


		 
		 // ORDER ITEMS OF SUPPLIER BY ORDERID 
		 public List supplierOrderItemList(int orderId) throws AdException {
		        try {
		            begin();
		            Query q = getSession().createQuery("from OrderItem where orderId = :orderId ");
		            q.setParameter("orderId",orderId);
					List list = q.list();
		            commit();
		            return list;
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Could not list the OrderItems of supplier", e);
		        }
		    }

		 
		 
		 
		 // ORDER ITEM OBJECT BY ORDERITEMID 
		 public OrderItem orderItemIdObject(int orderItemId) throws AdException {
		        try {
		            begin();
		            Query q = getSession().createQuery("from OrderItem where orderItemId = :orderItemId ");
		            q.setParameter("orderItemId",orderItemId);
					OrderItem orderItem = (OrderItem) q.uniqueResult();
		            commit();
		            return orderItem;
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Could not list the OrderItems of supplier", e);
		        }
		    }

		 

}
