package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class BillImport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn
	private Supplier supplier;
	@ManyToOne
	@JoinColumn(name = "warehouse_employee_id")
	private Employee warehouse_employee;
	private Date date_created;
	private Long total_money;
	private String note;

}