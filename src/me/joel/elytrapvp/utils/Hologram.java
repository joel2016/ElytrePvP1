package me.joel.elytrapvp.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.joel.elytrapvp.Main;

public class Hologram {

	private String[] lines;
	private Location loc;
	private ArrayList<Integer> ids;
	private ArrayList<Player> players;

	private static final double ABS = 0.23D;
	private static String path;
	private static String version;

	/*
	 * Cache for getPacket()-Method
	 */
	private static Class<?> armorStand;
	private static Class<?> worldClass;
	private static Class<?> nmsEntity;
	private static Class<?> craftWorld;
	private static Class<?> packetClass;
	private static Class<?> entityLivingClass;
	private static Class<?> destroy;
	private static Constructor<?> armorStandConstructor;

	/*
	 * Cache for sendPacket()-Method
	 */
	private static Class<?> nmsPacket;

	static {
		path = Bukkit.getServer().getClass().getPackage().getName();
		version = path.substring(path.lastIndexOf(".") + 1, path.length());

		try {
			armorStand = Class.forName("net.minecraft.server." + version + ".EntityArmorStand");
			worldClass = Class.forName("net.minecraft.server." + version + ".World");
			nmsEntity = Class.forName("net.minecraft.server." + version + ".Entity");
			craftWorld = Class.forName("org.bukkit.craftbukkit." + version + ".CraftWorld");
			packetClass = Class.forName("net.minecraft.server." + version + ".PacketPlayOutSpawnEntityLiving");
			entityLivingClass = Class.forName("net.minecraft.server." + version + ".EntityLiving");
			destroy = Class.forName("net.minecraft.server." + version + ".PacketPlayOutEntityDestroy");
			armorStandConstructor = armorStand.getConstructor(new Class[] { worldClass });

			nmsPacket = Class.forName("net.minecraft.server." + version + ".Packet");
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException ex) {
			Main.error(ex);
		}
	}

	public Hologram(Location loc, String... lines) {
		this.lines = lines;
		this.loc = loc;
		players = new ArrayList<>();
		ids = new ArrayList<>();
	}

	/**
	 * 
	 * @return Returns the location of the hologram
	 */
	public Location getLocation() {
		return loc;
	}

	/**
	 * Show the hologram to a player
	 * 
	 * @param p
	 *            The Player you want to show the hologram
	 * @return Returns if the hologram was successfull
	 */
	public boolean display(Player p) {
		Location displayLoc = loc.clone().add(0, (ABS * lines.length) - 1.97D, 0);
		for (int i = 0; i < lines.length; i++) {
			Object packet = this.getPacket(this.loc.getWorld(), displayLoc.getX(), displayLoc.getY(), displayLoc.getZ(),
					this.lines[i]);
			if (packet == null)
				return false;
			this.sendPacket(p, packet);
			displayLoc.add(0, -ABS, 0);
		}
		players.add(p);

		return true;
	}

	/**
	 * Destroy the hologram
	 */
	public void destroy() {
		try {
			int[] ints = new int[this.ids.size()];
			for (int j = 0; j < ints.length; j++) {
				ints[j] = ids.get(j);
			}
			Constructor<?> cw = destroy.getConstructor(new Class<?>[] { int[].class });
			Object packet = cw.newInstance(new Object[] { ints });
			for (Player p : players)
				sendPacket(p, packet);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			Main.error(e);
		}
	}

	private Object getPacket(World w, double x, double y, double z, String text) {
		try {
			Object craftWorldObj = craftWorld.cast(w);
			Method getHandleMethod = craftWorldObj.getClass().getMethod("getHandle", new Class<?>[0]);
			Object entityObject = armorStandConstructor
					.newInstance(new Object[] { getHandleMethod.invoke(craftWorldObj, new Object[0]) });
			Method getID = entityObject.getClass().getMethod("getId");
			ids.add((int) getID.invoke(entityObject));
			Method setCustomName = entityObject.getClass().getMethod("setCustomName", new Class<?>[] { String.class });
			setCustomName.invoke(entityObject, new Object[] { text });
			Method setCustomNameVisible = nmsEntity.getMethod("setCustomNameVisible", new Class[] { boolean.class });
			setCustomNameVisible.invoke(entityObject, new Object[] { true });
			Method setGravity = entityObject.getClass().getMethod("setGravity", new Class<?>[] { boolean.class });
			setGravity.invoke(entityObject, new Object[] { false });
			Method setLocation = entityObject.getClass().getMethod("setLocation",
					new Class<?>[] { double.class, double.class, double.class, float.class, float.class });
			setLocation.invoke(entityObject, new Object[] { x, y, z, 0.0F, 0.0F });
			Method setInvisible = entityObject.getClass().getMethod("setInvisible", new Class<?>[] { boolean.class });
			setInvisible.invoke(entityObject, new Object[] { true });
			Constructor<?> cw = packetClass.getConstructor(new Class<?>[] { entityLivingClass });
			Object packetObject = cw.newInstance(new Object[] { entityObject });
			return packetObject;
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			Main.error(e);
		}
		return null;
	}

	private void sendPacket(Player p, Object packet) {
		try {
			Method getHandle = p.getClass().getMethod("getHandle");
			Object entityPlayer = getHandle.invoke(p);
			Object pConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
			Method sendMethod = pConnection.getClass().getMethod("sendPacket", new Class[] { nmsPacket });
			sendMethod.invoke(pConnection, new Object[] { packet });
		} catch (Exception e) {
			Main.error(e);
		}
	}

}
