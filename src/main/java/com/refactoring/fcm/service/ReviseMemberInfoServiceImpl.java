package com.refactoring.fcm.service;

import com.refactoring.fcm.DTO.user.Account;
import com.refactoring.fcm.DTO.user.MemberDTO;
import com.refactoring.fcm.dao.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reviseMemberInfoService")
public class ReviseMemberInfoServiceImpl implements ReviseMyInfoService{

	private MemberDAO memberDao;

	@Autowired
	public void setMemberDao(MemberDAO memberDao){
		this.memberDao=memberDao;
	}

	@Override
	public Account reviseMyInfo(Account member) {
		memberDao.reviseMemberData((MemberDTO)member);
		return member;
	}

}
