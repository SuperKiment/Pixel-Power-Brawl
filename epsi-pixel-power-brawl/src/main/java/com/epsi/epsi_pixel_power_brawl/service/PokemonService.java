package com.epsi.epsi_pixel_power_brawl.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.epsi.epsi_pixel_power_brawl.dto.PokemonDto;
import com.epsi.epsi_pixel_power_brawl.dto.ResistancesDto;
import com.epsi.epsi_pixel_power_brawl.dto.SpritesDto;
import com.epsi.epsi_pixel_power_brawl.dto.StatsDto;
import com.epsi.epsi_pixel_power_brawl.dto.TalentsDto;
import com.epsi.epsi_pixel_power_brawl.dto.TypesDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PokemonService {
	
	@Autowired
	private WebClient webClient;
	
	public List<PokemonDto> getAllPokemons() {
		String jsonResponse = webClient.get().uri("https://tyradex.vercel.app/api/v1/pokemon").retrieve()
				.bodyToMono(String.class).block();

		ObjectMapper objectMapper = new ObjectMapper();
		List<PokemonDto> pokemonList = new ArrayList<>();
		try {
			JsonNode rootNode = objectMapper.readTree(jsonResponse);
			for (JsonNode node : rootNode) {
				PokemonDto pokemon = new PokemonDto();
				pokemon.setCategory(node.get("category").asText());
				pokemon.setName(node.get("name").get("fr").asText());
				pokemon.setPokedexId(node.get("pokedex_id").asInt());
				pokemon.setShiny(Math.random() <= 0.01);

				SpritesDto sprites = objectMapper.convertValue(node.get("sprites"), SpritesDto.class);
				pokemon.setSprites(sprites);

				StatsDto stats = objectMapper.convertValue(node.get("stats"), StatsDto.class);
				pokemon.setStats(stats);

				pokemon.setResistances(
						objectMapper.convertValue(node.get("resistances"), new TypeReference<List<ResistancesDto>>() {
						}));

				pokemon.setTalents(
						objectMapper.convertValue(node.get("talents"), new TypeReference<List<TalentsDto>>() {
						}));

				pokemon.setTypes(objectMapper.convertValue(node.get("types"), new TypeReference<List<TypesDto>>() {
				}));

				pokemonList.add(pokemon);
			}
			return pokemonList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
