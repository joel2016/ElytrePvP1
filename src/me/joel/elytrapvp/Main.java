package me.joel.elytrapvp;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.joel.elytrapvp.commands.Commands;

public class Main extends JavaPlugin {

	public int min = 2;
	public int max = 8;
	public Filemanager fm;
	public static ArrayList<Player> game = new ArrayList();
	public static Main main;

	public void onEnable() {
		System.out.println("[ElytraPvP] Das Plugin wurde aktivirt.");
		game = new ArrayList();
		main = this;
		this.fm = new Filemanager();
		Filemanager.savecfg();
		this.fm.register();
		getCommand("help").setExecutor(new Commands());
	}

}
