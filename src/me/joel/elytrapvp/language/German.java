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
		// SETUP
		msg.put("where", "Wo bin ich?");
		msg.put("back", "Zurück");
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
		msg.put("stats", "§7Stats §6<rechts klick>");
		msg.put("join", "§7Joine das Spiel");
		msg.put("leave", "§7Leave das Spiel");
		msg.put("spawns", "§7Sete sie Spawns");
		msg.put("create", "§7Erschaffe ein Spiel");
		msg.put("i_stats", "§7Sehe deine stats");
		msg.put("vote", "§7Vote für Maps");
		msg.put("l_back", "§7Zurück"); //Lobby verlassen 
		msg.put("permi", "§7Du hast keine Permission für diesen Command"); 
		msg.put("s_join", "§5%v ist dem Spiel beigetreten"); 
		msg.put("s_leave", "§5%v hat das Spiel verlassen"); 
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
