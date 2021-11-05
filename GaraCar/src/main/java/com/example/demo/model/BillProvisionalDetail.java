package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
@Data
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "bill_provisional_id", "service_id" }) })
public class BillProvisionalDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "bill_provisional_id")
	private BillProvisional bill_provisional;
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;
	private Integer count;
}