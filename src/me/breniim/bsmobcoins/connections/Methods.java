package me.breniim.bsmobcoins.connections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class Methods extends Connections {

	private static PreparedStatement stm = null;

	public static boolean containsPlayer(String jogador) {
		try {
			stm = getCon().prepareStatement("SELECT * FROM " + table + " WHERE nome = ?");
			stm.setString(1, jogador);
			ResultSet rs = stm.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void setPlayer(String jogador) {
		try {
			stm = getCon().prepareStatement("INSERT INTO " + table + "(nome, coins) VALUES (?, ?)");
			stm.setString(1, jogador);
			stm.setDouble(2, 0);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §cErro ao criar novo jogador");
		}
	}

	public static void setMobCoins(String jogador, Double vezes) {
		if (containsPlayer(jogador)) {
			try {
				stm = getCon().prepareStatement("UPDATE " + table + " SET coins = ? WHERE nome = ?");
				stm.setDouble(1, vezes);
				stm.setString(2, jogador);
				stm.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				Bukkit.getConsoleSender().sendMessage("§b[BSMobCoins] §cErro ao criar novo jogador");
			}
		} else {
			setPlayer(jogador);
		}

	}

	public static Double getMobCoins(String jogador) {
		if (containsPlayer(jogador)) {
			try {
				stm = getCon().prepareStatement("SELECT * FROM " + table + " WHERE nome = ?");
				stm.setString(1, jogador);
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					return rs.getDouble("coins");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			setPlayer(jogador);
		}
		return 0.0;
	}
}
