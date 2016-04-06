package me.joel.elytrapvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Join implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		Player p = (Player) sender;

		if (p.hasPermission("ep.join")) {
			p.getName().equalsIgnoreCase("ep.join");
		}

		return false;
	}
}
