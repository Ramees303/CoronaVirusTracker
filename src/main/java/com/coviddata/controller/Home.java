package com.coviddata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coviddata.Model.Covidata;
import com.coviddata.service.CoronaVirusTrackerService;



@Controller
public class Home {
	
	@Autowired
	private CoronaVirusTrackerService coronadataservice;
	
	
	@RequestMapping("/")
		public String home(Model model) {
			
		List<Covidata> data=coronadataservice.getFinaldatalist();
		int totaldeathreported=data.stream().mapToInt(res->res.getLastedTotalCases()).sum();
		model.addAttribute("data", data);
		model.addAttribute("totaldeathreported",totaldeathreported);
		
		return "home";
		
	}

}
