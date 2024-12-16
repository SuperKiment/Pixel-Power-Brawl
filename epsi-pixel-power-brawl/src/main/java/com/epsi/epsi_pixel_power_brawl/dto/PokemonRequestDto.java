package com.epsi.epsi_pixel_power_brawl.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PokemonRequestDto {
	@NotEmpty
	@NotNull
    private String parameter;
	
	@NotEmpty
	@NotNull
    private String query;
}
