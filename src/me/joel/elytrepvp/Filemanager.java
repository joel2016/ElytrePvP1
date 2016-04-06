package me.joel.elytrepvp;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Filemanager {

	  public static File file = new File("plugins/" + Main.main.getName(), "config.yml");
	  public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	  public void register() {
	    cfg.options().copyDefaults(true);
	    cfg.addDefault("Minplayers", Integer.valueOf(Main.main.max));
	    cfg.addDefault("Minplayers", Integer.valueOf(Main.main.min));
	    savecfg();
	    Main.main.max = cfg.getInt("");
	    Main.main.max = cfg.getInt("");
	  }

	  public static void savecfg()
	  {
	    try
	    {
	      cfg.save(file);
	    }
	    catch (IOException localIOException)
	    {
	    }
	  }
}
