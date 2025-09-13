package com.satendrait.service;

import com.satendrait.dto.DashboardResponse;
import com.satendrait.entities.Counsellor;

public interface CounsellorService {
	
	//login method-1 (very imp)
	
	public Counsellor findByEmail(String email);
	
	
	public boolean register(Counsellor counsellor);
	
	public Counsellor login(String email, String pwd);
	
	public DashboardResponse getDashboardInfo(Integer CounsellorID);

}
