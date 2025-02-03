package com.epsi.epsi_pixel_power_brawl.websocket;

import java.util.List;

public class SendTeamInfo {
	private SimplifiedPokemonTeam pokemonTeam;
	private String username;
	private String token;
	private String type;

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


