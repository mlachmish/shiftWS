package com.shiftapp.ws.service.weekinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.shiftapp.ws.authentication.IAuthenticator;
import com.shiftapp.ws.exceptions.TokenOutOfDateException;
import com.shiftapp.ws.exceptions.UnauthorizedTokenException;
import com.shiftapp.ws.model.classes.Business;
import com.shiftapp.ws.model.classes.BusinessCategory;
import com.shiftapp.ws.model.classes.BusinessEmployee;
import com.shiftapp.ws.model.classes.BusinessShift;
import com.shiftapp.ws.model.classes.EmployeeMissingShiftResponse;
import com.shiftapp.ws.model.classes.ScheduleCrew;
import com.shiftapp.ws.model.classes.User;
import com.shiftapp.ws.model.classes.WeeklySchedule;

public class WeekInfoService  implements IWeekInfoService{
	
	private static final Logger logger = Logger.getLogger(WeekInfoService.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private IAuthenticator authenticator;

	@Override
	@Transactional
	public WeeklySchedule getWeeklySchedule(String token, long businessId) throws UnauthorizedTokenException, TokenOutOfDateException {
		User user = authenticator.authenticateToken(token);
		
		Session session = sessionFactory.getCurrentSession();
		Business business = (Business) session.get(Business.class, businessId);
		
		if (business != null) {
			return business.getWeeklySchedule();
		}
		logger.error("Could not find weekly schedule for user: " + user + " with business id: " + businessId);
		return null;
	}

	@Override
	@Transactional
	public List<ScheduleCrew> getCrewsForShift(String token, long businessShiftId) throws UnauthorizedTokenException, TokenOutOfDateException {
		User user = authenticator.authenticateToken(token);
		
		Session session = sessionFactory.getCurrentSession();
		BusinessShift businessShift = (BusinessShift) session.get(BusinessShift.class, businessShiftId);
		
		if (businessShift != null) {
			return businessShift.getCrews();
		}

		logger.error("Could not find shift crew for user: " + user + " with business shift id: " + businessShiftId);
		return null;
	}

	@Override
	@Transactional
	public List<BusinessCategory> getBusinessCategories(String token, long businessId) throws UnauthorizedTokenException, TokenOutOfDateException {
		User user = authenticator.authenticateToken(token);
		
		Session session = sessionFactory.getCurrentSession();
		Business business = (Business) session.get(Business.class, businessId);
		
		if (business != null) {
			return business.getBusinessCategories();
		}
		logger.error("Could not find business categories for user: " + user + " with business id: " + businessId);
		return null;
	}

	@Override
	@Transactional
	public Map<BusinessEmployee, String> getBusinessEmployeesWithReasonForCategory(
			String token, long categoryId, long shiftId) throws UnauthorizedTokenException, TokenOutOfDateException {
		authenticator.authenticateToken(token);
		
		Session session = sessionFactory.getCurrentSession();
		BusinessShift businessShift = (BusinessShift) session.get(BusinessShift.class, shiftId);
		
		BusinessCategory businessCategory = (BusinessCategory) session.get(BusinessCategory.class, categoryId);
		List<BusinessEmployee> businessEmployees = businessCategory.getBusinessEmployees();

		Map<BusinessEmployee, String> employeeReasonMap = new HashMap<>();
		for (BusinessEmployee businessEmployee : businessEmployees) {
			Criteria criteria = session.createCriteria(EmployeeMissingShiftResponse.class);
			criteria.add(Restrictions.eq("businessEmployee", businessEmployee));
			criteria.add(Restrictions.eq("businessShift", businessShift));
			@SuppressWarnings("unchecked")
			List<EmployeeMissingShiftResponse> queryResults = criteria.list();
			if (queryResults.size() == 1) {
				EmployeeMissingShiftResponse missingShiftResult = queryResults.iterator().next();
				employeeReasonMap.put(missingShiftResult.getBusinessEmployee(), missingShiftResult.getReason());
			} else if (queryResults.size() > 1){
				logger.error("Found more than one EmployeeMissingShiftResponse for employee: " +businessEmployee);
			}
		}
		
		return employeeReasonMap;
	}

	@Override
	@Transactional
	public boolean addBusinessEmployeeWithCategoryToShift(String token,
			long employeeId, long categoryId, long shiftId) {
		Session session = sessionFactory.getCurrentSession();
		BusinessEmployee businessEmployee = (BusinessEmployee) session.get(BusinessEmployee.class, employeeId);

		ScheduleCrew scheduleCrew = findScheduleCrewForShiftAndCategory(categoryId, shiftId);
		if (scheduleCrew != null) {
			scheduleCrew.addBusinessEmployee(businessEmployee);
			session.save(scheduleCrew);
			return true;
		}
		logger.error("Could not find crew for category id: " + categoryId + " in shift id: " 
		+ shiftId + " in order to remove: " + businessEmployee);
		return false;
	}

	@Override
	@Transactional
	public boolean removeBusinessEmployeeWithCategoryToShift(String token,
			long employeeId, long categoryId, long shiftId) {
		Session session = sessionFactory.getCurrentSession();
		BusinessEmployee businessEmployee = (BusinessEmployee) session.get(BusinessEmployee.class, employeeId);

		ScheduleCrew scheduleCrew = findScheduleCrewForShiftAndCategory(categoryId, shiftId);
		if (scheduleCrew != null) {
			scheduleCrew.removeBusinessEmployee(businessEmployee);
			session.save(scheduleCrew);
			return true;
		}
		logger.error("Could not find crew for category id: " + categoryId + " in shift id: " 
		+ shiftId + " in order to remove: " + businessEmployee);
		return false;
	}

	@Transactional
	private ScheduleCrew findScheduleCrewForShiftAndCategory(long categoryId, long shiftId) {
		Session session = sessionFactory.getCurrentSession();
		BusinessShift businessShift = (BusinessShift) session.get(BusinessShift.class, shiftId);
		List<ScheduleCrew> crewsForshift = businessShift.getCrews();
		
		for (ScheduleCrew scheduleCrew : crewsForshift) {
			if (scheduleCrew.getBusinessCategory().getBusinessCategoryId() == categoryId) {
				return scheduleCrew;
			}
		}
		
		//If now crew is found create one.
		ScheduleCrew newCrew = new ScheduleCrew();
		BusinessCategory businessCategory = (BusinessCategory) session.get(BusinessCategory.class, categoryId);
		newCrew.setBusinessCategory(businessCategory);
		businessShift.addScheduleCrew(newCrew);
		return newCrew;
	}
}
