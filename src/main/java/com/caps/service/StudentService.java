package com.caps.service;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.caps.entity.Course;
import com.caps.entity.Account;
import com.caps.entity.Enrollment;
import com.caps.entity.EnrollmentPK;

public interface StudentService {

	List<Enrollment> findByIdCourseid(int courseid);

	List<Enrollment> findByIdUserid(HttpSession httpsession);
	
    Enrollment createStudent(Enrollment e);
	
    public List<Course> findAllCourses();
	void removeStudent(Enrollment e);
	
	public void Delete(EnrollmentPK id);
	
}