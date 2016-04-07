package me.joel.elytrapvp.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;
import java.sql.Statement;

import org.bukkit.entity.Player;

import me.joel.elytrapvp.Main;
import me.joel.elytrapvp.language.Messages;

public class Stats {

	private static SimpleConfig cfg = Config.getData();
	private static MySQL sql = Main.sql;
	private static String field = "Player";

	public static String[] getLines(Player p, Player t) {
		return getLines(p, t.getUniqueId());
	}

	public static String[] getLines(Player p, UUID id) {
		String[] s = new String[7];
		s[0] = Messages.msg(p, "stats");
		s[1] = Messages.msg(p, "h_rank").replace("%v", "" + getRank(id));
		s[2] = Messages.msg(p, "h_kills").replace("%v", Stats.getKills(id) + "");
		s[3] = Messages.msg(p, "h_deaths").replace("%v", Stats.getDeaths(id) + "");
		s[4] = Messages.msg(p, "h_kd").replace("%v", Stats.getKD(id) + "");
		s[5] = Messages.msg(p, "h_played").replace("%v", Stats.getGames(id) + "");
		s[6] = Messages.msg(p, "h_wins").replace("%v", Stats.getWins(id) + "");
		return s;
	}

	public static String[] getLines(Player p) {
		return getLines(p, p);
	}

	private static void saveConfig() {
		cfg.saveConfig();
	}

	public static int getRank(Player p) {
		return getRank(p.getUniqueId());
	}

	public static int getRank(UUID id) {
		ArrayList<UUID> list = new ArrayList<>();
		if (sql != null) {
			try {
				if (sql.checkConnection())
					sql.openConnection();
				Statement statement = sql.getConnection().createStatement();
				ResultSet set = statement.executeQuery("SELECT Player FROM ep_stats ORDER BY Points DESC");
				while (set.next()) {
					list.add(UUID.fromString(set.getString("Player")));
				}
			} catch (SQLException e) {
				Main.error(e);
			}
		} else {
			if (cfg.contains("stats"))
				for (String s : cfg.getConfigurationSection("stats").getKeys(false)) {
					list.add(UUID.fromString(s));
				}
			Collections.sort(list, new Comparator<UUID>() {

				@Override
				public int compare(UUID arg0, UUID arg1) {
					int p1 = getPoints(arg0);
					int p2 = getPoints(arg1);
					return p1 < p2 ? 1 : p1 == p2 ? 0 : -1;
				}

			});
		}
		if (list.contains(id))
			return list.indexOf(id) + 1;
		else
			return -1;
	}

	public static void addPoints(Player p, int points) {
		addPoints(p.getUniqueId(), points);
	}

	public static void addPoints(UUID id, int points) {
		int point = getPoints(id);
		if (sql != null)
			setInt("Points", field, id.toString(), point + points);
		else {
			cfg.set("stats." + id + ".points", point + points);
			saveConfig();
		}
	}

	public static int getPoints(Player p) {
		return getPoints(p.getUniqueId());
	}

	public static int getPoints(UUID id) {
		if (sql != null)
			return getInt("Points", field, id.toString());
		else {
			if (!cfg.contains("stats." + id + ".points")) {
				cfg.set("stats." + id + ".points", 0);
				saveConfig();
			}
			return cfg.getInt("stats." + id + ".points");
		}
	}

	public static void addKill(Player p) {
		addKill(p.getUniqueId());
	}

	public static void addKill(UUID id) {
		int k = getKills(id);
		if (sql != null)
			setInt("Kills", field, id.toString(), k + 1);
		cfg.set("stats." + id + ".kills", k + 1);
		saveConfig();
	}

	public static int getKills(Player p) {
		return getKills(p.getUniqueId());
	}

	public static int getKills(UUID id) {
		if (sql != null)
			return getInt("Kills", field, id.toString());
		else {
			if (!cfg.contains("stats." + id + ".kills")) {
				cfg.set("stats." + id + ".kills", 0);
				saveConfig();
			}
			return cfg.getInt("stats." + id + ".kills");
		}
	}

	public static void addDeath(Player p) {
		addDeath(p.getUniqueId());
	}

	public static void addDeath(UUID id) {
		int k = getDeaths(id);
		if (sql != null)
			setInt("Deaths", field, id.toString(), k + 1);
		else {
			cfg.set("stats." + id + ".deaths", k + 1);
			saveConfig();
		}
	}

	public static int getDeaths(Player p) {
		return getDeaths(p.getUniqueId());
	}

	public static int getDeaths(UUID id) {
		if (sql != null) {
			return getInt("Deaths", field, id.toString());
		} else {
			if (!cfg.contains("stats." + id + ".deaths")) {
				cfg.set("stats." + id + ".deaths", 0);
				saveConfig();
			}
			return cfg.getInt("stats." + id + ".deaths");
		}
	}

	public static void addGame(Player p) {
		addGame(p.getUniqueId());
	}

	public static void addGame(UUID id) {
		int k = getGames(id);
		if (sql != null)
			setInt("Games", field, id.toString(), k + 1);
		else {
			cfg.set("stats." + id + ".games", k + 1);
			saveConfig();
		}
	}

	public static int getGames(Player p) {
		return getGames(p.getUniqueId());
	}

	public static int getGames(UUID id) {
		if (sql != null) {
			return getInt("Games", field, id.toString());
		} else {
			if (!cfg.contains("stats." + id + ".games")) {
				cfg.set("stats." + id + ".games", 0);
				saveConfig();
			}
			return cfg.getInt("stats." + id + ".games");
		}
	}

	public static void addWin(Player p) {
		addWin(p.getUniqueId());
	}

	public static void addWin(UUID id) {
		int k = getWins(id);
		if (sql != null)
			setInt("Wins", field, id.toString(), k + 1);
		else {
			cfg.set("stats." + id + ".wins", k + 1);
			saveConfig();
		}
	}

	public static int getWins(Player p) {
		return getWins(p.getUniqueId());
	}

	public static int getWins(UUID id) {
		if (sql != null) {
			return getInt("Wins", field, id.toString());
		} else {
			if (!cfg.contains("stats." + id + ".wins")) {
				cfg.set("stats." + id + ".wins", 0);
				saveConfig();
			}
			return cfg.getInt("stats." + id + ".wins");
		}
	}

	public static double getKD(Player p) {
		if (getDeaths(p) == 0)
			return getKills(p);
		return getKills(p) / getDeaths(p);
	}

	public static double getKD(UUID id) {
		if (getDeaths(id) == 0)
			return getKills(id);
		return getKills(id) / getDeaths(id);
	}

	public static int getInt(String column, String field, String search) {
		if (sql.checkConnection())
			sql.openConnection();
		Connection conn = sql.getConnection();
		int back = 0;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM ep_stats WHERE " + field + " = '" + search + "'");
			while (rs.next()) {
				back = rs.getInt(column);
			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			Main.error(e);
		}
		return back;
	}

	public static void setInt(String column, String field, String search, int set) {
		sql.updateSQL("INSERT INTO ep_stats (" + field + "," + column + ") VALUES ('" + search + "','" + set
				+ "') ON DUPLICATE KEY UPDATE " + column + " = '" + set + "'");
	}

}
