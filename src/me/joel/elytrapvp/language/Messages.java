package me.joel.elytrapvp.language;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.joel.elytrapvp.utils.Settings;

public class Messages {

	public static ChatColor d = ChatColor.getByChar('7');
	private static HashMap<String, Language> languages = new HashMap<>();

	public static void info(Player p, String key, Object... values) {
		p.sendMessage("§7[§bElytraPVP§7] " + d + manage(msg(p, key), values));
	}

	public static void info(String key, Object... values) {
		Bukkit.getConsoleSender().sendMessage("§7[§bElytraPVP§7] " + d + manage(msg(key), values));
	}

	public static void error(Player p, String key, Object... values) {
		p.sendMessage("§7[§cElytraPVP§7] " + d + manage(msg(p, key), values));
	}

	public static void error(String key, Object... values) {
		Bukkit.getConsoleSender().sendMessage("§7[§cElytraPVP§7] " + d + manage(msg(key), values));
	}

	public static void success(Player p, String key, Object... values) {
		p.sendMessage("§7[§aElytraPVP§7] " + d + manage(msg(p, key), values));
	}

	public static void success(String key, Object... values) {
		Bukkit.getConsoleSender().sendMessage("§7[§aElytraPVP§7] " + d + manage(msg(key), values));
	}

	private static String manage(String msg, Object... values) {
		if (msg == null)
			msg = "There is an error with a message";
		msg = msg.replace("%h1", "&6");
		msg = msg.replace("%h2", "&c");
		msg = msg.replace("%r", d + "");
		int i = 0;
		while (msg.contains("%v") && i < values.length) {
			msg = msg.replaceFirst("%v", values[i] + "");
			i++;
		}
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	public static String msg(Player p, String msg, Object... values) {
		setup();
		Language l = Settings.getLanguage(p);
		String message = l.getError(p, msg);
		if (l.getList(p).containsKey(msg))
			message = l.getList(p).get(msg);
		return manage(message, values);
	}

	public static String msg(String msg) {
		if (!getDefault().getList(null).containsKey(msg))
			return ChatColor.translateAlternateColorCodes('&', getDefault().getError(null, msg));
		return manage(getDefault().getList(null).get(msg));
	}

	private static void setup() {
		if (languages.isEmpty()) {
			languages.put("en_US", new English());
			languages.put("en_GB", new English());
			languages.put("de_DE", new German());
			languages.put("auto", new Automatic());
		}
	}

	public static Language getNext(String key) {
		setup();
		switch (key) {
		case "de_DE":
			return languages.get("en_US");
		case "en_US":
			return languages.get("auto");
		case "en_GB":
			return languages.get("auto");
		case "auto":
			return languages.get("de_DE");
		default:
			return getDefault();
		}
	}

	public static Language getLanguage(Player p) {
		setup();
		String s = getPlayer(p);
		if (languages.containsKey(s))
			return languages.get(s);
		else
			return getDefault();
	}

	public static Language getLanguage(String key) {
		setup();
		if (languages.containsKey(key))
			return languages.get(key);
		else
			return getDefault();
	}

	public static String getKey(Language l) {
		for (String s : languages.keySet())
			if (l == languages.get(s))
				return s;
		return "auto";
	}

	private static Language getDefault() {
		setup();
		return languages.get("en_US");
	}

	private static String getPlayer(Player p) {
		Object ep = null;
		try {
			ep = get("getHandle", p.getClass()).invoke(p, (Object[]) null);
		} catch (Exception e1) {
			return "";
		}
		Field f = null;
		try {
			f = ep.getClass().getDeclaredField("locale");
		} catch (NoSuchFieldException | SecurityException e) {
			return "";
		}
		f.setAccessible(true);
		String language = "";
		try {
			language = (String) f.get(ep);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return "";
		}
		return language;
	}

	private static Method get(String name, Class<?> clazz) {
		for (Method m : clazz.getDeclaredMethods()) {
			if (m.getName().equals(name))
				return m;
		}
		return null;
	}
}
