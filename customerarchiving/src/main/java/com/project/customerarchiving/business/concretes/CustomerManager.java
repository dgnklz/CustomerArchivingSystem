package com.project.customerarchiving.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.customerarchiving.business.abstracts.CustomerService;
import com.project.customerarchiving.dataAccess.abstracts.CustomerDao;
import com.project.customerarchiving.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService{
	
	CustomerDao customerDao;

	public CustomerManager(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.findAll();
	}

	@Override
	public Customer saveOneCustomer(Customer newCustomer) {
		return customerDao.save(newCustomer);
	}

	@Override
	public Customer updateOneCustomer(Long customerId, Customer newCustomer) {
		Optional<Customer> customer = customerDao.findById(customerId);
		if(customer.isPresent()) {
			Customer foundCustomer = customer.get();
			foundCustomer.setCustomerName(newCustomer.getCustomerName());
			foundCustomer.setCustomerLastName(newCustomer.getCustomerLastName());
			customerDao.save(foundCustomer);
			return foundCustomer;
		}
		else
			return null;
	}

	@Override
	public void deleteById(Long customerId) {
		customerDao.deleteById(customerId);
	}
	
	
}
