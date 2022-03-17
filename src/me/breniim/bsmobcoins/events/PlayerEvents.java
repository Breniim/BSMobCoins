package me.breniim.bsmobcoins.events;

import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.breniim.bsmobcoins.Main;
import me.breniim.bsmobcoins.API.mobcoins.MobCoinsAPI;
import me.breniim.bsmobcoins.utils.Messages;
import me.breniim.bsmobcoins.utils.stackmobs.JHStackMobs;

public class PlayerEvents implements Listener {

	public FileConfiguration config = Main.config.getConfig();

	@EventHandler
	public void onMob(EntityDeathEvent e) {
		Player p = e.getEntity().getKiller();
		Entity et = e.getEntity();
		int valor = 0;
		if (!(e.getEntity().getKiller() instanceof Player)) {
			return;
		}
		if (et.getType() == EntityType.PLAYER) {
			return;
		}
		String permissao = null;
		Double porcentagem = 0.0;
		Double coin = 0.0;
		for (String mob : config.getConfigurationSection("Mobs.").getKeys(false)) {
			if (et.getType().equals(EntityType.valueOf(config.getString("Mobs." + mob + ".Type").toUpperCase()))) {
				permissao = config.getString("Mobs." + mob + ".Permissions");
				porcentagem = config.getDouble("Mobs." + mob + ".Percentage");
				coin = config.getDouble("Mobs." + mob + ".Value");
			}
		}
		if (p.hasPermission(permissao)) {
			if (this.percentChance(porcentagem)) {
				int quantia = 1;
				if (JHStackMobs.JHStack) {
					if (JH_StackMobs.Main.getInstace().getConfig().getBoolean("Kill.MatarTodosSemShift")) {
						quantia = JH_StackMobs.API.getStackAmount(et);
					} else {
						if (p.isSneaking()) {
							quantia = JH_StackMobs.API.getStackAmount(et);
						}
					}
				}
				if (BoosterEvents.booster.contains(p.getName())) {
					valor = BoosterEvents.multiplicador.get(p);
					MobCoinsAPI.multiplicador(p, coin * quantia * valor);
				} else {
					MobCoinsAPI.multiplicador(p, coin * quantia);
				}
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 1F);
			}
		} else {
			p.sendMessage(Messages.notallowed);
		}
	}

	protected boolean percentChance(final double percent) {
		if (percent < 0.0 || percent > 100.0) {
			throw new IllegalArgumentException("A percentagem nao pode ser maior do que 100 nem menor do que 0");
		}
		final double result = new Random().nextDouble() * 100.0;
		return result <= percent;
	}
}
