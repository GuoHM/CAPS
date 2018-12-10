package com.caps.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the enrollment database table.
 * 
 */
@Entity
@NamedQuery(name="Enrollment.findAll", query="SELECT e FROM Enrollment e")
public class Enrollment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EnrollmentPK id;

	@Column(name="enrollment_date")
	private String enrollmentDate;

	private int grades;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="userid")
	private Account account;

	//bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name="courseid")
	private Course course;

	public Enrollment() {
	}

	public EnrollmentPK getId() {
		return this.id;
	}

	public void setId(EnrollmentPK id) {
		this.id = id;
	}

	public String getEnrollmentDate() {
		return this.enrollmentDate;
	}

	public void setEnrollmentDate(String enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public int getGrades() {
		return this.grades;
	}

	public void setGrades(int grades) {
		this.grades = grades;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}