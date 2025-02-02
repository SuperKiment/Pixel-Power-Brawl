package com.epsi.epsi_pixel_power_brawl.websocket;

import java.util.List;

public class SendTeamInfo {
	private SimplifiedPokemonTeam pokemonTeam;
	private String username;
	private String token;
	private String type;

	// Constructeur vide requis par Jackson
	public SendTeamInfo() {
	}

	public SendTeamInfo(SimplifiedPokemonTeam pokemonTeam, String username, String token) {
		this.pokemonTeam = pokemonTeam;
		this.username = username;
		this.token = token;
	}

	public SimplifiedPokemonTeam getPokemonTeam() {
		return pokemonTeam;
	}

	public void setPokemonTeam(SimplifiedPokemonTeam pokemonTeam) {
		this.pokemonTeam = pokemonTeam;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

class SimplifiedPokemonTeam {
	private List<SimplifiedPokemon> pokemons;

	// Constructeur vide requis par Jackson
	public SimplifiedPokemonTeam() {
	}

	public SimplifiedPokemonTeam(List<SimplifiedPokemon> pokemons) {
		this.pokemons = pokemons;
	}

	public List<SimplifiedPokemon> getPokemons() {
		return pokemons;
	}

	public void setPokemons(List<SimplifiedPokemon> pokemons) {
		this.pokemons = pokemons;
	}
}

class SimplifiedPokemon {
	private int pokedexID;
	private boolean isShiny;

	// Constructeur vide requis par Jackson
	public SimplifiedPokemon() {
	}

	public SimplifiedPokemon(int pokedexID, boolean isShiny) {
		this.pokedexID = pokedexID;
		this.isShiny = isShiny;
	}

	public int getPokedexID() {
		return pokedexID;
	}

	public void setPokedexID(int pokedexID) {
		this.pokedexID = pokedexID;
	}

	public boolean isShiny() {
		return isShiny;
	}

	public void setIsShiny(boolean isShiny) {
		this.isShiny = isShiny;
	}
}
