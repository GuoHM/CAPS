package com.caps.service;

import java.util.List;

import com.caps.entity.*;


public interface AdminService {
	List<Course> findAllCourses();	
	List<Account> findAllLecturers();
	List<Account> findStuNotenroll(int courseid);
	List<Account> findByType(); //Mingjie 12/13/2018/3:50
	List<Enrollment> findEnrollment(int courseid);
    void removeEnrollment(int userid);
    void updateEnrollment(int courseid,int userid,int grades,String enrollmentDate);
    void addEnrollment(int courseid,int userid,int grades,String enrollmentDate);
	List<Account> findByType(String type);
	public Course insertOrUpdate(Course course);
	public Account insertOrUpdate(Account account);
	public Course findByCourseId(int id);
	public void delete(int id);
	public void deleteStudent(int id);

	Enrollment findByEnrollmentId(EnrollmentPK id);
	List<Enrollment> findEnrollmentByStudentid(int id);
	public List<Course> findCourseByLecturer(int id);
	




}
