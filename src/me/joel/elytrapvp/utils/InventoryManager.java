package me.joel.elytrapvp.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.joel.elytrapvp.Game;
import me.joel.elytrapvp.language.Messages;

public class InventoryManager {

	public static void open(Player p, String titleKey, ArrayList<ItemStack> contents) {
		open(p, titleKey, contents.toArray(new ItemStack[contents.size()]));
	}

	public static void open(Player p, String titleKey, ItemStack[] contents) {
		int size = getSize(contents.length);
		final Inventory inv = Bukkit.createInventory(p, size, ChatColor.AQUA + Messages.msg(p, titleKey));
		for (int i = 0; i < contents.length; i++)
			inv.setItem(i, contents[i]);
		p.openInventory(inv);
	}

	public static ItemStack getInfo(Player p, Game g, String lvl) {
		Item i = new Item(Material.SIGN);
		i.setName(ChatColor.GOLD + Messages.msg(p, "where"));
		i.addLore(ChatColor.GRAY + g.getName(), ChatColor.GRAY + lvl);
		return i.getItem();
	}

	public static ItemStack getBlack() {
		Item i = new Item(Material.STAINED_GLASS_PANE);
		i.setColor(15);
		i.setName(" ");
		return i.getItem();
	}

	public static ItemStack getWhite() {
		Item i = new Item(Material.STAINED_GLASS_PANE);
		i.setName(" ");
		return i.getItem();
	}

	public static ItemStack getBack(Player p) {
		Item i = new Item(Material.SLIME_BALL);
		i.setName(ChatColor.RED + "< " + ChatColor.GREEN + Messages.msg(p, "back"));
		return i.getItem();
	}

	private static int getSize(int amount) {
		if (amount == 0)
			return 9;
		if (amount % 9 == 0)
			return amount;
		amount = amount / 9;
		amount = amount * 9;
		return amount + 9;
	}

}
