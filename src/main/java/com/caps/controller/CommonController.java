package com.caps.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CommonController {
	
	@RequestMapping("/Hello")
	public ModelAndView insertCustomers(Model model) {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("Text", "This is a demo controller, the url request is localhost:8080/CAPS/Hello");
		return mav;
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/logout")
	public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    ModelAndView mav = new ModelAndView("index");
	    mav.addObject("Text", "logout successfully");
	    return mav;//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}

}
