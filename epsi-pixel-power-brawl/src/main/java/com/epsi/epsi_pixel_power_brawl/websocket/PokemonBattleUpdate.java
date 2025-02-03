package com.epsi.epsi_pixel_power_brawl.websocket;

public class PokemonBattleUpdate {
	private BattlePokemonTeam team1;
	private BattlePokemonTeam team2;
	private boolean isP1Turn = false;
	private String type = "PokemonBattleUpdate";

	public PokemonBattleUpdate(BattlePokemonTeam team1, BattlePokemonTeam team2, boolean isP1Turn) {
		this.team1 = team1;
		this.team2 = team2;
		this.isP1Turn = isP1Turn;
	}

	public BattlePokemonTeam getTeam1() {
		return team1;
	}

	public BattlePokemonTeam getTeam2() {
		return team2;
	}

	public boolean isP1Turn() {
		return isP1Turn;
	}

	public void setTeam1(BattlePokemonTeam team1) {
		this.team1 = team1;
	}

	public void setTeam2(BattlePokemonTeam team2) {
		this.team2 = team2;
	}

	public void setP1Turn(boolean isP1Turn) {
		this.isP1Turn = isP1Turn;
	}

	public String getType() {
		return type;
	}
}
