package com.epsi.epsi_pixel_power_brawl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonController {

	@GetMapping("/pokemon")
	@ResponseBody
	public String[] getAllPokemon() {
		String[] response = { "a", "b" };
		return response;
	}
}
