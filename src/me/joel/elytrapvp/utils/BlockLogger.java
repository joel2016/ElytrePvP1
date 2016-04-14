package me.joel.elytrapvp.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

public class BlockLogger {

	private Location loc;
	private Material m;
	private Biome b;
	private byte data;

	@SuppressWarnings("deprecation")
	public BlockLogger(Block b) {
		loc = b.getLocation();
		m = Material.AIR;
		data = b.getData();
		this.b = b.getBiome();
	}

	public Location getLocation() {
		return loc;
	}

	@SuppressWarnings("deprecation")
	public void reset() {
		Block block = loc.getBlock();
		block.setBiome(b);
		block.setType(m);
		block.setData(data);
	}

	@Override
	public String toString() {
		return "[" + m.toString() + "] (" + data + ") at " + loc.getBlockX() + "," + loc.getBlockY() + ","
				+ loc.getBlockZ() + " in " + loc.getWorld().getName();
	}
}