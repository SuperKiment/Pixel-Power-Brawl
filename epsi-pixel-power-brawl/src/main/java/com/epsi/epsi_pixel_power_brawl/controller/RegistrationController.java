package com.epsi.epsi_pixel_power_brawl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.epsi.epsi_pixel_power_brawl.dto.LoginDto;
import com.epsi.epsi_pixel_power_brawl.dto.RegisterDto;
import com.epsi.epsi_pixel_power_brawl.model.Utilisateur;
import com.epsi.epsi_pixel_power_brawl.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class RegistrationController {
		
	 @Autowired
	 private UserService userService;

	@PostMapping("/user/registration")
	@ResponseBody
	public ResponseEntity<Map<String, String>> registerUserAccount(@RequestBody RegisterDto userDto) {

	    try {
	        Utilisateur registered = userService.registerNewUserAccount(userDto);
	    } catch (Exception e) {
	    	Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("error", "User already exists");
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	    }	    
	    
        Map<String, String> response = new HashMap<>();
        response.put("response", "Registration Successful!");
        return ResponseEntity.ok(response);

	}
}
