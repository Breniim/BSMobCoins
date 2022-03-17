package me.breniim.bsmobcoins.utils.ItemBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class B_Create {
	public static ItemStack add(Material m) {
		return new ItemStack(m);
	}

	public static ItemStack add(Material m, int quantidade) {
		return new ItemStack(m, quantidade);
	}

	public static ItemStack add(Material m, int quantidade, int durabilidade) {
		ItemStack item = new ItemStack(m, quantidade);
		item.setDurability((short) durabilidade);
		ItemMeta meta = item.getItemMeta();
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome) {
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack hideAttributes(Material m, String nome) {
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add1(Material m, String nome, int quantidade) {
		ItemStack item = new ItemStack(m, quantidade);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add1(Material m, String nome, int quantidade, int durabilidade) {
		ItemStack item = new ItemStack(m, quantidade);
		item.setDurability((short) durabilidade);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add1(Material m, String nome, String[] lore, int quantidade, int durabilidade) {
		ItemStack item = new ItemStack(m, quantidade);
		item.setDurability((short) durabilidade);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, Enchantment ench, int level) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(ench, level);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add2(Material m, String nome, Enchantment ench, int level, Enchantment ench2, int level2) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(ench, level);
		item.addUnsafeEnchantment(ench2, level2);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add2(Material m, String nome, String[] lore, Enchantment ench, int level, Enchantment ench2,
			int level2) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(ench, level);
		item.addUnsafeEnchantment(ench2, level2);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add3(Material m, String nome, Enchantment ench, int level, Enchantment ench2, int level2,
			Enchantment ench3, int level3) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(ench, level);
		item.addUnsafeEnchantment(ench2, level2);
		item.addUnsafeEnchantment(ench3, level3);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add3(Material m, String nome, String[] lore, Enchantment ench, int level, Enchantment ench2,
			int level2, Enchantment ench3, int level3) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(ench, level);
		item.addUnsafeEnchantment(ench2, level2);
		item.addUnsafeEnchantment(ench3, level3);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add4(Material m, String nome, Enchantment ench, int level, Enchantment ench2, int level2,
			Enchantment ench3, int level3, Enchantment ench4, int level4) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(ench, level);
		item.addUnsafeEnchantment(ench2, level2);
		item.addUnsafeEnchantment(ench3, level3);
		item.addUnsafeEnchantment(ench4, level4);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, Enchantment ench, int level, int quantidade) {
		ItemStack item = new ItemStack(m, quantidade);
		item.addUnsafeEnchantment(ench, level);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, int durability) {
		ItemStack item = new ItemStack(m);
		item.setDurability((short) durability);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, int durability, String[] lore) {
		ItemStack item = new ItemStack(m);
		item.setDurability((short) durability);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, int quantidade, String nada) {
		ItemStack item = new ItemStack(m);
		item.setAmount(quantidade);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(String nome, String dono, String[] lore) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1);
		item.setDurability((short) 3);
		SkullMeta skull = (SkullMeta) item.getItemMeta();
		skull.setDisplayName(nome);
		skull.setOwner(dono);
		skull.setLore(Arrays.asList(lore));
		item.setItemMeta(skull);
		return item;
	}

	public static ItemStack add(Material m, String nome, String[] lore, int durability) {
		ItemStack item = new ItemStack(m);
		item.setDurability((short) durability);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, String[] lore) {
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, ArrayList<String> lore) {
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, String[] lore, Enchantment enchant, int lvl) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(enchant, lvl);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, String[] lore, Enchantment enchant1, int lvl1,
			Enchantment enchant2, int lvl2) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(enchant1, lvl1);
		item.addUnsafeEnchantment(enchant2, lvl2);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, String[] lore, Enchantment enchant1, int lvl1,
			Enchantment enchant2, int lvl2, Enchantment enchant3, int lvl3) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(enchant1, lvl1);
		item.addUnsafeEnchantment(enchant2, lvl2);
		item.addUnsafeEnchantment(enchant3, lvl3);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack potion(PotionEffectType efeito, String nome, String[] lore) {
		ItemStack item = new ItemStack(Material.POTION);
		PotionMeta eta = (PotionMeta) item.getItemMeta();
		eta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 9), true);
		eta.setLore(Arrays.asList(lore));
		eta.setDisplayName(nome);
		item.setItemMeta(eta);
		return item;
	}

	public static ItemStack couro() {
		ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.BLUE);
		meta.setDisplayName("�bArmadura de Couro");
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack couro(Color cor, String nome) {
		ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(cor);
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getNamedSkull(String nick, String nome, ArrayList<String> lore) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore(lore);
		meta.setOwner(nick);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		skull.setItemMeta(meta);
		return skull;
	}

	public static ItemStack getNamedSkull(String nick, String nome, String[] lore) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore(Arrays.asList(lore));
		meta.setOwner(nick);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		skull.setItemMeta(meta);
		return skull;
	}

	public static ItemStack getCustomHead(String name, String url, ArrayList<String> lore) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		if (url == null || url.isEmpty())
			return skull;
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		if (url.length() < 16) {
			meta.setOwner(url);
			meta.setDisplayName(name);
			meta.setLore(lore);
			skull.setItemMeta(meta);
			return skull;
		}
		StringBuilder s_url = new StringBuilder();
		s_url.append("https://textures.minecraft.net/texture/").append(url);

		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		byte[] data = Base64.getEncoder()
				.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", s_url.toString()).getBytes());
		profile.getProperties().put("textures", new Property("textures", new String(data)));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		} catch (Exception e) {
			e.printStackTrace();
			return skull;
		}
		meta.setLore(lore);
		meta.setDisplayName(name);
		skull.setItemMeta(meta);
		return skull;
	}

	public static ItemStack outro(Material m, String nome, Enchantment enchant, int lvl) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(enchant, lvl);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack outro(Material m, String nome, Enchantment enchant1, int lvl1, Enchantment enchant2,
			int lvl2) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(enchant1, lvl1);
		item.addUnsafeEnchantment(enchant2, lvl2);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack outro(Material m, String nome, Enchantment enchant1, int lvl1, Enchantment enchant2,
			int lvl2, Enchantment enchant3, int lvl3) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(enchant1, lvl1);
		item.addUnsafeEnchantment(enchant2, lvl2);
		item.addUnsafeEnchantment(enchant3, lvl3);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getSpawner(String nome, int amount, EntityType type) {
		ItemStack item = new ItemStack(Material.MOB_SPAWNER, amount);
		List<String> lore = new ArrayList<String>();

		String loreString = type.toString();
		loreString = loreString.substring(0, 1).toUpperCase() + loreString.substring(1).toLowerCase();
		loreString = loreString + " Spawner";
		lore.add(loreString);

		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);
		meta.setDisplayName(nome);
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack cap(Material m, int durabilidade) {
		ItemStack item = new ItemStack(m);
		item.setDurability((short) durabilidade);
		return item;
	}

}