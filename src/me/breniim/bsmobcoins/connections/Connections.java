package me.breniim.bsmobcoins.connections;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import me.breniim.bsmobcoins.Main;

public class Connections {

	private static FileConfiguration config = Main.config.getConfig();
	private static Connection con;
	public static String host, database, username, password, table;
	public static int port;

	public static void openConnection() {
		boolean mysql = config.getBoolean("SQL.MySQL");
		host = config.getString("SQL.Host");
		port = config.getInt("SQL.Port");
		database = config.getString("SQL.Database");
		username = config.getString("SQL.User");
		password = config.getString("SQL.Password");
		table = config.getString("SQL.Table");

		try {
			synchronized (config) {
				if (getCon() != null && !getCon().isClosed()) {
					return;
				}
				if (mysql) {
					Class.forName("com.mysql.JDBC.Driver");
					setCon(DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username,
							password));
					createTable();
					Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §aConexão com §fMySQL §afeita com sucesso");
				} else {
					File file = new File(Main.m.getDataFolder(), "mobcoins.db");
					try {
						if (file.createNewFile()) {
							Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §aDatabase criada com sucesso");
						} else {
							
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					Class.forName("org.sqlite.JDBC");
					setCon(DriverManager.getConnection("jdbc:sqlite:" + file));
					createTable();
					Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §aConexão com §fSQLite §afeita com sucesso");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + table + "(nome VARCHAR(16), coins DOUBLE)";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.executeUpdate();
			Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §aTabela §f" + table + " §acriada/carregada com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection() {
		try {
			if (getCon() != null && !getCon().isClosed()) {
				con.close();
				Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §aInformacoes salvas com sucesso");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		Connections.con = con;
	}

}
