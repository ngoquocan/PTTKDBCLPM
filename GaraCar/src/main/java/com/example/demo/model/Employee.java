package com.example.demo.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class Employee extends User {	
	private String position;
	
	private Long salary_hard;
}