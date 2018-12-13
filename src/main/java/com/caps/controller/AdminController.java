package com.caps.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caps.entity.Account;
import com.caps.entity.Course;
import com.caps.entity.Enrollment;
import com.caps.entity.EnrollmentPK;
import com.caps.service.AdminService;
import com.caps.service.TestService;
import com.caps.service.StudentService;
import com.caps.util.UserUtil;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;




@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	StudentService studentService;
	
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
		mav.addObject("lecturer", new Account());
	
		return mav;
	}
	
	@RequestMapping("/findCourses")
	@ResponseBody
	public ModelAndView displayCourses(Model model) {
		ModelAndView mav = new ModelAndView("/admin/list-course");
		mav.addObject("course", new Course());
		mav.addObject("courselist", adminService.findByType("lecturer"));
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
	
	@RequestMapping(value = "/deleteCourse/{courseID}", method = RequestMethod.GET)
	public ModelAndView deleteCourse(@PathVariable String courseID, final RedirectAttributes redirectAttributes, HttpSession httpsession)
			 {
		Course course = adminService.findByCourseId(Integer.parseInt(courseID));
		EnrollmentPK e = new EnrollmentPK();
		String userid = UserUtil.currentUser(httpsession);
		e.setCourseid(Integer.parseInt(courseID));
		e.setUserid(Integer.parseInt(userid));
		Enrollment en = adminService.findByEnrollmentId(e);
		if(en.getCourse().getCourseid()==Integer.parseInt(courseID))
		{
			studentService.Delete(e);
		}
		
			
		adminService.delete(Integer.parseInt(courseID));
		ModelAndView mav = new ModelAndView("redirect:/admin/findCourses");
		
		String message = "The Course " + course.getCourseName()  + " was successfully deleted.";
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
	
	@RequestMapping(value = "/deleteStudent/{studentID}", method = RequestMethod.GET)
	public ModelAndView deleteStudent(@PathVariable String studentID, final RedirectAttributes redirectAttributes)
			 {
		EnrollmentPK e = new EnrollmentPK();
		e.setUserid(Integer.parseInt(studentID));
		List<Enrollment> enrollment = adminService.findEnrollmentByStudentid(Integer.parseInt(studentID));
		for (Enrollment es : enrollment) {
			//result.add(userDao.findByUserid(e.getId().getUserid()));
			e.setUserid(Integer.parseInt(studentID));
			e.setCourseid(es.getCourse().getCourseid());
			System.out.println(es.getCourse().getCourseid());
			studentService.Delete(e);			
		}
		
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
		mav.addObject("courselist", adminService.findByType("lecturer"));
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
	
	@RequestMapping(value = "/addLecturer")
	public ModelAndView createLecturers() {
		
		ModelAndView mav = new ModelAndView("admin/Newlecturerform");
		mav.addObject("lecturer", new Account());
		return mav;
	}
	
	@PostMapping("/LecturerForm")
	public ModelAndView insertCustomers(@ModelAttribute Account lecturer) {
		adminService.insertOrUpdate(lecturer);
		ModelAndView mav = new ModelAndView("admin/Listlecturer");
		mav.addObject("lecturer", new Account());
		return mav;
	}
	
	@RequestMapping(value = "/delete/{userid}", method = RequestMethod.GET)
	public ModelAndView deleteLecturer(@PathVariable String userid)
	{
		;
		List<Course> course = adminService.findCourseByLecturer(Integer.parseInt(userid));
		for (Course es : course) {
			//result.add(userDao.findByUserid(e.getId().getUserid()));
			if(es.getAccount().getUserid()==Integer.parseInt(userid));
			{
				System.out.println(es.getCourseid());
				adminService.delete(es.getCourseid());	
			}
		}
		
		adminService.deleteStudent(Integer.parseInt(userid));
		ModelAndView mav = new ModelAndView("admin/Listlecturer");
		mav.addObject("lecturer", new Account());
		return mav;
	}
	
	@PostMapping("/update")
	public ModelAndView updateLecturer(@ModelAttribute Account lecturer) {
		
		adminService.insertOrUpdate(lecturer);
		ModelAndView mav = new ModelAndView("admin/Listlecturer");	
		mav.addObject("lecturer", new Account());
		return mav;
	}

}
