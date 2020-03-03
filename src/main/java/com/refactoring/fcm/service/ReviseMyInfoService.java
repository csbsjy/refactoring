package com.refactoring.fcm.service;

import com.refactoring.fcm.DTO.user.Account;

/*
 * 회원/트레이너의 개인정보수정 서비스(마이페이지)
 * 변경가능성 보통
 */
public interface ReviseMyInfoService {
	public Account reviseMyInfo(Account account) ;
}
