package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.OrderDao;
import com.example.dao.OrderDetailDao;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao dao;
	
	@Autowired
	OrderDetailDao dao1;
	
	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		
		Order order = mapper.convertValue(orderData, Order.class);
		dao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetail"), type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		dao1.saveAll(details);
		
		return order;
	}

	@Override
	public Order findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public List<Order> findByUsernameCheck(String username) {
		return dao.findByUsernameCheck(username);
	}
	
	@Override
	public List<Order> findByCheck() {
		return dao.findByCheck();
	}
	
	@Override
	public List<Order> findByUsernameSuccess(String username) {
		return dao.findByUsernameSuccess(username);
	}
	
	@Override
	public List<Order> findBySuccess() {
		return dao.findBySuccess();
	}

	// count order
	@Override
	public Integer countCheck(String username) {
		return dao.countCheck(username);
	}
	@Override
	public Integer countSuccess(String username) {
		return dao.countSuccess(username);
	}
	
	// count order admin
	@Override
	public Integer countCheckAdmin() {
		return dao.countCheckAdmin();
	}
	@Override
	public Integer countSuccessAdmin() {
		return dao.countSuccessAdmin();
	}

	@Override
	public Long doanhThuAdmin() {
		return dao.doanhThuAdmin();
	}

	@Override
	public Order update(Order order) {
		return dao.save(order);
	}

	// count day - yesteday - moth
	@Override
	public Integer countDay() {
		return dao.countDay();
	}
	@Override
	public Integer countYesterday() {
		return dao.countYesterday();
	}
	@Override
	public Integer countMoth() {
		return dao.countMoth();
	}
	
	// sum day - yesteday - moth
	@Override
	public Long sumDay() {
		return dao.sumDay();
	}
	@Override
	public Long sumYesterday() {
		return dao.sumYesterday();
	}
	@Override
	public Long sumMoth() {
		return dao.sumMoth();
	}
	
	// list order day - yesterday - moth - year
	@Override
	public List<Order> listDay() {
		return dao.listDay();
	}
	@Override
	public List<Order> listYesterday() {
		return dao.listYesterday();
	}
	@Override
	public List<Order> listMoth() {
		return dao.listMoth();
	}
	@Override
	public List<Order> listYear() {
		return dao.listYear();
	}
}
