package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.ViewEnquiryFilterResponse;
import com.example.entity.Enquiry;
import com.example.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	public EnquiryService enquiryService;

	public EnquiryController(EnquiryService enquiryService) {
		this.enquiryService = enquiryService;
	}
	
	@PostMapping("/filter-enqrs")
	public String filterRequests(ViewEnquiryFilterResponse viewEnquiryFilterResponse ,HttpServletRequest request,Model model) {
		
		HttpSession session = request.getSession(false);
		Integer counselorId = (Integer) session.getAttribute("counselorId");
		
		List<Enquiry> enqList = enquiryService.getEnquriesWithFilter(viewEnquiryFilterResponse, counselorId);
		model.addAttribute("enquiries", enqList);
		return "viewEnqrsPage" ;
	}

	@GetMapping("/editEnq")
	public String editEnquiry(@RequestParam("enqId") Integer enqId, Model model) {
		Enquiry enquiry = enquiryService.getEnquiryById(enqId);
		model.addAttribute("enquiry", enquiry);
		return "enquiryform";
	}
	
	
	@GetMapping("/view-enquiries")
	public String getEnquiries(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Integer id = (Integer)session.getAttribute("counselorId");
		
		List<Enquiry> list = enquiryService.getAllEnquiries(id);
		model.addAttribute("enquiries",list);
		//Form Binding
		ViewEnquiryFilterResponse viewEnquiryFilterResponse  = new ViewEnquiryFilterResponse();
		model.addAttribute("viewEnquiryFilterResponse", viewEnquiryFilterResponse );
		return "viewEnqrsPage";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {

		Enquiry enqObj = new Enquiry();

		model.addAttribute("enquiry", enqObj);

		return "enquiryform";
	}

	
	
	@PostMapping("/addEnq")
	public String handleEnquiryPage(Enquiry enquiry, HttpServletRequest request ,Model model) throws Exception {
/*@ModelAttribute("enq") no need form  binding again when return "enquiryform"
	@ModelAttribute("enq") == model.addAttribute("enq", enqObj);
*/
		//Get existing session
		HttpSession session = request.getSession(false);  //false buzz not new Session..
		Integer counselorId =(Integer)session.getAttribute("counselorId");
		
		boolean isSaved = enquiryService.addEnquiry(enquiry, counselorId);
		if(isSaved) {
			model.addAttribute("smsg","Enquiry Added");
		}else {
			model.addAttribute("emsg","Failed to add Enquiry");
		}
		return "enquiryform";
	}
}
