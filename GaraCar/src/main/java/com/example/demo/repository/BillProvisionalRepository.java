package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.BillProvisional;

public interface BillProvisionalRepository extends JpaRepository<BillProvisional, Long>{
	@Query(value = "SELECT * FROM bill_provisional b "
			+ "WHERE b.technical_employee_id = ?1 AND MONTH(b.date_created) = ?2 "
			+ "AND YEAR(b.date_created) = ?3",nativeQuery = true)
	List<BillProvisional> findByTechnicalEmployeeId(Long id,Integer month,Integer year);
	
}
