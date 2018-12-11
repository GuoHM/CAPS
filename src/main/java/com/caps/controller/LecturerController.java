package com.caps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("/api/listcourse")
	public List<Course> listCourses(HttpSession httpsession) {	
		return lecturerservice.findCourseByLecturer(httpsession);
	}
}

