package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class SalaryMonth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn
	private Employee employee;
	private int month;
	private Long total_money;
	private Long hard_salary;
	private String status;
	private String note;

	public void getSalaryMonth() {
		// TODO - implement SalaryMonth.getSalaryMonth
		throw new UnsupportedOperationException();
	}

	public void updateStatus() {
		// TODO - implement SalaryMonth.updateStatus
		throw new UnsupportedOperationException();
	}

	public void saveSalary() {
		// TODO - implement SalaryMonth.saveSalary
		throw new UnsupportedOperationException();
	}

}