package com.epsi.epsi_pixel_power_brawl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epsi.epsi_pixel_power_brawl.dto.LoginDto;
import com.epsi.epsi_pixel_power_brawl.service.UserService;
import com.epsi.epsi_pixel_power_brawl.util.jwt.JwtUtil;

@RestController
public class LoginController {

	@Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
	@ResponseBody
	public String login(@RequestBody LoginDto loginDto) {
	    if (userService.authenticate(loginDto.getUsername(), loginDto.getPassword())) {
	        return jwtUtil.generateToken(loginDto.getUsername());
	    }
	    throw new RuntimeException("Login failed");
	}
}