package me.breniim.bsmobcoins.API.mobcoins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.breniim.bsmobcoins.Main;

public class MobCoinsInventario {

	private static FileConfiguration config = Main.config.getConfig();
	private static FileConfiguration shop = Main.itens.getConfig();
	private static Map<Enchantment, Integer> enchants = new HashMap<>();

	public static void MobCoinsInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, config.getInt("Store.Size"),
				config.getString("Store.Title").replace("&", "§"));

		for (String item : shop.getConfigurationSection("Store.Itens.").getKeys(false)) {
			ArrayList<String> lore = new ArrayList<>();
			for (String linha : shop.getStringList("Store.Itens." + item + ".Lore")) {
				lore.add(linha.replace("&", "§").replace("%mobcoins%",
						String.valueOf(MobCoinsAPI.getMobCoins(p.getName()))));
			}
			int slot = shop.getInt("Store.Itens." + item + ".Slot");
			String material = shop.getString("Store.Itens." + item + ".Material");
			String nome = shop.getString("Store.Itens." + item + ".Name").replace("&", "§");
			int quantidade = shop.getInt("Store.Itens." + item + ".Amount");
			int durabilidade = shop.getInt("Store.Itens." + item + ".Data");
			boolean enchantuse = shop.getBoolean("Store.Itens." + item + ".Enchantments-Use");
			if (enchantuse) {
				for (String encantar : shop.getConfigurationSection("Store.Itens." + item + ".Enchantments.")
						.getKeys(false)) {
					String encantamento = shop.getString("Store.Itens." + item + ".Enchantments." + encantar + ".Type");
					int nivel = config.getInt("Store.Itens." + item + ".Enchantments." + encantar + ".Level");
					inv.setItem(slot, add(Material.getMaterial(material), nome, lore, quantidade, durabilidade,
							Enchantment.getByName(encantamento.toUpperCase()), nivel));
				}
			} else {
				inv.setItem(slot, add(Material.getMaterial(material), nome, lore, quantidade, durabilidade));
			}
		}
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 1F);
		p.openInventory(inv);
	}

	public static ItemStack add(Material m, String nome, ArrayList<String> lore, int quantidade, int durabilidade) {
		ItemStack item = new ItemStack(m, quantidade);
		item.setDurability((short) durabilidade);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, ArrayList<String> lore, int quantidade, int durabilidade,
			Enchantment ench, int level) {
		ItemStack it = new ItemStack(m, quantidade);
		it.setDurability((short) durabilidade);
		setEnchant();
		for (String item : shop.getConfigurationSection("Store.Itens.").getKeys(false)) {
			for (String encantar : shop.getConfigurationSection("Store.Itens." + item + ".Enchantments.")
					.getKeys(false)) {
				String type = "Store.Itens." + item + ".Enchantments." + encantar + ".Type";
				String nivel = "Store.Itens." + item + ".Enchantments." + encantar + ".Level";
				if (shop.contains(type) && shop.contains(nivel)) {
					it.addUnsafeEnchantments(enchants);
				} else {
					Bukkit.getConsoleSender().sendMessage("§4[ERROR] Check item enchantments");
				}
			}
		}
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore(lore);
		it.setItemMeta(meta);
		return it;
	}

	private static void setEnchant() {
		for (String item : shop.getConfigurationSection("Store.Itens.").getKeys(false)) {
			for (String encantar : shop.getConfigurationSection("Store.Itens." + item + ".Enchantments.")
					.getKeys(false)) {
				String encantamento = shop.getString("Store.Itens." + item + ".Enchantments." + encantar + ".Type");
				int nivel = shop.getInt("Store.Itens." + item + ".Enchantments." + encantar + ".Level");
				enchants.put(Enchantment.getByName(encantamento), nivel);
			}
		}
	}

}
