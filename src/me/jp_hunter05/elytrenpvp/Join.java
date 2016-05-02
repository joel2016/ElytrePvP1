package me.jp_hunter05.elytrenpvp;

import java.awt.Color;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Join implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		Player p = (Player) sender;
		
		if(p.hasPermission("ep.join")){
			if(p.getName().equalsIgnoreCase("ep.join")){
			}
			
			
		}
		
		return false;
	}

}
