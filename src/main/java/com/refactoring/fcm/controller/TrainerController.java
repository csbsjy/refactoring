package com.refactoring.fcm.controller;

import com.refactoring.fcm.DTO.InbodyDTO;
import com.refactoring.fcm.DTO.ScheduleDTO;
import com.refactoring.fcm.DTO.user.Account;
import com.refactoring.fcm.DTO.user.MemberDTO;
import com.refactoring.fcm.DTO.user.MemberTrDTO;
import com.refactoring.fcm.DTO.user.TrainerDTO;
import com.refactoring.fcm.service.*;
import com.refactoring.fcm.service.s3.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class TrainerController {
	Logger logger=LoggerFactory.getLogger(TrainerController.class);


	private S3Service s3Service;
	private ReviseMyInfoService reviseTrainerInfoService;
	private AccountService accountService;
	private FindUserService findUserService;
	private ScheduleService scheduleService;
	private InbodyServiceImpl inbodyService;


	@Autowired
	public void setS3Service(S3Service s3Service){
		this.s3Service=s3Service;
	}

	@Autowired
	public void setReviseMyInfoService(ReviseMyInfoService reviseTrainerInfoService){
		this.reviseTrainerInfoService=reviseTrainerInfoService;
	}
	@Autowired
	public void setAccointService(AccountService accountService){
		this.accountService=accountService;
	}

	@Autowired
	public void setFindUserService(FindUserService findUserService){
		this.findUserService=findUserService;
	}

	@Autowired
	public void setScheduleService(ScheduleService scheduleService){
		this.scheduleService=scheduleService;
	}

	@Autowired
	public void setInbodyService(InbodyServiceImpl inbodyService){
		this.inbodyService=inbodyService;
	}


	@GetMapping("/trainer")
	public ModelAndView trainerMain(@AuthenticationPrincipal Account account){
		ModelAndView mv = new ModelAndView("/schedule");

		mv.addObject("schedule", "active");
		mv.addObject("type", account.getType());

		List<ScheduleDTO> schedules = scheduleService.findThisWeekScheduleByTrainerId(account.getId());

		mv.addObject("schedules", schedules);

		return mv;
	}

	@GetMapping("/trainer/mypage")
	public String trainerMyPage(@AuthenticationPrincipal Account account, Model model, HttpServletRequest req) {
		if (req.getParameter("pwerror") != null)
			model.addAttribute("message", "패스워드를 다시한번 확인해주세요!!");
		else if (req.getParameter("error") != null)
			model.addAttribute("message", "예기치못한 오류가 발생하였습니다!!");
		else if (req.getParameter("success") != null)
			model.addAttribute("message", "정상적으로 변경되었습니다!!");
		else
			model.addAttribute("message","");

		TrainerDTO trainer=findUserService.findTrainerById(account.getId());
		model.addAttribute("trainer",trainer);
		model.addAttribute("closed_day",trainer.getClosed_day());
		model.addAttribute("img",s3Service.getFileURL(s3Service.getBucket(), trainer.getId()));

		model.addAttribute("mypage", "active");
		return "trainer/tr_mypage";
	}

	@PostMapping("/trainer/mypage")
	public String trainerMyPageByPost(@AuthenticationPrincipal Account account, @Valid TrainerDTO trainer, HttpServletRequest req, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile multipartFile) throws IOException {
		if (bindingResult.hasErrors()) {
			logger.info(bindingResult.getAllErrors().get(0).toString());
			return "/trainer/tr_mypage";
		}

		// confirm password
		if (!accountService.matchPassword(trainer.getId(), req.getParameter("cur_password"))) {
			return "redirect:/trainer/mypage?pwerror";
		}
		try {
			accountService.updatePassword(trainer.getId(), trainer.getPassword());
			reviseTrainerInfoService.reviseMyInfo(trainer);
			if (!multipartFile.isEmpty()) {
				s3Service.deleteFile(trainer.getId());
				s3Service.upload(multipartFile, "trainer", trainer.getId());
			}
		}catch(Exception e){
			return "redirect:/trainer/mypage?error";
		}

		model.addAttribute("trainer",findUserService.findTrainerById(account.getId()));
		model.addAttribute("mypage", "active");
		return "/trainer/tr_mypage";
	}

	@GetMapping("/trainer/showMemberList")
	public String showMemberListByGet(@AuthenticationPrincipal Account account, Model model, HttpServletRequest req){
		List<MemberDTO> members=findUserService.findMembersByTrainerId(account.getId());
		model.addAttribute("members", members);
		model.addAttribute("management", "active");
		return "/trainer/memberinfo";
	}

	@PostMapping("/trainer/showMemberList")
	public String showMemberListByPost(@AuthenticationPrincipal Account account, Model model, HttpServletRequest req){
		List<MemberTrDTO> members=findUserService.findMembersByName(req.getParameter("name"), account.getCenter_id());
		model.addAttribute("members", members);
		model.addAttribute("management", "active");
		return "/trainer/memberinfo";
	}

	@GetMapping("/trainer/inbody.do")
	public String showMemberInbody(@AuthenticationPrincipal Account account, Model model, HttpServletRequest req){
		MemberDTO member=findUserService.findMemberById(req.getParameter("id"));
		model.addAttribute("management", "active");
		model.addAttribute("member",member);

		List<InbodyDTO> inbody=inbodyService.selectInbodyList(req.getParameter("id"));
		model.addAttribute("inbody",inbody);
		return "/trainer/memberInbody";
	}

	@GetMapping("/trainer/record_inbody")
	public ModelAndView recordInbody(@AuthenticationPrincipal Account account, Model model, HttpServletRequest req){
		ModelAndView mv=new ModelAndView("/trainer/record_inbody");
		MemberDTO member=findUserService.findMemberById(req.getParameter("id"));
		mv.addObject("member", member);
		return mv;
	}
	@PostMapping("/trainer/record_inbody")
	public ModelAndView recordInbodyByPM(@AuthenticationPrincipal Account account, Model model, HttpServletRequest req,InbodyDTO inbody){
		ModelAndView mv=new ModelAndView("/trainer/record_inbody");
		MemberDTO member=findUserService.findMemberById(req.getParameter("id"));
		mv.addObject("member", member);
		inbodyService.insertInbodyInfo(inbody);

		mv.addObject("message","기록되었습니다");
		return mv;
	}

}
