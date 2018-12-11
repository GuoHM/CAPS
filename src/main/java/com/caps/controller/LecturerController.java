package com.caps.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.caps.entity.Account;
import com.caps.entity.Course;
import com.caps.service.LecturerService;

@Controller
@RequestMapping(value = "/lecturer")
public class LecturerController {
	@Autowired
	LecturerService lecturerservice;

	@RequestMapping("/welcome")
	public String login() {
		return "/lecturer/welcome";
	}

	@RequestMapping("/lecturer-course")
	public String listCourses() {
		return "/lecturer/lecturer-course";
	}
	
	@RequestMapping("/view-enrollment")
	public ModelAndView viewEnrollment(HttpSession httpsession) {
		ModelAndView mav = new ModelAndView("/lecturer/view-enrollment");
		mav.addObject("courselist", lecturerservice.findCourseByLecturer(httpsession));
		return mav;
	}

	@RequestMapping("/api/listcourse")
	@ResponseBody
	public List<Course> listCourses(HttpSession httpsession) {
		return lecturerservice.findCourseByLecturer(httpsession);
	}
	
	@RequestMapping("/api/listenrollment")
	@ResponseBody
	public List<Account> listEnrollment(HttpServletRequest request) {
		int courseid=Integer.parseInt(request.getParameter("courseid"));
		return lecturerservice.findStudentsByCourseid(courseid);
	}
}
