package com.epsi.epsi_pixel_power_brawl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.epsi.epsi_pixel_power_brawl.dto.TestDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class TestController {
	
    ModelAndView mav = new ModelAndView(); 

	@GetMapping("/test/hello")
	@ResponseBody
	public String registerUserAccount(@RequestBody TestDto testDto) {
	    
		if (testDto.getTestString() != null && testDto.getTestInt() != null) {
		    return "The test was succesful ! :)";
		}
        return "missing Arguments : testString or testInt";

	}



}
