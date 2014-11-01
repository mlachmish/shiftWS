package com.shiftapp.ws.service.weekinfo;

import java.util.List;
import java.util.Map;

import com.shiftapp.ws.exceptions.TokenOutOfDateException;
import com.shiftapp.ws.exceptions.UnauthorizedTokenException;
import com.shiftapp.ws.model.classes.BusinessCategory;
import com.shiftapp.ws.model.classes.BusinessEmployee;
import com.shiftapp.ws.model.classes.ScheduleCrew;
import com.shiftapp.ws.model.classes.WeeklySchedule;

public interface IWeekInfoService {

	public WeeklySchedule getWeeklySchedule(String token, long businessId) throws UnauthorizedTokenException, TokenOutOfDateException;
	public List<ScheduleCrew> getCrewsForShift(String token, long businessShiftId) throws UnauthorizedTokenException, TokenOutOfDateException;
	
	//Manager only
	public List<BusinessCategory> getBusinessCategories(String token, long businessId) throws UnauthorizedTokenException, TokenOutOfDateException;
	public Map<BusinessEmployee, String> getBusinessEmployeesWithReasonForCategory(String token, long categoryId, long shiftid) throws UnauthorizedTokenException, TokenOutOfDateException;
	public boolean addBusinessEmployeeWithCategoryToShift(String token, long employeeId, long categoryId, long shiftId);
	public boolean removeBusinessEmployeeWithCategoryToShift(String token, long employeeId, long categoryId, long shiftId);
}
