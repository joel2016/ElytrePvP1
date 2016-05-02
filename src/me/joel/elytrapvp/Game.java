package me.joel.elytrapvp;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import me.joel.elytrapvp.utils.Config;
import me.joel.elytrapvp.utils.GameAPI;
import me.joel.elytrapvp.utils.GameState;
import me.joel.elytrapvp.utils.SimpleConfig;

public class Game {

	final private String name;
	private SimpleConfig cfg;
	private JavaPlugin plugin;

	public Game(String name) {
		this.name = name;
		this.plugin = Main.plugin;
		this.cfg = Config.getData();
		player = new ArrayList<>();
	}

	public void join(Player p) {
		Game.player.add(p);
	}

	public void giveLobbyItems(Player p) {
	}

	public void leave(Player p) {
		Game.player.remove(player);
	}

	public void stop() {
		Game.player.remove(player);
	}

	public void reset() {
		if (GameState.END == GameState.LOBBY) {
			Game.player.remove(player);
		}
	}

	public void dead(Player p) {
		Game.player.remove(player);
	}

	public void setHolo(Location loc) {
		setLocation(loc, "holo");
	}

	public Location getHolo() {
		return getLocation("holo");
	}

	public void setLobby(Location loc) {
		setLocation(loc, "lobby");
	}

	public Location getLobby() {
		return getLocation("lobby");
	}

	public void setLocation(Location loc, String key) {
		// TODO Setzen der Location in der cfg
	}

	public Location getLocation(String key) {
		return null; // TODO Holen der Location aus der cfg
	}

	public String getName() {
		return name;
	}

	public static ArrayList<Player> player;

}
