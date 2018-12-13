package com.caps.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.caps.entity.Account;
import com.caps.entity.Course;
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
	
	
	
	
	@RequestMapping("/student-course")	
	public ModelAndView viewEnrollment(HttpSession httpsession) {
		ModelAndView mav = new ModelAndView("/student/student-course");
		mav.addObject("courselist", studentService.findByIdUserid(httpsession));
		return mav;

	}
		
	
	@RequestMapping("/student-create-enrollment")
	@ResponseBody
	public ModelAndView displayCourses(Model model) {
		ModelAndView mav = new ModelAndView("/student/student-create-enrollment");
		mav.addObject("course", new Course());
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
	
	@RequestMapping(value = "/delete/{courseid}/{userid}", method = RequestMethod.GET)
	public ModelAndView deleteStudent(@PathVariable String courseid,@PathVariable String userid)
			 {
		EnrollmentPK e = new EnrollmentPK();
	e.setCourseid(Integer.parseInt(courseid));	
	e.setUserid(Integer.parseInt(userid));
		
	studentService.Delete(e);
		ModelAndView mav = new ModelAndView("student/student-course");
		return mav;
	}
	
	@RequestMapping(value = "/addenrollement/{courseid}", method = RequestMethod.GET)
	public ModelAndView addEnrollment(@PathVariable String courseid,HttpSession httpsession)
			 {
		EnrollmentPK e = new EnrollmentPK();
	e.setCourseid(Integer.parseInt(courseid));	

	e.setUserid(Integer.parseInt(UserUtil.currentUser(httpsession)));
	Enrollment ex = new Enrollment();
	ex.setId(e);
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();	
	ex.setEnrollmentDate(dateFormat.format(date));		
	studentService.createStudent(ex);
		ModelAndView mav = new ModelAndView("student/student-course");
		return mav;
	}
	
	@RequestMapping("/api/enrollment")
	@ResponseBody
	public List<Enrollment> listEnrollment(HttpSession httpsession) {

	
		return (List<Enrollment>)studentService.findByIdUserid(httpsession);
	}
	
	@RequestMapping("/api/listcourses")
	@ResponseBody
	public List<Course> listCourses() {
		return studentService.findAllCourses();
	}

}