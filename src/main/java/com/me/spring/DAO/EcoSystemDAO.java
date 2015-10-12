package com.me.spring.DAO;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.me.spring.pojo.Customer;
import com.me.spring.pojo.EcoSystem;
import com.me.spring.exception.AdException;

public class EcoSystemDAO extends DAO{

	
	public EcoSystemDAO(){
		
	}

	 
// CREATE

	public void save(EcoSystem ecoSystem) throws AdException {
        try {
            begin();
            getSession().save(ecoSystem);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not save the EcoSystem User - "+e);
        }
    }
	
	
	
// READ 
	 public List list() throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from EcoSystem");
	            List list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not list the EcoSystem Users", e);
	        }
	    }


	    
// UPDATE	    
	    public void update(EcoSystem ecoSystem) throws AdException
		{
			try
			{
				begin();
				getSession().update(ecoSystem);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Could not update the EcoSystem User - "+e);
				rollback();
			}
		}
	
// DELETE	
	
	    public void remove(EcoSystem ecoSystem) throws AdException
		{
			try
			{
				begin();
				getSession().delete(ecoSystem);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Could not update the EcoSystem User - "+e);
				rollback();
			}
		}
		    
	    
	 // VALIDATE ACCOUNT
	    
	    public EcoSystem loginCheck(String username,String password)
		{
			  Session session=getSession();
			  Transaction tx=session.beginTransaction();
			  Query query=session.createQuery("from EcoSystem where username = :username and password = :password");
			  query.setParameter("username",username);
			  query.setParameter("password",password);
			  EcoSystem ecoSystem=(EcoSystem)query.uniqueResult();
			  tx.commit();
			  return ecoSystem;
		}
	    
	    
// ACCOUNT EXIST QUERY
	    
	    public String AccountExist(String username)
		{
			  Session session=getSession();
			  Transaction tx=session.beginTransaction();
			  Query query=session.createQuery("from EcoSystem where username = :username");
			  query.setParameter("username",username);
			  EcoSystem ecoSystem=(EcoSystem)query.uniqueResult();
			  tx.commit();
			  if(ecoSystem!=null)
			  return "yes";
			  else 
			  return "no";
		}

    
	    
	    
	
}
