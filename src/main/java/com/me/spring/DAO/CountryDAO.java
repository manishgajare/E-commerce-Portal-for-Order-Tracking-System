package com.me.spring.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.Country;
import com.me.spring.pojo.Customer;

public class CountryDAO extends DAO{

	// CREATE

			public void save(Country country) throws AdException {
		        try {
		            begin();
		            getSession().save(country);
		            commit();
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Failed to Create Country - "+e);
		        }
		    }
			
			
			
		// READ 
			 public List list() throws AdException {
			        try {
			            begin();
			            Query q = getSession().createQuery("from Country"); 
			            List list = q.list();
			            commit();
			            return list;
			        } catch (HibernateException e) {
			            rollback();
			            throw new AdException("Could not list the Countries", e);
			        }
			    }


			    
		// UPDATE	    
			    public void update(Country country) throws AdException
				{
					try
					{
						begin();
						getSession().update(country);
						commit();
					}
					catch(Exception e)
					{
						System.out.println("Could not update the Country - "+e);
						rollback();
					}
				}
			
		// DELETE	
			
			    public void remove(Country country) throws AdException
				{
					try
					{
						begin();
						getSession().delete(country);
						commit();
					}
					catch(Exception e)
					{
						System.out.println("Failed to Delete Country - "+e);
						rollback();
					}
				}
				    
	// GET COUNTRY OBJECT BY ID		    
			    public Country returnCountryObject(int countryId)
				{
			    	  Session session = getSession();
			    	  Transaction tx=session.beginTransaction();
			    	  Query query=session.createQuery("from Country where countryId = :countryId  order by name ASC");
					  query.setParameter("countryId",countryId);
					  Country country=(Country)query.uniqueResult();
					  tx.commit();
					  return country;
				}
		
	
	
}
