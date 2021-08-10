package com.example.hr.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.application.HrApplication;
import com.example.hr.service.HrService;

@RestController
@RequestScope
@RequestMapping("/employees")
@CrossOrigin
public class HrRestController { // Protocol Adapter -> http -> class method
	private HrService hrService;
	
	// Constructor Injection
	public HrRestController(HrService hrService) {
		this.hrService = hrService;
	}

	@PostMapping
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}
	
	@DeleteMapping("{identityNo}")
	public FireEmployeeResponse fireEmployee(@PathVariable String identityNo) {
		return hrService.fireEmployee(identityNo);
	}
}
