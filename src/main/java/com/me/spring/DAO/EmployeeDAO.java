package com.me.spring.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.Customer;
import com.me.spring.pojo.Employee;

public class EmployeeDAO extends DAO{

	
	
	
	// CREATE

		public void save(Employee employee) throws AdException {
	        try {
	            begin();
	            getSession().save(employee);
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
		            Query q = getSession().createQuery("from Employee");
		            List list = q.list();
		            commit();
		            return list;
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Could not list the Employees", e);
		        }
		    }


		    
	// UPDATE	    
		    public void update(Employee employee) throws AdException
			{
				try
				{
					begin();
					getSession().update(employee);
					commit();
				}
				catch(Exception e)
				{
					System.out.println("Could not update the Employee Details - "+e);
					rollback();
				}
			}
		
	// DELETE	
		
		    public void remove(Employee employee) throws AdException
			{
				try
				{
					begin();
					getSession().delete(employee);
					commit();
				}
				catch(Exception e)
				{
					System.out.println("Failed to Delete Employee Account - "+e);
					rollback();
				}
			}
			    
	// VALIDATE ACCOUNT
		    
		    public Employee loginCheck(String username,String password)
			{
				  Session session=getSession();
				  Transaction tx=session.beginTransaction();
				  Query query=session.createQuery("from Employee where username = :username and password = :password");
				  query.setParameter("username",username);
				  query.setParameter("password",password);
				  Employee employee=(Employee)query.uniqueResult();
				  tx.commit();
				  return employee;
			}
	
 // ACCOUNT EXIST QUERY
		    
		    public String AccountExist(String username)
			{
				  Session session=getSession();
				  Transaction tx=session.beginTransaction();
				  Query query=session.createQuery("from Employee where username = :username");
				  query.setParameter("username",username);
				  Employee employee=(Employee)query.uniqueResult();
				  tx.commit();
				  if(employee!=null)
				  return "yes";
				  else 
				  return "no";
			}
		    
		    

	
}
