package me.joel.elytrapvp.utils;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemCreator {
	
	public static ItemStack CreateItemwithID(int id, int subid, int amount, String DisplayName,
			ArrayList<String> lore) {

		@SuppressWarnings("deprecation")
		ItemStack is = new ItemStack(id, amount, (short) subid);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(DisplayName);
		im.setLore(lore);
		is.setItemMeta(im);
		return is;

	}

	public static ItemStack CreateItemwithMaterial(Material m, int subid, int amount, String DisplayName,
			ArrayList<String> lore) {
		ItemStack is = new ItemStack(m, amount, (short) subid);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(DisplayName);
		im.setLore(lore);
		is.setItemMeta(im);
		return is;

	}
}