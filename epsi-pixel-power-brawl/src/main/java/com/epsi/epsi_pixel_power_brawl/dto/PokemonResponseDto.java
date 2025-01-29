package com.epsi.epsi_pixel_power_brawl.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonResponseDto {
	private List<String> results;
}
