package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Employee;
import com.example.demo.model.SalaryMonth;
import com.example.demo.repository.SalaryMonthRepository;
import com.example.demo.repository.UserRepository;

import Utils.KeyWord;
import javassist.compiler.ast.Keyword;

@Controller
@RequestMapping()
public class ManageController {
	@Autowired
	private SalaryMonthRepository salaryMonthRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/quanlybangluong")
	public String qlbangluong(ModelMap modelMap) {
		//lấy tháng hiện tại
		Calendar time = Calendar.getInstance();
		int month = time.get(Calendar.MONTH);
		//lấy danh sách lương nhân niên theo tháng.
		List<SalaryMonth> listSalaryMonth = salaryMonthRepository.findByMonth(month);
		
		//chưa có thì tạo mới.
		if(listSalaryMonth == null) {
			listSalaryMonth = new ArrayList<>();
			List<Employee> listEmployee = userRepository.findAll();
			for(Employee e : listEmployee) {
				SalaryMonth salaryMonth = new SalaryMonth();
				salaryMonth.setEmployee(e);
				salaryMonth.setHard_salary(e.getSalary_hard());
				salaryMonth.setMonth(month);
				salaryMonth.setStatus(KeyWord.CHUA_TT);
				
				//set tổng lương
				//nếu là nhân viên kỹ thuật
				if(e.getPosition().equals(KeyWord.TECHNICAL_EMPLOYEE)) {
					salaryMonth.setTotal_money(tongtien(e));
				}
				else {
					salaryMonth.setTotal_money(e.getSalary_hard());
				}
				//salaryMonthRepository.save(salaryMonth);
				listSalaryMonth.add(salaryMonth);
			}
		}			
		modelMap.addAttribute("listSalary",listSalaryMonth);
		return "gdQuanLyBangLuong";
	}
	
	private Long tongtien(Employee e) {
		Long tongtien = e.getSalary_hard();
		return tongtien;
	}
}
