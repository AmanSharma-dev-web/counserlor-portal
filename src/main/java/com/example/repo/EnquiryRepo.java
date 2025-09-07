package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Enquiry;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {
	
	@Query(value = "select * from enquiries_tbl where counselor_id=:counselorId",
			nativeQuery = true)
	public List<Enquiry> getEnquiriesByCounselorId(Integer counselorId);
}
