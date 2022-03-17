package me.breniim.bsmobcoins.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.breniim.bsmobcoins.Main;
import me.breniim.bsmobcoins.API.BoosterAPI;
import me.breniim.bsmobcoins.API.TopAPI;
import me.breniim.bsmobcoins.API.mobcoins.MobCoinsAPI;
import me.breniim.bsmobcoins.API.mobcoins.MobCoinsInventario;
import me.breniim.bsmobcoins.utils.ItemBuilder.B_Create;
import net.citizensnpcs.api.event.NPCRightClickEvent;

public class NPCEvent implements Listener {

	private static FileConfiguration config = Main.config.getConfig();
	private static int id = config.getInt("NPC.ID");

	@EventHandler
	public void onClick(NPCRightClickEvent e) {
		Player p = e.getClicker();
		if (e.getNPC().getId() == id) {
			openInv(p);
			return;
		}
	}

	@EventHandler
	public void onClik(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equals(config.getString("NPC.Inventory").replace("&", "§"))) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null)
				return;
			if (e.getCurrentItem().getType() == Material.AIR)
				return;
			if (e.getCurrentItem().getItemMeta().getDisplayName() == null)
				return;
			ItemStack item = e.getCurrentItem();
			switch (item.getItemMeta().getDisplayName()) {
			case "§eShop":
				MobCoinsInventario.MobCoinsInv(p);
				break;
			case "§eBoosters":
				BoosterAPI.BoosterInv(p);
				break;
			case "§eTop MobCoins":
				TopAPI.TopChat(p);
				p.closeInventory();
				break;
			}
		}
	}

	private void openInv(Player p) {
		String title = Main.config.getConfig().getString("NPC.Inventory");
		Inventory inv = Bukkit.createInventory(null, 27, title.replace("&", "§"));
		String[] lore1 = { "", "§e-> §fClick for open shop", "§e-> §fClique para abrir a loja", "" };
		String[] lore2 = { "", "§e-> §fClick for open Boosters Menu", "§e-> §fClique para abrir o Menu de Boosters", "" };
		String[] lore3 = { "", "§e-> §fMobCoins: §e" + MobCoinsAPI.getMobCoins(p.getName()), "" };
		String[] lore4 = { "", "§e-> §fClick to see the top MobCoins", "§e-> §fClique para ver os tops MobCoins", "" };
		inv.setItem(13, B_Create.add(Material.BEACON, "§eShop", lore1));
		inv.setItem(11, B_Create.getNamedSkull("ABigDwarf", "§eBoosters", lore2));
		inv.setItem(15, B_Create.getNamedSkull("Tereneckla", "§eTop MobCoins", lore4));
		inv.setItem(26, B_Create.add(Material.DOUBLE_PLANT, "§bMobCoins", lore3));
		p.openInventory(inv);
	}
}
