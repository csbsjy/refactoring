package com.refactoring.fcm.mapper;


import com.refactoring.fcm.DTO.user.ManagerDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface ManagerMapper {

	@Select("SELECT * FROM MANAGER WHERE id= #{id}")
	ManagerDTO findManagerByManagerId(@Param("id") String id);

	@Insert("INSERT INTO Manager VALUES(#{manager.id},#{manager.name},#{manager.center_id},#{manager.phoneNumber})")
	public void insertManager(@Param("manager") ManagerDTO manager);


}
