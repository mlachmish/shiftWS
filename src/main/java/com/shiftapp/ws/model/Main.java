package com.shiftapp.ws.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.util.compare.EqualsHelper;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mysql.fabric.xmlrpc.base.Array;
import com.shiftapp.ws.model.classes.WeeklySchedule;
import com.shiftapp.ws.model.enums.CountryCodeEnum;

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
//         User u1 = new User("Matan", "Lachmish", "972", "123", "/sad/asd.jpeg");
//         session.persist(u1);
//         User u2 = new User("Mike", "Himi", "972", "555", "/sad/bab.jpeg");
//         session.persist(u2);
//         Business b = new Business("El gaucho", "somewhere", "/asd/logo.jeg");
//         session.persist(b);
//         Map<Long, Integer> crew = new HashMap<>();
//         crew.put((long) 1, 2);
//         crew.put((long) 3, 8);
//         Set<Integer> days = new HashSet<>();
//         days.add(14);
//         days.add(22);
//         BusinessShift shift = new BusinessShift(1, days, Time.valueOf( "08:00:00" ), Time.valueOf( "14:30:00" ), crew);
//         session.persist(shift);
//         
//         Set<Integer> days2 = new HashSet<>();
//         days2.add(214);
//         days2.add(222);
//         BusinessShift shift2 = new BusinessShift(1, days2, Time.valueOf( "18:00:00" ), Time.valueOf( "24:30:00" ), crew);
//         session.persist(shift2);
//         ExtraAbsenceRequest e = new ExtraAbsenceRequest(3, 2, "just cause", RequestStatusEnum.Pending);
//         session.persist(e);
         
//         WeeklySchedule ws = new WeeklySchedule(1, new Date(), null, RequestStatusEnum.Approved);
//         ScheduleCrew crew1 = new ScheduleCrew((long)1, (long)1, (long)1, WeekDayEnum.Sun, Arrays.asList((long)1,(long)2,(long)3,(long)4), ws);
//         ScheduleCrew crew2 = new ScheduleCrew(1, 1, 2, WeekDayEnum.Sun, Arrays.asList((long)5,(long)6,(long)7,(long)8), ws);
//         ws.setSchedule(Arrays.asList(crew1,crew2));
//         session.persist(ws);
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      
      tx = null;
      session = sessionFactory.openSession();
      try{
          tx = session.beginTransaction();
          Criteria cr = session.createCriteria(WeeklySchedule.class).add(Restrictions.eq("businessId", (long)1));
          List list = cr.list();
          for (WeeklySchedule weeklySchedule : (List<WeeklySchedule>)list) {
			System.out.println("!@#!@# " + weeklySchedule);
			System.out.println(weeklySchedule.getSchedule());
		}
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