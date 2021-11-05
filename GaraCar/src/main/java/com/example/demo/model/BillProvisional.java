package com.example.demo.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Data
@Entity
public class BillProvisional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "technical_manage_id")
	private Employee technical_manage;
	@ManyToOne
	@JoinColumn(name = "technical_employee_id")
	private Employee technical_employee;
	@ManyToOne
	@JoinColumn(name = "accounting_id")
	private Employee accounting;
	@ManyToOne
	@JoinColumn
	private Car car;
	private Date date_created;
	private Long total_money;
	private String note;
	@ManyToOne
	@JoinColumn
	private Slot slot;
	
	@OneToMany(mappedBy = "bill_provisional")
	@EqualsAndHashCode.Exclude
    @ToString.Exclude
	private List<BillProvisionalDetail> listBillDetaill;

}