package com.caps.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.caps.entity.Account;
import com.caps.entity.Course;
import com.caps.entity.Enrollment;
import com.caps.service.AdminService;
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
		ModelAndView mav = new ModelAndView("/admin/Listlecturer");
	
		return mav;
	}
	
	@RequestMapping("/api/listlecturer")
	@ResponseBody
	public List<Account> listEnrollment() {
		return adminService.findByType();
	}

	@RequestMapping(value="/editErollment/{userid}/{enrollmentDate}/{grades}/{courseid}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView enrollmentSTUED(@PathVariable String userid,@PathVariable String enrollmentDate,@PathVariable String grades,@PathVariable String courseid) {
		ModelAndView mav = new ModelAndView("redirect:/admin/enrollment-student");
		mav.addObject("courseid",courseid);
		mav.addObject("userid",userid);
		//mav.addObject("enrollmentDate",enrollmentDate);
		//mav.addObject("grades",grades);
		adminService.updateEnrollment(2,2,2, "2018-10-10");
		return mav;
	}
	
	@RequestMapping(value="/deleteErollment/{userid}/{courseid}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView enrollmentSTUDL(@PathVariable String userid,@PathVariable String courseid) {
		ModelAndView mav = new ModelAndView("redirect:/admin/enrollment-student");
		mav.addObject("courseid",courseid);
		mav.addObject("userid",userid);
		adminService.removeEnrollment(Integer.parseInt(userid));
		return mav;
	}
	
	@RequestMapping("/enrollment")
	@ResponseBody
	public ModelAndView enrollment(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("/admin/enrollment-course");
		mav.addObject("Text", UserUtil.currentUser(httpSession));
		return mav;
	}
	
	@RequestMapping("/enrollment-student")
	@ResponseBody
	public ModelAndView enrollmentSTU(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/admin/enrollment-student");
		mav.addObject("courseid",request.getParameter("courseid"));
		return mav;
	}
	
	@RequestMapping("/api/enrollment-course")
	@ResponseBody
	public List<Course> listCourse() {
		return adminService.findAllCourses();
	}
	
	@RequestMapping("/api/enrollment-student")
	@ResponseBody
	public List<Enrollment> listEnrollmentStu(HttpServletRequest request) {
		int courseid=Integer.parseInt(request.getParameter("courseid"));
		return adminService.findEnrollment(courseid);
	}
	
}
