package com.epsi.epsi_pixel_power_brawl.dto;

import com.epsi.epsi_pixel_power_brawl.util.annotations.CustomAnnotations.PasswordMatches;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatches
public class UserDto {
	
    @NotNull
    @NotEmpty
    private String username;
    
    @NotNull
    @NotEmpty
    private String password;
    
    @NotNull
    @NotEmpty
    private String matchingPassword;
        
    // standard getters and setters
}
