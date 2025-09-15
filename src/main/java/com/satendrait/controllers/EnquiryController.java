package com.satendrait.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.satendrait.dto.DashboardResponse;
import com.satendrait.dto.ViewEnqsFilterRequest;
import com.satendrait.entities.Enquiry;
import com.satendrait.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class EnquiryController {
	
	private EnquiryService enqService;

	public EnquiryController(EnquiryService enqService) {
		
		this.enqService = enqService;
	}
	
	@PostMapping("/filter-enqs")
	public String filterEnquries(ViewEnqsFilterRequest viewEnqsFilterRequest, HttpServletRequest req, Model model) {
		
		
		//get existing Session obj;
		
				HttpSession session = req.getSession(false);
				Integer counsellorId = (Integer) session.getAttribute("counsellorId");
				
				
	List<Enquiry> enqsList = enqService.getEnquiresWithFilter(viewEnqsFilterRequest, counsellorId);
	
	model.addAttribute("enquiries", enqsList);
				
				return "viewEnqsPage";
				
	}
	
	
	
	
	@GetMapping("/view-enquiries")
	public String getEnquries(HttpServletRequest request, Model model) {
		
		//get existing Session obj;
		
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
	List<Enquiry>enqList = 	enqService.getAllEnquiries(counsellorId);
		
	model.addAttribute("enquiries", enqList);
	
	
	// search from binding object
	ViewEnqsFilterRequest filterReq = new ViewEnqsFilterRequest();
	model.addAttribute("viewEnqsFilterRequest",filterReq);
	
	
	
	
	
	return "viewEnqsPage";
	}
	
	
	
	
	
	
@GetMapping("/enquiry")
public String addEnquiryPage(Model model) {
    Enquiry enquiry = new Enquiry();
    model.addAttribute("enquiry", enquiry);
      return "enquiryForm";
}





	
@PostMapping("/addEnq")
public String handleAddEnquriy(Enquiry enquiry,HttpServletRequest req, Model model)throws Exception {
	
	//get existing Session obj;
	
	HttpSession session = req.getSession(false);
	Integer counsellorId = (Integer) session.getAttribute("counsellorId");
	 
	boolean isSaved = enqService.addEnquiry(enquiry, counsellorId);
	
	 if (isSaved) {
		
		model.addAttribute("smsg","Enquiry Added");
	} else {
		model.addAttribute("emsg", "failed to Add Enquiry");
		
	}
	
	 enquiry = new Enquiry();
	    model.addAttribute("enquiry", enquiry);
	 
	 
	 
	return "enquiryForm";
}




}
