package com.refactoring.fcm.controller;

import com.refactoring.fcm.DTO.user.*;
import com.refactoring.fcm.service.FindUserService;
import com.refactoring.fcm.service.ReviseUserInfoServiceByManager;
import com.refactoring.fcm.service.UserManagementService;
import com.refactoring.fcm.service.s3.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
public class ManagerController {
	Logger logger=LoggerFactory.getLogger(ManagerController.class);


	private FindUserService findUserService;
	private S3Service s3Service;
	private UserManagementService userManagementService;
	private ReviseUserInfoServiceByManager reviseUserInfoService;

	@Autowired
	public void setFindUserService(FindUserService findUserService){
		this.findUserService=findUserService;
	}

	@Autowired
	public void setS3Service(S3Service s3Service){
		this.s3Service=s3Service;
	}

	@Autowired
	public void setUserManagementService(UserManagementService userManagementService){
		this.userManagementService=userManagementService;
	}

	@Autowired
	public void setReviseUserInfoServiceByManager(ReviseUserInfoServiceByManager reviseUserInfoService){
		this.reviseUserInfoService=reviseUserInfoService;
	}

	@GetMapping("/manager")
	public String manager(@AuthenticationPrincipal Account account, Model model){
		ManagerDTO manager=findUserService.findManagerById(account.getId());
		model.addAttribute("schedule","active");
		model.addAttribute("type",manager.getType());

		return "/schedule";
	}

	@GetMapping("/manager/addMember")
	public String addUserByGet(@AuthenticationPrincipal Account account, Model model, HttpServletRequest req){
		if(req.getParameter("error")==null && req.getParameter("success")!=null)
		{
			model.addAttribute("message","정상적으로 등록되었습니다");
		}
		if(req.getParameter("error")!=null)
		{
			model.addAttribute("message","사용자등록오류");
		}
		ManagerDTO manager=findUserService.findManagerById(account.getId());
		List<TrainerDTO> trainers=findUserService.findAllTrainers(manager.getCenter_id());
		model.addAttribute("management","active");
		model.addAttribute("member", new MemberDTO());
		model.addAttribute("trainers",trainers);
		return "manager/addMember";
	}

	@PostMapping("/manager/addMember")
	public String addUserByPost(@AuthenticationPrincipal Account account,@Valid @ModelAttribute("member") MemberDTO member, BindingResult bindingResult,Model model){
		ManagerDTO manager=findUserService.findManagerById(account.getId());

		if(bindingResult.hasErrors()){
			List<TrainerDTO> trainers=findUserService.findAllTrainers(manager.getCenter_id());
			model.addAttribute("management","active");
			model.addAttribute("trainers",trainers);
			return "/manager/addMember";
		}

		member.setCenter_id(manager.getCenter_id());
		try{
			userManagementService.addMember(member);
		}catch(Exception ex){
			ex.printStackTrace();
			return "redirect:/manager/addMember?error";
		}
		return "redirect:/manager/addMember?success";
	}

	@GetMapping("/manager/addTrainer")
	public String addTrainerByGET(Model model,HttpServletRequest req){
		if(req.getParameter("error")==null && req.getParameter("success")!=null)
		{
			model.addAttribute("message","정상적으로 등록되었습니다");
		}
		if(req.getParameter("error")!=null)
		{
			model.addAttribute("message","사용자등록오류");
		}
		model.addAttribute("trainer", new TrainerDTO());
		model.addAttribute("management","active");
		return "/manager/addTrainer";
	}
	@PostMapping("/manager/addTrainer")
	public String addTrainer(@AuthenticationPrincipal Account account,@Valid @ModelAttribute("trainer") TrainerDTO trainer,BindingResult bindingResult, Model model,HttpServletRequest req,@RequestParam("file") MultipartFile multipartFile){
		ManagerDTO manager=findUserService.findManagerById(account.getId());
		if(bindingResult.hasErrors()){
			logger.info("form error in addTrainer");
			return "/manager/addTrainer";
		}

		trainer.setCenter_id(manager.getCenter_id());
		try{
			userManagementService.addTrainer(trainer);
			s3Service.upload(multipartFile, "trainer",trainer.getId());
		}catch(Exception ex){
			ex.printStackTrace();
			return "redirect:/manager/addTrainer?error";
		}

		return "redirect:/manager/addTrainer?success";
	}

