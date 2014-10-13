package com.shiftapp.ws.model;

import java.sql.Time;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Main {
   private static SessionFactory sessionFactory; 
   
   public static void main(String[] args) {
      try{
         Configuration configuration = new Configuration().
                   configure();
         StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder(). applySettings(configuration.getProperties());
         sessionFactory = configuration.buildSessionFactory(builder.build());
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }

      Session session = sessionFactory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         User u1 = new User("Matan", "Lachmish", "972", "123", "/sad/asd.jpeg");
         session.persist(u1);
         User u2 = new User("Mike", "Himi", "972", "555", "/sad/bab.jpeg");
         session.persist(u2);
         Business b = new Business("El gaucho", "somewhere", "/asd/logo.jeg");
         session.persist(b);
         Map<Long, Integer> crew = new HashMap<>();
         crew.put((long) 1, 2);
         crew.put((long) 3, 8);
         Set<Integer> days = new HashSet<>();
         days.add(14);
         days.add(22);
         BusinessShift shift = new BusinessShift(1, days, Time.valueOf( "08:00:00" ), Time.valueOf( "14:30:00" ), crew);
         session.persist(shift);
         ExtraAbsenceRequest e = new ExtraAbsenceRequest(3, 2, "just cause", RequestStatusEnum.Pending);
         session.persist(e);
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
//      sessionFactory.close();
   }
}