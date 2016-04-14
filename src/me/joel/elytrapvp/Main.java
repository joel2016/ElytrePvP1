package me.joel.elytrapvp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.joel.elytrapvp.commands.Commands;
import me.joel.elytrapvp.language.Messages;
import me.joel.elytrapvp.utils.Config;
import me.joel.elytrapvp.utils.MySQL;
import me.joel.elytrapvp.utils.SimpleConfig;

public class Main extends JavaPlugin {

	public int min = 2;
	public int max = 8;
	public static Main plugin;
	public static String prefix;
	public static MySQL sql;
	public static ArrayList<Game> games;

	public void onEnable() {
		prefix = "§2[§cElytraPVP§2] " + Messages.d;
		plugin = this;
		getCommand("elytrapvp").setExecutor(new Commands());
		Config.load();
		setupSQL();
	}

	public void setupSQL() {
		SimpleConfig cfg = Config.getConfig();
		if (cfg.getBoolean("sql.enable")) {
			String host = cfg.getString("sql.host");
			String user = cfg.getString("sql.user");
			String password = cfg.getString("sql.pass");
			String database = cfg.getString("sql.database");
			int port = cfg.getInt("sql.port");
			try {
				sql = new MySQL(this, host, port, database, user, password);
				sql.openConnection();
				if (sql.checkConnection()) {
					sql.createTable("sw_stats",
							"Player varchar(50) NOT NULL DEFAULT '',Kills int(9),Deaths int(9),Games int(9),Wins int(9),Points int(9),PRIMARY KEY (Player),UNIQUE KEY Player (Player)");
					sql.createTable("sw_settings",
							"ID MEDIUMINT NOT NULL AUTO_INCREMENT,Player_ID varchar(50) NOT NULL, Setting varchar(50) NOT NULL,Value varchar(50) NOT NULL,PRIMARY KEY (ID)");
					Messages.success("mysqlloaded");
				} else
					sql = null;
			} catch (Exception e) {
				error(e);
				sql = null;
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void error(Exception e) {
		if (e.getMessage() == null) {
			if (e.getLocalizedMessage() == null) {
				Messages.error("error", "");
				e.printStackTrace();
			} else {
				Messages.error("error", e.getLocalizedMessage());
			}
		} else {
			Bukkit.getConsoleSender().sendMessage(prefix + "§4ERROR§c: §7" + e.getMessage());
		}
		try {
			File error = new File(plugin.getDataFolder(), "errors.log");
			FileWriter fw = new FileWriter(error, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.write("<-- System-data -->");
			pw.write("\nVersion: " + plugin.getDescription().getVersion());
			pw.write("\nServerversion: " + Bukkit.getVersion());
			Date d = new Date();
			pw.write("\nTime: " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds());
			pw.write("\nInstalled Plugins: " + Bukkit.getPluginManager().getPlugins().length + "\n|---|\n");
			String plugins = "";
			for (Plugin p : Bukkit.getPluginManager().getPlugins())
				plugins = plugins + p.getName() + ", ";
			plugins = plugins.substring(0, plugins.length() - 2);
			pw.write(plugins);
			pw.write("\n|---|");
			pw.write("\n<<<ERROR-MESSAGE>>>\n");
			e.printStackTrace(pw);
			pw.write("\n\n");
			pw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
