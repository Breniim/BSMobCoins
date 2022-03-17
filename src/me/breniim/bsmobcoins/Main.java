package me.breniim.bsmobcoins;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.breniim.bsmobcoins.API.TopAPI;
import me.breniim.bsmobcoins.API.mobcoins.MobCoinsAPI;
import me.breniim.bsmobcoins.API.npc.NpcUpdate;
import me.breniim.bsmobcoins.API.npc.NpcUtils;
import me.breniim.bsmobcoins.commands.Commands;
import me.breniim.bsmobcoins.connections.Connections;
import me.breniim.bsmobcoins.connections.Methods;
import me.breniim.bsmobcoins.events.BoosterEvents;
import me.breniim.bsmobcoins.events.CheckEvent;
import me.breniim.bsmobcoins.events.Events;
import me.breniim.bsmobcoins.events.NPCEvent;
import me.breniim.bsmobcoins.events.PlayerEvents;
import me.breniim.bsmobcoins.utils.B_Config;
import me.breniim.bsmobcoins.utils.placeholders.MVdWPlaceholderAPI;
import me.breniim.bsmobcoins.utils.placeholders.PlaceHolder;
import me.breniim.bsmobcoins.utils.stackmobs.JHStackMobs;

public class Main extends JavaPlugin{

	public static Main m;
	public static B_Config config;
	public static B_Config itens;

	@Override
	public void onLoad() {
		m = this;
		config = new B_Config(this, "config.yml");
		itens = new B_Config(this, "itens.yml");
	}

	@Override
	public void onEnable() {
		config.saveDefaultConfig();
		itens.saveDefaultConfig();
		dependencies();
		registerCommands();
		registerEvents();
		Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §aMobCoins habilitado");
		Bukkit.getScheduler().runTaskLater(this, () -> new NpcUpdate().Update(this, new NpcUtils()), 20L);
		Connections.openConnection();
		TopAPI.atualizador();
		MobCoinsAPI.atualizador();
		startMobCoins();
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §aMobCoins desabilitado");
		saveMobCoins();
		Connections.closeConnection();
	}

	void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		Bukkit.getPluginManager().registerEvents(new BoosterEvents(), this);
		Bukkit.getPluginManager().registerEvents(new NPCEvent(), this);
		Bukkit.getPluginManager().registerEvents(new CheckEvent(), this);
	}

	void registerCommands() {
		getCommand("mobcoins").setExecutor(new Commands());
	}

	void saveMobCoins() {
		for (String player : MobCoinsAPI.mobcoins.keySet()) {
			Methods.setMobCoins(player, MobCoinsAPI.getMobCoins(player));
		}
	}

	void startMobCoins() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (Methods.containsPlayer(player.getName())) {
				MobCoinsAPI.mobcoins.put(player.getName(), Methods.getMobCoins(player.getName()));
			}
		}
		for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
			if (Methods.containsPlayer(player.getName())) {
				MobCoinsAPI.mobcoins.put(player.getName(), Methods.getMobCoins(player.getName()));
			}
		}
		Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §aJogadores carregados com sucesso");
	}

	void dependencies() {
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			Bukkit.getConsoleSender()
					.sendMessage("§b[BSMobCoins] §aPlaceholderAPI encontrado, registrando placeholder...");
			new PlaceHolder().register();
		} else {
			Bukkit.getConsoleSender()
					.sendMessage("§b[BSMobCoins] §cPlaceholderAPI não encontrado, placeholder não registrada");
		}
		new MVdWPlaceholderAPI().register();
		new JHStackMobs().register();
	}
}
