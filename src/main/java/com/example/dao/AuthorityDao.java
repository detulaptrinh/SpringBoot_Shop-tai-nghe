package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Account;
import com.example.entity.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Integer> {
	@Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
	List<Authority> authoritiesOf(List<Account> accounts);

}
