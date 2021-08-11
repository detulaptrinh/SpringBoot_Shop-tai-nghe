package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CategoryDao;
import com.example.entity.Category;
import com.example.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired 
	CategoryDao dao;
	
	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}
	
	@Override
	public Category findById(String id) {
		return dao.findById(id).get();
	}

	@Override
	public Category create(Category category) {
		return dao.save(category);
	}

	@Override
	public Category update(Category category) {
		return dao.save(category);
	}

	@Override
	public void delete(String id) {
		dao.deleteById(id);
	}
}
