package com.project.customerarchiving.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.customerarchiving.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Long> {

	User findByUserName(String userName);
	//Jpa repository uses auto methods like delete, change, update, bring all, find by id
}
