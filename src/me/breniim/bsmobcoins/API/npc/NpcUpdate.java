package me.breniim.bsmobcoins.API.npc;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.breniim.bsmobcoins.Main;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

public class NpcUpdate {

	private static FileConfiguration config = Main.config.getConfig();
	private static int id = config.getInt("NPC.ID");

	public void Update(Main main, NpcUtils lu) {

		if (config.get("NPC.Location") == null || config.getString("NPC.Location").isEmpty()
				|| config.getString("NPC.Location").contains("none")) {
			return;
		}
		Location loc = lu.getLocation(config.getString("NPC.Location"));
		for (NPC npc : CitizensAPI.getNPCRegistry()) {
			if (npc.getId() == id)
				npc.despawn();
		}
		for (Hologram h : HologramsAPI.getHolograms(Main.m)) {
			h.delete();
		}
		if (!config.getString("NPC.Location").contains("none")) {
			NpcAPI.verifyNpc();
			NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, UUID.randomUUID(), id, "§7");
			npc.data().set("player-skin-name", config.getString("NPC.Skin"));
			npc.spawn(loc);
			Location loc1 = loc.clone().add(0.0D, 3.1D, 0.0D);
			Hologram hologram = HologramsAPI.createHologram(Main.m, loc1);
			ArrayList<String> npcHolograma = new ArrayList<>();
			for (String linha : config.getStringList("NPC.Holograma")) {
				npcHolograma.add(linha.replace("&", "§"));
			}
			for (String msg : npcHolograma) {
				hologram.appendTextLine(msg.replace("&", "§"));
			}

		}
	}
}
