package com.epsi.epsi_pixel_power_brawl.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginDto loginDto) {
        if (userService.authenticate(loginDto.getUsername(), loginDto.getPassword())) {
            String jwtToken = jwtUtil.generateToken(loginDto.getUsername());
            
            Map<String, String> response = new HashMap<>();
            response.put("token", jwtToken);
            return ResponseEntity.ok(response);
        }
        
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Informations d'identifications erronées");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}