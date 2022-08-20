package com.project.customerarchiving.apiControllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.customerarchiving.business.abstracts.CustomerService;
import com.project.customerarchiving.entities.concretes.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService){
		this.customerService = customerService;
	}
	
	//Bring all created customers
	@GetMapping
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	//Create new customer
	@PostMapping
	public Customer createCustomer(@RequestBody Customer newCustomer) {
		return customerService.saveOneCustomer(newCustomer);
	}
	
	//Update customer
	@PutMapping("/{customerId}")
	public Customer updateOneCustomer(@PathVariable Long customerId, @RequestBody Customer newCustomer) {
		return customerService.updateOneCustomer(customerId, newCustomer);
	}
	
	//Delete customer
	@DeleteMapping("/{customerId}")
	public void deleteOneCustomer(@PathVariable Long customerId) {
		customerService.deleteById(customerId);
	}
	
}
