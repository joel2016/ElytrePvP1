package me.jp_hunter05.elytrenpvp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		Player p = (Player) sender;
	
	
	if(cmd.getName().equalsIgnoreCase("help")){
		if(p.hasPermission("ep.*")) {
			if(args.length == 0){
				p.sendMessage("§5ElytrenPvP");
				p.sendMessage("§6======================");
				p.sendMessage("§5/ep.join");
				p.sendMessage("§6Trete das Spiel bei");
				p.sendMessage("§5/ep.leave");
				p.sendMessage("§6Verlasse das Spiel");
				p.sendMessage("§5/ep.setspawn");
				p.sendMessage("§6Setzte für 8 Spieler den spawn");
				p.sendMessage("§5/ep.create");
				p.sendMessage("§6Füge eine map hinzu");
				p.sendMessage("§5Seite 2 /ep.help2");
				p.sendMessage("§5/ep.vote");
				p.sendMessage("§6öfne das Vote inv");
				p.sendMessage("§5/ep.stats");
				p.sendMessage("§6Sehe deine stats");
				return true;
			}
		
		}else{
			p.sendMessage("Du hast nicht genug Rechte für diesen Befehl§e!");
			return true;
		}
	}
		
		return true;
	}

}
