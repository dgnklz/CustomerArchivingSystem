package com.project.customerarchiving.entities.concretes;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.project.customerarchiving.entities.abstracts.IEntity;

import lombok.Data;

@Entity
@Table(name="files")
@Data
public class File implements IEntity{
	
	@Id
	Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	Customer customerId;
	
	String title;
	
}
