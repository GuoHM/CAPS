package com.caps.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caps.entity.Account;

public interface AccountDao extends JpaRepository<Account, Integer> {
	Account findByUserid(int userid);

	Account findByType(String type);

}
