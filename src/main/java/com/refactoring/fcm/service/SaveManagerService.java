package com.refactoring.fcm.service;


import com.refactoring.fcm.DTO.user.CenterDTO;
import com.refactoring.fcm.DTO.user.ManagerDTO;
import com.refactoring.fcm.dao.CenterDAO;
import com.refactoring.fcm.dao.ManagerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * Create fitness center Manager service
 */
@Service
public class SaveManagerService {

	private ManagerDAO managerDao;

	private CenterDAO centerDao;

	@Autowired
	public void setManagerDao(ManagerDAO managerDao){
		this.managerDao=managerDao;
	}

	@Autowired
	public void setCenterDao(CenterDAO centerDao){
		this.centerDao=centerDao;
	}

	public void saveManager(ManagerDTO manager, CenterDTO center) throws Exception {
		managerDao.saveManager(manager);
		centerDao.saveCenter(center);
	}
}
