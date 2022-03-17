package me.breniim.bsmobcoins.API.mobcoins;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.breniim.bsmobcoins.Main;
import me.breniim.bsmobcoins.connections.Methods;
import me.breniim.bsmobcoins.utils.Actionbar;
import me.breniim.bsmobcoins.utils.Messages;

public class MobCoinsAPI {

	public static HashMap<String, Double> mobcoins = new HashMap<>();
	public static boolean chat;

	public static boolean containsPlayer(String name) {
		return mobcoins.containsKey(name);
	}

	public static boolean containsMobCoins(String name, double value) {
		if (getMobCoins(name) >= value) {
			return false;
		} else {
			return true;
		}
	}

	public static Double getMobCoins(String name) {
		return mobcoins.get(name);
	}

	public static void setMobCoins(String name, Double value) {
		mobcoins.put(name, value);
	}

	public static void addMobCoins(String name, Double value) {
		setMobCoins(name, getMobCoins(name) + value);
	}

	public static void removeMobCoins(String name, Double value) {
		setMobCoins(name, getMobCoins(name) - value);
	}

	public static void atualizador() {
		new BukkitRunnable() {

			@Override
			public void run() {
				for (String player : mobcoins.keySet()) {
					if (Methods.containsPlayer(player)) {
						Methods.setMobCoins(player, getMobCoins(player));
					} else {
						Methods.setPlayer(player);
						Methods.setMobCoins(player, getMobCoins(player));
					}
				}
				Bukkit.getConsoleSender().sendMessage("§b[MobCoins] §aMobCoins salvos no banco de dados com sucesso");
			}
		}.runTaskTimerAsynchronously(Main.m, 20L, 20 * 300);
	}

	private static double getMultiplicador(Player p, double multiplicador) {
		ItemStack itemNaMao = p.getItemInHand();
		if ((itemNaMao == null) || (itemNaMao.getType() == Material.AIR))
			return 0;
		int nivel = itemNaMao.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);
		if (nivel == 0) {
			return multiplicador;
		}
		return nivel * multiplicador;
	}

	public static double multiplicador(Player p, double valor) {
		FileConfiguration configs = Main.config.getConfig();
		String permissao = null;
		Double multiplicador = 0.0;
		for (String cfg : configs.getConfigurationSection("Ranks.").getKeys(false)) {
			permissao = configs.getString("Ranks." + cfg + ".Permissions");
			multiplicador = configs.getDouble("Ranks." + cfg + ".Bonus");
		}
		if (p.hasPermission(permissao)) {
			mobcoins.put(p.getName(), getMobCoins(p.getName()) + valor * getMultiplicador(p, multiplicador));
			Actionbar.sendActionbar(p, Messages.mobcoinsactionbar.replace("{value}", String.valueOf(valor))
					.replace("{multiplier}", String.valueOf(multiplicador)));
			if (MobCoinsAPI.chat) {
				p.sendMessage(Messages.mobcoinschat.replace("{value}",
						String.valueOf(valor * getMultiplicador(p, multiplicador))));
			}
		} else {
			p.sendMessage(Messages.dontperm);
		}
		return 0.0;
	}
}
