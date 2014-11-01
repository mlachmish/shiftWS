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
import com.shiftapp.ws.model.classes.Business;
import com.shiftapp.ws.model.classes.BusinessCategory;
import com.shiftapp.ws.model.classes.BusinessEmployee;
import com.shiftapp.ws.model.classes.BusinessShift;
import com.shiftapp.ws.model.classes.EmployeeMissingShiftResponse;
import com.shiftapp.ws.model.classes.ScheduleCrew;
import com.shiftapp.ws.model.classes.User;
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
        User u1 = new User();
        u1.setFirstName("Matan");
        u1.setLastName("Lachmish");
        u1.setCountryCode(CountryCodeEnum.getInstance("+972"));
        u1.setPhoneNumber("123");
        
        session.persist(u1);
 		Business business = new Business();
 		business.setBusinessName("Test Business");
 		business.setAddress("Somewhere");

 		BusinessCategory waiter = new BusinessCategory();
 		waiter.setCategoryName("Waiter");
 		BusinessCategory manager = new BusinessCategory();
 		manager.setCategoryName("Manager");
 		business.addBusinessCategory(waiter);
 		business.addBusinessCategory(manager);
 		
 		BusinessEmployee emp1 = new BusinessEmployee();
 		emp1.addBusinessCategory(waiter);
 		emp1.addBusinessCategory(manager);
 		emp1.setManager(true);
 		BusinessEmployee emp2 = new BusinessEmployee();
 		emp2.addBusinessCategory(waiter);
 		business.addBusinessEmployee(emp1);
 		business.addBusinessEmployee(emp2);
 		
 		ScheduleCrew crew1 = new ScheduleCrew();
 		crew1.setBusinessCategory(waiter);
 		crew1.addBusinessEmployee(emp1);
 		crew1.addBusinessEmployee(emp2);
 		ScheduleCrew crew2 = new ScheduleCrew();
 		crew2.setBusinessCategory(manager);
 		crew2.addBusinessEmployee(emp1);
 		ScheduleCrew crew3 = new ScheduleCrew();
 		crew3.setBusinessCategory(manager);

 		BusinessShift shift1 = new BusinessShift();
 		BusinessShift shift2 = new BusinessShift();
 		shift1.addScheduleCrew(crew1);
 		shift1.addScheduleCrew(crew2);
 		shift2.addScheduleCrew(crew3);
 		
 		WeeklySchedule weekSchedule = new WeeklySchedule();
 		weekSchedule.addBusinessShift(shift1);
 		weekSchedule.addBusinessShift(shift2);
 		business.setWeeklySchedule(weekSchedule);

 		EmployeeMissingShiftResponse missingShiftEmp1 = new EmployeeMissingShiftResponse();
 		missingShiftEmp1.setBusinessEmployee(emp1);
 		missingShiftEmp1.setBusinessShift(shift1);
 		missingShiftEmp1.setReason("At a wedding");
 		EmployeeMissingShiftResponse missingShiftEmp2 = new EmployeeMissingShiftResponse();
 		missingShiftEmp2.setBusinessEmployee(emp2);
 		missingShiftEmp2.setBusinessShift(shift1);
 		missingShiftEmp2.setReason("Have a test");
 		EmployeeMissingShiftResponse missingShiftEmp3 = new EmployeeMissingShiftResponse();
 		missingShiftEmp3.setBusinessEmployee(emp2);
 		missingShiftEmp3.setBusinessShift(shift2);
 		missingShiftEmp3.setReason("Have a test");
 		business.addMissingShift(missingShiftEmp1);
 		business.addMissingShift(missingShiftEmp2);
 		business.addMissingShift(missingShiftEmp3);
 		
 		session.save(business);
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
}