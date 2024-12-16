package com.epsi.epsi_pixel_power_brawl.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestDto {
	
    @NotNull
    @NotEmpty
    private String testString;
    
    @NotNull
    @NotEmpty
    private Integer testInt;
           
}
