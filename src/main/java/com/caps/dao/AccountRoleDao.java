package com.caps.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.caps.entity.Accountrole;

public interface AccountRoleDao extends JpaRepository<Accountrole, Integer> {
	
	

}
