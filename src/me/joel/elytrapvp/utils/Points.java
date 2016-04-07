package me.joel.elytrapvp.utils;

import me.BukkitPVP.PointsAPI.PointsAPI;
import me.joel.elytrapvp.language.Messages;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Points {

	private static PointsAPI point;

	public static boolean addPoints(Player p, int points) {
		if (checkPointsAPI()) {
			point.addPoints(p, points);
			Stats.addPoints(p, points);
			Messages.success(p, "getpoints", points);
			return true;
		}
		return false;
	}

	public static int getPoints(Player p) {
		if (checkPointsAPI()) {
			return point.getPoints(p);
		}
		return 0;
	}

	public static boolean removePoints(Player p, int points) {
		if (checkPointsAPI()) {
			if (getPoints(p) - points >= 0) {
				point.removePoints(p, points);
				return true;
			}
		}
		return false;
	}

	public static boolean checkPointsAPI() {
		if (Bukkit.getPluginManager().isPluginEnabled("PointsAPI")) {
			if (point == null)
				point = (PointsAPI) Bukkit.getPluginManager().getPlugin("PointsAPI");
			return true;
		}
		return false;
	}

}
