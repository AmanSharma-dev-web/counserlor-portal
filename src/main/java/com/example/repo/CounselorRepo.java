package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Counselor;

@Repository
public interface CounselorRepo extends JpaRepository<Counselor, Integer> {

	//select * from counselor_tbl where email=:email and pwd=:pwd;
	public Counselor findByEmailAndPwd(String email, String pwd);
	
	//select * from counselor_tbl where email=:email;
	public Counselor findByEmail(String email);
}
