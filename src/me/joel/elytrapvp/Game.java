package me.joel.elytrapvp;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.joel.elytrapvp.utils.Config;
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
		// TODO Spieler tritt dem Spiel bei
	}

	public void giveLobbyItems(Player p) {
		// TODO Gibt dem Spieler alle Lobby-Items
	}

	public void leave(Player p) {
		// TODO Spieler verlässt das Spiel
	}

	public void stop() {
		// TODO Stoppt das Spiel
	}

	public void reset() {
		// TODO Setzt alle ArrayListen und geschossene Projektile zurück
	}

	public void dead(Player p) {
		// TODO wird aufgerufen, wenn ein Spieler stirbt
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
