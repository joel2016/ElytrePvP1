package me.joel.elytrapvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawns implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String labl, String[] args) {
		Player p = (Player) sender;

		if (p.hasPermission("ep.spawns")) {
			p.getName().equalsIgnoreCase("spawns");
		}

		return false;
	}
}
