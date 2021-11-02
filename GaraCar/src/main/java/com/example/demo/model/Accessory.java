package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "accessory")
public class Accessory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "import_price")
	private Long import_price;
	@Column(name = "image")
	private String image;
	@Column(name = "note")
	private String note;
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

}