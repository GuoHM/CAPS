package com.caps.service;

import java.util.List;

import com.caps.entity.*;


public interface AdminService {
	List<Course> findAllCourses();	
	List<Account> findAllLecturers();
	List<Account> findByType(String type);
	public Course insertOrUpdate(Course course);
	public Account insertOrUpdate(Account account);
	public Course findByCourseId(int id);
	public void delete(int id);
	public void deleteStudent(int id);

}
