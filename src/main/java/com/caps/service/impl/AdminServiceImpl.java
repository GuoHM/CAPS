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
import com.caps.service.AdminService;;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AccountDao userDao;
	
	@Autowired
	EnrollmentDao enrollmentDao;
	
	@Autowired
	CourseDao courseDao;
	
	@Override
	public List<Course> findAllCourses() {
		// TODO Auto-generated method stub
//		List<Account> result = new ArrayList<Account>();
//		result.add(userDao.findByUserid(1));
//		return result;
		return courseDao.findAll();
	}

	@Override
	public List<Enrollment> findEnrollment() {
		// TODO Auto-generated method stub
		return enrollmentDao.findByIdUserid(1);
	}

	@Override
	public List<Account> findAllLecturers() {
		// TODO Auto-generated method stub
//		List<Account> result = new ArrayList<Account>();
//		result.add(userDao.findByUserid(1));
//		return result;
		return userDao.findAll();
		
	}

	@Override
	public List<Account> findByType() {
		// TODO Auto-generated method stub
		return userDao.findByType("lecturer");
	}

}
