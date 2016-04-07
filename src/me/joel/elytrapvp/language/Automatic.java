package me.joel.elytrapvp.language;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class Automatic implements Language {

	@Override
	public HashMap<String, String> getList(Player p) {
		return Messages.getLanguage(p).getList(p);
	}

	@Override
	public String getError(Player p, String key) {
		return Messages.getLanguage(p).getError(p, key);
	}

	@Override
	public void setup() {

	}

	@Override
	public String getName(Player p) {
		return Messages.msg(p, "automatic");
	}

}
