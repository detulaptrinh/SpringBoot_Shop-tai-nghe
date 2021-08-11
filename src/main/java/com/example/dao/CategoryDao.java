package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Category;

public interface CategoryDao extends JpaRepository<Category, String> {

}
