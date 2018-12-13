package com.caps.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the course database table.
 * 
 */
@Entity
@NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int courseid;

	@Column(name = "class_size")
	private int classSize;

	@Column(name = "course_name")
	private String courseName;

	@Column(name = "course_status")
	private String courseStatus;

	private int credit;

	private String duration;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_date")
	private Date startDate;

	// bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name = "lecture_id")
	private Account account;

	// bi-directional many-to-one association to Enrollment
	@OneToMany(mappedBy = "course")
	@JsonIgnore
	private List<Enrollment> enrollments;

	public Course() {
	}

	public int getCourseid() {
		return this.courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public int getClassSize() {
		return this.classSize;
	}

	public void setClassSize(int classSize) {
		this.classSize = classSize;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseStatus() {
		return this.courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

	public int getCredit() {
		return this.credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Enrollment> getEnrollments() {
		return this.enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public Enrollment addEnrollment(Enrollment enrollment) {
		getEnrollments().add(enrollment);
		enrollment.setCourse(this);

		return enrollment;
	}

	public Enrollment removeEnrollment(Enrollment enrollment) {
		getEnrollments().remove(enrollment);
		enrollment.setCourse(null);

		return enrollment;
	}

}