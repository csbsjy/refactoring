package com.refactoring.fcm.service;


import com.refactoring.fcm.DTO.user.MemberDTO;
import com.refactoring.fcm.DTO.user.TrainerDTO;

/*
 * 헬스장 관리자의 사용자(회원/트레이너)등록/삭제 서비스
 * 변경가능성 보통
 */
public interface UserManagementService {
	public MemberDTO addMember(MemberDTO member) throws Exception;
	public TrainerDTO addTrainer(TrainerDTO trainer) throws Exception;

	public String removeMember(String id);
	public String removeTrainer(String id);
}
