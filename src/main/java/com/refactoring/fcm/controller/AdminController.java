package com.refactoring.fcm.controller;


import com.refactoring.fcm.DTO.user.Account;
import com.refactoring.fcm.DTO.user.CenterDTO;
import com.refactoring.fcm.DTO.user.ManagerDTO;
import com.refactoring.fcm.service.AccountService;
import com.refactoring.fcm.service.SaveManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AdminController {

	private AccountService accountService;

	private SaveManagerService saveManagerService;

	@Autowired
	public void setAccountService(AccountService accountService){
		this.accountService=accountService;
	}

	@Autowired
	public void setSaveManagerService(SaveManagerService saveManagerService){
		this.saveManagerService = saveManagerService;
	}


	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model, HttpServletRequest req) {

		return "admin/admin";
	}


	//관리자 추가
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String addManager(CenterDTO center, ManagerDTO manager, HttpServletRequest req, Model model) {


		try {
			Account account = new Account();
			account.setId(manager.getId());
			account.setType("MANAGER");
			account.setPassword(manager.getPassword());
			account.setCenter_id(center.getCenter_id());

			accountService.save(account, "ROLE_MANAGER", "MANAGER");
			saveManagerService.saveManager(manager, center);

		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("message", "관리자 추가 에러!!");
			return "admin/admin";
		}

		model.addAttribute("message","정상적으로 추가되었습니다");
		return "admin/admin";

	}

	//ADMIN 계정 부여
		@GetMapping("/create")
		@ResponseBody
		public Account create(){
			Account account=new Account();
			account.setId("admin");
			account.setPassword("1234");
			accountService.save(account, "ROLE_ADMIN", "ADMIN");
			return account;
		}


}
