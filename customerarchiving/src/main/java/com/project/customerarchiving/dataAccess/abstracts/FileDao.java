package com.project.customerarchiving.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.customerarchiving.entities.concretes.File;

public interface FileDao extends JpaRepository<File, Long> {
	//Jpa repository uses auto methods like delete, change, update, bring all, find by id ...
	
	List<File> findByCustomerId(Long customerId);
	
}
