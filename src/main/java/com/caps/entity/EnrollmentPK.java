package com.caps.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the enrollment database table.
 * 
 */
@Embeddable
public class EnrollmentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int courseid;

	@Column(insertable=false, updatable=false)
	private int userid;

	public EnrollmentPK() {
	}
	public int getCourseid() {
		return this.courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public int getUserid() {
		return this.userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EnrollmentPK)) {
			return false;
		}
		EnrollmentPK castOther = (EnrollmentPK)other;
		return 
			(this.courseid == castOther.courseid)
			&& (this.userid == castOther.userid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.courseid;
		hash = hash * prime + this.userid;
		
		return hash;
	}
}