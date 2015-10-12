package com.me.spring.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Controller;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.Inventory;
import com.me.spring.pojo.ZipcodePosition;


public class InventoryDAO extends DAO{

	
	
	// CREATE

		public void save(Inventory inventory) throws AdException {
	        try {
	            begin();
	            getSession().save(inventory);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Failed to add Product - "+e);
	        }
	    }
		
		
		
	// READ 
		 public List list() throws AdException {
		        try {
		            begin();
		            Query q = getSession().createQuery("from Inventory");
		            List list = q.list();
		            commit();
		            return list;
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Could not list the Products", e);
		        }
		    }


		    
	// UPDATE	    
		    public void update(Inventory inventory) throws AdException
			{
				try
				{
					begin();
					getSession().update(inventory);
					commit();
				}
				catch(Exception e)
				{
					System.out.println("Could not update the Product - "+e);
					rollback();
				}
			}
		
	// DELETE	
		
		    public void remove(Inventory inventory) throws AdException
			{
				try
				{
					begin();
					getSession().delete(inventory);
					commit();
				}
				catch(Exception e)
				{
					System.out.println("Failed to Delete Product - "+e);
					rollback();
				}
			}
		    

	
		 // PRODUCT VIEW LIST 
			 public List productViewList(String category, String subCategory, int supplierId) throws AdException {
			        try {
			            begin();
			            Query q = getSession().createQuery("from Inventory where supplierId = :supplierId and category = :category and subCategory = :subCategory");
			            q.setParameter("category",category);
			            q.setParameter("subCategory",subCategory);
			            q.setParameter("supplierId",supplierId);
						 List list = q.list();
			            commit();
			            return list;
			        } catch (HibernateException e) {
			            rollback();
			            throw new AdException("Could not list the Products view list ", e);
			        }
			    }
		    
		    
			 
			// GET PRODUCT OBJECT BY INVENTORY ITEM ID 
			 public Inventory getProductObject(int inventoryItemId) throws AdException {
			        try {
			            begin();
			            Query q = getSession().createQuery("from Inventory where inventoryItemId = :inventoryItemId ");
			            q.setParameter("inventoryItemId",inventoryItemId);
			            Inventory inventory = (Inventory) q.uniqueResult();
			            commit();
			            return inventory;
			        } catch (HibernateException e) {
			            rollback();
			            throw new AdException("Could not return the Product Object", e);
			        }
			    }
			 
		    
	
}
