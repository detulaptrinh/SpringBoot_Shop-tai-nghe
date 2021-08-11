package com.example.service;

import java.util.List;

import com.example.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {

	Order create(JsonNode orderData);

	Order findById(Long id);
	
	Order update(Order order);

	List<Order> findByUsernameCheck(String username);
	List<Order> findByCheck();

	List<Order> findByUsernameSuccess(String username);
	List<Order> findBySuccess();

	// count
	Integer countCheck(String username);
	Integer countSuccess(String username);
	
	// count admin
	Integer countCheckAdmin();
	Integer countSuccessAdmin();
	Long doanhThuAdmin();
	
	// count day - yesteday - moth
	Integer countDay();
	Integer countYesterday();
	Integer countMoth();
	
	// sum day - yesteday - moth
	Long sumDay();
	Long sumYesterday();
	Long sumMoth();
	
	// list order day - yesterday - moth - year
	List<Order> listDay();
	List<Order> listYesterday();
	List<Order> listMoth();
	List<Order> listYear();

}
