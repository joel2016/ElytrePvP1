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
		// SETUP
		msg.put("back", "Back");
		msg.put("where", "Where am I?");
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
		msg.put("h_wins", "§7Games Won: §c%v");
		msg.put("stats", "§7Stats §6<right-click>");
		msg.put("join", "§7Join the game");
		msg.put("leave", "§7Leave the game");
		msg.put("spawns", "§7Set the spawns");
		msg.put("create", "§7Create the game");
		msg.put("stats", "§7See your Stats");
		msg.put("vote", "§7Vote for the game");
		msg.put("l_back", "§7Back"); //Lobby verlassen 
		msg.put("permi", "§7You have not Permission for this Command"); 
		msg.put("s_join", "§5%v joint the game"); 
		msg.put("s_leave", "§5%v leavt the game"); 
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
