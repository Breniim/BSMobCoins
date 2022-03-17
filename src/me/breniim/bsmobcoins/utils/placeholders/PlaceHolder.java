package me.breniim.bsmobcoins.utils.placeholders;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.breniim.bsmobcoins.Main;
import me.breniim.bsmobcoins.API.mobcoins.MobCoinsAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceHolder extends PlaceholderExpansion {
	
	//{placeholderapi_BSMobCoins_bsmobcoins}

	@SuppressWarnings("unused")
	private static Main plugin;

	@Override
	public String getRequiredPlugin() {
		return "BSMobCoins";
	}

	@Override
	public String getAuthor() {
		return "Breniim";
	}

	@Override
	public String getIdentifier() {
		return "BSMobCoins";
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}

	@Override
	public boolean canRegister() {
		return (plugin = (Main) Bukkit.getPluginManager().getPlugin(getRequiredPlugin())) != null;
	}

	@Override
	public String onRequest(OfflinePlayer player, String identifier) {
		if (identifier.equalsIgnoreCase("bsmobcoins")) {
			return String.valueOf(MobCoinsAPI.getMobCoins(player.getName()));
		}
		return null;
	}
	
}
