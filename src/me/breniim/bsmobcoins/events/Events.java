package me.breniim.bsmobcoins.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import me.breniim.bsmobcoins.Main;
import me.breniim.bsmobcoins.API.mobcoins.MobCoinsAPI;
import me.breniim.bsmobcoins.API.mobcoins.MobCoinsInventario;
import me.breniim.bsmobcoins.connections.Methods;
import me.breniim.bsmobcoins.utils.Messages;

public class Events implements Listener {

	private static FileConfiguration config = Main.config.getConfig();
	private static FileConfiguration shop = Main.itens.getConfig();

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = (Player) e.getPlayer();
		if (!MobCoinsAPI.containsPlayer(p.getName())) {
			MobCoinsAPI.mobcoins.put(p.getName(), Double.valueOf(0));
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = (Player) e.getPlayer();
		if (!Methods.containsPlayer(p.getName())) {
			Methods.setPlayer(p.getName());
			Methods.setMobCoins(p.getName(), MobCoinsAPI.getMobCoins(p.getName()));
		} else {
			Methods.setMobCoins(p.getName(), MobCoinsAPI.getMobCoins(p.getName()));
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equals(config.getString("Store.Title").replace("&", "§"))) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null)
				return;
			if (e.getCurrentItem().getType() == Material.AIR)
				return;
			if (e.getCurrentItem().getItemMeta().getDisplayName() == null)
				return;
			for (String item : shop.getConfigurationSection("Store.Itens.").getKeys(false)) {

				if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equals(shop.getString("Store.Itens." + item + ".Name").replace("&", "§"))) {
					double valor = shop.getDouble("Store.Itens." + item + ".Price");
					if (MobCoinsAPI.containsMobCoins(p.getName(), valor)) {
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.BAT_DEATH, 1F, 1F);
						p.sendMessage(Messages.dontbuy);
						return;
					}
					if (!(p.getInventory().firstEmpty() != -1)) {
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.BAT_DEATH, 1F, 1F);
						p.sendMessage(Messages.dontspace);
						return;
					}
					ArrayList<String> lore = new ArrayList<>();
					for (String linha : shop.getStringList("Store.Itens." + item + ".Lore")) {
						lore.add(linha.replace("&", "§").replace("%mobcoins%",
								String.valueOf(MobCoinsAPI.getMobCoins(p.getName()))));
					}
					String material = shop.getString("Store.Itens." + item + ".Material");
					String nome = shop.getString("Store.Itens." + item + ".Name");
					int quantidade = shop.getInt("Store.Itens." + item + ".Amount");
					int durabilidade = shop.getInt("Store.Itens." + item + ".Data");
					Enchantment encantamento = null;
					int nivel = 0;
					boolean enchantuse = shop.getBoolean("Store.Itens." + item + ".Enchantments-Use");
					List<String> comando = shop.getStringList("Store.Itens." + item + ".Commands");
					boolean illustrative = shop.getBoolean("Store.Itens." + item + ".Illustrative-item");
					ItemStack itens;
					for (String encantar : shop.getConfigurationSection("Store.Itens." + item + ".Enchantments.")
							.getKeys(false)) {
							encantamento = Enchantment.getByName(
									shop.getString("Store.Itens." + item + ".Enchantments." + encantar + ".Type"));
							nivel = shop.getInt("Store.Itens." + item + ".Enchantments." + encantar + ".Level");
					}
					if (illustrative) {
						for (String comandos : comando) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
									comandos.replace("{player}", p.getName()));
						}
						MobCoinsAPI.removeMobCoins(p.getName(), valor);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 1F);
						p.closeInventory();
						p.sendMessage(Messages.buy);
						return;
					}
					for (String comandos : comando) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comandos.replace("{player}", p.getName()));
					}
					if (enchantuse) {
						itens = MobCoinsInventario.add(Material.getMaterial(material), nome.replace("&", "§"), lore,
								quantidade, durabilidade, encantamento, nivel);
					} else {
						itens = MobCoinsInventario.add(Material.getMaterial(material), nome.replace("&", "§"), lore,
								quantidade, durabilidade);
					}
					MobCoinsAPI.removeMobCoins(p.getName(), valor);
					p.getInventory().addItem(itens);
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 1F);
					p.closeInventory();
					p.sendMessage(Messages.buy);
				}
			}
		}
	}
}
