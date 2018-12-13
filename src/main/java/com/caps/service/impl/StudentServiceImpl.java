package com.caps.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caps.dao.AccountDao;
import com.caps.dao.CourseDao;
import com.caps.dao.EnrollmentDao;
import com.caps.entity.Course;
import com.caps.entity.Enrollment;
import com.caps.entity.EnrollmentPK;
import com.caps.service.StudentService;
import com.caps.util.UserUtil;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	AccountDao userDao;

	@Autowired
	EnrollmentDao enrollmentDao;

	@Autowired
	CourseDao courseDao;

	@Override
	public List<Enrollment> findByIdCourseid(int courseid) {
		// TODO Auto-generated method stub
		Enrollment enrollment = enrollmentDao.findByIdCourseid(courseid).get(courseid);
		System.out.println(enrollment.toString());
		return (List<Enrollment>) findByIdCourseid(courseid);

	}

	@Override
	public List<Enrollment> findByIdUserid(HttpSession httpsession) {
		// TODO Auto-generated method stub
		String userid = UserUtil.currentUser(httpsession);
		return enrollmentDao.findByIdUserid(Integer.parseInt(userid));
	}

	@Override
	public Enrollment createStudent(Enrollment e) {
		// TODO Auto-generated method stub
		enrollmentDao.save(e);
		return null;
	}

	@Override
	public void removeStudent(Enrollment e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Delete(EnrollmentPK e) {
		// TODO Auto-generated method stub

		enrollmentDao.delete(e);
	}

	@Override
	public List<Course> findAllCourses() {
		// TODO Auto-generated method stub
		// List<Account> result = new ArrayList<Account>();
		// result.add(userDao.findByUserid(1));
		// return result;
		return courseDao.findAll();
	}

	@Override
	public double calculateGPA(HttpSession httpsession) {
		// TODO Auto-generated method stub
		double count=0.0;
		double result=0.0;
		double gpa=0;
		int userid = Integer.parseInt(UserUtil.currentUser(httpsession));
		List<Enrollment> enrollment = enrollmentDao.findByIdUserid(userid);
		for (Enrollment e : enrollment) {

			gpa = (e.getGrades()*e.getCourse().getCredit()) +gpa;
			result += e.getCourse().getCredit();	 
			count++;

		}
		count = gpa/result;
		return count;
	}

}