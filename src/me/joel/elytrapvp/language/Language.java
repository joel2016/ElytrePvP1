package me.joel.elytrapvp.language;

import java.util.HashMap;

import org.bukkit.entity.Player;

public interface Language {

	public String getName(Player p);

	public HashMap<String, String> getList(Player p);

	public String getError(Player p, String key);

	public void setup();

}
