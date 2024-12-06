package com.epsi.epsi_pixel_power_brawl.service;

import com.epsi.epsi_pixel_power_brawl.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsi.epsi_pixel_power_brawl.dto.RegisterDto;
import com.epsi.epsi_pixel_power_brawl.model.Utilisateur;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public Utilisateur registerNewUserAccount(RegisterDto userDto) throws Exception {
        if (usernameExists(userDto.getUsername())) {
            throw new Exception("There is an account with that username: "
              + userDto.getUsername());
        }

        Utilisateur user = new Utilisateur();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Arrays.asList("ROLE_USER"));

        return repository.save(user);
    }

    
    
    
    @Override
    public boolean authenticate(String username, String password) {
        Utilisateur user = repository.findByUsername(username);
    	
    	if (user != null) {
    		if (passwordEncoder.matches(password, user.getPassword())) {
    			return true;
    		}
        }
        return false;

    }
    
    private boolean usernameExists(String username) {
        return repository.findByUsername(username) != null;
    }
}
