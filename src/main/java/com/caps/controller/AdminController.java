package com.caps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.caps.entity.Account;
import com.caps.service.AdminService;
import com.caps.service.TestService;
import com.caps.util.UserUtil;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping("/welcome")
	@ResponseBody
	public ModelAndView insertCustomers(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("/admin/welcome");
		mav.addObject("Text", UserUtil.currentUser(httpSession));
		return mav;
	}
	
	@RequestMapping("/findlecturer")
	@ResponseBody
	public ModelAndView insertCustomers(Model model) {
		ModelAndView mav = new ModelAndView("admin/Listlecturer");
	
		return mav;
	}
	
	@RequestMapping("/api/listlecturer")
	@ResponseBody
	public List<Account> listEnrollment() {
		return adminService.findByType();
	}

}
