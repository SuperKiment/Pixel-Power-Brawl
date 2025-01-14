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
    private WebClient webClient;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/pokemons")
	@ResponseBody
public ResponseEntity<List<PokemonDto>> fetchAllPokemons() {
		//TODO : Ne pas oublier de revoir la structure (mettre un service, etc...) 
		// Get raw JSON response
		String jsonResponse = webClient.get()
	            .uri("https://tyradex.vercel.app/api/v1/pokemon")
	            .retrieve()
	            .bodyToMono(String.class)
	            .block();
		
	    // Parse JSON response and map to PokemonDto
	    ObjectMapper objectMapper = new ObjectMapper();
	    List<PokemonDto> pokemonList = new ArrayList<>();
	    try {
	        JsonNode rootNode = objectMapper.readTree(jsonResponse);
	        for (JsonNode node : rootNode) {
	            PokemonDto pokemon = new PokemonDto();
	            pokemon.setCategory(node.get("category").asText());
	            pokemon.setName(node.get("name").get("fr").asText());
	            pokemon.setPokedexId(node.get("pokedex_id").asInt());
	            pokemon.setShiny(Math.random() <= 0.01); // Generate isShiny dynamically
	            
	            SpritesDto sprites = objectMapper.convertValue(
	                    node.get("sprites"), SpritesDto.class);
	            pokemon.setSprites(sprites);
	            
	            StatsDto stats = objectMapper.convertValue(
	                    node.get("stats"), StatsDto.class);
	            pokemon.setStats(stats);
	            
	            
	            //List keys
	            pokemon.setResistances(objectMapper.convertValue(
	                    node.get("resistances"), new TypeReference<List<ResistancesDto>>() {}));
	            
	            pokemon.setTalents(objectMapper.convertValue(
	                    node.get("talents"), new TypeReference<List<TalentsDto>>() {}));
	            
	            pokemon.setTypes(objectMapper.convertValue(
	                    node.get("types"), new TypeReference<List<TypesDto>>() {}));
	            
	            pokemonList.add(pokemon);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Log error
	    }	
	    
        return ResponseEntity.ok(pokemonList);
	}

}
