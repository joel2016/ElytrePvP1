package me.joel.elytrapvp.language;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class German implements Language {

	private static HashMap<String, String> msg = new HashMap<>();

	public String getName(Player p) {
		return "Deutsch";
	}

	public void setup() {
		// Other
		msg.put("automatic", "Automatisch");
		// Messages to console
		msg.put("error", "&4Ein Fehler ist aufgetreten: %r%v");
		// Points
		msg.put("getpoints", "Du hast %h1%v%r Punkte bekommen.");
		// Stats
		msg.put("stats", "§7Statistiken");
		msg.put("h_rank", "§7Rang: §c%v");
		msg.put("h_kills", "§7Kills: §c%v");
		msg.put("h_deaths", "§7Tode: §c%v");
		msg.put("h_kd", "§7K/D: §c%v");
		msg.put("h_played", "§7Gespielte Spiele: §c%v");
		msg.put("h_wins", "§7Gewonnene Spiele: §c%v");
	}

	public HashMap<String, String> getList(Player p) {
		if (msg.isEmpty())
			setup();
		return msg;
	}

	public String getError(Player p, String key) {
		return "&7MELDE: &6" + key;
	}
}
