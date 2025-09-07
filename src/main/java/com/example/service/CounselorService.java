package com.example.service;

import org.springframework.stereotype.Service;

import com.example.dto.DashboardResponse;
import com.example.entity.Counselor;

@Service
public interface CounselorService {
	
	public Counselor findByEmail(String email);
	
	public boolean register(Counselor counselor);
	
	public Counselor login(String email, String pwd);
	/* return type counselor to know which counselor logged In.
	So can see DashBoard accordingly
	*/
	public DashboardResponse getDashboardInfo(Integer counselorId);
	
}
