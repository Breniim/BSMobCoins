package me.breniim.bsmobcoins.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.breniim.bsmobcoins.Main;
import me.breniim.bsmobcoins.utils.Messages;

public class BoosterEvents implements Listener {

	private static FileConfiguration config = Main.config.getConfig();
	public static List<String> booster = new ArrayList<>();
	public static Map<Player, String> status = new HashMap<>();
	public static Map<Player, Integer> multiplicador = new HashMap<>();
	public static List<Player> ativado = new ArrayList<>();

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equals(config.getString("Booster.Inventory-Name").replace("&", "§"))) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null)
				return;
			if (e.getCurrentItem().getType() == Material.AIR)
				return;
			if (e.getCurrentItem().getItemMeta().getDisplayName() == null)
				return;
			String clicado = e.getCurrentItem().getItemMeta().getDisplayName();
			for (String item : config.getConfigurationSection("Booster.Boosters.").getKeys(false)) {

				clicado = config.getString("Booster.Boosters." + item + ".Name").replace("&", "§");
				String key = config.getString("Booster.Boosters." + item + ".Key");
				int multipli = config.getInt("Booster.Boosters." + item + ".Multiplier");
				String permissao = Main.config.getConfig().getString("Booster.Boosters." + item + ".Permission");
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(clicado)) {
					if (p.hasPermission(permissao)) {
						if (ativado.contains(p)) {
							disable(p, key, multipli);
						} else {
							enable(p, key, multipli);
						}
					} else {
						p.closeInventory();
						p.sendMessage(Messages.boosternotperm);
						p.playSound(p.getLocation(), Sound.BAT_HURT, 1F, 1F);
					}
				}
			}
		}
	}

	private static void enable(Player p, String key, Integer multipli) {
		if (status.containsKey(p) && status.get(p) != key && status.get(p) != null) {
			p.closeInventory();
			p.sendMessage(Messages.severalbooster);
			return;
		}
		booster.add(p.getName());
		p.closeInventory();
		p.sendMessage(Messages.boosterenable);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 1F);
		status.put(p.getPlayer(), key);
		multiplicador.put(p, multipli);
		ativado.add(p);
	}

	private static void disable(Player p, String key, Integer multipli) {
		if (status.containsKey(p) && status.get(p) != key) {
			p.closeInventory();
			p.sendMessage(Messages.severalbooster);
			return;
		}
		booster.remove(p.getName());
		p.closeInventory();
		p.sendMessage(Messages.boosterdisable);
		p.playSound(p.getLocation(), Sound.BAT_DEATH, 1F, 1F);
		status.put(p, null);
		multiplicador.put(p, 1);
		ativado.remove(p);
	}
}
