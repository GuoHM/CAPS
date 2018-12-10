package com.caps.service;

import java.util.List;

import com.caps.entity.*;

public interface TestService {
	
	List<Account> findUser();
	List<Course> findCourse();
	List<Enrollment> findEnrollment();

}
