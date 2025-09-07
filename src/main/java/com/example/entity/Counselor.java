package com.example.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="counselor_tbl")
public class Counselor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer counserlorId;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	private Long phone;
	private String pwd;
	
	@CreationTimestamp
	private LocalDate created_Date;
	
	@UpdateTimestamp
	private LocalDate updated_Date;

	
	public Counselor() {
		
	}

	public Counselor(Integer counserlorId, String name, String email, Long phone, String pwd,
			LocalDate created_Date, LocalDate updated_Date) {
		
		this.counserlorId = counserlorId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.pwd = pwd;
		this.created_Date = created_Date;
		this.updated_Date = updated_Date;
		
	}

	public Integer getCounserlorId() {
		return counserlorId;
	}

	public void setCounserlorId(Integer counserlorId) {
		this.counserlorId = counserlorId;
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

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	

	@Override
	public String toString() {
		return "Counsellor [counserlorId=" + counserlorId + ", name=" + name + ", email=" + email + ", phone="
				+ phone + ", pwd=" + pwd + ", created_Date=" + created_Date + ", updated_Date=" + updated_Date
				 + "]";
	}

}
