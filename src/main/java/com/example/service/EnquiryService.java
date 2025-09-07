package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.ViewEnquiryFilterResponse;
import com.example.entity.Enquiry;

@Service
public interface EnquiryService {
//Integer counselorId bcz want to which counselor is responsible
	
	public boolean addEnquiry(Enquiry enq, Integer counselorId) throws Exception;
	
	public List<Enquiry> getAllEnquiries(Integer counselorId);
	
	public List<Enquiry> getEnquriesWithFilter(ViewEnquiryFilterResponse filterReq,Integer counselorId);

	public Enquiry getEnquiryById(Integer enqId);
}
