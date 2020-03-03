package com.refactoring.fcm.mapper;

import com.refactoring.fcm.DTO.ScheduleDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScheduleMapper {

	@Select("SELECT mem.name as member_name, tr.name as trainer_name, sc.hour, sc.date FROM SCHEDULES as sc, MEMBER as mem, TRAINER as tr "
			+ "WHERE mem.id=#{mem_id} and mem.id=sc.member_id and tr.id=sc.trainer_id and YEARWEEK(date) = YEARWEEK(now()) order by date asc")
	public List<ScheduleDTO> selectThisWeekSchedulesByMemberId(@Param("mem_id") String member_id);

	@Select("SELECT mem.name as member_name, tr.name as trainer_name, sc.hour, sc.date FROM SCHEDULES as sc, MEMBER as mem, TRAINER as tr "
			+ "WHERE tr.id=#{trainer_id} and mem.id=sc.member_id and tr.id=sc.trainer_id and YEARWEEK(date) = YEARWEEK(now()) order by date asc")
	public List<ScheduleDTO> selectThisWeekSchedulesByTrainerId(@Param("trainer_id") String trainer_id);

	@Insert("INSERT INTO SCHEDULES VALUE(#{member_id}, #{trainer_id}, #{hour},#{date} )")
	public boolean applySchedule(@Param("member_id")String member_id, @Param("trainer_id") String trainer_id, @Param("hour") String hour,@Param("date") String date);

	@Select("SELECT count(*) FROM SCHEDULES WHERE member_id=#{member_id} and date >= #{date}")
	public int countApplyRecord(@Param("member_id") String member_id, @Param("date")String date);

	@Select("SELECT* FROM SCHEDULES WHERE hour=#{hour} AND date >=#{start_date} AND date <=#{end_date} AND trainer_id=#{trainer_id} ORDER BY DATE ASC")
	public List<ScheduleDTO> findSchedulesByHourAndDateAndTrainerId(@Param("hour") String hour, @Param("start_date") String start_date, @Param("end_date") String end_date, @Param("trainer_id") String trainer_id);

	@Delete("DELETE FROM SCHEDULES WHERE hour=#{hour} AND date=#{date} AND member_id=#{member_id}")
	public void cancleSchedule(@Param("hour") String hour, @Param("date") String date, @Param("member_id") String member_id);

	@Select("SELECT MAX(idx) FROM RESERVE WHERE hour=#{hour} AND date=#{date} AND member_id=#{member_id} AND trainer_id=#{trainer_id}")
	public Integer getMaxIdxFromReserve(@Param("hour") String hour, @Param("date") String date, @Param("member_id") String member_id, @Param("trainer_id") String trainer_id);

	@Insert("INSERT INTO RESERVE VALUES(#{member_id},#{trainer_id},#{hour},#{date}, #{idx})")
	public void reserveSchedule(@Param("member_id")String member_id, @Param("trainer_id")String trainer_id, @Param("hour") String hour, @Param("date") String date, @Param("idx") int idx);

}
