package me.breniim.bsmobcoins.utils.stackmobs;

import org.bukkit.Bukkit;

public class JHStackMobs {

	public static boolean JHStack;

	public void register() {
		if (Bukkit.getPluginManager().isPluginEnabled("JH_StackMobs")) {
			JHStack = true;
			Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §aJHStackMobs encontrado, ativando compatibilidade...");
		}
	}
}
