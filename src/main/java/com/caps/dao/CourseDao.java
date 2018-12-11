package com.caps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caps.entity.Account;
import com.caps.entity.Course;

public interface CourseDao extends JpaRepository<Course, Integer> {
	List<Course> findByCourseid(int courseid);
	
	List<Course> findByAccount(Account account);
}
