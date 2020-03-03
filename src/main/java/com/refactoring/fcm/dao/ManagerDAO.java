package com.refactoring.fcm.dao;

import com.refactoring.fcm.DTO.user.ManagerDTO;
import com.refactoring.fcm.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 *  Create Manager and Center
 *  read Manager
 */
@Repository
public class ManagerDAO {

	private ManagerMapper managerMapper;

	@Autowired
	public void setManagerMapper(ManagerMapper managerMapper){
		this.managerMapper=managerMapper;
	}

	public ManagerDTO saveManager(ManagerDTO manager){
		managerMapper.insertManager(manager);
		return manager;
	}

	public ManagerDTO findManagerById(String id){
		return managerMapper.findManagerByManagerId(id);
	}
}
