package com.caps.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.caps.util.UserUtil;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@RequestMapping("/welcome")
	@ResponseBody
	public ModelAndView insertCustomers(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("/admin/welcome");
		mav.addObject("Text", UserUtil.currentUser(httpSession));
		return mav;
	}

}
