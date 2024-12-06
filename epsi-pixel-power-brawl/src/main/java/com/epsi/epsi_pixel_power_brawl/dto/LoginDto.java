package com.epsi.epsi_pixel_power_brawl.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
	
    @NotNull
    @NotEmpty
    private String username;
    
    @NotNull
    @NotEmpty
    private String password;
           
}
