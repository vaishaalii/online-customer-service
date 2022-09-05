package com.ocsp.backend.project.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ocsp.backend.project.model.Customer;


@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	CustomerService userService;

	@Before
	public void setUp() throws Exception {
		userService = new CustomerService();
	}

	@Test
	public void testValidUser() {
		Customer user = new Customer();
		user.setUserId(1001);
		user.setDob("11-12-2012");
		user.setFirstName("Diva");
		user.setPassword("diva");
		userService.save(user);

		assertNotNull(userService.login(user));
	}

	@Test
	public void testInvalidUserName() {
		Customer user = new Customer();
		user.setFirstName("Divahar");
		user.setPassword("diva");

		assertNull(userService.login(user));
	}

	@Test
	public void testInvalidPassword() {
		Customer user = new Customer();
		user.setFirstName("Diva");
		user.setPassword("12345");

		assertNull(userService.login(user));
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}