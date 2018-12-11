package com.caps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caps.entity.Account;

public interface AccountDao extends JpaRepository<Account, Integer> {
	Account findByUserid(int userid);

	List<Account> findByType(String type);

}
