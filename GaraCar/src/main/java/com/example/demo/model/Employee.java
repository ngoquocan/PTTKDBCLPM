package com.example.demo.model;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Entity
public class Employee extends User {	
	private String position;

	public void getAllEmployee() {
		// TODO - implement Employee.getAllEmployee
		throw new UnsupportedOperationException();
	}

	public void getEmployee() {
		// TODO - implement Employee.getEmployee
		throw new UnsupportedOperationException();
	}

}