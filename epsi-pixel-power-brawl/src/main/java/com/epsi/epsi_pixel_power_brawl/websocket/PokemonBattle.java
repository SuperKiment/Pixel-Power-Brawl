package com.epsi.epsi_pixel_power_brawl.websocket;

import java.util.ArrayList;
import java.util.List;

public class PokemonBattle {

	private BattlePokemonTeam team1;
	private BattlePokemonTeam team2;
	private boolean isP1Turn = false;

	private WaitingUser user1;
	private WaitingUser user2;

	PokemonBattle(WaitingUser user1, WaitingUser user2) {
		this.user1 = user1;
		this.user2 = user2;

		team1 = new BattlePokemonTeam(user1.getPokemonTeam());
		team2 = new BattlePokemonTeam(user2.getPokemonTeam());
	}

	public boolean isP1Turn() {
		return isP1Turn;
	}

	public void switchTurn() {
		isP1Turn = !isP1Turn;
	}

	public PokemonBattleUpdate Turn(BattleAction action) {
		switch (action) {
		case ATTACK:
			if (this.isP1Turn) {
				team2.getActivePokemon().takeDamage(20);
				switchTurn();
			} else {
				team1.getActivePokemon().takeDamage(20);
				switchTurn();
			}
			break;
		case DEFEND:
			if (this.isP1Turn) {
				team1.getActivePokemon().upDEF();
				switchTurn();
			} else {
				team2.getActivePokemon().upDEF();
				switchTurn();
			}
			break;
		case HEAL:
			if (this.isP1Turn) {
				team1.getActivePokemon().takeDamage(-15);
				switchTurn();
			} else {
				team2.getActivePokemon().takeDamage(-15);
				switchTurn();
			}
			break;
		default:
			break;

		}

		System.out.println(isP1Turn);

		return new PokemonBattleUpdate(team1, team2, isP1Turn);
	}

	public WaitingUser getUser1() {
		return user1;
	}

	public WaitingUser getUser2() {
		return user2;
	}
}
