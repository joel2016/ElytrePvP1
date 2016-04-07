package me.joel.elytrapvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.BukkitPVP.VIPHide.VIPHide;

public class Hide {

	private static VIPHide api;

	public static void toggle(Player p) {
		if (check()) {
			if (api.isDisguised(p))
				api.undisguise(p);
			else
				api.disguise(p);
		}
	}

	public static boolean check() {
		if (Bukkit.getPluginManager().isPluginEnabled("VIPHide")) {
			if (api == null)
				api = (VIPHide) Bukkit.getPluginManager().getPlugin("VIPHide");
			return true;
		}
		return false;
	}

}
