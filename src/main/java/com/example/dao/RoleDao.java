package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Role;

public interface RoleDao extends JpaRepository<Role, String> {

}
