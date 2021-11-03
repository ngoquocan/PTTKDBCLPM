package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Employee;
import com.example.demo.model.SalaryMonth;
import com.example.demo.model.User;
import com.example.demo.repository.SalaryMonthRepository;
import com.example.demo.repository.UserRepository;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SalaryMonthRepository salaryMonthRepository;
	
	@GetMapping(value = {"/","login"})
	public String login(Model model) {		
		return "login";
	}
	@PostMapping("/home")
	public String home(ModelMap modelMap,HttpServletRequest request,
			HttpSession session) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
//		User user = userRepository.findOneByUsername(username);
//		if(user == null) {
//			return "login";
//		}
//		else if(password.equals(user.getPassword())) {
//			session.setAttribute("USER", user);
//			return "gdChinhQuanLy";
//		}
//		else {
//			return "login";
//		}
		User user = new Employee();
		user.setUsername(username);
		session.setAttribute("USER", user);
		return "gdChinhQuanLy";
	}
	@GetMapping("/quanlybangluong")
	public String qlbangluong() {
		//lấy tháng hiện tại
		Calendar time = Calendar.getInstance();
		int month = time.get(Calendar.MONTH);
		//lấy danh sách lương nhân niên theo tháng.
		List<SalaryMonth> listSalaryMonth = salaryMonthRepository.findByMonth(month);
		
		//chưa có thì tạo mới.
		if(listSalaryMonth == null) {
			listSalaryMonth = new ArrayList<>();
		}
		return "gdQuanLyBangLuong";
	}
}
