package me.joel.elytrapvp.language;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class English implements Language {

	private static HashMap<String, String> msg = new HashMap<>();

	public String getName(Player p) {
		return "English";
	}

	public void setup() {
		// Other
		msg.put("automatic", "Automatic");
		// Messages to console
		msg.put("error", "&4An error occurred: %r%v");
		// Points
		msg.put("getpoints", "You got %h1%v%r points.");
		msg.put("stats", "§7Stats");
		msg.put("h_rank", "§7Rank: §c%v");
		msg.put("h_kills", "§7Kills: §c%v");
		msg.put("h_deaths", "§7Deaths: §c%v");
		msg.put("h_kd", "§7K/D: §c%v");
		msg.put("h_played", "§7Played games: §c%v");
		msg.put("h_wins", "§7Won games: §c%v");
	}

	public HashMap<String, String> getList(Player p) {
		if (msg.isEmpty())
			setup();
		return msg;
	}

	public String getError(Player p, String key) {
		return "&7REPORT: &6" + key;
	}
}
