package com.epsi.epsi_pixel_power_brawl.websocket;

public class WaitingUserSelected {
	private String username;
	private String type;

	public WaitingUserSelected() {
	}

	public WaitingUserSelected(String username, String type) {
		this.username = username;
		this.type = type;
	}

	String getUsername() {
		return username;
	}

	String getType() {
		return type;
	}

	void setUsername(String username) {
		this.username = username;
	}

	void setType(String type) {
		this.type = type;
	}

}