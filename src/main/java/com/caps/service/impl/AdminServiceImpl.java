package com.caps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caps.dao.AccountDao;
import com.caps.dao.AccountRoleDao;
import com.caps.dao.CourseDao;
import com.caps.dao.EnrollmentDao;
import com.caps.entity.Account;
import com.caps.entity.Accountrole;
import com.caps.entity.Course;
import com.caps.entity.Enrollment;
import com.caps.entity.EnrollmentPK;
import com.caps.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AccountDao userDao;

	@Autowired
	EnrollmentDao enrollmentDao;

	@Autowired
	CourseDao courseDao;

	@Autowired
	AccountDao accountDao;
	
	@Autowired
	AccountRoleDao accountRoleDao;

	@Override
	public List<Course> findAllCourses() {
		// TODO Auto-generated method stub
		// List<Account> result = new ArrayList<Account>();
		// result.add(userDao.findByUserid(1));
		// return result;
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
		// List<Account> result = new ArrayList<Account>();
		// result.add(userDao.findByUserid(1));
		// return result;
		return userDao.findAll();

	}

	@Override
	public List<Account> findByType(String type) {
		// TODO Auto-generated method stub
		return userDao.findByType(type);
	}

	@Override
	public Course insertOrUpdate(Course course) {
		// TODO Auto-generated method stub
		return courseDao.save(course);
	}

	@Override
	public Account insertOrUpdate(Account account) {
		return userDao.save(account);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		courseDao.delete(id);
	}

	@Override
	public void deleteStudent(int id) {
		userDao.delete(id);
	}

	@Override
	public Course findByCourseId(int id) {
		return courseDao.findByCourseid(id);
	}

	@Override
	public Enrollment findByEnrollmentId(EnrollmentPK id) {
		return enrollmentDao.findById(id);
	}

	@Override
	public List<Enrollment> findEnrollmentByStudentid(int id) {
		// TODO Auto-generated method stub
		return enrollmentDao.findByIdUserid(id);
	}

	@Override
	public List<Course> findCourseByLecturer(int id) {
		// TODO Auto-generated method stub

		Account account = userDao.findByUserid(id);
		return courseDao.findByAccount(account);
	}

	@Override
	public void removeEnrollment(int userid) {
		// TODO Auto-generated method stub
		List<Enrollment> Liste = enrollmentDao.findByIdUserid(userid);
		for (Enrollment en : Liste) {
			enrollmentDao.delete(en);
		}

	}

	@Override
	public void updateEnrollment(int courseid, int userid, int grades, String enrollmentDate) {
		// TODO Auto-generated method stub
		EnrollmentPK epk = new EnrollmentPK();
		epk.setCourseid(courseid);
		epk.setUserid(userid);
		Enrollment enrollment = enrollmentDao.findOne(epk);
		enrollment.setGrades(grades);
		enrollment.setEnrollmentDate(enrollmentDate);
		enrollmentDao.saveAndFlush(enrollment);

	}

	@Override
	public List<Account> findStuNotenroll(int courseid) {
		// TODO Auto-generated method stub


		List<Account> accounts = accountDao.findByType("ROLE_STUDENT");
        List<Enrollment> enrollments = enrollmentDao.findByIdCourseid(courseid);
        for(int i = 0;i<enrollments.size();i++ ) {
        	Enrollment enrollment = enrollments.get(i);
        	for(int j =0;j<accounts.size();j++) {
        		Account account = accounts.get(j);
        		if(account.equals(enrollment.getAccount())) {;
        		accounts.remove(account);
        		break;
        		}
        	}
        	
        }	
		return accounts;

	}

	@Override
	public List<Account> findByType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEnrollment(int courseid, int userid, int grades, String enrollmentDate) {
		// TODO Auto-generated method stub
		Enrollment enrollment = new Enrollment();
		EnrollmentPK epk = new EnrollmentPK();
		epk.setCourseid(courseid);
		epk.setUserid(userid);
		enrollment.setId(epk);
		enrollment.setGrades(grades);
		enrollment.setEnrollmentDate(enrollmentDate);
		enrollmentDao.saveAndFlush(enrollment);

	}
	@Override
	public Accountrole insertOrUpdateRole(Accountrole account) {
		return accountRoleDao.save(account);
	}
	

}
