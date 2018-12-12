package com.caps.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.caps.entity.Account;
import com.caps.entity.Course;
import com.caps.entity.Enrollment;
import com.caps.service.LecturerService;
import com.caps.util.UserUtil;

@Controller
@RequestMapping(value = "/lecturer")
public class LecturerController {
	@Autowired
	LecturerService lecturerservice;

	@ModelAttribute
	public void setNavBarLogin(Model model, HttpSession httpsession) {
		model.addAttribute("login", UserUtil.currentUser(httpsession));
		model.addAttribute("enrollment", new Enrollment());
		model.addAttribute("courselist", lecturerservice.findCourseByLecturer(httpsession));
	}

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
		return mav;
	}

	@RequestMapping(value="/grades",method = RequestMethod.POST)
	public String setGrades(@RequestParam("userid") String userid, @RequestParam("courseid") String courseid, @RequestParam("grades") String grades) {
		int user = Integer.parseInt(userid);
		int course = Integer.parseInt(courseid);
		int grade = Integer.parseInt(grades);
		lecturerservice.updateGradesByCourseidAndUserid(course, user, grade);
		return "/lecturer/view-enrollment";
	}

	@RequestMapping("/api/listcourse")
	@ResponseBody
	public List<Course> listCourses(HttpSession httpsession) {
		return lecturerservice.findCourseByLecturer(httpsession);
	}

	@RequestMapping("/api/listenrollment")
	@ResponseBody
	public List<Enrollment> listEnrollment(HttpServletRequest request) {
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		return lecturerservice.findEnrollmentByCourseid(courseid);
	}
}
