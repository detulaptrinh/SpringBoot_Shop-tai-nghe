package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Account;

public interface AccountDao extends JpaRepository<Account, String> {

	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('USER','ADMIN')")
	List<Account> getAdminstrators();
	
	@Query("select count(o) from Account o")
	Integer countAll();
}
