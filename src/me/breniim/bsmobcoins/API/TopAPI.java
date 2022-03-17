package me.breniim.bsmobcoins.API;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.breniim.bsmobcoins.Main;
import me.breniim.bsmobcoins.connections.Methods;
import me.breniim.bsmobcoins.utils.Messages;

public class TopAPI {

	private static HashMap<OfflinePlayer, Double> top = new HashMap<>();
	private static List<Entry<OfflinePlayer, Double>> valores;

	public static void TopChat(CommandSender sender) {

		Stream<Entry<OfflinePlayer, Double>> ordenadas = top.entrySet().stream()
				.sorted((x, y) -> y.getValue().compareTo(x.getValue()));
		valores = ordenadas.collect(Collectors.toList());
		int id = 1;

		sender.sendMessage(Messages.toptitle);
		sender.sendMessage("");
		for (Entry<OfflinePlayer, Double> entrada : valores) {
			OfflinePlayer jogador = entrada.getKey();
			Double valor = entrada.getValue();
			sender.sendMessage(Messages.topmessage.replace("{rank}", String.valueOf(id))
					.replace("{player}", jogador.getName()).replace("{value}", String.valueOf(valor)));
			id++;
			if (id > 10)
				break;
		}

	}

	public static void atualizador() {

		new BukkitRunnable() {

			@Override
			public void run() {
				for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
					top.put(p, Methods.getMobCoins(p.getName()));
				}
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.sendMessage(Messages.topmobcoins);
				}
			}
		}.runTaskTimerAsynchronously(Main.m, 20L, 20 * 600);

		Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §aMobCoinsTOP started");
	}

}
