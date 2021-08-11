package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {

	@Query("select p from Product p where p.category.id = ?1")
	List<Product> findByCategoryId(String cid);

}
