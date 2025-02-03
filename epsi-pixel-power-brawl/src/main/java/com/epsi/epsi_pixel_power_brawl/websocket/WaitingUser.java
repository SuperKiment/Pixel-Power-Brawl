package com.epsi.epsi_pixel_power_brawl.websocket;

import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WaitingUser {
	@JsonIgnore
	private final WebSocketSession session;
	private final String username;
	private final SimplifiedPokemonTeam pokemonTeam;

	public WaitingUser(WebSocketSession session, String username, SimplifiedPokemonTeam pokemonTeam) {
		this.session = session;
		this.username = username;
		this.pokemonTeam = pokemonTeam;
	}

	public WebSocketSession getSession() {
		return session;
	}

	public String getUsername() {
		return username;
	}

	public SimplifiedPokemonTeam getPokemonTeam() {
		return pokemonTeam;
	}
}