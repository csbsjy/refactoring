package com.refactoring.fcm.dao;

import com.refactoring.fcm.DTO.user.CenterDTO;
import com.refactoring.fcm.mapper.CenterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CenterDAO {

	private CenterMapper centerMapper;

	@Autowired
	public void setCenterMapper(CenterMapper centerMapper){
		this.centerMapper=centerMapper;
	}

	public CenterDTO saveCenter(CenterDTO center){
		centerMapper.insertCenterData(center);
		return center;
	}

}
