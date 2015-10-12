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

public class SubCategoryDAO extends DAO{
	
	
	
	// CREATE

	public void save(SubCategory subCategory) throws AdException {
        try {
            begin();
            getSession().save(subCategory);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Failed to Create SubCategory - "+e);
        }
    }
	
	
	
// READ 
	 public List list() throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from SubCategory"); 
	            List list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not list the SubCategory", e);
	        }
	    }


	    
// UPDATE	    
	    public void update(SubCategory subCategory) throws AdException
		{
			try
			{
				begin();
				getSession().update(subCategory);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Could not update the SubCategory - "+e);
				rollback();
			}
		}
	
// DELETE	
	
	    public void remove(SubCategory subCategory) throws AdException
		{
			try
			{
				begin();
				getSession().delete(subCategory);
				commit();
			}
			catch(Exception e)
			{
				System.out.println("Failed to Delete SubCategory - "+e);
				rollback();
			}
		}
		 
	
// SUBCATEGORY EXIST QUERY
	    
	    public SubCategory SubCategoryExist(String name)
		{
			  Session session=getSession();
			  Transaction tx=session.beginTransaction();
			  Query query=session.createQuery("from SubCategory where name = :name");
			  query.setParameter("name",name);
			  SubCategory subTypeObj = (SubCategory)query.uniqueResult();
			  tx.commit();
			  return subTypeObj;
		}

	
	    
	 // GET SUBCATEGORY LIST BY TYPEID
	    
		 public List subCategoryList(int categoryId) throws AdException {
		        try {
		            begin();
		            Query q = getSession().createSQLQuery("select name from SubCategory where categoryId = :categoryId order by name ASC");
			    	q.setParameter("categoryId",categoryId);
		            List list = q.list();
		            commit();
		            return list;
		        } catch (HibernateException e) {
		            rollback();
		            throw new AdException("Could not list the suCategories ", e);
		        }
		    }
	    
	    
	    


}
