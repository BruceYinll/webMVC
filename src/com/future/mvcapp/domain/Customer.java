package com.future.mvcapp.domain;

public class Customer {
	
	private Integer id;
	private String address;
	private String phone;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
//	public Customer() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", address=" + address + ", phone=" + phone + ", name=" + name + ", getId()="
				+ getId() + ", getAddress()=" + getAddress() + ", getPhone()=" + getPhone() + ", getName()=" + getName()
				+ "]";
	}


}
