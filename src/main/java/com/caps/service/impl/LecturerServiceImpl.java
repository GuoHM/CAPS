package com.caps.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caps.dao.AccountDao;
import com.caps.dao.CourseDao;
import com.caps.dao.EnrollmentDao;
import com.caps.entity.Account;
import com.caps.entity.Course;
import com.caps.entity.Enrollment;
import com.caps.entity.EnrollmentPK;
import com.caps.service.LecturerService;
import com.caps.util.UserUtil;

@Service
public class LecturerServiceImpl implements LecturerService {
	@Autowired
	CourseDao courseDao;

	@Autowired
	AccountDao userDao;

	@Autowired
	EnrollmentDao enrollmentDao;

	@Override
	public List<Course> findCourseByLecturer(HttpSession httpsession) {
		// TODO Auto-generated method stub
		String userid = UserUtil.currentUser(httpsession);
		Account account = userDao.findByUserid(Integer.parseInt(userid));
		return courseDao.findByAccount(account);
	}

	@Override
	public List<Account> findStudentsByCourseid(int courseid) {
		// TODO Auto-generated method stub
		List<Enrollment> enrollment = enrollmentDao.findByIdCourseid(courseid);
		List<Account> result = new ArrayList<Account>();
		for (Enrollment e : enrollment) {
			result.add(userDao.findByUserid(e.getId().getUserid()));
		}
		return result;
	}

	@Override
	public List<Enrollment> findEnrollmentByCourseid(int courseid) {
		// TODO Auto-generated method stub
		return enrollmentDao.findByIdCourseid(courseid);
	}

	@Override
	public void updateGradesByCourseidAndUserid(int courseid, int userid, int grades) {
		// TODO Auto-generated method stub
		EnrollmentPK pk = new EnrollmentPK();
		pk.setCourseid(courseid);
		pk.setUserid(userid);
		Enrollment enrollment = enrollmentDao.findById(pk);
		enrollment.setGrades(grades);
		enrollmentDao.saveAndFlush(enrollment);
	}

}
