package com.project.customerarchiving.entities.concretes;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.customerarchiving.entities.abstracts.IEntity;

import lombok.Data;

@Entity
@Table(name="customers")
@Data
public class Customer implements IEntity{
	
	@Id
	Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=true)
	User userId;
	
	String customerName;
	String customerLastName;
	
}
