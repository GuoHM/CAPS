package com.caps.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caps.dao.AccountDao;
import com.caps.dao.CourseDao;
import com.caps.entity.Account;
import com.caps.entity.Course;
import com.caps.service.LecturerService;
import com.caps.util.UserUtil;

@Service
public class LecturerServiceImpl implements LecturerService {
	@Autowired
	CourseDao courseDao;
	
	@Autowired
	AccountDao userDao;
	
	@Override
	public List<Course> findCourseByLecturer(HttpSession httpsession) {
		// TODO Auto-generated method stub
		String userid = UserUtil.currentUser(httpsession);
		Account account = userDao.findByUserid(Integer.parseInt(userid));
		return courseDao.findByAccount(account);
	}

}
