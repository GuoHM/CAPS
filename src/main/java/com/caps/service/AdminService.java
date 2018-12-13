package com.caps.service;

import java.util.List;

import com.caps.entity.*;

public interface AdminService {
	List<Course> findAllCourses();	
	List<Account> findAllLecturers();
	List<Account> findByType();
	List<Enrollment> findEnrollment(int courseid);
    void removeEnrollment(int userid);
    void updateEnrollment(int courseid,int userid,int grades,String enrollDate);
}
