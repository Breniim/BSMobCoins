package me.breniim.bsmobcoins.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.breniim.bsmobcoins.API.BoosterAPI;
import me.breniim.bsmobcoins.API.CheckAPI;
import me.breniim.bsmobcoins.API.TopAPI;
import me.breniim.bsmobcoins.API.mobcoins.MobCoinsAPI;
import me.breniim.bsmobcoins.API.mobcoins.MobCoinsInventario;
import me.breniim.bsmobcoins.API.npc.NpcAPI;
import me.breniim.bsmobcoins.utils.Messages;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

		if (cmd.getName().equals("mobcoins")) {
			if (args.length == 0) {
				if (!(sender instanceof Player)) {
					return true;
				}
				Player p = (Player) sender;
				p.sendMessage(
						Messages.mobcoins.replace("{mobcoins}", String.valueOf(MobCoinsAPI.getMobCoins(p.getName()))));
				return true;
			}
			if (args.length == 1) {
				if (args[0].equals("shop") || args[0].equals("loja")) {
					if (!(sender instanceof Player)) {
						return true;
					}
					Player p = (Player) sender;
					MobCoinsInventario.MobCoinsInv(p);
				} else if (args[0].equals("chat")) {
					if (!(sender instanceof Player)) {
						return true;
					}
					Player p = (Player) sender;
					if (MobCoinsAPI.chat) {
						p.sendMessage(Messages.chatoff);
						MobCoinsAPI.chat = false;
					} else {
						p.sendMessage(Messages.chaton);
						MobCoinsAPI.chat = true;
					}
				} else if (args[0].equals("add") || args[0].equals("set") || args[0].equals("remove")) {
					if (sender.hasPermission("bsmobcoins.admin")) {
						sender.sendMessage("§b[BSMobCoins] §c/mobcoins add/set/remove {nick} {amount}");
					}
				} else if (args[0].equals("booster")) {
					if (!(sender instanceof Player)) {
						return true;
					}
					Player p = (Player) sender;
					BoosterAPI.BoosterInv(p);
				} else if (args[0].equals("removeall")) {
					if (sender.hasPermission("bsmobcoins.admin")) {
						sender.sendMessage("§b[BSMobCoins] §c/mobcoins removeall {nick}");
						sender.sendMessage("§b[BSMobCoins] §cThe player is missing");
					}
				} else if (args[0].equals("top")) {
					TopAPI.TopChat(sender);
				} else if (args[0].equals("setnpc")) {
					if (sender.hasPermission("bsmobcoins.admin")) {
						if (!(sender instanceof Player)) {
							return true;
						}
						Player p = (Player) sender;
						NpcAPI.createNpc(p, p.getLocation());
					}
				} else if (args[0].equals("removenpc")) {
					if (sender.hasPermission("bsmobcoins.admin")) {
						if (!(sender instanceof Player)) {
							return true;
						}
						Player p = (Player) sender;
						NpcAPI.deleteNpc(p);
					}
				} else if (args[0].equals("check") || (args[0].equals("cheque"))) {
					if (sender.hasPermission("bsmobcoins.admin")) {
						sender.sendMessage("§b[BSMobCoins] §c/mobcoins check {quantia}");
						sender.sendMessage("§b[BSMobCoins] §cThe value is missing");
					}
				} else {
					sender.sendMessage(Messages.wrongcommand);
				}
			}
			if (args.length == 2) {
				if (sender.hasPermission("bsmobcoins.admin")) {
					if (args[0].equals("add") || args[0].equals("set") || args[0].equals("remove")) {
						sender.sendMessage("§b[BSMobCoins] §cQuantity is missing");
					} else if (args[0].equals("removeall")) {
						Player target = Bukkit.getPlayerExact(args[1]);
						if (target == null || !MobCoinsAPI.containsPlayer(target.getName())) {
							sender.sendMessage(Messages.playernfound);
							return true;
						}
						Double valor = 0.0;
						MobCoinsAPI.setMobCoins(target.getName(), 0.0);
						target.sendMessage(Messages.setsuccess.replace("{staffer}", sender.getName())
								.replace("{amount}", String.valueOf(valor)));
					} else if (args[0].equals("check") || (args[0].equals("cheque"))) {
						if (!(sender instanceof Player)) {
							return true;
						}
						Player p = (Player) sender;
						Double valor = Double.valueOf(args[1]);
						if (MobCoinsAPI.containsMobCoins(p.getName(), valor)) {
							p.sendMessage(Messages.checkdontmoney);
							return true;
						}
						MobCoinsAPI.mobcoins.put(p.getName(), MobCoinsAPI.getMobCoins(p.getName()) - valor);
						CheckAPI.cheque(p, valor);
					}
				}
			}
			if (args.length == 3) {
				if (sender.hasPermission("bsmobcoins.admin")) {
					if (args[0].equals("add")) {
						Player target = Bukkit.getPlayer(args[1]);
						if (target == null || !MobCoinsAPI.containsPlayer(target.getName())) {
							sender.sendMessage(Messages.playernfound);
							return true;
						}
						try {
							Double valor = Double.valueOf(args[2]);
							MobCoinsAPI.addMobCoins(target.getName(), valor);
							target.sendMessage(Messages.addsuccess.replace("{staffer}", sender.getName())
									.replace("{amount}", String.valueOf(valor)));
							sender.sendMessage("§b[BSMobCoins] §aYou added §f" + valor + " §amobcoins to player §f"
									+ target.getName());
						} catch (Exception e) {
							sender.sendMessage(Messages.wrongnumber);
						}
					} else if (args[0].equals("set")) {
						Player target = Bukkit.getPlayer(args[1]);
						if (target == null || !MobCoinsAPI.containsPlayer(target.getName())) {
							sender.sendMessage(Messages.playernfound);
							return true;
						}
						try {
							Double valor = Double.valueOf(args[2]);
							if (valor < 0) {
								sender.sendMessage(Messages.wrongnumber);
								return true;
							}
							MobCoinsAPI.setMobCoins(target.getName(), valor);
							target.sendMessage(Messages.setsuccess.replace("{staffer}", sender.getName())
									.replace("{amount}", String.valueOf(valor)));
							sender.sendMessage("§b[BSMobCoins] §aYou set §f" + valor + " §amobcoins to player §f"
									+ target.getName());
						} catch (Exception e) {
							sender.sendMessage(Messages.wrongnumber);
						}
					} else if (args[0].equals("remove")) {
						Player target = Bukkit.getPlayer(args[1]);
						if (target == null || !MobCoinsAPI.containsPlayer(target.getName())) {
							sender.sendMessage(Messages.playernfound);
							return true;
						}
						try {
							Double valor = Double.valueOf(args[2]);
							if (MobCoinsAPI.getMobCoins(target.getName()) - valor < 0) {
								sender.sendMessage(Messages.quantitynegative);
								return true;
							}
							MobCoinsAPI.removeMobCoins(target.getName(), valor);
							target.sendMessage(Messages.removesuccess.replace("{staffer}", sender.getName())
									.replace("{amount}", String.valueOf(valor)));
							sender.sendMessage("§b[BSMobCoins] §aYou removed §f" + valor + " §amobcoins to player §f"
									+ target.getName());
						} catch (Exception e) {
							sender.sendMessage(Messages.wrongnumber);
						}
					}
				}
			}
			if (args.length >= 4) {
				return true;
			}
		}
		return false;
	}
}
