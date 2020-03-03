package com.refactoring.fcm.service;

import com.refactoring.fcm.DTO.user.ManagerDTO;
import com.refactoring.fcm.DTO.user.MemberDTO;
import com.refactoring.fcm.DTO.user.MemberTrDTO;
import com.refactoring.fcm.DTO.user.TrainerDTO;
import com.refactoring.fcm.dao.ManagerDAO;
import com.refactoring.fcm.dao.MemberDAO;
import com.refactoring.fcm.dao.TrainerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUserServiceImpl implements FindUserService {

	private ManagerDAO managerDao;

	private TrainerDAO trainerDao;

	private MemberDAO memberDao;

	@Autowired
	public void setManagerDao(ManagerDAO managerDao){
		this.managerDao=managerDao;
	}

	@Autowired
	public void setTrainerDao(TrainerDAO trainerDao){
		this.trainerDao=trainerDao;
	}

	@Autowired
	public void setMemberDao(MemberDAO memberDao){
		this.memberDao=memberDao;
	}


	@Override
	public ManagerDTO findManagerById(String id) {
		return managerDao.findManagerById(id);
	}

	@Override
	public TrainerDTO findTrainerById(String id) {
		return trainerDao.findTrainerById(id);
	}

	@Override
	public MemberDTO findMemberById(String id) {
		return memberDao.findMemberById(id);
	}

	@Override
	public List<TrainerDTO> findAllTrainers(String center_id) {
		return trainerDao.findAllTrainers(center_id);
	}

	@Override
	public List<MemberTrDTO> findAllMembers(String center_id) {
		List<MemberTrDTO> members=memberDao.findAllMembersWithTrainer(center_id);
		members.addAll(memberDao.findAllMembersWithNullTrainer(center_id));
		return members;
	}

	@Override
	public List<MemberTrDTO> findMembersByName(String name, String center_id) {
		List<MemberTrDTO> members=memberDao.findMembersByNameWithTrainer(name, center_id);
		members.addAll(memberDao.findMembersByNameWithNullTrainer(name, center_id));
		return members;
	}

	@Override
	public List<TrainerDTO> findTrainersByName(String name, String center_id) {
		List<TrainerDTO> trainers=trainerDao.findTrainersByName(name, center_id);
		return trainers;
	}

	@Override
	public List<MemberDTO> findMembersByTrainerId(String id) {
		return memberDao.findMembersByTrainerId(id);
	}

}
