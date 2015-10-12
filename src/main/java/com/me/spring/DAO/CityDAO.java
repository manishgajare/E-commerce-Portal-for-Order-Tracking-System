package com.me.spring.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.City;
import com.me.spring.pojo.Country;
import com.me.spring.pojo.Employee;
import com.me.spring.pojo.State;

public class CityDAO extends DAO{
	
	
	// CREATE

	public void save(City city) throws AdException {
        try {
            begin();
            getSession().save(city);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Failed to Create City - "+e);
        }
    }
	
	
	
// READ 
	 public List list() throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from City"); 
	            List list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not list the Cities", e);
	        }
	    }


	    
// UPDATE	    
	    public void update(City city) throws AdException
		{
			try
			{
				begin();
				getSession().update(city);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Could not update the City - "+e);
				rollback();
			}
		}
	
// DELETE	
	
	    public void remove(City city) throws AdException
		{
			try
			{
				begin();
				getSession().delete(city);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Failed to Delete City - "+e);
				rollback();
			}
		}
		 
	
	 // CITY EXIST QUERY
	    
	    public City CityExist(String name)
		{
			  Session session=getSession();
			  Transaction tx=session.beginTransaction();
			  Query query=session.createQuery("from City where name = :name");
			  query.setParameter("name",name);
			  City city=(City)query.uniqueResult();
			  tx.commit();
			  return city;
		}
	    
	    
	    
	 // GET CITY OBJECT BY NAME and STATE ID		    
	    public City returnCityObject(int stateId,String name)
		{
	    	  Session session = getSession();
	    	  Transaction tx=session.beginTransaction();
	    	  Query query=session.createQuery("from City where stateId = :stateId and name = :name order by name ASC");
	    	  query.setParameter("stateId",stateId);
	    	  query.setParameter("name",name);
			  City city=(City)query.uniqueResult();
			  tx.commit();
			  return city;
		}

	    
	    

}
