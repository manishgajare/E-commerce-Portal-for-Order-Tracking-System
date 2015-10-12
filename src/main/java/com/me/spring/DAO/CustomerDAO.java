package com.me.spring.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.Customer;
import com.me.spring.pojo.EcoSystem;
import com.me.spring.pojo.Employee;

public class CustomerDAO extends DAO {

	
	
	// CREATE

		public void save(Customer customer) throws AdException {
	        try {
	            begin();
	            getSession().save(customer);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Failed to SignUp - "+e);
	        }
	    }
		
		
		
	// READ 
		 public List list() throws AdException {
		        try {
		            begin();
		            Query q = getSession().createQuery("from Customer");
		            List list = q.list();
		            commit();
		            return list;
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Could not list the Customers", e);
		        }
		    }


		    
	// UPDATE	    
		    public void update(Customer customer) throws AdException
			{
				try
				{
					begin();
					getSession().update(customer);
					commit();
				}
				catch(Exception e)
				{
					System.out.println("Could not update the Account Details - "+e);
					rollback();
				}
			}
		
	// DELETE	
		
		    public void remove(Customer customer) throws AdException
			{
				try
				{
					begin();
					getSession().delete(customer);
					commit();
				}
				catch(Exception e)
				{
					System.out.println("Failed to Delete Customer Account - "+e);
					rollback();
				}
			}
			    
		    
// VALIDATE ACCOUNT
		    
		    public Customer loginCheck(String username,String password)
			{
				  Session session=getSession();
				  Transaction tx=session.beginTransaction();
				  Query query=session.createQuery("from Customer where username = :username and password = :password");
				  query.setParameter("username",username);
				  query.setParameter("password",password);
				  Customer customer=(Customer)query.uniqueResult();
				  tx.commit();
				  return customer;
			}
	
	
		 // ACCOUNT EXIST QUERY
		    
		    public String AccountExist(String username)
			{
				  Session session=getSession();
				  Transaction tx=session.beginTransaction();
				  Query query=session.createQuery("from Customer where username = :username");
				  query.setParameter("username",username);
				  Customer customer=(Customer)query.uniqueResult();
				  tx.commit();
				  if(customer!=null)
				  return "yes";
				  else 
				  return "no";
			}

	
	
}
