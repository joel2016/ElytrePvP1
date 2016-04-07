package me.joel.elytrapvp.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Item {

	private ItemStack i;
	private ItemMeta m;
	private List<String> l = new ArrayList<>();
	private byte dyecolor = 20;
	private byte potion = 0;
	private byte dataid = 0;
	private short dura = -1;
	private LeatherArmorMeta lc;
	private BookMeta bm;
	private String skull;

	/**
	 * Create a new Item with a definded Material and an amount
	 * 
	 * @param m
	 *            The Material of the item
	 * @param amount
	 *            The amount of the item
	 */
	public Item(Material m, int amount) {
		i = new ItemStack(m, amount);
		this.m = i.getItemMeta();
		if (isLeather())
			lc = (LeatherArmorMeta) i.getItemMeta();
		if (m == Material.WRITTEN_BOOK)
			bm = (BookMeta) i.getItemMeta();
	}

	/**
	 * Creates a new item obejct with an ItemStack
	 * 
	 * @param i
	 *            The ItemStack
	 */
	@SuppressWarnings("deprecation")
	public Item(ItemStack i) {
		this.i = i;
		if (i.getType() == Material.WRITTEN_BOOK)
			bm = (BookMeta) i.getItemMeta();
		if (isLeather())
			lc = (LeatherArmorMeta) i.getItemMeta();
		this.m = i.getItemMeta();
		this.l = i.getItemMeta().getLore();
		this.dataid = i.getData().getData();
		this.dura = i.getDurability();
	}

	/**
	 * Create a new Item with a defined Material amount of 1
	 * 
	 * @param m
	 *            The Material of the item
	 */
	public Item(Material m) {
		this(m, 1);
	}

	/**
	 * Give the item a displayname
	 * 
	 * @param name
	 *            The displayname of the item
	 */
	public void setName(String name) {
		m.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
	}

	/**
	 * Return the material of the item
	 * 
	 * @return The material of the item
	 */
	public Material getMaterial() {
		return i.getType();
	}

	/**
	 * Return the amount of the item
	 * 
	 * @return The amount of the item
	 */
	public int getAmount() {
		return i.getAmount();
	}

	/**
	 * Remove the lore
	 */
	public void removeLore() {
		l.clear();
	}

	/**
	 * Add Strings to the lore of the item
	 * 
	 * @param lore
	 *            Add to the lore of the item
	 */
	public void addLore(String... lore) {
		for (String s : lore)
			l.add(ChatColor.translateAlternateColorCodes('&', s));
	}

	/**
	 * Add Strings to the lore of the item
	 * 
	 * @param lore
	 *            Add to the lore of the item
	 */
	public void addLore(List<String> lore) {
		for (String s : lore)
			l.add(ChatColor.translateAlternateColorCodes('&', s));
	}

	/**
	 * Set the lore of the item Remove the old lore
	 * 
	 * @param lore
	 *            Set the lore of the item
	 */
	public void setLore(String... lore) {
		clearLore();
		for (String s : lore)
			l.add(ChatColor.translateAlternateColorCodes('&', s));
	}

	/**
	 * Set the lore of the item Remove the old lore
	 * 
	 * @param lore
	 *            Set the lore of the item
	 */
	public void setLore(List<String> lore) {
		l = lore;
	}

	/**
	 * Clear the lore
	 */
	public void clearLore() {
		if (l != null)
			l.clear();
		else
			l = new ArrayList<>();
	}

	public void setDurability(int uses) {
		this.dura = (short) uses;
	}

	/**
	 * Create a Player Skull Material must be Material.SKULL_ITEM
	 * 
	 * @param name
	 *            The name of the Player
	 */
	public void setSkullOwner(String name) {
		skull = name;
	}

	/**
	 * Set the material of the item
	 * 
	 * @param m
	 *            Material of the item
	 */
	public void setMaterial(Material m) {
		i.setType(m);
	}

	/**
	 * Set the amount of the item
	 * 
	 * @param amount
	 *            Amount of the item
	 */
	public void setAmount(int amount) {
		i.setAmount(amount);
	}

	/**
	 * Set the color of the item (Supported: wool, Stained Clay, Stained Glass
	 * and Carpet)
	 * 
	 * @param colorid
	 *            Color-id <a href="http://bit.ly/1lGeJgS">Click to see a
	 *            list</a>
	 */
	public void setColor(int colorid) {
		dyecolor = (byte) colorid;
	}

	/**
	 * Set the data of the item
	 * 
	 * @param data
	 *            Color-id and Data
	 */
	public void setData(int data) {
		dataid = (byte) data;
	}

	/**
	 * Returns the data ID
	 * 
	 * @return byte
	 */
	public byte getData() {
		return dataid;
	}

	/**
	 * Add an enchantment with a specific level
	 * 
	 * @param ench
	 *            Enchantment of the item
	 * @param level
	 *            The level of the item as an int
	 */
	public void addEnchantment(Enchantment ench, int level) {
		m.addEnchant(ench, level, true);
	}

	private boolean isLeather() {
		if (getMaterial() == Material.LEATHER_BOOTS || getMaterial() == Material.LEATHER_CHESTPLATE
				|| getMaterial() == Material.LEATHER_HELMET || getMaterial() == Material.LEATHER_LEGGINGS) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Set the color of the leather armor
	 * 
	 * @param c
	 *            Color of the leather armor
	 */
	public void setLeatherColor(Color c) {
		if (isLeather()) {
			lc.setColor(c);
		}
	}

	/**
	 * <b>Item material must be <code>Material.WRITTEN_BOOK</code></b>
	 * <p>
	 * Set the title of a book
	 * </p>
	 * 
	 * @param title
	 *            The title (max. length: 16 chars)
	 */
	public void setBookTitle(String title) {
		if (bm != null)
			bm.setTitle(ChatColor.translateAlternateColorCodes('&', title));
	}

	/**
	 * <b>Item material must be <code>Material.WRITTEN_BOOK</code></b>
	 * <p>
	 * Set the auhtor of a book
	 * </p>
	 * 
	 * @param author
	 *            The author of the book
	 */
	public void setBookAuthor(String author) {
		if (bm != null)
			bm.setAuthor(author);
	}

	/**
	 * <b>Item material must be <code>Material.WRITTEN_BOOK</code></b>
	 * <p>
	 * Set the pages of a book
	 * </p>
	 * 
	 * @param content
	 *            The content of the book in a list of Strings. Every entry will
	 *            be one page
	 */
	public void setBookPages(String... content) {
		int i = 0;
		for (String s : content) {
			content[i] = ChatColor.translateAlternateColorCodes('&', s);
			i++;
		}
		if (bm != null)
			bm.setPages(content);
	}

	/**
	 * <b>Item material must be <code>Material.WRITTEN_BOOK</code></b>
	 * <p>
	 * Sets the specified page in the book. Pages of the book must be
	 * contiguous.<br />
	 * The data can be up to 256 characters in length, additional characters are
	 * truncated.
	 * </p>
	 * 
	 * @param page
	 *            The page of the book
	 * @param content
	 *            The content of the page
	 */
	public void setBookPage(int page, String content) {
		if (bm != null)
			bm.setPage(page, ChatColor.translateAlternateColorCodes('&', content));
	}

	/**
	 * <b>Item material must be <code>Material.WRITTEN_BOOK</code></b> Add pages
	 * at the end of the book
	 * 
	 * @param content
	 *            The content of the book in a list of Strings. Every entry will
	 *            be one page
	 */
	public void addBookPage(String... content) {
		int i = 0;
		for (String s : content) {
			content[i] = ChatColor.translateAlternateColorCodes('&', s);
			i++;
		}
		if (bm != null)
			bm.addPage(content);
	}

	/**
	 * Set the the potion id of the potion
	 * <a href="http://bit.ly/1rqDJuE">IDs</a>
	 * 
	 * @param id
	 *            The id of the potion
	 */
	public void setPotion(int id) {
		potion = (byte) id;
	}

	/**
	 * The only way to get your Item to use it
	 * 
	 * @return The Item as an itemstack
	 */
	@SuppressWarnings("deprecation")
	public ItemStack getItem() {
		if (dyecolor < 20) {
			i = new ItemStack(i.getType(), i.getAmount(), dyecolor);
			i.getData().setData(dyecolor);
		}
		if (potion > 0) {
			i = new ItemStack(i.getType(), i.getAmount(), potion);
			i.getData().setData(potion);
		}
		if (dataid != 0) {
			i = new ItemStack(i.getType(), i.getAmount(), dataid);
			i.getData().setData(dataid);
		}
		m.setLore(l);
		if (skull != null && i.getType() == Material.SKULL_ITEM) {
			dataid = 3;
			SkullMeta meta = (SkullMeta) i.getItemMeta();
			meta.setOwner(skull);
			meta.setDisplayName(m.getDisplayName());
			meta.setLore(l);
			i.setItemMeta(meta);
			m = i.getItemMeta();
		} else if (isLeather()) {
			lc.setDisplayName(m.getDisplayName());
			lc.setLore(l);
			i.setItemMeta(lc);
		} else if (bm != null) {
			bm.setDisplayName(m.getDisplayName());
			bm.setLore(l);
			i.setItemMeta(bm);
		} else {
			i.setItemMeta(m);
		}
		if (dura >= 0) {
			i.setDurability(dura);
		}
		return i;
	}

}