package com.me.spring.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.Customer;
import com.me.spring.pojo.Employee;
import com.me.spring.pojo.ZipcodePosition;

public class ZipcodePositionDAO extends DAO{

	

	
	// CREATE

		public void save(ZipcodePosition zipcodePosition) throws AdException {
	        try {
	            begin();
	            getSession().save(zipcodePosition);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Failed to add Zipcode Position - "+e);
	        }
	    }
		
		
		
	// READ 
		 public List list() throws AdException {
		        try {
		            begin();
		            Query q = getSession().createQuery("from ZipcodePosition");
		            List list = q.list();
		            commit();
		            return list;
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Could not list the Zipcode Positions", e);
		        }
		    }


		    
	// UPDATE	    
		    public void update(ZipcodePosition zipcodePosition) throws AdException
			{
				try
				{
					begin();
					getSession().update(zipcodePosition);
					commit();
				}
				catch(Exception e)
				{
					System.out.println("Could not update the Zipcode Position Details - "+e);
					rollback();
				}
			}
		
	// DELETE	
		
		    public void remove(ZipcodePosition zipcodePosition) throws AdException
			{
				try
				{
					begin();
					getSession().delete(zipcodePosition);
					commit();
				}
				catch(Exception e)
				{
					System.out.println("Failed to Delete Zipcode Position - "+e);
					rollback();
				}
			}
		    
		    
		// COUNTRY LIST    
		    
		    public List countryList() throws AdException {
		        try {
		            begin();
		            Query q = getSession().createSQLQuery("select DISTINCT country from ZipcodePosition");
		            List list = q.list();
		            commit();
		            return list;
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Could not list the countries", e);
		        }
		    }
	     
		    
		 // STATE LIST 
		    
			 public List stateList() throws AdException {
			        try {
			            begin();
			            Query q = getSession().createSQLQuery("select DISTINCT state from ZipcodePosition");
			            List list = q.list();
			            commit();
			            return list;
			        } catch (HibernateException e) {
			            rollback();
			            throw new AdException("Could not list the states", e);
			        }
			    }
		    
			// CITY LIST 
			    
			 public List cityList(String state) throws AdException {
			        try {
			            begin();
			            Query q = getSession().createSQLQuery("select DISTINCT city from ZipcodePosition where state = :state");
			            q.setParameter("state",state);
			            List list = q.list();
			            commit();
			            return list;
			        } catch (HibernateException e) {
			            rollback();
			            throw new AdException("Could not list the cities", e);
			        }
			    }
		    
			// ZIPCODE LIST 
			    
			 public List zipcodeList(String state,String city) throws AdException {
			        try {
			            begin();
			            Query q = getSession().createSQLQuery("select DISTINCT zipcode from ZipcodePosition where state = :state and city = :city");
			            q.setParameter("state",state);
			            q.setParameter("city",city);
			            List list = q.list();
			            commit();
			            return list;
			        } catch (HibernateException e) {
			            rollback();
			            throw new AdException("Could not list the zipcodes", e);
			        }
			    }
		    

			 
			// GET LATITUDE OF ZIPCODE
			    
			 public String zipcodeLatitude(String zipcode) throws AdException {
			        try {
			            begin();
			            Query q = getSession().createSQLQuery("select DISTINCT latitude from zipcodeposition where zipcode = :zipcode");
			            q.setParameter("zipcode",zipcode);
			            String latitude = (String)q.uniqueResult();
			            commit();
			            return latitude;
			        } catch (HibernateException e) {
			            rollback();
			            throw new AdException("Could not return latitudes", e);
			        }
			    }
			 
			 
			// GET LONGITUDE OF ZIPCODE
			    
			 public String zipcodeLongitude(String zipcode) throws AdException {
			        try {
			            begin();
			            Query q = getSession().createSQLQuery("select DISTINCT longitude from zipcodeposition where zipcode = :zipcode");
			            q.setParameter("zipcode",zipcode);
			            String longitude = (String)q.uniqueResult();
			            commit();
			            return longitude;
			        } catch (HibernateException e) {
			            rollback();
			            throw new AdException("Could not return longitude", e);
			        }
			    }
			 


}
