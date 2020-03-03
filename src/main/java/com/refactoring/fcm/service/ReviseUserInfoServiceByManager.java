package com.refactoring.fcm.service;

import com.refactoring.fcm.DTO.user.MemberDTO;
import com.refactoring.fcm.DTO.user.TrainerDTO;

/*
 * 헬스장관리자가 회원/트레이너의 정보를 수정하는 서비스
 * 변경가능성 보통
 */
public interface ReviseUserInfoServiceByManager {
	public MemberDTO reviseMemberInfo(MemberDTO member );
	public TrainerDTO reviseTrainerInfo(TrainerDTO trainer);
}
