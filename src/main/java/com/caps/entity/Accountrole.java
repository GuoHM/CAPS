package com.caps.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the accountrole database table.
 * 
 */
@Entity
@NamedQuery(name="Accountrole.findAll", query="SELECT a FROM Accountrole a")
public class Accountrole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int userid;

	private String authority;

	//bi-directional one-to-one association to Account
	@OneToOne
	@JoinColumn(name="userid")
	private Account account;

	public Accountrole() {
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}