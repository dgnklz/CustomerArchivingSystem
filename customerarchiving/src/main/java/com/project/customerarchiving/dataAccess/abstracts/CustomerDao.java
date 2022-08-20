package com.project.customerarchiving.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.customerarchiving.entities.concretes.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {
	//Jpa repository uses auto methods like delete, change, update, bring all, find by id
}
