package com.example.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.DashboardResponse;
import com.example.entity.Counselor;
import com.example.entity.Enquiry;
import com.example.repo.CounselorRepo;
import com.example.repo.EnquiryRepo;
import com.example.repo.CounselorRepo;
import com.example.service.CounselorService;
import com.example.service.CounselorService;

@Service
public class CounselorServiceImpl implements CounselorService {

	private CounselorRepo repo;

	private EnquiryRepo repo2;

	public CounselorServiceImpl(CounselorRepo repo, EnquiryRepo repo2) {
		this.repo = repo;
		this.repo2 = repo2;
	}

	// Check if there is email is present or not(Check Duplicate records)
	@Override
	public Counselor findByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public boolean register(Counselor counselor) {
		
		
		Counselor savedCounselor = repo.save(counselor);
		System.out.println(savedCounselor);
		if ( savedCounselor.getCounserlorId()!= null ) {
			// Id is auto generated so Id will come from Object.
			return true;
		}
		return false;
	}

	@Override
	public Counselor login(String email, String pwd) {
		Counselor counselor = repo.findByEmailAndPwd(email, pwd);
		return counselor;
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer counselorId) {
		DashboardResponse response = new DashboardResponse();
		// Contacting only one time with database then use streaming
		List<Enquiry> enqList = repo2.getEnquiriesByCounselorId(counselorId);
		int totalEnq = enqList.size();

		int enrolledEnqs = enqList.stream().filter(e -> e.getEnquiryStatus().equals("Enrolled"))
				.collect(Collectors.toList()).size();

		int lostEnqs = enqList.stream().filter(e -> e.getEnquiryStatus().equals("Lost")).collect(Collectors.toList())
				.size();

		int openEnqs = enqList.stream().filter(e -> e.getEnquiryStatus().equals("Open")).collect(Collectors.toList())
				.size();

		response.setTotalEnqrs(totalEnq);
		response.setLostEnqrs(lostEnqs);
		response.setOpenEnqrs(openEnqs);
		response.setEnrollEnqrs(enrolledEnqs);

		return response;
	}

}
