package me.joel.elytrapvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.joel.elytrapvp.Game;
import me.joel.elytrapvp.language.Messages;

public class Commands implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		Player p = (Player) sender;
	
	if(cmd.getName().equalsIgnoreCase("elytrapvp")){
		if(p.hasPermission("elytra.*")) {
			if(args.length == 0){
				p.sendMessage("§5ElytrenPvP");
				p.sendMessage("§6======================");
				p.sendMessage("§5/ep.join");
				p.sendMessage(Messages.msg(p, "join",  p.getName()));
				p.sendMessage("§5/ep.leave");
				p.sendMessage(Messages.msg(p, "leave", p.getName()));
				p.sendMessage("§5/ep.setspawn");
				p.sendMessage(Messages.msg(p, "spawns", p.getName()));
				p.sendMessage("§5/ep.create");
				p.sendMessage(Messages.msg(p, "create", p.getName()));
				p.sendMessage("§5/ep.vote");
				p.sendMessage(Messages.msg(p, "vote", p.getName()));
				p.sendMessage("§5/ep.stats");
				p.sendMessage(Messages.msg(p, "stats", p.getName()));
				return true;
				}
			
            if(args.length == 1){
			if(args[0].equalsIgnoreCase("join")){
    		p.sendMessage(Messages.msg(p, "s_join", Game.player));
    		
				return true;
			
			}
			if(args[0].equalsIgnoreCase("leave")){
				return true;
			}
            }
			}else{
				p.sendMessage(Messages.msg(p, "permi", p.getName()));
				return true;
			}
		}
			
			return true;
		}
}

