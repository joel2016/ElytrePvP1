package me.joel.elytrapvp.utils;

import org.bukkit.ChatColor;

public enum GameState {
	RUNNING("Running", ChatColor.YELLOW), LOBBY("Lobby", ChatColor.GREEN), FULL("Lobby", ChatColor.GOLD), UNKNOWN(
			"Unknown", ChatColor.WHITE), ERROR("Error",
					ChatColor.DARK_RED), SETUP("Setup", ChatColor.BLUE), STARTING("Starting", ChatColor.GREEN);

	private String state;
	private ChatColor color;

	GameState(String state, ChatColor color) {
		this.state = state;
		this.color = color;
	}

	public String getState() {
		return state;
	}

	public ChatColor getColor() {
		return color;
	}

	@Override
	public String toString() {
		return color + state;
	}
}
