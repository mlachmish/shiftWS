package com.shiftapp.test.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.shiftapp.ws.model.classes.Business;
import com.shiftapp.ws.model.classes.BusinessCategory;
import com.shiftapp.ws.model.classes.BusinessEmployee;
import com.shiftapp.ws.model.classes.BusinessShift;
import com.shiftapp.ws.model.classes.EmployeeMissingShiftResponse;
import com.shiftapp.ws.model.classes.ScheduleCrew;
import com.shiftapp.ws.model.classes.WeeklySchedule;

public class ScenarioBuilder {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public Business buildTestScenario() {
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
		
		Session session = sessionFactory.getCurrentSession();
		session.save(business);
		return business;
	}
}
