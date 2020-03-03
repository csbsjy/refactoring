package com.refactoring.fcm.mapper;

import com.refactoring.fcm.DTO.user.MemberDTO;
import com.refactoring.fcm.DTO.user.MemberTrDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {

	@Select("SELECT * FROM Member WHERE id= #{id}")
	MemberDTO findMemberByMemberId(@Param("id") String id);

	@Insert("INSERT INTO MEMBER VALUES(#{member.id},#{member.name}, #{member.center_id}, #{member.gender}, #{member.pt}, #{member.phone_number}, #{member.trainer_id})")
	void insertMember(@Param("member") MemberDTO member);

	@Update("UPDATE MEMBER SET phone_number=#{member.phone_number} WHERE id=#{member.id}")
	void reviseMemberData(@Param("member") MemberDTO member);
	//| id      | name      | center_id | gender | pt   | phone_number | trainer_id
	@Select("SELECT member.id, member.name, member.gender, member.pt, member.phone_number, trainer.name as trainer_name  FROM Member as member, Trainer as trainer WHERE member.center_id=#{center_id} and member.trainer_id=trainer.id")
	List<MemberTrDTO> selectAllMembersWithTrainer(String center_id);

	@Select("SELECT * FROM MEMBER WHERE center_id=#{center_id} and trainer_id='null'")
	List<MemberTrDTO> selectAllMemberWithNullTrainer(String center_id);

	@Select("SELECT member.id, member.name, member.gender, member.pt, member.phone_number, trainer.name as trainer_name  FROM Member as member, Trainer as trainer WHERE member.center_id=#{center_id} and member.name=#{name} and member.trainer_id=trainer.id")
	List<MemberTrDTO> selectMembersByNameWithTrainer(@Param("name") String name, @Param("center_id") String center_id);

	@Select("SELECT * FROM MEMBER WHERE name=#{name} and center_id=#{center_id} and trainer_id='null'")
	List<MemberTrDTO> selectMembersByNameWithNullTrainer(@Param("name") String name, @Param("center_id") String center_id);

	@Update("UPDATE MEMBER SET pt=#{member.pt}, trainer_id=#{member.trainer_id} WHERE id=#{member.id}")
	void updateMemberInfoByManager(@Param("member") MemberDTO member);

	@Delete("DELETE FROM MEMBER WHERE id=#{id}")
	void deleteMember(String id);

	@Select("SELECT* FROM MEMBER WHERE trainer_id=#{id}")
	List<MemberDTO> findMembersByTrainerId(String id);

}
