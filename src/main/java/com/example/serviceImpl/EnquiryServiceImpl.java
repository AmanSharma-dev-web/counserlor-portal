package com.example.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.example.controller.EnquiryController;
import com.example.dto.ViewEnquiryFilterResponse;
import com.example.entity.Counselor;
import com.example.entity.Enquiry;
import com.example.repo.CounselorRepo;
import com.example.repo.EnquiryRepo;
import com.example.service.EnquiryService;

import io.micrometer.common.util.StringUtils;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    

	private EnquiryRepo repo;
	private CounselorRepo repo2;

	public EnquiryServiceImpl(EnquiryRepo repo, CounselorRepo repo2) {
		this.repo = repo;
		this.repo2 = repo2;
		
	}

	@Override
	public boolean addEnquiry(Enquiry enquiry, Integer counselorId) throws Exception {
		// Step 2 to get counselorId
		Counselor counselor = repo2.findById(counselorId).orElse(null);

		if (counselor == null) {
			throw new Exception("No Counselor Found");
		}

		// Step 3 associating counselor to enquiry (mapping)
		enquiry.setCounselor(counselor);

		// Step 1 saving Enquiry
		Enquiry save = repo.save(enquiry);	//(UPSERT = SAVE + UPDATE)
		/*for edit functionality need to add hidden enqId so that save act as UPDATE
		 * instead of save
		 * */
		if (save.getEnquiry_id() != null) {
			//System.out.println(save);
			return true;
		}
		return false;
	}

	@Override
	public List<Enquiry> getAllEnquiries(Integer counselorId) {
		return repo.getEnquiriesByCounselorId(counselorId);
	}

	// For edit Functionality
	@Override
	public Enquiry getEnquiryById(Integer enqId) {
		return repo.findById(enqId).orElse(null);
	}

	@Override
	public List<Enquiry> getEnquriesWithFilter(ViewEnquiryFilterResponse filterReq, Integer counselorId) {

		// QBE implementation (Dynamic Query Preparation)

		//StringUtils is new API which check it is empty or null		

		Enquiry enq = new Enquiry();	//entity
		if(StringUtils.isNotEmpty(filterReq.getClassMode())) {
			enq.setClassMode(filterReq.getClassMode());
		}
		if(StringUtils.isNotEmpty(filterReq.getEnquiryStatus())) {
			enq.setEnquiryStatus(filterReq.getEnquiryStatus());
		}
		if(StringUtils.isNotEmpty(filterReq.getCourseName())) {
			enq.setCourseName(filterReq.getCourseName());
		}
		//Set Counselor Id before getting the obj
		Counselor counselor = repo2.findById(counselorId).orElse(null);
		enq.setCounselor(counselor);
		
		Example<Enquiry> of = Example.of(enq);	//dynamic query
		List<Enquiry> enqList = repo.findAll(of);// If not example object retrieve all the records

		return enqList;
	}

}
