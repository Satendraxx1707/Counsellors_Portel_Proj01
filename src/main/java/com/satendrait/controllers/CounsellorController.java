package com.satendrait.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.satendrait.dto.DashboardResponse;
import com.satendrait.entities.Counsellor;
import com.satendrait.service.CounsellorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

 @Controller
public class CounsellorController {
	
	private  CounsellorService counsellorService;
	
	 // Constructor injection
	public CounsellorController(CounsellorService counsellorService) {
        this.counsellorService = counsellorService;
}


	
	
	@GetMapping("/")
	public String index(Model model) {
		
		Counsellor cobj = new Counsellor();
		
		// sending data from controller to UI
		model.addAttribute("counsellor", cobj);
		
		
		
		// returning view name
		
		return "index";
	}
	
	@PostMapping ("/login")
	public String handleloginBtn(Counsellor counsellor,HttpServletRequest request, Model model) {
		Counsellor c = counsellorService.login(counsellor.getEmail(),counsellor.getPwd());
	
		if (c==null) {
			model.addAttribute("emsg", "Invalid Credentials");
			return "index";
		
		}else {
			
			// valid login, store counsellorID in session for future purpose 
			
HttpSession session = request.getSession(true);
session.setAttribute("counsellorId",c.getCounsellorId());
			
			DashboardResponse dbresobj = counsellorService.getDashboardInfo(c.getCounsellorId());
			model.addAttribute("dashboardInfo", dbresobj);
			return "dashboard";
		}
		
		
	
		
	}
	
	@GetMapping("/register")
	public String registerPage(Model model ) {
		
Counsellor cobj = new Counsellor();
		
		// sending data from controller to UI  binding kari
		model.addAttribute("counsellor", cobj);
		
		
		
		return "register";
	}
	
	@PostMapping("/register")
	public String handleRegistration(Counsellor counsellor, Model model) {
		
		
		Counsellor byEmail = counsellorService.findByEmail(counsellor.getEmail());
		
		if (byEmail != null) {
			
			model.addAttribute("emsg","Duplicate Email");
			
			return "register";
		}
		
		
		 boolean isRegistered = counsellorService.register(counsellor);
		
		 if(isRegistered) {
			 // success
			 
			 model.addAttribute("smsg", "Registration Sucess..!!");
			 
		 } else {
			 
			 // failure
			 
			 model.addAttribute("emsg", "Registration Failed..!!");
		 }
		 
		 
		 return  "register";
	}
	
	
	
	
	
	
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		
		// get existing session and invalidate it
		
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		//redirect to login page
		return "redirect:/";
	}
	
	
	
	
	

}
