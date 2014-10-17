package com.shiftapp.ws.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiftapp.ws.model.User;

@Path("/signup")
public class SignUpAPI {

	@Autowired
	private SessionFactory sessionFactory;
	
	@GET
	@Produces({"application/json"})
	public Response login(@QueryParam("phoneNumber") String phoneNumber) {

		List results = null;
		
		Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Criteria cr = session.createCriteria(User.class);
	         cr.add(Restrictions.eq("phoneNumber", phoneNumber));
	         results = cr.list();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      
	      String output = "shift ws say : ";

		return Response.status(200).entity(results).build();

	}

}
