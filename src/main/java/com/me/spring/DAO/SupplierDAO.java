package com.me.spring.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.Employee;
import com.me.spring.pojo.Supplier;
import com.me.spring.pojo.Zipcode;

public class SupplierDAO extends DAO{

	

	// CREATE

		public void save(Supplier supplier) throws AdException {
	        try {
	            begin();
	            getSession().save(supplier);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Failed to save Supplier - "+e);
	        }
	    }
		
		
		
	// READ 
		 public List list() throws AdException {
		        try {
		            begin();
		            Query q = getSession().createQuery("from Supplier");
		            List list = q.list();
		            commit();
		            return list;
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Could not list the Suppliers", e);
		        }
		    }


		    
	// UPDATE	    
		    public void update(Supplier supplier) throws AdException
			{
				try
				{
					begin();
					getSession().update(supplier);
					commit();
				}
				catch(Exception e)
				{
					System.out.println("Could not update the Supplier Details - "+e);
					rollback();
				}
			}
		
	// DELETE	
		
		    public void remove(Supplier supplier) throws AdException
			{
				try
				{
					begin();
					getSession().delete(supplier);
					commit();
				}
				catch(Exception e)
				{
					System.out.println("Failed to Delete Supplier Account - "+e);
					rollback();
				}
			}
		    
		    
		    
		 // GET SUPPLIER OBJECT BY ZIPCODE		    
		    public Supplier returnSupplierObjectUsingZipcode(int zipcodeId)
			{
		    	  Session session = getSession();
		    	  Transaction tx=session.beginTransaction();
		    	  Query query=session.createQuery("from Supplier where zipcodeId = :zipcodeId");
		    	  query.setParameter("zipcodeId",zipcodeId);
				  Supplier supplierObj=(Supplier)query.uniqueResult();
				  tx.commit();
				  return supplierObj;
			}
	
		    
		
	
}
