package com.me.spring.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.City;
import com.me.spring.pojo.Country;
import com.me.spring.pojo.State;

public class StateDAO extends DAO{

	
	
	// CREATE

	public void save(State state) throws AdException {
        try {
            begin();
            getSession().save(state);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Failed to Create State - "+e);
        }
    }
	
	
	
// READ 
	 public List list() throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from State"); 
	            List list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not list the States", e);
	        }
	    }


	    
// UPDATE	    
	    public void update(State state) throws AdException
		{
			try
			{
				begin();
				getSession().update(state);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Could not update the State - "+e);
				rollback();
			}
		}
	
// DELETE	
	
	    public void remove(State state) throws AdException
		{
			try
			{
				begin();
				getSession().delete(state);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Failed to Delete State - "+e);
				rollback();
			}
		}
		 
	
// STATE EXIST QUERY
	    
	    public State StateExist(String name)
		{
			  Session session=getSession();
			  Transaction tx=session.beginTransaction();
			  Query query=session.createQuery("from State where name = :name");
			  query.setParameter("name",name);
			  State state=(State)query.uniqueResult();
			  tx.commit();
			  return state;
		}
	    
	    
	// GET STATE OBJECT BY NAME and COUNTRY ID		    
	    public State returnStateObject(int countryId,String name)
		{
	    	  Session session = getSession();
	    	  Transaction tx=session.beginTransaction();
	    	  Query query=session.createQuery("from State where countryId = :countryId and name = :name order by name ASC");
	    	  query.setParameter("countryId",countryId);
	    	  query.setParameter("name",name);
			  State state=(State)query.uniqueResult();
			  tx.commit();
			  return state;
		}



	
}
