package me.joel.elytrapvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Game {
	
	public static void startGame1(){
		GameAPI.gameCountdown(10, GameState.LOBBY, null);
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setHealth(20);
			p.setFoodLevel(20);
			p.sendMessage("Spiel Begint");
		}
	}
	public static void startGame2(){
		GameAPI.gameCountdown(10, GameState.RUNNING, null);
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setHealth(20);
			p.setFoodLevel(20);
			p.sendMessage("Spiel ist Beendet");
		}
	}
	public static void startGame3(){
		
		GameAPI.gameCountdown(10, GameState.END, null);
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setFoodLevel(20);
			p.sendMessage("Server Stopt");
		}
	}

}
