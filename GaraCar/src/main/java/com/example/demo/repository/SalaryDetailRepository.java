package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.SalaryDetail;

public interface SalaryDetailRepository extends JpaRepository<SalaryDetail, Long>{
	@Query(value = "CALL list_salary_month_detail_by_employee(?1,?2,?3)", nativeQuery = true)
	List<SalaryDetail> getSalaryDetailByEmployee(Long id,Integer month,Integer year);
}
