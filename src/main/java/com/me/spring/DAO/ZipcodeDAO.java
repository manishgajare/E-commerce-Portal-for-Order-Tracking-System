package com.me.spring.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.City;
import com.me.spring.pojo.State;
import com.me.spring.pojo.Zipcode;

public class ZipcodeDAO extends DAO{

	
	
	

	// CREATE

	public void save(Zipcode zipcode) throws AdException {
        try {
            begin();
            getSession().save(zipcode);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Failed to Create Zipcode - "+e);
        }
    }
	
	
	
// READ 
	 public List list() throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from Zipcode"); 
	            List list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not list the Zipcodes", e);
	        }
	    }


	    
// UPDATE	    
	    public void update(Zipcode zipcode) throws AdException
		{
			try
			{
				begin();
				getSession().update(zipcode);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Could not update the Zipcode - "+e);
				rollback();
			}
		}
	
// DELETE	
	
	    public void remove(Zipcode zipcode) throws AdException
		{
			try
			{
				begin();
				getSession().delete(zipcode);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Failed to Delete Zipcode - "+e);
				rollback();
			}
		}
		 
	
// ZIPCODE EXIST QUERY
	    
	    public Zipcode ZipcodeExist(String zipcode)
		{
			  Session session=getSession();
			  Transaction tx=session.beginTransaction();
			  Query query=session.createQuery("from Zipcode where zipcode = :zipcode");
			  query.setParameter("zipcode",zipcode);
			  Zipcode zipcodeObj=(Zipcode)query.uniqueResult();
			  tx.commit();
			  return zipcodeObj;
		}

	
// GET ZIPCODE OBJECT BY ZIPCODE and CITY ID		    
	    public Zipcode returnZipcodeObject(int cityId,String zipcode)
		{
	    	  Session session = getSession();
	    	  Transaction tx=session.beginTransaction();
	    	  Query query=session.createQuery("from Zipcode where cityId = :cityId and zipcode = :zipcode order by zipcode ASC");
	    	  query.setParameter("cityId",cityId);
	    	  query.setParameter("zipcode",zipcode);
			  Zipcode zipcodeObj=(Zipcode)query.uniqueResult();
			  tx.commit();
			  return zipcodeObj;
		}
	    
	   
	 // ZIPCODE LIST WHERE SUPPLIERS ARE PRESENT
	    
		 public List zipcodeWithSuppliersList() throws AdException {
		        try {
		            begin();
		            Query q = getSession().createSQLQuery("select DISTINCT zipcode from Zipcode");
		            List list = q.list();
		            commit();
		            return list;
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Could not list the zipcodes where suppliers are present ", e);
		        }
		    }
	
	
	
		// GET ZIPCODE OBJECT BY ZIPCODE		    
		    public Zipcode returnZipcodeObjectUsingZipcode(String zipcode)
			{
		    	  Session session = getSession();
		    	  Transaction tx=session.beginTransaction();
		    	  Query query=session.createQuery("from Zipcode where zipcode = :zipcode order by zipcode ASC");
		    	  query.setParameter("zipcode",zipcode);
				  Zipcode zipcodeObj=(Zipcode)query.uniqueResult();
				  tx.commit();
				  return zipcodeObj;
			}
		
		 
		 
}
