package com.caps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/CAPS")
public class DemoController {
	
	@RequestMapping("/Hello")
	@ResponseBody
	public ModelAndView insertCustomers(Model model) {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("Text", "This is a demo controller, the url request is localhost:8080/CAPS/Hello");
		return mav;
	}

}
