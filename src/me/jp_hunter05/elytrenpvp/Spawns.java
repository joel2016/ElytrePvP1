package me.jp_hunter05.elytrenpvp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawns implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String labl, String[] args) {
		Player p = (Player) sender;
		
		if(p.hasPermission("ep.spawns")){
			if(p.getName().equalsIgnoreCase("spawns")){
			}
		}
		return false;
	}
	
	

}
