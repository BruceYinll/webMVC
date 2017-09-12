package com.future.mvcapp.dao.impl;

import java.util.List;

import com.future.mvcapp.dao.CustomerDAO;
import com.future.mvcapp.dao.DAO;
import com.future.mvcapp.domain.Customer;

public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO{

	@Override
	public List<Customer> getAll() {
		String sql = "select id, name, address, phone from customers";
		return getForList(sql);
	}

	@Override
	public void save(Customer Customer) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO customers(name, address, phone) VALUES(?,?,?)";
		update(sql,Customer.getName(),Customer.getAddress(),Customer.getPhone());
	}
	
	@Override
	public Customer get(Integer id) {
		// TODO Auto-generated method stub
		
		String sql = "select id,name,address,phone from customers where id =?";
		return get(sql,id);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from customers where id =?";
		update(sql,id);
		
	}

	@Override
	public long getCountWithName(String name) {
		// TODO Auto-generated method stub
		String sql = "select count(id) from customers where name =?";
		return getForValue(sql, name);
	}

}
