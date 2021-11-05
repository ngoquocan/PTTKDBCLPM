package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.BillProvisional;
import com.example.demo.model.BillProvisionalDetail;
import com.example.demo.model.Employee;
import com.example.demo.model.SalaryDetail;
import com.example.demo.model.SalaryMonth;
import com.example.demo.repository.BillProvisionalRepository;
import com.example.demo.repository.SalaryDetailRepository;
import com.example.demo.repository.SalaryMonthRepository;
import com.example.demo.repository.UserRepository;

import Utils.KeyWord;

@Controller
@RequestMapping()
public class ManageController {
	@Autowired
	private SalaryMonthRepository salaryMonthRepository;
	
	@Autowired
	private SalaryDetailRepository salaryDetailRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private BillProvisionalRepository bill;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/quanlybangluong")
	public String qlbangluong(ModelMap modelMap,HttpSession session) {
		if(session.getAttribute("USER") == null) {
			modelMap.addAttribute("checkLogin","Hết phiên làm việc!!!");
			return "login";
		}
		//lấy tháng hiện tại
		Calendar time = Calendar.getInstance();
		int month = time.get(Calendar.MONTH)+1;
		int year = time.get(Calendar.YEAR);
		String thangnam = year + "-" + month;
		//lấy danh sách lương nhân niên theo tháng.
		List<SalaryMonth> listSalaryMonth = salaryMonthRepository.findByMonth(thangnam);
		
		//nếu đã có từ trước nhưng cập nhật thêm dịch vụ mới làm
		if(!listSalaryMonth.isEmpty()) {
			for(int i = 0;i<listSalaryMonth.size();i++) {
				SalaryMonth salaryMonth = listSalaryMonth.get(i);
				if(salaryMonth.getEmployee().getPosition().equals(KeyWord.TECHNICAL_EMPLOYEE)) {
					Long new_total_money = tongtien(salaryMonth.getEmployee());
					salaryMonth.setTotal_money(new_total_money);
				
					//cập nhật lại trong list
					listSalaryMonth.set(i, salaryMonth);
					//cập nhật lại trong db
					salaryMonthRepository.save(salaryMonth);
				}
			}
		}		
		//chưa có thì tạo mới.
		else{
			listSalaryMonth = new ArrayList<>();
			List<Employee> listEmployee = userRepository.findAll();
			for(Employee e : listEmployee) {
				SalaryMonth salaryMonth = new SalaryMonth();
				salaryMonth.setEmployee(e);
				salaryMonth.setHard_salary(e.getSalary_hard());
				salaryMonth.setMonth(thangnam);
				salaryMonth.setStatus(KeyWord.CHUA_TT);
				
				//set tổng lương
				//nếu là nhân viên kỹ thuật
				if(e.getPosition().equals(KeyWord.TECHNICAL_EMPLOYEE)) {
					salaryMonth.setTotal_money(tongtien(e));
				}
				else {
					salaryMonth.setTotal_money(e.getSalary_hard());
				}
				salaryMonthRepository.save(salaryMonth);
				listSalaryMonth.add(salaryMonth);
			}
		}
		modelMap.addAttribute("thang",month+"/"+year);
		modelMap.addAttribute("listSalary",listSalaryMonth);
		return "gdQuanLyBangLuong";
	}
	
	@GetMapping("/chitietluong/{id}")
	public String chitietluong(ModelMap modelMap, @PathVariable(name = "id") Long id,HttpSession session) {
		if(session.getAttribute("USER") == null) {
			modelMap.addAttribute("checkLogin","Hết phiên làm việc!!!");
			return "login";
		}
		SalaryMonth salaryMonthEm = salaryMonthRepository.findById(id).get();
		List<SalaryDetail> listSalaryDetail = new ArrayList<>();
		
		//nếu là nhân viên kỹ thuật thì tính thêm hóa hồng
		if(salaryMonthEm.getEmployee().getPosition().equals(KeyWord.TECHNICAL_EMPLOYEE)) {
			listSalaryDetail = getListSalaryDetail(salaryMonthEm);
		}
		//xóa nếu đã tồn tại
		session.removeAttribute("listSalaryDetail");
		session.setAttribute("listSalaryDetail", listSalaryDetail);
		session.removeAttribute("salaryMonth");
		session.setAttribute("salaryMonth", salaryMonthEm);
		
		modelMap.addAttribute("listSalaryDetail",listSalaryDetail);
		modelMap.addAttribute("salaryMonth",salaryMonthEm);
		
		if(salaryMonthEm.getStatus().equals(KeyWord.DA_TT)) {
			return "gdChiTietBangLuongTT";
		}
		return "gdChiTietBangLuong";
	}
	@SuppressWarnings("unchecked")
	@GetMapping("/saveSalary")
	public String saveSalary(ModelMap modelMap, HttpSession session) {
		if(session.getAttribute("USER") == null) {
			modelMap.addAttribute("checkLogin","Hết phiên làm việc!!!");
			return "login";
		}

		List<SalaryDetail> lisSalaryDetail =  (List<SalaryDetail>) session.getAttribute("listSalaryDetail");
		if(!lisSalaryDetail.isEmpty()) {
			try {
				salaryDetailRepository.saveAll(lisSalaryDetail);
			} catch (DataIntegrityViolationException  e) {
				System.out.println("Đã đc lưu");
			}
		}
		
		SalaryMonth salaryMonth = (SalaryMonth) session.getAttribute("salaryMonth");
		salaryMonth.setStatus(KeyWord.DA_TT);
		salaryMonthRepository.save(salaryMonth);
		session.removeAttribute("salaryMonth");
		return "gdChinhQuanLy";
	}
	private Long tongtien(Employee e) {
		Calendar time = Calendar.getInstance();
		Integer month = time.get(Calendar.MONTH)+1;
		Integer year = time.get(Calendar.YEAR);
		
		Long tongtien = e.getSalary_hard();
		List<BillProvisional> listBill = bill.findByTechnicalEmployeeId(e.getId(),month,year);
		System.out.println(listBill);
		for(BillProvisional bill : listBill) {
			for(BillProvisionalDetail billDetail : bill.getListBillDetaill()) {
				Long hoahong = (long) (billDetail.getCount() * billDetail.getService().getDiscount()*billDetail.getService().getPrice()/100);
				tongtien += hoahong;
			}
		}
		return tongtien;
	}

	private List<SalaryDetail> getListSalaryDetail(SalaryMonth salaryMonthEm){
		Calendar time = Calendar.getInstance();
		int month = time.get(Calendar.MONTH)+1;
		int year = time.get(Calendar.YEAR);
		List<SalaryDetail> listSalaryDetail = salaryDetailRepository.getSalaryDetailByEmployee(salaryMonthEm.getEmployee().getId(),month,year);
		for(int i = 0;i<listSalaryDetail.size();i++) {
			SalaryDetail salaryDetail = listSalaryDetail.get(i);
			salaryDetail.setId(null);
			salaryDetail.setMoney(salaryDetail.getCount()*salaryDetail.getService().getPrice());
			salaryDetail.setDiscount(salaryDetail.getService().getDiscount());
			salaryDetail.setCommission(salaryDetail.getDiscount()*salaryDetail.getMoney()/100);
			salaryDetail.setSalary_month(salaryMonthEm);
		}
		return listSalaryDetail;
	}
}
