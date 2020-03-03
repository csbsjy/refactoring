package com.refactoring.fcm.controller;

import com.refactoring.fcm.DTO.ScheduleDTO;
import com.refactoring.fcm.DTO.user.Account;
import com.refactoring.fcm.DTO.user.MemberDTO;
import com.refactoring.fcm.DTO.user.TrainerDTO;
import com.refactoring.fcm.service.AccountService;
import com.refactoring.fcm.service.FindUserService;
import com.refactoring.fcm.service.ReviseMyInfoService;
import com.refactoring.fcm.service.ScheduleService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MemberController {


	Logger logger = LoggerFactory.getLogger(MemberController.class);

	private ReviseMyInfoService reviseMemberInfoService;
	private AccountService accountService;
	private FindUserService findUserService;
	private ScheduleService scheduleService;

	@Autowired
	public void setReviseMyInfoService(ReviseMyInfoService reviseMemberInfoService){
		this.reviseMemberInfoService=reviseMemberInfoService;
	}

	@Autowired
	public void setAccountService(AccountService accountService){
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

	@GetMapping("/member")
	public ModelAndView showMemberView(@AuthenticationPrincipal Account account,ModelAndView model){
		ModelAndView mv=new ModelAndView("/schedule");
		mv.addObject("schedule","active");
		mv.addObject("type",account.getType());
		List<ScheduleDTO> schedules=scheduleService.findThisWeekScheduleByMemberId(account.getId());
		mv.addObject("schedules",schedules);
		return mv;
	}

	@GetMapping("/member/apply")
	public ModelAndView applyScheduleView(@AuthenticationPrincipal Account account,HttpServletRequest req){
	//https://localhost:8090/member/apply?success
		ModelAndView mv=new ModelAndView("/member/apply");
		if(req.getParameter("success")!=null){
			mv.addObject("message","신청완료");
		}
		if(req.getParameter("error")!=null){
			mv.addObject("message","PT신청횟수를 초과하였습니다!!");
		}
		if(req.getParameter("cancle_error")!=null){
			mv.addObject("message","PT취소 중 에러가 발생했습니다!!");
		}
		if(req.getParameter("cancle_success")!=null){
			mv.addObject("message","취소완료");
		}
		if(req.getParameter("reserve_error")!=null){
			mv.addObject("message","예약 대기중 오류 발생!!");
		}
		if(req.getParameter("reserve_success")!=null){
			mv.addObject("message",req.getParameter("idx")+"번째 예약대기 성공");
		}
		mv.addObject("schedule","active");
		mv.addObject("type",account.getType());
		mv.addObject("id", account.getId());

		TrainerDTO trainer=new TrainerDTO();
		trainer=findUserService.findTrainerById(findUserService.findMemberById(account.getId()).getTrainer_id());

		List<ScheduleDTO> schedules=scheduleService.findAfterTwoWeeksSchedulesByTrainerIdAndHour(trainer.getId());
		mv.addObject("schedules",schedules);

		mv.addObject("trainer",trainer);

		return mv;
	}

	@GetMapping("/member/ptapply.do")
	public String applySchedule(@AuthenticationPrincipal Account account, String day, String hour ){

		MemberDTO member=findUserService.findMemberById(account.getId());
		if(scheduleService.applySchedule(member.getId(), member.getTrainer_id(),day , hour, member.getPt()))
			return "redirect:/member/apply?success";
		return "redirect:/member/apply?error";
	}

	@GetMapping("/member/mypage")
	public String myPageByGet(@AuthenticationPrincipal Account account, Model model, HttpServletRequest req){
		model.addAttribute("mypage","active");
		model.addAttribute("member",findUserService.findMemberById(account.getId()));
		if(req.getParameter("pwerror")!=null)
			model.addAttribute("message","패스워드를 다시한번 확인해주세요!!");
		else if(req.getParameter("error")!=null)
			model.addAttribute("message","예기치못한 오류가 발생하였습니다!!");
		else if(req.getParameter("success")!=null)
			model.addAttribute("message","정상적으로 변경되었습니다!!");
		else
			model.addAttribute("message","");
		return "/member/mem_mypage";
	}

	@PostMapping("/member/mypage")
	public String myPageByPost(@Valid @ModelAttribute("member") MemberDTO member, BindingResult bindingResult, HttpServletRequest req,Model model) {
		if (bindingResult.hasErrors()) {
			logger.info(Integer.toString(bindingResult.getErrorCount()));
			logger.info(bindingResult.getAllErrors().get(0).toString());
			return "/member/mem_mypage";
		}
		// confirm password
		if (!accountService.matchPassword(member.getId(), req.getParameter("cur_password"))) {
			return "redirect:/member/mypage?pwerror";
		}
		try {
			accountService.updatePassword(member.getId(), member.getPassword());
			reviseMemberInfoService.reviseMyInfo(member);
		} catch (Exception e) {
			return "redirect:/member/mypage?error";
		}
		model.addAttribute("mypage", "active");
		model.addAttribute("member",member);
		return "redirect:/member/mypage?success";
	}

	@GetMapping("/member/ptcancle.do")
	public String cancleSchedule(@AuthenticationPrincipal Account account, String day, String hour )
	{
		try{
			scheduleService.cancleSchedule(account.getId(), day, hour);
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/member/apply?cancle_error";
		}
		return "redirect:/member/apply?cancle_success";
	}

	@GetMapping("/member/ptreserv.do")
	public String reserveSchedule(@AuthenticationPrincipal Account account, String day, String hour )
	{
		MemberDTO member=findUserService.findMemberById(account.getId());
		int idx=0;
		try{
			idx=scheduleService.reserveSchedule(account.getId(), member.getTrainer_id(), hour, day);
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/member/apply?reserve_error";
		}
		return "redirect:/member/apply?reserve_success&idx="+idx;
	}


}
