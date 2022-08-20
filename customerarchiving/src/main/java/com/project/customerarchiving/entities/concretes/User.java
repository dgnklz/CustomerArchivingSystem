package com.project.customerarchiving.entities.concretes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.customerarchiving.entities.abstracts.IEntity;

import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User implements IEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String username;
	String password;
	
}
