package me.breniim.bsmobcoins.API.npc;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.breniim.bsmobcoins.Main;
import me.breniim.bsmobcoins.utils.Messages;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

public class NpcAPI {

	private static FileConfiguration config = Main.config.getConfig();
	private static NpcUtils lu = new NpcUtils();
	private static int id = config.getInt("NPC.ID");

	public static void createNpc(Player p, Location loc) {
		if(verifyNpc()) {
			p.sendMessage(Messages.npcexist);
			return;
		}
		Location loc1 = loc.clone().add(0.0D, 3.1D, 0.0D);
		Hologram holo = HologramsAPI.createHologram(Main.m, loc1);
		ArrayList<String> npcHolograma = new ArrayList<>();
		for(String linha : config.getStringList("NPC.Holograma")) {
			npcHolograma.add(linha.replace("&", "§"));
		}
		for (String msg : npcHolograma) {
			holo.appendTextLine(msg.replace("&", "§"));
		}
		NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, UUID.randomUUID(), id, "§7");
		npc.data().set("player-skin-name", config.getString("NPC.Skin"));
		npc.getStoredLocation();
		npc.spawn(loc);
		Main.config.getConfig().set("NPC.Location", lu.getLocation(p.getLocation()));
		Main.config.saveConfig();
		p.sendMessage(Messages.npccreate);
	}

	public static boolean verifyNpc() {
		NPC npc = CitizensAPI.getNPCRegistry().getById(id);
		if (npc != null && npc.isSpawned()) {
			HologramsAPI.getHolograms(Main.m).forEach(h -> h.delete());
			return true;
		}
		return false;
	}

	public static void deleteNpc(Player p) {
		NPC npc = CitizensAPI.getNPCRegistry().getById(id);
		if (npc == null || !npc.isSpawned()) {
			p.sendMessage(Messages.npcdontexist);
			return;
		}
		npc.destroy();
		HologramsAPI.getHolograms(Main.m).forEach(h -> h.delete());
		Main.config.getConfig().set("NPC.Location", "none");
		Main.config.saveConfig();
		p.sendMessage(Messages.npcdelete);
	}

}
