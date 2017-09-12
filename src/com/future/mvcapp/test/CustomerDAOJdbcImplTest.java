package com.future.mvcapp.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.future.mvcapp.dao.CustomerDAO;
import com.future.mvcapp.dao.impl.CustomerDAOJdbcImpl;
import com.future.mvcapp.domain.Customer;

public class CustomerDAOJdbcImplTest {
	
	private CustomerDAO customerDAO = new CustomerDAOJdbcImpl();

	@Test
	public void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		
		Customer customer = new Customer();
		
		customer.setName("Mike");	
		customer.setAddress("ShenZhen");
		customer.setPhone("12121343434");
		
		customerDAO.save(customer);
	}

	@Test
	public void testGetInteger() {
		Customer cust = customerDAO.get(1);
		System.out.println(cust);
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCountWithName() {
		fail("Not yet implemented");
	}

}
