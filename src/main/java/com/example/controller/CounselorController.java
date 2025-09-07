package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dto.DashboardResponse;
import com.example.entity.Counselor;
import com.example.service.CounselorService;
import com.example.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounselorController {

	private CounselorService counselorService;
	private EnquiryService enquiryService;
	
	
	public CounselorController(CounselorService counselorService, EnquiryService enquiryService) {
		this.counselorService = counselorService;
		this.enquiryService = enquiryService;
	}

	//Load index page
	@GetMapping("/")
	public String index(Model model) {	//If sending something from controller to UI
		//Form binding
		Counselor cobj = new Counselor();
		
		// sending data from controller to UI.
		model.addAttribute("counselor", cobj);
		return "index"; //return view
	}
	
	//Validates User credentials are validate or not
	//Expecting email and password from Index so need form binding --> Need to map counselor class with index
	
	@PostMapping("/login")
	public String handleLogin(Counselor counselor, HttpServletRequest request, Model model) {
// Counselor counselor --> takes form data
// Model model send data from controller to UI
		Counselor c = counselorService.login(counselor.getEmail(), counselor.getPwd());
		if(c == null) {
			model.addAttribute("emsg", "Invalid Credentials");
			return "index" ;
		}else {
			
// valid login, store counselorId in session for future purpose
			HttpSession session = request.getSession(true); //true means create a session
			session.setAttribute("counselorId", c.getCounserlorId());
			
		//Get counselorId which later store in session
			DashboardResponse dobj = counselorService.getDashboardInfo(c.getCounserlorId());
			model.addAttribute("dashboardInfo", dobj);
			return "dashboard" ;
		}
	}
	
	@GetMapping("/dashboard")
	public String displayDashboard(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false); 
		Integer counselorId = (Integer)session.getAttribute("counselorId");
		
	//Get counselorId which later store in session
		DashboardResponse dobj = counselorService.getDashboardInfo(counselorId);
		model.addAttribute("dashboardInfo", dobj);
		return "dashboard";
	}
	
	//Register
	@GetMapping("/register")
	public String registerPage(Model model) {
		//For form binding
		Counselor cobj= new Counselor();
		//send data to counselor to UI.
		model.addAttribute("counselor",cobj);
		return "register";
	}
	
	@PostMapping("/register")
	public String handleRegistration(Counselor counselor, Model model) {
		
		//To check Duplicate emailIds.
		Counselor byEmail = counselorService.findByEmail(counselor.getEmail());
		if(byEmail != null) {
			model.addAttribute("emsg", "Duplicate Email Id");
				return "register";
		}
		
		
		boolean isRegister = counselorService.register(counselor);
		if(isRegister) {
			//success
			model.addAttribute("smsg", "Registration Success");
		}else {
			//failure
			model.addAttribute("emsg", "Registration Failed");
		}
		return "register";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		//Get existing session and invalidate it 
		HttpSession session = request.getSession(false);
		session.invalidate();
//return to login page (using redirect to avoid form binding again here)
		return "redirect:/";
	}
	
	
}
