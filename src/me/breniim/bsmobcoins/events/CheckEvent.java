package me.breniim.bsmobcoins.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.breniim.bsmobcoins.Main;
import me.breniim.bsmobcoins.API.mobcoins.MobCoinsAPI;
import me.breniim.bsmobcoins.utils.Messages;

public class CheckEvent implements Listener {

	@EventHandler
	public void aoInteragir(PlayerInteractEvent e) {
		if (!(e.getPlayer() instanceof Player)) {
			return;
		}
		Player p = e.getPlayer();
		ItemStack mao = p.getItemInHand();
		if (mao.getType() == Material.PAPER && e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (mao.getItemMeta().getDisplayName() != null && mao.getItemMeta().getDisplayName()
					.equals(Main.config.getConfig().getString("Check.Name").replace("&", "§").replace("{value}",
							String.valueOf(Double.valueOf(mao.getItemMeta().getDisplayName().split("-")[1]))))) {

				double valor = Double.valueOf(mao.getItemMeta().getDisplayName().split("-")[1]);
				if (p.isSneaking()) {
					MobCoinsAPI.addMobCoins(p.getName(), valor * mao.getAmount());
					p.getInventory().remove(p.getItemInHand());
					p.sendMessage(Messages.checkuse.replace("{value}", String.valueOf(valor * mao.getAmount())));
					return;
				}
				MobCoinsAPI.addMobCoins(p.getName(), valor);
				p.getInventory().remove(p.getItemInHand());
				p.sendMessage(Messages.checkuse.replace("{value}", String.valueOf(valor)));
			} else {
				return;
			}
		} else {
			return;
		}
	}
}
