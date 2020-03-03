package com.refactoring.fcm.service;

import com.refactoring.fcm.DTO.user.MemberDTO;
import com.refactoring.fcm.DTO.user.TrainerDTO;
import com.refactoring.fcm.dao.MemberDAO;
import com.refactoring.fcm.dao.TrainerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviseUserInfoServiceImpl implements ReviseUserInfoServiceByManager {

	private MemberDAO memberDao;

	private TrainerDAO trainerDao;


	@Autowired
	public void setMemberDao(MemberDAO memberDao){
		this.memberDao=memberDao;
	}

	@Autowired
	public void setTrainerDao(TrainerDAO trainerDao){
		this.trainerDao=trainerDao;
	}

	@Override
	public MemberDTO reviseMemberInfo(MemberDTO member) {
		// TODO 주 pt횟수와 담당트레이너만 변경 가능
		return memberDao.reviseMemberDataByManager(member);
	}

	@Override
	public TrainerDTO reviseTrainerInfo(TrainerDTO trainer) {
		// TODO 휴무일만 변경 가능
		return trainerDao.reviseTrainerDataMyManager(trainer);
	}

}
