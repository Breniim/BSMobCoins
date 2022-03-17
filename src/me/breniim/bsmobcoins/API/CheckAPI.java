package me.breniim.bsmobcoins.API;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.breniim.bsmobcoins.Main;
import me.breniim.bsmobcoins.utils.Messages;
import me.breniim.bsmobcoins.utils.ItemBuilder.B_Create;

public class CheckAPI {

	public static ItemStack cheque(Player p, Double valor) {

		String nome = Main.config.getConfig().getString("Check.Name").replace("&", "§").replace("{value}",
				String.valueOf(valor));
		ArrayList<String> lore = new ArrayList<>();
		for (String linha : Main.config.getConfig().getStringList("Check.Lore")) {
			lore.add(linha.replace("&", "§").replace("{player}", p.getName()));
		}
		ItemStack cheque = B_Create.add(Material.PAPER, nome, lore);
		if (p.getInventory().firstEmpty() != -1) {
			p.getInventory().addItem(cheque);
		} else {
			p.sendMessage(Messages.checkdontspace);
		}
		return null;
	}

}
