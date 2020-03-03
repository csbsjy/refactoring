package com.refactoring.fcm.mapper;

import com.refactoring.fcm.DTO.user.CenterDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface CenterMapper {
	// center_id | center_name  | center_phone_number | registration_date | end_date   | address
	@Select("SELECT center_name FROM center WHERE center_id = #{center_id}")
	String findCenterNameByCenterId(@Param("center_id") String center_id);

	@Insert("INSERT INTO CENTER VALUES(#{center.center_id}, #{center.center_name}, #{center.center_phone_number}, #{center.address})")
	public void insertCenterData(@Param("center") CenterDTO center);
}
