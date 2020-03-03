package com.refactoring.fcm.dao;

import com.refactoring.fcm.DTO.InbodyDTO;
import com.refactoring.fcm.mapper.InbodyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InbodyDao {

	private InbodyMapper inbodyMapper;

	@Autowired
	public void setInbodyMapper(InbodyMapper inbodyMapper){
		this.inbodyMapper=inbodyMapper;
	}

	public List<InbodyDTO> selectInbodyByMemberId(String memberId){
		return inbodyMapper.selectFromInbodyByMemberId(memberId);
	}

	public InbodyDTO insertInbody(InbodyDTO inbody){
		inbodyMapper.insertInbody(inbody);
		return inbody;
	}

}
