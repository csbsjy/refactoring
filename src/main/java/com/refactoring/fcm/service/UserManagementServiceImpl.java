package com.refactoring.fcm.service;

import com.refactoring.fcm.DTO.user.Account;
import com.refactoring.fcm.DTO.user.MemberDTO;
import com.refactoring.fcm.DTO.user.TrainerDTO;
import com.refactoring.fcm.dao.AccountDAO;
import com.refactoring.fcm.dao.MemberDAO;
import com.refactoring.fcm.dao.TrainerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	private AccountDAO accountDao;

	private MemberDAO memberDao;

	private TrainerDAO trainerDao;

	private PasswordEncoder passwordEncoder;


	@Autowired
	public void setAccountDao(AccountDAO accountDao){
		this.accountDao=accountDao;
	}

	@Autowired
	public void setMemberDao(MemberDAO memberDao){
		this.memberDao=memberDao;
	}

	@Autowired
	public void setTrainerDao(TrainerDAO trainerDao){
		this.trainerDao=trainerDao;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder){
		this.passwordEncoder=passwordEncoder;
	}


	@Override
	public MemberDTO addMember(MemberDTO member) {
		Account account = new Account();
		account.setId(member.getId());
		account.setPassword(member.getPassword());
		account.setCenter_id(member.getCenter_id());
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setType("MEMBER");
		accountDao.save(account);
		accountDao.saveAutority(account, "ROLE_MEMBER");

		return memberDao.saveMember(member);
	}

	@Override
	public TrainerDTO addTrainer(TrainerDTO trainer) {
		Account account = new Account();
		account.setId(trainer.getId());
		account.setType("TRAINER");
		account.setPassword(trainer.getPassword());
		account.setCenter_id(trainer.getCenter_id());
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountDao.save(account);
		accountDao.saveAutority(account, "ROLE_TRAINER");

		return trainerDao.saveTrainer(trainer);
	}

	@Override
	public String removeMember(String id) {
		accountDao.deleteAutorities(id);
		accountDao.deleteUser(id);
		memberDao.deleteMember(id);
		return id;
	}

	@Override
	public String removeTrainer(String id) {
		accountDao.deleteAutorities(id);
		accountDao.deleteUser(id);
		trainerDao.deleteTrainer(id);
		return id;
	}




}
