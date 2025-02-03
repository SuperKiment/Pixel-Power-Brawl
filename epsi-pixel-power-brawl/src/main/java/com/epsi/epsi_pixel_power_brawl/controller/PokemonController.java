package com.epsi.epsi_pixel_power_brawl.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.reactive.function.client.WebClient;

import com.epsi.epsi_pixel_power_brawl.dto.LoginDto;
import com.epsi.epsi_pixel_power_brawl.dto.PokemonDto;
import com.epsi.epsi_pixel_power_brawl.dto.SpritesDto;
import com.epsi.epsi_pixel_power_brawl.dto.StatsDto;
import com.epsi.epsi_pixel_power_brawl.dto.TalentsDto;
import com.epsi.epsi_pixel_power_brawl.dto.ResistancesDto;
import com.epsi.epsi_pixel_power_brawl.dto.TypesDto;
import com.epsi.epsi_pixel_power_brawl.dto.RegisterDto;
import com.epsi.epsi_pixel_power_brawl.model.Utilisateur;
import com.epsi.epsi_pixel_power_brawl.service.PokemonService;
import com.epsi.epsi_pixel_power_brawl.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.channel.ChannelOption;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import reactor.netty.http.client.HttpClient;

@Controller
public class PokemonController {
	
	@Autowired
	PokemonService pokemonService;

	@SuppressWarnings("unchecked")
	@GetMapping("/pokemons")
	@ResponseBody
	public ResponseEntity<List<PokemonDto>> fetchAllPokemons() {

		List<PokemonDto> pokemonList = pokemonService.getAllPokemons();

		return ResponseEntity.ok(pokemonList);
	}

}
