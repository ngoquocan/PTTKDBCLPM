package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SalaryMonth;

public interface SalaryMonthRepository extends JpaRepository<SalaryMonth, Long> {
	List<SalaryMonth> findByMonth(int month);

}
