package me.joel.elytrapvp;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.joel.elytrapvp.Main;
import me.joel.elytrapvp.language.Messages;
import me.joel.elytrapvp.utils.Config;
import me.joel.elytrapvp.utils.GameState;
import me.joel.elytrapvp.utils.ItemCreator;
import me.joel.elytrapvp.utils.SimpleConfig;

public class Game {

	final private String name;
	private SimpleConfig cfg;
	private JavaPlugin plugin;
	private boolean start;
	private boolean stop;
	private GameState state;

	public Game(String name) {
		this.name = name;
		this.plugin = Main.plugin;
		this.cfg = Config.getData();
		player = new ArrayList<>();
	}

	public void join(Player p) {
		player.add(p);
	}

	public void giveLobbyItems(Player p) {
		ItemStack back = ItemCreator.CreateItemwithID(378, 1, 0, Messages.msg(p, "l_back", p.getName()), null);

		ItemStack stats = ItemCreator.CreateItemwithID(378, 1, 0, Messages.msg(p, "i_stats", p.getName()), null);

		if (player.contains(p)) {
			p.getInventory().setItem(1, back);
			p.getInventory().setItem(8, stats);
		}
	}

	public void leave(Player p) {
		player.remove(p);
	}

	public void stop() {
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

	public void startWaiting() {
		this.start = false;
		this.stop = false;
		this.state = GameState.STARTING;
		new BukkitRunnable() {
			int counter = 60;

			public void run() {
				if ((Game.this.getPlayers().size() < 2) || (Game.this.stop)) {
					for (Player p : Game.this.getPlayers()) {
						p.setLevel(0);
					}
					cancel();
				}
				if (Game.this.start) {
					Game.this.start = false;
					this.counter = 5;
				}
				for (Player p : Game.this.getPlayers()) {
					p.setLevel(this.counter);
					if (this.counter <= 3) {
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20.0F, 20.0F);
					}
				}
				if (this.counter <= 0) {
					Game.this.startWaiting();
					cancel();
				}
				this.counter -= 1;
			}
		}.runTaskTimer(this.plugin, 20L, 20L);
	}

	protected ArrayList<Player> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void forceStart() {
		this.start = true;
	}

}
