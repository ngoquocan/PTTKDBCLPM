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
public class BillExport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "warehouse_employee_id")
	private Employee warehouse_employee;
	@ManyToOne
	@JoinColumn(name = "technical_employee_id")
	private Employee technical_employee;
	private Date date_created;
	private Long total_money;
	private String note;

	public void getIdBillTechnicalEmployee() {
		// TODO - implement BillExport.getIdBillTechnicalEmployee
		throw new UnsupportedOperationException();
	}

}