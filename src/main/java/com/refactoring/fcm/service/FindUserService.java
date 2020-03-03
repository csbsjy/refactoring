package com.refactoring.fcm.service;

import com.refactoring.fcm.DTO.user.ManagerDTO;
import com.refactoring.fcm.DTO.user.MemberDTO;
import com.refactoring.fcm.DTO.user.MemberTrDTO;
import com.refactoring.fcm.DTO.user.TrainerDTO;

import java.util.List;

/*
 * DB에서 유저를 호출하는 서비스
 * 변경가능성 보통
 */
public interface FindUserService {
	public ManagerDTO findManagerById(String id);

	public TrainerDTO findTrainerById(String id);
	public MemberDTO findMemberById(String id);
	public List<TrainerDTO> findAllTrainers(String center_id);
	public List<TrainerDTO> findTrainersByName(String name, String center_id);
	public List<MemberTrDTO> findAllMembers(String center_id);
	public List<MemberTrDTO> findMembersByName(String name, String center_id);
	public List<MemberDTO> findMembersByTrainerId(String id);
}
