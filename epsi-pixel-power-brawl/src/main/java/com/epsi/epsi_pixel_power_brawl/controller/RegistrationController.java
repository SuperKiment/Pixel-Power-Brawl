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

import com.epsi.epsi_pixel_power_brawl.dto.RegisterDto;
import com.epsi.epsi_pixel_power_brawl.model.Utilisateur;
import com.epsi.epsi_pixel_power_brawl.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class RegistrationController {
	
    ModelAndView mav = new ModelAndView();  // Initialize ModelAndView
	
	 @Autowired
	 private UserService userService; // Dependency injection

	@PostMapping("/user/registration")
	@ResponseBody
	public String registerUserAccount(@RequestBody RegisterDto userDto) {
	    
	    try {
	        Utilisateur registered = userService.registerNewUserAccount(userDto);
	    } catch (Exception uaeEx) {
	        return "user already exists";
	    }

	    return "registration successful!";
	}



}
