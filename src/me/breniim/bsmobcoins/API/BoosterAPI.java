package me.breniim.bsmobcoins.API;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.breniim.bsmobcoins.Main;
import me.breniim.bsmobcoins.events.BoosterEvents;
import me.breniim.bsmobcoins.utils.ItemBuilder.B_Create;

public class BoosterAPI {

	private static FileConfiguration config = Main.config.getConfig();
	public static String status = null;

	public static void BoosterInv(Player p) {

		String nome = config.getString("Booster.Inventory-Name").replace("&", "§");
		int tamanho = config.getInt("Booster.Inventory-Size");
		Inventory inv = Bukkit.createInventory(null, tamanho, nome);

		for (String item : config.getConfigurationSection("Booster.Boosters.").getKeys(false)) {

			int slot = config.getInt("Booster.Boosters." + item + ".Inventory-Slot");
			String nomeskul = config.getString("Booster.Boosters." + item + ".Name").replace("&", "§");
			String skin = config.getString("Booster.Boosters." + item + ".Skin-Head");
			String key = config.getString("Booster.Boosters." + item + ".Key");

			if (BoosterEvents.ativado.contains(p) && BoosterEvents.status.get(p) == key) {
				status = "§aEnabled";
			} else {
				status = "§cDisabled";
			}
			ArrayList<String> lore = new ArrayList<>();
			for (String linha : config.getStringList("Booster.Boosters." + item + ".Lore")) {
				lore.add(linha.replace("&", "§").replace("{status}", status));
			}
			inv.setItem(slot, B_Create.getCustomHead(nomeskul, skin, lore));
		}
		p.openInventory(inv);
	}

}
