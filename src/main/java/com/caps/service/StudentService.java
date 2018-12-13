package com.caps.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.caps.entity.Course;
import com.caps.entity.Enrollment;
import com.caps.entity.EnrollmentPK;

public interface StudentService {

	List<Enrollment> findByIdCourseid(int courseid);

	List<Enrollment> findByIdUserid(HttpSession httpsession);

	Enrollment createStudent(Enrollment e);

	List<Course> findAllCourses();

	void removeStudent(Enrollment e);

	void Delete(EnrollmentPK id);
	
	double calculateGPA(HttpSession httpsession);

}