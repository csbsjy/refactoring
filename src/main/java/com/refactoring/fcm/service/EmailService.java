package com.refactoring.fcm.service;

/*
 * 이메일전송 서비스
 * 변경가능성 높음
 */
public interface EmailService {
	public boolean sendSimpleMessage(String to, String subject, String text);
}
