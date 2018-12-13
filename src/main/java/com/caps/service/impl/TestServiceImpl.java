package com.caps.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caps.dao.CourseDao;
import com.caps.dao.EnrollmentDao;
import com.caps.dao.AccountDao;
import com.caps.entity.Course;
import com.caps.entity.Enrollment;
import com.caps.entity.Account;
import com.caps.service.TestService;


@Service
public class TestServiceImpl implements TestService {

	@Autowired
	AccountDao userDao;
	
	@Autowired
	EnrollmentDao enrollmentDao;
	
	@Autowired
	CourseDao courseDao;

	@Override
	public List<Course> findCourse() {
		// TODO Auto-generated method stub
		return courseDao.findAll();
	}

	@Override
	public List<Enrollment> findEnrollment() {
		// TODO Auto-generated method stub
		//return enrollmentDao.findAll();
		return enrollmentDao.findAll();
	}

	@Override
	public List<Account> findUser() {
		// TODO Auto-generated method stub
//		List<Account> result = new ArrayList<Account>();
//		result.add(userDao.findByUserid(1));
//		return result;
		return userDao.findAll();
	}

}
