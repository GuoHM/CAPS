package com.caps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.caps.entity.*;

public interface EnrollmentDao extends JpaRepository<Enrollment, EnrollmentPK> {
	List<Enrollment> findByIdCourseid(int courseid);
	List<Enrollment> findByIdUserid(int userid);
	
	Enrollment findById(EnrollmentPK pk);
	

}
