package com.epsi.epsi_pixel_power_brawl.websocket;

import java.util.List;

public class SimplifiedPokemonTeam {
	private List<SimplifiedPokemon> pokemons;

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