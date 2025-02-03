package com.epsi.epsi_pixel_power_brawl.websocket;

public class UserBattleAction {
	private String action;
	private String type;

	public UserBattleAction() {
	}

	public UserBattleAction(String action, String type) {
		this.action = action;
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public String getType() {
		return type;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setType(String type) {
		this.type = type;
	}
}
