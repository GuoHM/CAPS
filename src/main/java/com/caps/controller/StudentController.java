package com.caps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.caps.entity.Account;
import com.caps.service.StudentService;
import com.caps.service.TestService;
import com.caps.util.UserUtil;
import com.caps.entity.Enrollment;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@RequestMapping("/welcome")
	public String login() {
		return "/student/welcome";
	}
	
	
	
	
	@RequestMapping("/student-course")	
	public ModelAndView viewEnrollment(HttpSession httpsession) {
		ModelAndView mav = new ModelAndView("/student/student-course");
		mav.addObject("courselist", studentService.findByIdUserid(httpsession));
		return mav;

	}
	
	
	
	
	@RequestMapping("/api/enrollment")
	@ResponseBody
	public List<Enrollment> listEnrollment(HttpSession httpsession) {

	
		return (List<Enrollment>)studentService.findByIdUserid(httpsession);
	}

}