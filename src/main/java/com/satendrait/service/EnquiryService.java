package com.satendrait.service;

import java.util.List;

import com.satendrait.dto.ViewEnqsFilterRequest;
import com.satendrait.entities.Enquiry;

public interface EnquiryService {
	
	
	public boolean addEnquiry(Enquiry enq, Integer counsellorId) throws Exception ;
	
	public List<Enquiry> getAllEnquiries(Integer counsellorId);
	
	public List<Enquiry> getEnquiresWithFilter(ViewEnqsFilterRequest filterReq, Integer counsellorId);
	
	
	 public Enquiry getEnquriById(Integer enqId);
	

}
