package com.example.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "enquiries_tbl")
public class Enquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enquiry_id;
	private String name;
	private String email;
	private String phone;
	private String classMode;
	private String courseName;
	private String enquiryStatus;
	@CreationTimestamp
	private LocalDate created_Date;
	@UpdateTimestamp
	private LocalDate updated_Date;

	@ManyToOne
	@JoinColumn(name = "counselorId")
	private Counselor counselor;

	public Enquiry() {

	}

	
	
	public Enquiry(Integer enquiry_id, String name, String email, String phone, String classMode, String courseName,
			String enquiryStatus, LocalDate created_Date, LocalDate updated_Date, Counselor counselor) {
		this.enquiry_id = enquiry_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.classMode = classMode;
		this.courseName = courseName;
		this.enquiryStatus = enquiryStatus;
		this.created_Date = created_Date;
		this.updated_Date = updated_Date;
		this.counselor = counselor;
	}



	public Integer getEnquiry_id() {
		return enquiry_id;
	}

	public void setEnquiry_id(Integer enquiry_id) {
		this.enquiry_id = enquiry_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getClassMode() {
		return classMode;
	}

	public void setClassMode(String classMode) {
		this.classMode = classMode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getEnquiryStatus() {
		return enquiryStatus;
	}

	public void setEnquiryStatus(String enquiryStatus) {
		this.enquiryStatus = enquiryStatus;
	}

	public LocalDate getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(LocalDate created_Date) {
		this.created_Date = created_Date;
	}

	public LocalDate getUpdated_Date() {
		return updated_Date;
	}

	public void setUpdated_Date(LocalDate updated_Date) {
		this.updated_Date = updated_Date;
	}

	public Counselor getCounselor() {
		return counselor;
	}

	public void setCounselor(Counselor counselor) {
		this.counselor = counselor;
	}

	@Override
	public String toString() {
		return "Enquiry [enquiry_id=" + enquiry_id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", classMode=" + classMode + ", courseName=" + courseName + ", enquiryStatus=" + enquiryStatus
				+ ", created_Date=" + created_Date + ", updated_Date=" + updated_Date + ", counselor=" + counselor
				+ "]";
	}

}
