package com.caps.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.caps.entity.Account;
import com.caps.entity.Course;
import com.caps.entity.Enrollment;

public interface LecturerService {
	List<Course> findCourseByLecturer(HttpSession httpsession);
	
	List<Account> findStudentsByCourseid(int courseid);
	
	List<Enrollment> findEnrollmentByCourseid(int courseid);
	
	void updateGradesByCourseidAndUserid(int courseid,int userid,int grade);
}
