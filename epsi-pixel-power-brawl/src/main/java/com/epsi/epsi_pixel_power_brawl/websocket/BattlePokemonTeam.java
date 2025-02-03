package com.epsi.epsi_pixel_power_brawl.websocket;

import java.util.ArrayList;
import java.util.List;

public class BattlePokemonTeam {

	private List<BattlePokemon> battlePokemonList;

	BattlePokemonTeam(SimplifiedPokemonTeam simplifiedTeam) {
		battlePokemonList = new ArrayList<BattlePokemon>();

		for (SimplifiedPokemon simplifiedPokemon : simplifiedTeam.getPokemons()) {
			battlePokemonList.add(new BattlePokemon(simplifiedPokemon));
		}

	}

	public List<BattlePokemon> getTeam() {
		return battlePokemonList;
	}

	public BattlePokemon getActivePokemon() {
		for (BattlePokemon pokemon : battlePokemonList) {
			if (pokemon.getHP() > 0) {
				return pokemon;
			}
		}

		return null;
	}
}

class BattlePokemon extends SimplifiedPokemon {

	private int HP = 100;
	private float DEF = 0;

	BattlePokemon(SimplifiedPokemon simplifiedPokemon) {
		super(simplifiedPokemon.getPokedexID(), simplifiedPokemon.isShiny());
	}

	public int getHP() {
		return HP;
	}

	public void takeDamage(int damage) {
		HP -= damage;

		if (HP <= 0) {
			HP = 0;
			System.out.println("faint ! " + this.getPokedexID());
		}

		if (HP > 100) {
			HP = 100;
		}

	}

	public float getDEF() {
		return DEF;
	}

	public void upDEF() {
		DEF += 0.1f;

	}
}