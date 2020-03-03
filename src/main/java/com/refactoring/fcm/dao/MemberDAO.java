package com.refactoring.fcm.dao;

import com.refactoring.fcm.DTO.user.MemberDTO;
import com.refactoring.fcm.DTO.user.MemberTrDTO;
import com.refactoring.fcm.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * create member
 * read member
 * revise member data
 * delete member
 */
@Repository
public class MemberDAO {

	private MemberMapper memberMapper;

	@Autowired
	public void setMemberMapper(MemberMapper memberMapper){
		this.memberMapper=memberMapper;
	}

	public MemberDTO saveMember(MemberDTO member){
		memberMapper.insertMember(member);
		return member;
	}
	public MemberDTO findMemberById(String id) {
		return memberMapper.findMemberByMemberId(id);
	}

	public List<MemberTrDTO> findAllMembersWithTrainer(String center_id) {
		List<MemberTrDTO> members=memberMapper.selectAllMembersWithTrainer(center_id);
		return members;
	}

	public List<MemberTrDTO> findAllMembersWithNullTrainer(String center_id) {
		List<MemberTrDTO> members=memberMapper.selectAllMemberWithNullTrainer(center_id);
		return members;
	}

	public List<MemberTrDTO> findMembersByNameWithTrainer(String name, String center_id){
		List<MemberTrDTO> members=memberMapper.selectMembersByNameWithTrainer(name, center_id);
		return members;
	}

	public List<MemberTrDTO> findMembersByNameWithNullTrainer(String name, String center_id){
		List<MemberTrDTO> members=memberMapper.selectMembersByNameWithNullTrainer(name, center_id);
		return members;
	}

	public List<MemberDTO> findMembersByTrainerId(String id){
		List<MemberDTO> members=memberMapper.findMembersByTrainerId(id);
		return members;
	}

	public MemberDTO reviseMemberData(MemberDTO member){
		memberMapper.reviseMemberData(member);
		return member;
	}

	public MemberDTO reviseMemberDataByManager(MemberDTO member){
		memberMapper.updateMemberInfoByManager(member);
		return member;
	}

	public boolean deleteMember(String id) {
		memberMapper.deleteMember(id);
		return true;
	}
}
