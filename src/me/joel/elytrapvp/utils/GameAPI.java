package me.joel.elytrapvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.joel.elytrapvp.Main;

public class GameAPI {
	
    final Player p;
    
	public GameAPI(Player p){
		this.p = p;
	}
	
	public static void gameCountdown(final int time, final GameState s, Location loc) {
		for(Player p : Bukkit.getOnlinePlayers()) {
		}
		BukkitRunnable run = new BukkitRunnable() {
			int i = time;
			@Override
			public void run() {
				if(i != 0) {
					if(i == 10) {
						for(Player p : Bukkit.getOnlinePlayers()) {
							p.sendMessage(Main.prefix+"Das Spiel endet in 10 Sekunden!");
						}
					} else if(i == 5) {
						for(Player p : Bukkit.getOnlinePlayers()) {
							p.sendMessage(Main.prefix+"Das Spiel endet in 5 Sekunden!");
						}
					}else if(i == 4){
						for(Player p : Bukkit.getOnlinePlayers()) {
							p.sendMessage(Main.prefix+"Das Spiel endet in 4 Sekunden!");
						}
					}else if(i == 3){
						for(Player p : Bukkit.getOnlinePlayers()) {
							p.sendMessage(Main.prefix+"Das Spiel endet in 3 Sekunden!");
						}
					}else if(i == 2){
						for(Player p : Bukkit.getOnlinePlayers()) {
							p.sendMessage(Main.prefix+"Das Spiel endet in 2 Sekunden!");
						}
					}else if(i == 1){
						for(Player p : Bukkit.getOnlinePlayers()) {
							p.sendMessage(Main.prefix+"Das Spiel endet in 1 Sekunden!");
						}
						
					}
					i--;
				} else {
					for(Player p : Bukkit.getOnlinePlayers()) {
						p.sendMessage("");
					}
					if(s == GameState.LOBBY){
						Game.startGame1();
					}else if(s == GameState.RUNNING)	{
						Game.startGame2();
					}else if(s == GameState.END) {
						Game.startGame3();
					}
					cancel();
				}
				
			}
		};
		run.runTaskTimer(Main.plugin, 0, 20);
		
	}

}
