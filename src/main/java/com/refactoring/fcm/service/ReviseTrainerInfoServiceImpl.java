package com.refactoring.fcm.service;

import com.refactoring.fcm.DTO.user.Account;
import com.refactoring.fcm.DTO.user.TrainerDTO;
import com.refactoring.fcm.dao.TrainerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("reviseTrainerInfoService")
public class ReviseTrainerInfoServiceImpl implements ReviseMyInfoService {
	Logger logger=LoggerFactory.getLogger(ReviseMyInfoService.class);

	private TrainerDAO trainerDao;

	@Autowired
	public void setTrainerDao(TrainerDAO trainerDao){
		this.trainerDao=trainerDao;
	}

	@Override
	public Account reviseMyInfo(Account trainer) {
		trainerDao.reviseTrainerData((TrainerDTO)trainer);
		return trainer;
	}


}
