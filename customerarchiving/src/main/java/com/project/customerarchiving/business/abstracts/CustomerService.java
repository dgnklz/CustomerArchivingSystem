package com.project.customerarchiving.business.abstracts;

import java.util.List;

import com.project.customerarchiving.entities.concretes.Customer;


public interface CustomerService {

	List<Customer> getAllCustomers();

	Customer saveOneCustomer(Customer newCustomer);
	
	Customer updateOneCustomer(Long customerId, Customer newCustomer);

	void deleteById(Long customerId);
	
}