	@GetMapping("/manager/userInfo")
	public String managingUser(@AuthenticationPrincipal Account account,Model model, HttpServletRequest req){
		ManagerDTO manager=findUserService.findManagerById(account.getId());

		if(req.getParameter("success")!=null)
			model.addAttribute("message","정상적으로 삭제되었습니다!!");

		List<MemberTrDTO> members=findUserService.findAllMembers(manager.getCenter_id());
		List<TrainerDTO> trainers=findUserService.findAllTrainers(manager.getCenter_id());

		model.addAttribute("members",members);
		model.addAttribute("trainers",trainers);
		model.addAttribute("management","active");
		return "/manager/userinfo";
	}

	@GetMapping("/manager/reviseMemInfo.do")
	public String reviseMemberInfoByGet( HttpServletRequest req,Model model){

		if(req.getParameter("error")!=null){
			model.addAttribute("message","처리중 오류가 발생하였습니다!!");

		}
		else if(req.getParameter("success")!=null)
			model.addAttribute("message","정상적으로 수정되었습니다!!");
		else if(req.getParameter("delete")!=null){
			model.addAttribute("delmessage","delete");
			model.addAttribute("message","");
		}
		else
			model.addAttribute("message","");

		MemberDTO member=findUserService.findMemberById(req.getParameter("id"));
		List<TrainerDTO> trainers=findUserService.findAllTrainers(member.getCenter_id());
		model.addAttribute("member",member);
		model.addAttribute("trainers",trainers);
		model.addAttribute("management","active");
		return "/manager/reviseMember";
	}

	@PostMapping("/manager/reviseMemInfo.do")
	public String reviseMemberInfoByPoset(@ModelAttribute("member") MemberDTO member, BindingResult bindingResult, HttpServletRequest req,Model model) {
		//주 pt횟수, 담당 트레이너 업데이트
		try {
			model.addAttribute("id", member.getId());
			reviseUserInfoService.reviseMemberInfo(member);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/manager/reviseMemInfo.do?error&id=" + member.getId();
		}
		model.addAttribute("management", "active");
		return "redirect:/manager/reviseMemInfo.do?success&id=" + member.getId();

	}

	@GetMapping("/manager/member/delete.do")
	public String deleteMember(HttpServletRequest req, Model model)
	{
		try{
			userManagementService.removeMember(req.getParameter("id"));
		}catch(Exception e){
			return "redirect:/manager/reviseMemInfo.do?error&id="+req.getParameter("id");
		}
		return "redirect:/manager/userInfo?success";
	}

	@GetMapping("/manager/reviseTrInfo.do")
	public String reviseTrInfoByGet(Model model, HttpServletRequest req){
		if(req.getParameter("success")!=null){
			model.addAttribute("message", "정상적으로 변경되었습니다!!");
		}
		else if(req.getParameter("error")!=null){
			model.addAttribute("message","처리중 오류가 발생했습니다!!");
		}
		else if(req.getParameter("delete")!=null){
			model.addAttribute("delmessage","delete");
		}
		TrainerDTO trainer=findUserService.findTrainerById(req.getParameter("id"));
		model.addAttribute("trainer", trainer);
		model.addAttribute("imgUrl",s3Service.getFileURL("trbucket", req.getParameter("id")));
		model.addAttribute("management","active");
		return "/manager/reviseTrainer";
	}

	@PostMapping("/manager/reviseTrInfo.do")
	public String reviseTrInfoByPost(TrainerDTO trainer, Model model, HttpServletRequest req){
		try{
			reviseUserInfoService.reviseTrainerInfo(trainer);
		}catch(Exception e){
			return "redirect:/manager/reviseTrInfo.do?error&id="+trainer.getId();
		}
		return "redirect:/manager/reviseTrInfo.do?success&id="+trainer.getId();
	}

	@GetMapping("/manager/trainer/delete.do")
	public String deleteTrainer(HttpServletRequest req){
		try{
			userManagementService.removeTrainer(req.getParameter("id"));
			s3Service.deleteFile(req.getParameter("id"));
		}catch(Exception e){
			return "redirect:/manager/reviseInfo.do?error&id="+req.getParameter("id");
		}
		return "redirect:/manager/userInfo?success";
	}

	@PostMapping("/manager/userInfo")
	public String findUser(@AuthenticationPrincipal Account account, HttpServletRequest req,Model model){
		String name=req.getParameter("name");
		int category=Integer.parseInt(req.getParameter("category"));
		if(category==1){
			//회원
			List<MemberTrDTO> members = findUserService.findMembersByName(name, account.getCenter_id());
			model.addAttribute("members", members);
		}
		if (category == 2) {
			//트레이너
			List<TrainerDTO> trainers = findUserService.findTrainersByName(name, account.getCenter_id());
			model.addAttribute("trainers", trainers);
		}
		model.addAttribute("management", "active");
		return "/manager/userinfo";
	}

}
