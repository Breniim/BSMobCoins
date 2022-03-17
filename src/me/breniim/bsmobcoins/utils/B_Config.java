package me.breniim.bsmobcoins.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class B_Config {
	
	private File file;
	private FileConfiguration config;
	private JavaPlugin plugin;

	public B_Config(JavaPlugin plugin, String name) {
		this.plugin = plugin;
		file = new File(plugin.getDataFolder(), name);
		reloadConfig();
	}

	public FileConfiguration getConfig() {
		if (config == null)
			reloadConfig();
		return config;
	}

	public File getFile() {
		return file;
	}

	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(file);
		InputStream imputStream = plugin.getResource(file.getName());
		if (imputStream != null) {
			YamlConfiguration imputConfig = YamlConfiguration.loadConfiguration(file);
			config.setDefaults(imputConfig);
		}
	}

	public void saveConfig() {
		try {
			config.save(file);
		} catch (IOException ex) {
			Bukkit.getConsoleSender().sendMessage("nfoi salvo");
		}
	}

	public void saveDefault() {
		config.options().copyDefaults(true);
		saveConfig();
	}

	public void saveDefaultConfig() {
		if (!file.exists())
			plugin.saveResource(file.getName(), false);
	}

}
