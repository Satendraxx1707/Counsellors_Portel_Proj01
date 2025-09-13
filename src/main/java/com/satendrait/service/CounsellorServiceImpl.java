package com.satendrait.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satendrait.dto.DashboardResponse;
import com.satendrait.entities.Counsellor;
import com.satendrait.entities.Enquiry;
import com.satendrait.repos.CounsellorRepo;
import com.satendrait.repos.EnquiryRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService {
	
	
	
	 private CounsellorRepo counsellorRepo;
	
	  private EnquiryRepo enqRepo;
	  
	  
	  public CounsellorServiceImpl(CounsellorRepo counsellorRepo, EnquiryRepo enqRepo) {
		  this.counsellorRepo = counsellorRepo;
		  this.enqRepo = enqRepo;
	}

	
	
	@Override
	public Counsellor findByEmail(String email) {
		
		return counsellorRepo.findByEmail(email);
	}
	
	
	
	
	
	@Override
	public boolean register(Counsellor counsellor) {
		
		Counsellor savedCounsellor = counsellorRepo.save(counsellor);
		
		if (null != savedCounsellor.getCounsellorId()) {
			
			return true ;
		}
		
		
		return false ;
				
	}

	@Override
	public Counsellor login(String email, String pwd) {
		
		Counsellor counsellor = counsellorRepo.findByEmailAndPwd(email, pwd);
		return counsellor;
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer CounsellorId) {
		
		DashboardResponse response = new DashboardResponse();
		
		List<Enquiry> enqList  = enqRepo.getEnquriesByCounsellorId(CounsellorId);
		
		
		 
		 // using java 8 feature Stream API
		 
		 int totalEnq = enqList.size();
		 
		 int enrolledEnqs = (int) enqList.stream()
				 .filter(e -> e.getEnqStatus().equals("Enrolled"))
				 .collect(Collectors.toList())
				 .size();
		 
		 
		 int lostEnqs = (int) enqList.stream()
		            .filter(e ->e.getEnqStatus().equals("Lost"))
		            .collect(Collectors.toList())
					 .size();
		                
		 
		 int openEnqs = (int)enqList.stream()
				            .filter(e ->e.getEnqStatus().equals("open"))
				            .collect(Collectors.toList())
							 .size();
		 
		 response.setTotalEnqs(totalEnq);
	        response.setEnrolledEnqs(enrolledEnqs);
	        response.setLostEnqs(lostEnqs);
	        response.setOpenEnqs(openEnqs);
		 
		 
		 
		return response;
	}

}
