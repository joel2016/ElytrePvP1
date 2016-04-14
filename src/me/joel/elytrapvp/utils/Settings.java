package me.joel.elytrapvp.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;

import me.joel.elytrapvp.Main;
import me.joel.elytrapvp.language.Language;
import me.joel.elytrapvp.language.Messages;

public class Settings {

	private static MySQL sql = Main.sql;

	public static Language getLanguage(Player p) {
		String name = p.getUniqueId().toString();
		SimpleConfig c = Config.getData();
		String key = "auto";
		if (sql != null) {
			String l = getValue(p, "lang");
			if (l.equals("")) {
				setValue(p, "lang", "auto");
				l = getValue(p, "lang");
			}
			key = l;
		} else {
			if (c.contains("language." + name))
				key = c.getString("language." + name);
			else
				c.set("language." + name, "auto");
		}
		c.saveConfig();
		return Messages.getLanguage(key);
	}

	public static void switchLanguage(Player p) {
		String name = p.getUniqueId().toString();
		SimpleConfig c = Config.getData();
		Language next = Messages.getNext(Messages.getKey(getLanguage(p)));
		if (sql != null)
			setValue(p, "lang", Messages.getKey(next));
		c.set("language." + name, Messages.getKey(next));
		c.saveConfig();
	}

	private static String getValue(Player p, String key) {
		String s = "";
		String playerid = p.getUniqueId().toString();
		String SQL = "SELECT Value FROM ep_settings WHERE Player_ID='" + playerid + "' AND Setting='" + key + "'";
		if (sql.checkConnection())
			sql.openConnection();
		Connection conn = sql.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			while (rs.next()) {
				s = rs.getString(1);
			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			Main.error(e);
		}
		return s;
	}

	private static void setValue(Player p, String key, String value) {
		String playerid = p.getUniqueId().toString();
		String SQL = "";
		if (getValue(p, key).equals(""))
			SQL = "INSERT INTO ep_settings (Player_ID,Setting,Value) VALUES('" + playerid + "','" + key + "','" + value
					+ "')";
		else
			SQL = "UPDATE ep_settings SET Value='" + value + "' WHERE Player_ID='" + playerid + "' AND Setting='" + key
					+ "'";
		sql.updateSQL(SQL);
	}

}
