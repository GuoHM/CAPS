package com.caps.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.caps.entity.Course;

public interface LecturerService {
	List<Course> findCourseByLecturer(HttpSession httpsession);
}
