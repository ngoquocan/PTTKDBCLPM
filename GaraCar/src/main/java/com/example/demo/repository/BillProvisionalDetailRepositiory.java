package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.BillProvisional;
import com.example.demo.model.BillProvisionalDetail;

public interface BillProvisionalDetailRepositiory extends JpaRepository<BillProvisionalDetail, Long>{
}
