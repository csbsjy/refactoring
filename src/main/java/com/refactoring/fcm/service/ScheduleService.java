package com.refactoring.fcm.service;

import com.refactoring.fcm.DTO.ScheduleDTO;

import java.util.List;

public interface ScheduleService {
	public List<ScheduleDTO> findThisWeekScheduleByMemberId(String member_id);

	public List<ScheduleDTO> findThisWeekScheduleByTrainerId(String trainer_id);

	public boolean applySchedule(String member_id, String trainer_id, String day, String hour, int pt);

	public List<ScheduleDTO> findAfterTwoWeeksSchedulesByTrainerIdAndHour(String trainer_id);

	public void cancleSchedule(String member_id, String day, String hour);

	public int reserveSchedule(String member_id, String trainer_id, String hour, String date);
}
