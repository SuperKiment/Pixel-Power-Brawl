package com.epsi.epsi_pixel_power_brawl.dto;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpritesDto {
	
	    private String regular;
	    private String shiny;
	    private GmaxDto gmax;
}
