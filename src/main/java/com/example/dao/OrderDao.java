package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Order;

public interface OrderDao extends JpaRepository<Order, Long> {
	
	@Query("select o from Order o where o.account.username=?1 and o.status=false")
	List<Order> findByUsernameCheck(String username);
	
	@Query("select o from Order o where o.status=false")
	List<Order> findByCheck();

	@Query("select o from Order o where o.account.username=?1 and o.status=true")
	List<Order> findByUsernameSuccess(String username);
	
	@Query("select o from Order o where o.status=true")
	List<Order> findBySuccess();

	// count order
	@Query("select count(o.account.username) from Order o where o.account.username=?1 and o.status=false")
	Integer countCheck(String username);
	@Query("select count(o.account.username) from Order o where o.account.username=?1 and o.status=true")
	Integer countSuccess(String username);
	
	// count order admin
	@Query("select count(o) from Order o where o.status=false")
	Integer countCheckAdmin();
	@Query("select count(o) from Order o where o.status=true")
	Integer countSuccessAdmin();
	@Query("select sum(sumprice) from Order o where o.status=true")
	Long doanhThuAdmin();
	
	// count day - yesterday - moth
	@Query("select count(o.createDate) from Order o where day(o.createDate) = Day(GETDATE()) and Month(o.createDate) = datepart(month,GETDATE()) and year(o.createDate) = year(GETDATE())")
	Integer countDay();
	@Query("select count(o.createDate) from Order o where day(o.createDate) = Day(GETDATE() - 1) and Month(o.createDate) = datepart(month,GETDATE()) and year(o.createDate) = year(GETDATE())")
	Integer countYesterday();
	@Query("select count(o.createDate) from Order o where Month(o.createDate) = datepart(month,GETDATE()) and year(o.createDate) = year(GETDATE())")
	Integer countMoth();
	
	// sum day - yesterday - moth
	@Query("select sum(o.sumprice) from Order o where day(o.createDate) = Day(GETDATE()) and Month(o.createDate) = datepart(month,GETDATE()) and year(o.createDate) = year(GETDATE())")
	Long sumDay();
	@Query("select sum(o.sumprice) from Order o where day(o.createDate) = Day(GETDATE() - 1) and Month(o.createDate) = datepart(month,GETDATE()) and year(o.createDate) = year(GETDATE())")
	Long sumYesterday();
	@Query("select sum(o.sumprice) from Order o where Month(o.createDate) = datepart(month,GETDATE()) and year(o.createDate) = year(GETDATE())")
	Long sumMoth();
	
	// list order day - yesterday - moth - year
	@Query("select o from Order o where day(o.createDate) = Day(GETDATE()) and Month(o.createDate) = datepart(month,GETDATE()) and year(o.createDate) = year(GETDATE())")
	List<Order> listDay();
	@Query("select o from Order o where day(o.createDate) = Day(GETDATE() - 1) and Month(o.createDate) = datepart(month,GETDATE()) and year(o.createDate) = year(GETDATE())")
	List<Order> listYesterday();
	@Query("select o from Order o where Month(o.createDate) = datepart(month,GETDATE()) and year(o.createDate) = year(GETDATE())")
	List<Order> listMoth();
	@Query("select o from Order o where year(o.createDate) = year(GETDATE())")
	List<Order> listYear();
}
