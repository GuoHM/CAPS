package com.caps.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caps.entity.Account;
import com.caps.entity.Course;
import com.caps.entity.Enrollment;
import com.caps.service.AdminService;
import com.caps.util.UserUtil;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;




@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@ModelAttribute
	public void setFormEmptyObject(Model model,HttpSession httpsession) {
		model.addAttribute("login",UserUtil.currentUser(httpsession));
	}
	
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
	
	@RequestMapping("/findCourses")
	@ResponseBody
	public ModelAndView displayCourses(Model model) {
		ModelAndView mav = new ModelAndView("/admin/list-course");
		mav.addObject("course", new Course());
		return mav;
	}
	
	@RequestMapping("/findstudents")
	@ResponseBody
	public ModelAndView displayStudents(Model model) {
		ModelAndView mav = new ModelAndView("/admin/liststudent");
		model.addAttribute("student", new Account());	
		return mav;
	}
	
	@RequestMapping("/addcourse")
	@ResponseBody
	public ModelAndView addCourse(Model model) {
		ModelAndView mav = new ModelAndView("admin/add-course");
		model.addAttribute("course", new Course());	
		mav.addObject("courselist", adminService.findByType("lecturer"));
	
		return mav;
	}
	
	@RequestMapping("/addstudent")
	@ResponseBody
	public ModelAndView addStudent(Model model) {
		ModelAndView mav = new ModelAndView("admin/add-student");
		model.addAttribute("student", new Account());	
		//mav.addObject("courselist", adminService.findByType("lecturer"));	
		return mav;
	}
	
	@RequestMapping(value = "/delete/{courseID}", method = RequestMethod.GET)
	public ModelAndView deleteCourse(@PathVariable String courseID, final RedirectAttributes redirectAttributes)
			 {
		Course course = adminService.findByCourseId(Integer.parseInt(courseID));
		adminService.delete(Integer.parseInt(courseID));
		ModelAndView mav = new ModelAndView("redirect:/admin/findCourses");
		String message = "The Course " + course.getCourseName()  + " was successfully deleted.";
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
	
	@RequestMapping(value = "/deleteStudent/{studentID}", method = RequestMethod.GET)
	public ModelAndView deleteStudent(@PathVariable String studentID, final RedirectAttributes redirectAttributes)
			 {
	
		adminService.deleteStudent(Integer.parseInt(studentID));
		ModelAndView mav = new ModelAndView("redirect:/admin/findstudents");
		
		return mav;
	}
	
	@PostMapping("/NewCourse")
	public ModelAndView insertCourse(@ModelAttribute Course course) {
		//DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		//course.setStartDate(df.parse(course.getStartDate()));
		adminService.insertOrUpdate(course);
		ModelAndView mav = new ModelAndView("/admin/add-course");		
		mav.addObject("course", new Course());
		return mav;
	}
	
	@PostMapping("/NewStudent")
	public ModelAndView insertStudent(@ModelAttribute Account account) {
		//DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		//course.setStartDate(df.parse(course.getStartDate()));
		adminService.insertOrUpdate(account);
		ModelAndView mav = new ModelAndView("/admin/add-student");		
		mav.addObject("student", new Account());
		return mav;
	}


	@PostMapping("/UpdateCourse")
	public ModelAndView updateCourse(@ModelAttribute Course course) {
		//DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		//course.setStartDate(df.parse(course.getStartDate()));
		adminService.insertOrUpdate(course);
		ModelAndView mav = new ModelAndView("/admin/list-course");		
		//mav.addObject("course", course);
		mav.addObject("course", new Course());
		return mav;
	}
	
	@PostMapping("/UpdateStudent")
	public ModelAndView updateStudent(@ModelAttribute Account account) {
		//DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		//course.setStartDate(df.parse(course.getStartDate()));
		adminService.insertOrUpdate(account);
		ModelAndView mav = new ModelAndView("/admin/liststudent");		
		//mav.addObject("course", course);
		mav.addObject("student", new Account());
		return mav;
	}
	
	@RequestMapping("/api/listlecturer")
	@ResponseBody
	public List<Account> listEnrollment() {
		return adminService.findByType("lecturer");
	}
	
	@RequestMapping("/api/listcourses")
	@ResponseBody
	public List<Course> listCourses() {
		return adminService.findAllCourses();
	}
	@RequestMapping("/api/liststudents")
	@ResponseBody
	public List<Account> listStudents() {
		return adminService.findByType("student");
	}

	@RequestMapping(value="/editErollment/{userid}/{enrollmentDate}/{grades}/{courseid}", method = RequestMethod.GET)
	public ModelAndView enrollmentSTUED(@PathVariable String userid,@PathVariable String enrollmentDate,@PathVariable String grades,@PathVariable String courseid) {
		ModelAndView mav = new ModelAndView("redirect:/admin/enrollment-student");
		mav.addObject("courseid",courseid);
		mav.addObject("userid",userid);
		mav.addObject("enrolldate", enrollmentDate);
		mav.addObject("grades", grades);
		adminService.updateEnrollment(Integer.parseInt(courseid),Integer.parseInt(userid), Integer.parseInt(grades), enrollmentDate);
		return mav;
	}
	
	@RequestMapping(value="/deleteErollment/{userid}/{courseid}", method = RequestMethod.GET)
	public ModelAndView enrollmentSTUDL(@PathVariable String userid,@PathVariable String courseid) {
		ModelAndView mav = new ModelAndView("redirect:/admin/enrollment-student");
		mav.addObject("courseid",courseid);
		mav.addObject("userid",userid);
		adminService.removeEnrollment(Integer.parseInt(userid));
		return mav;
	}
	
	@RequestMapping("/addenrollment")
	public ModelAndView addenrollment(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/admin/enrollment");
		mav.addObject("userid", request.getParameter("userid"));
		return mav;
	}
	
	@RequestMapping("/enrollment")
	public ModelAndView enrollment(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("/admin/enrollment-course");
		mav.addObject("Text", UserUtil.currentUser(httpSession));
		return mav;
	}
	
	@RequestMapping("/enrollment-student")
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
	
	@RequestMapping("/api/enrollment-stuList")
	@ResponseBody
	public List<Account> listEnrollmentStud(HttpServletRequest request) {
		int courseid=Integer.parseInt(request.getParameter("courseid"));
        List<Account> ac = adminService.findStuNotenroll(courseid);
        return ac;   
	}
	
}
