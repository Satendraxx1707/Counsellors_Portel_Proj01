package com.satendrait.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.satendrait.dto.ViewEnqsFilterRequest;
import com.satendrait.entities.Counsellor;
import com.satendrait.entities.Enquiry;
import com.satendrait.repos.CounsellorRepo;
import com.satendrait.repos.EnquiryRepo;

import io.micrometer.common.util.StringUtils;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	   
	private CounsellorRepo  counsellorRepo;
	
	
   private EnquiryRepo enqRepo;
	
	//  Used parametrised constructor so no need to take @Autowired Annotation
	
	public EnquiryServiceImpl(EnquiryRepo enqRepo, CounsellorRepo counsellorRepo) {
		this.enqRepo = enqRepo;
		this.counsellorRepo = counsellorRepo;
	
	}
	
	@Override
	public boolean addEnquiry(Enquiry enq , Integer counsellorId) throws Exception {
		
		
		Counsellor counsellor= counsellorRepo.findById(counsellorId).orElse(null);
		
		if(counsellor ==null) {
			
			throw new Exception ("No counsellor found");
		}
		
		
		
		  // associating counsellor to enquiry
		 
		enq.setCounsellor(counsellor);
		 Enquiry save = enqRepo.save(enq);
		 
		
		 
		 if(save.getEnqId()!=null) {
			 
			 return true;
		 }
		
		return false;
	}

	@Override
	public List<Enquiry> getAllEnquiries(Integer counsellorId) {
		
		return enqRepo.getEnquriesByCounsellorId(counsellorId);
	}

	

	@Override
	public Enquiry getEnquriById(Integer enqId) {
		return enqRepo.findById(enqId).orElse(null);
		
	}
	
	@Override
	public List<Enquiry> getEnquiresWithFilter(ViewEnqsFilterRequest filterReq, Integer counsellorId) {
		// QBE Implementation (Dynamic Query preparation
		
		//isNotEmpty(filterReq.getClassMode())) {
		

		    Enquiry enq = new Enquiry(); // entity

		    if (StringUtils.isNotEmpty(filterReq.getClassMode())) {
		        enq.setClassMode(filterReq.getClassMode());
		    }

		    if (StringUtils.isNotEmpty(filterReq.getCourseName())) {
		        enq.setCourseName(filterReq.getCourseName());
		    }

		    if (StringUtils.isNotEmpty(filterReq.getEnqStatus())) {
		        enq.setEnqStatus(filterReq.getEnqStatus());
		    }

		    Counsellor c = counsellorRepo.findById(counsellorId).orElse(null);
		    enq.setCounsellor(c);

		    Example<Enquiry> of = Example.of(enq);  //dynamic query

		    List<Enquiry> enqList = enqRepo.findAll(of);

		    return enqList;
		}
		
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	
	


