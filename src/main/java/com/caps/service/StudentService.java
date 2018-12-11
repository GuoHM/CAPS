package com.caps.service;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.caps.entity.Enrollment;


public interface StudentService {

	List<Enrollment> findByIdCourseid(int courseid);

	List<Enrollment> findByIdUserid(HttpSession httpsession);
	
    Enrollment createStudent(Enrollment e);
	
	
	void removeStudent(Enrollment e);
}