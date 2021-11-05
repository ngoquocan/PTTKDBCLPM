package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Employee;
import com.example.demo.repository.UserRepository;

@Controller
@RequestMapping()
public class HomeController {
	@Autowired
	private UserRepository userRepository;
		
	@GetMapping(value = {"/","login"})
	public String login() {
		return "login";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("USER");
		return "login";
	}
	@GetMapping("/home")
	public String home(ModelMap modelMap,@RequestParam(name = "username",required = false) String username,
			@RequestParam(name = "password",required = false) String password,
			HttpSession session) {	
		if(username == null && password == null) {
			modelMap.addAttribute("checkLogin","Hết phiên làm việc!!!");
			return "login";
		}
		Employee user = userRepository.findOneByUsername(username);
		if(user == null) {
			modelMap.addAttribute("checkLogin","Tài khoản không tồn tại!!!");
			return "login";
		}
		else if(password.equals(user.getPassword())) {
			session.setAttribute("USER", user);
			return "gdChinhQuanLy";
		}
		else {
			modelMap.addAttribute("checkLogin","Tài khoản hoặc mật khẩu sai !!!");
			return "login";
		}
//		User user = new Employee();
//		user.setUsername(username);
//		session.setAttribute("USER", user);
//		return "gdChinhQuanLy";
	}
}
