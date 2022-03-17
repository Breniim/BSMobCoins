package me.breniim.bsmobcoins.utils.placeholders;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import me.breniim.bsmobcoins.Main;
import me.breniim.bsmobcoins.API.mobcoins.MobCoinsAPI;

public class MVdWPlaceholderAPI {

	public void register() {

		if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
			Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §aMVdWPlaceholderAPI encontrado, registrando placeholder...");
			PlaceholderAPI.registerPlaceholder(Main.m, "bsmobcoins", new PlaceholderReplacer() {
				@Override
				public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
					Player player = e.getPlayer();
					return String.valueOf(MobCoinsAPI.getMobCoins(player.getName()));
				}
			});
		}else {
			Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §cMVdWPlaceholderAPI não encontrado, placeholder não registrada");
		}
	}
}
