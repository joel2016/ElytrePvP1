package me.joel.elytrapvp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

/**
 * <p>
 * This updater does only work with plugins from http://www.mine-home.net/
 * </p>
 * <p>
 * This updater does <strong>not</strong> download files
 * </p>
 * <p>
 * This updater can be disabled in the config (plugins/MineHome/updater.yml)
 * </p>
 * 
 * @author BukkitPVP
 * @version 1.0.0
 * 
 */
class Updater implements Listener {

	private String newVersion;
	private String name;
	private int pid = 0;

	/**
	 * Start the updater
	 * 
	 * @param plugin
	 *            The plugin
	 * @param id
	 *            The product id on mine-home.net
	 */
	public Updater(Plugin plugin, int id) {
		this(plugin.getDescription().getVersion(), plugin.getName(), plugin, id);
	}

	/**
	 * Start the updater
	 * 
	 * @param version
	 *            The version of the actual plugin
	 * @param pluginname
	 *            The name of the plugin
	 * @param plugin
	 *            The plugin
	 * @param id
	 *            The product id on mine-home.net
	 */
	public Updater(String version, String pluginname, Plugin plugin, int id) {
		if (plugin == null)
			return;
		if (!checkConfig(pluginname))
			return;
		String v = getVersion(id);
		int result = compare(version, v);
		if (result < 0) { // UPDATE AVIABLE
			this.newVersion = v;
			this.name = pluginname;
			this.pid = id;
			for (Player p : Bukkit.getOnlinePlayers())
				if (p.hasPermission("updater.notification"))
					p.sendMessage("§8[§c"
							+ name
							+ "§8] §7 A new update is aviable (§c"
							+ newVersion
							+ "§7)! Click here to download it:§c http://www.mine-home.net/product?id="
							+ pid);
			Bukkit.getPluginManager().registerEvents(this, plugin);
		}
	}

	private boolean checkConfig(String name) {
		File file = new File("plugins/MineHome", "updater.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if (!cfg.contains("enable")) {
			cfg.set("enable", true);
			try {
				cfg.save(file);
			} catch (IOException e) {
			}
		}
		if (!cfg.contains(name)) {
			cfg.set(name, true);
			try {
				cfg.save(file);
			} catch (IOException e) {
			}
		}
		if (!cfg.getBoolean("enable"))
			return false;
		else
			return cfg.getBoolean(name);
	}

	private String getVersion(int id) {
		try {
			URL u = new URL("http://www.mine-home.net/version?id=" + id);
			URLConnection c = u.openConnection();
			InputStream r = c.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(r));
			for (String line; (line = reader.readLine()) != null;)
				return line;
		} catch (IOException e) {
		}
		return "0.0.0";
	}

	private Integer compare(String str1, String str2) {
		String[] vals1 = str1.split("\\.");
		String[] vals2 = str2.split("\\.");
		int i = 0;
		while (i < vals1.length && i < vals2.length
				&& vals1[i].equals(vals2[i])) {
			i++;
		}
		if (i < vals1.length && i < vals2.length) {
			int diff = Integer.valueOf(vals1[i]).compareTo(
					Integer.valueOf(vals2[i]));
			return Integer.signum(diff);
		} else {
			return Integer.signum(vals1.length - vals2.length);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission("updater.notification"))
			e.getPlayer()
					.sendMessage(
							"§8[§c"
									+ name
									+ "§8] §7 A new update is aviable (§c"
									+ newVersion
									+ "§7)! Click here to download it:§c http://www.mine-home.net/product?id="
									+ pid);
	}
}