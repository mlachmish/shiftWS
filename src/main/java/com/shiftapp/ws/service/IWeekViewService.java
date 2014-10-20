package com.shiftapp.ws.service;

import java.util.List;

import com.google.common.collect.Multimap;
import com.shiftapp.ws.model.classes.BusinessCategory;
import com.shiftapp.ws.model.classes.WeeklySchedule;

public interface IWeekViewService {

	public WeeklySchedule getWeekSchedule(long uid);
	public Multimap<Long, Long> getCrewForShift(long uid, long businessShiftId);
	public List<BusinessCategory> getCategories(long uid);
	public void getEmployeeWithReasonForCategory(long uid);
}
