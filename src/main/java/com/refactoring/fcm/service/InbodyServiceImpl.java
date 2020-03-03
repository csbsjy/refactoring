package com.refactoring.fcm.service;

import com.refactoring.fcm.DTO.InbodyDTO;
import com.refactoring.fcm.dao.InbodyDao;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InbodyServiceImpl {

	private InbodyDao inbodyDao;

	@Autowired
	public void setInbodyDao(InbodyDao inbodyDao){
		this.inbodyDao=inbodyDao;
	}

	public List<InbodyDTO> selectInbodyList(String member_id){
		return inbodyDao.selectInbodyByMemberId(member_id);
	}

	public InbodyDTO insertInbodyInfo(InbodyDTO inbody) {
		LocalDate currentDate = LocalDate.now();
		inbody.setRecord(currentDate.toString());
		return inbodyDao.insertInbody(inbody);
	}
}
