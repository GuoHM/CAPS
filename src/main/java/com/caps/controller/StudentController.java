package com.caps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.caps.entity.Account;
import com.caps.service.StudentService;
import com.caps.service.TestService;
import com.caps.util.UserUtil;
import com.caps.entity.Enrollment;
import com.caps.entity.EnrollmentPK;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@RequestMapping("/welcome")
	public String login() {
		return "/student/welcome";
	}
	
	@ModelAttribute
	public void setFormEmptyObject(Model model, HttpSession httpsession) {
		model.addAttribute("login", UserUtil.currentUser(httpsession));
	}
	
	
	@RequestMapping("/student-course")	
	public ModelAndView viewEnrollment(HttpSession httpsession) {
		ModelAndView mav = new ModelAndView("/student/student-course");
		mav.addObject("courselist", studentService.findByIdUserid(httpsession));
		return mav;

	}
	
	@RequestMapping("/student-create-enrollment")
	public ModelAndView createEnrollment()
	{
		ModelAndView mav = new ModelAndView("/student/student-create-enrollment");
		mav.addObject("enrollment", new Enrollment());
		return mav;
		
	}
	
	@PostMapping("/LecturerForm")
	public ModelAndView insertCustomers(@ModelAttribute Enrollment enrollment, HttpSession httpsession) {
		EnrollmentPK epk;
		
		
		
		//adminService.insertOrUpdate(lecturer);
		ModelAndView mav = new ModelAndView("admin/Listlecturer");	
		return mav;
	}
	
	
	@RequestMapping("/student-view-grades")	
	public ModelAndView viewGrade(HttpSession httpsession) {
		ModelAndView mav = new ModelAndView("/student/student-view-grades");
		
		mav.addObject("courselist", studentService.findByIdUserid(httpsession));
        List<Enrollment> enrollment = studentService.findByIdUserid(httpsession);
			
			double count=0.0;
			double result=0.0;
			int i=0;
		double gpa=0;
			for (Enrollment e : enrollment) {

				 gpa = (e.getGrades()*e.getCourse().getCredit()) +gpa;
				 result += e.getCourse().getCredit();	 
				 count++;
				 	
			}
			
			count = gpa/result;
				
			mav.addObject("CPA", count);
		return mav;

	}
	/*@RequestMapping(value = "/addLecturer")
	public ModelAndView createLecturers() {
		
		ModelAndView mav = new ModelAndView("admin/Newlecturerform");
		mav.addObject("lecturer", new Account());
		return mav;
	}
	
	@PostMapping("/LecturerForm")
	public ModelAndView insertCustomers(@ModelAttribute Account lecturer) {
		adminService.insertOrUpdate(lecturer);
		ModelAndView mav = new ModelAndView("admin/Listlecturer");	
		return mav;
	}*/
	
	@RequestMapping("/api/enrollment")
	@ResponseBody
	public List<Enrollment> listEnrollment(HttpSession httpsession) {

	
		return (List<Enrollment>)studentService.findByIdUserid(httpsession);
	}

}