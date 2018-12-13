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
	public List<Enrollment> findEnrollment(int courseid) {
		// TODO Auto-generated method stub
		return enrollmentDao.findByIdCourseid(courseid);
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

	@Override
	public void removeEnrollment(int userid) {
		// TODO Auto-generated method stub
        List<Enrollment> Liste = enrollmentDao.findByIdUserid(userid);
        for(Enrollment en :Liste) {
        	enrollmentDao.delete(en);
        }
	}

	@Override
	public void updateEnrollment(int courseid, int userid, int grades, String enrollmentDate) {
		// TODO Auto-generated method stub
		List<Enrollment> eList = enrollmentDao.findByIdUserid(userid);
		Enrollment e = eList.iterator().next();
		for(Enrollment en :eList) {
			int id = en.getCourse().getCourseid();
			if(id==courseid) {
				e=en;
			}
		}
		e.setEnrollmentDate(enrollmentDate);
		e.setGrades(grades);
		
	}

}
