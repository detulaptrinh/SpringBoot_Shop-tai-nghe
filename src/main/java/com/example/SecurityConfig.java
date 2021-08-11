package com.example;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.entity.Account;
import com.example.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountService accountService;
	
	@Autowired
	BCryptPasswordEncoder pe;
	
	
	// Cung cấp nguồn dữ liệu đăng nhập
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(Accountid -> {
			try {
				Account user = accountService.findById(Accountid);
				String password = pe.encode(user.getPassword());
				String[] roles = user.getAuthorities().stream()
						.map(er -> er.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(Accountid).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(Accountid + "Not found!");
			}
		});
	}
	
	// Phân quyền sử dụng
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/payment/**").authenticated()
			.antMatchers("/customer/**").hasAnyRole("CUST")
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			.anyRequest().permitAll();
		
		http.formLogin()
			.loginPage("/security/login/form")
			.loginProcessingUrl("/security/login")
			.defaultSuccessUrl("/shop", false)
			.failureUrl("/security/login/error");
		
		http.rememberMe()
			.tokenValiditySeconds(86400);
		
		http.logout()
			.logoutUrl("/security/logoff")
			.logoutSuccessUrl("/home");
	}
	
	// Cơ chế mã hóa mật khẩu
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Cho phép truy xuất REST API từ bên ngoài (domain khác)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
