package com.shiftapp.test.service.weekinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.shiftapp.test.util.ScenarioBuilder;
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
import com.shiftapp.ws.service.weekinfo.IWeekInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config.xml",
"classpath:spring-config-test.xml"})
public class WeekInfoServiceTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private IWeekInfoService weekInfoService;

	@Autowired
	private ScenarioBuilder scenarioBuilder;

	private User testUser;

	private Business business;

	@Before
	@Transactional
	public void init() {
		testUser = new User("TestUser", "TestUser", null, null, null, null, null, "test", null, null);
		Session session = sessionFactory.getCurrentSession();
		session.save(testUser);

		business = scenarioBuilder.buildTestScenario();
	}

	@Test
	@Transactional
	public void getWeekScheduleTest() throws UnauthorizedTokenException, TokenOutOfDateException {
		WeeklySchedule expectedWeeklySchedule = business.getWeeklySchedule();

		WeeklySchedule actualWeeklySchedule = weekInfoService.getWeeklySchedule(testUser.getAccessToken(), business.getBusinessId());
		Assert.assertEquals(expectedWeeklySchedule, actualWeeklySchedule);
	}

	@Test
	@Transactional
	public void getCrewsForShiftTest() throws UnauthorizedTokenException, TokenOutOfDateException {
		List<BusinessShift> businessShifts = business.getWeeklySchedule().getBusinessShifts();
		for (BusinessShift businessShift : businessShifts) {
			List<ScheduleCrew> expectedCrews = businessShift.getCrews();
			List<ScheduleCrew> actualCrews = weekInfoService.getCrewsForShift(testUser.getAccessToken(), businessShift.getBusinessShiftId());
			Assert.assertEquals(expectedCrews, actualCrews);
		}
	}

	@Test
	@Transactional
	public void getBusinessCategoriesTest() throws UnauthorizedTokenException, TokenOutOfDateException {
		List<BusinessCategory> expectedBusinessCategories = business.getBusinessCategories();
		List<BusinessCategory> actualBusinessCategories = weekInfoService.getBusinessCategories(testUser.getAccessToken(), business.getBusinessId());
		Assert.assertEquals(expectedBusinessCategories, actualBusinessCategories);
	}

	@Test
	@Transactional
	public void getBusinessEmployeesWithReasonForCategoryTest() throws UnauthorizedTokenException, TokenOutOfDateException {
		
		Map<BusinessEmployee, String> expectedEmployeeReasonMap = new HashMap<>();
		List<EmployeeMissingShiftResponse> missingShifts = business.getMissingShifts();
		for (EmployeeMissingShiftResponse employeeMissingShiftResponse : missingShifts) {
			if (employeeMissingShiftResponse.getBusinessShift().getBusinessShiftId() == 1) {
				expectedEmployeeReasonMap.put(employeeMissingShiftResponse.getBusinessEmployee(), employeeMissingShiftResponse.getReason());
			}
		}
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(BusinessCategory.class);
		criteria.add(Restrictions.eq("categoryName", "waiter"));
		@SuppressWarnings("unchecked")
		List<BusinessCategory> queryResults1 = criteria.list();
		BusinessCategory waiter = queryResults1.get(0);
		
		Map<BusinessEmployee, String> actualEmployeeReasonMap = weekInfoService.getBusinessEmployeesWithReasonForCategory(testUser.getAccessToken(), waiter.getBusinessCategoryId(), (long)1);
		Assert.assertEquals(expectedEmployeeReasonMap, actualEmployeeReasonMap);
	}
	
	@Test
	@Transactional
	public void addBusinessEmployeeWithCategoryToShiftCategoryExistTest() throws UnauthorizedTokenException, TokenOutOfDateException {

		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(BusinessCategory.class);
		criteria.add(Restrictions.eq("categoryName", "manager"));
		@SuppressWarnings("unchecked")
		List<BusinessCategory> queryResults2 = criteria.list();
		BusinessCategory manager = queryResults2.get(0);

		BusinessEmployee emp3 = new BusinessEmployee();
		emp3.addBusinessCategory(manager);
		session.save(emp3);
		
		List<BusinessShift> businessShifts = business.getWeeklySchedule().getBusinessShifts();
		BusinessShift businessShiftToUpdate = businessShifts.get(0);
		
		weekInfoService.addBusinessEmployeeWithCategoryToShift(testUser.getAccessToken(), emp3.getBusinessEmployeeId(), manager.getBusinessCategoryId(), businessShiftToUpdate.getBusinessShiftId());
		
		for (BusinessShift businessShift : businessShifts) {
			for (ScheduleCrew actualCrew : businessShift.getCrews()) {
				if (actualCrew.getBusinessEmployees().contains(emp3)) {
					return; //Success
				}
			}
		}
		Assert.fail("No crew with new employee found");
	}
	
	@Test
	@Transactional
	public void addBusinessEmployeeWithCategoryToShiftCategoryNotExistTest() throws UnauthorizedTokenException, TokenOutOfDateException {
		
		Session session = sessionFactory.getCurrentSession();
		
		BusinessCategory chef = new BusinessCategory();
		chef.setCategoryName("chef");
		session.save(chef);
		
		BusinessEmployee emp3 = new BusinessEmployee();
		emp3.addBusinessCategory(chef);
		session.save(emp3);
		
		List<BusinessShift> businessShifts = business.getWeeklySchedule().getBusinessShifts();
		BusinessShift businessShiftToUpdate = businessShifts.get(0);
		
		weekInfoService.addBusinessEmployeeWithCategoryToShift(testUser.getAccessToken(), emp3.getBusinessEmployeeId(), chef.getBusinessCategoryId(), businessShiftToUpdate.getBusinessShiftId());
		
		for (BusinessShift businessShift : businessShifts) {
			for (ScheduleCrew actualCrew : businessShift.getCrews()) {
				if (actualCrew.getBusinessEmployees().contains(emp3)) {
					return; //Success
				}
			}
		}
		Assert.fail("No crew with new employee found");
	}
}
