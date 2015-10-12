package com.me.spring.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.SubCategory;
import com.me.spring.pojo.Category;
import com.me.spring.pojo.Zipcode;

public class CategoryDAO extends DAO{

	

	
	

	// CREATE

	public void save(Category category) throws AdException {
        try {
            begin();
            getSession().save(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Failed to Create Category - "+e);
        }
    }
	
	
	
// READ 
	 public List list() throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from Category"); 
	            List list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not list the Categories", e);
	        }
	    }


	    
// UPDATE	    
	    public void update(Category category) throws AdException
		{
			try
			{
				begin();
				getSession().update(category);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Could not update the Category - "+e);
				rollback();
			}
		}
	
// DELETE	
	
	    public void remove(Category category) throws AdException
		{
			try
			{
				begin();
				getSession().delete(category);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Failed to Delete Category - "+e);
				rollback();
			}
		}
		 
	
// CATEGORY EXIST QUERY
	    
	    public Category CategoryExist(String name)
		{
			  Session session=getSession();
			  Transaction tx=session.beginTransaction();
			  Query query=session.createQuery("from Category where name = :name");
			  query.setParameter("name",name);
			  Category categoryObj = (Category)query.uniqueResult();
			  tx.commit();
			  return categoryObj;
		}

	
// GET CATEGORY  LIST
	    
		 public List CategoryList() throws AdException {
		        try {
		            begin();
		            Query q = getSession().createSQLQuery("select name from Category");
			    	List list = q.list();
		            commit();
		            return list;
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Could not list the suCategories ", e);
		        }
		    }
	    

	
	
}
