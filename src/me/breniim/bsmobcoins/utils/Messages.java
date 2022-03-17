package me.breniim.bsmobcoins.utils;

import org.bukkit.configuration.file.FileConfiguration;

import me.breniim.bsmobcoins.Main;

public class Messages {

	private static FileConfiguration config = Main.config.getConfig();

	public static String dontbuy = config.getString("Messages.Dont-Buy").replace("&", "§");

	public static String dontspace = config.getString("Messages.Dont-Space").replace("&", "§");

	public static String buy = config.getString("Messages.Buy").replace("&", "§");

	public static String dontperm = config.getString("Messages.Dont-Permission").replace("&", "§");

	public static String chaton = config.getString("Messages.Chat-On").replace("&", "§");

	public static String chatoff = config.getString("Messages.Chat-Off").replace("&", "§");

	public static String mobcoins = config.getString("Messages.MobCoins").replace("&", "§");

	public static String mobcoinschat = config.getString("Messages.MobCoins-Chat").replace("&", "§");

	public static String mobcoinsactionbar = config.getString("Messages.MobCoins-ActionBar").replace("&", "§");

	public static String wrongcommand = config.getString("Messages.Wrong-Command").replace("&", "§");

	public static String playernfound = config.getString("Messages.Player-Not-Found").replace("&", "§");

	public static String addsuccess = config.getString("Messages.Add-Success").replace("&", "§");

	public static String setsuccess = config.getString("Messages.Set-Success").replace("&", "§");

	public static String removesuccess = config.getString("Messages.Remove-Success").replace("&", "§");

	public static String wrongnumber = config.getString("Messages.Wrong-Number").replace("&", "§");
	
	public static String quantitynegative = config.getString("Messages.Remove-Failed").replace("&", "§");
	
	public static String notallowed = config.getString("Messages.MobCoins-Not-Allowed").replace("&", "§");
	
	public static String boosterenable = config.getString("Booster.Enable").replace("&", "§");
	
	public static String boosterdisable = config.getString("Booster.Disable").replace("&", "§");
	
	public static String boosternotperm = config.getString("Booster.Not-Perm").replace("&", "§");
	
	public static String severalbooster = config.getString("Booster.Multi-Boosters").replace("&", "§");
	
	public static String topmobcoins = config.getString("Messages.MobCoins-Updated").replace("&", "§");
	
	public static String toptitle = config.getString("MobCoins-TOP.Title").replace("&", "§");
	
	public static String topmessage = config.getString("MobCoins-TOP.Message").replace("&", "§");
	
	public static String npcexist = config.getString("Messages.Npc-Exist").replace("&", "§");
	
	public static String npcdontexist = config.getString("Messages.Npc-Dont-Create").replace("&", "§");
	
	public static String npcdelete = config.getString("Messages.Npc-Delete").replace("&", "§");
	
	public static String npccreate = config.getString("Messages.Npc-Create").replace("&", "§");
	
	public static String checkuse = config.getString("Messages.Check-Use").replace("&", "§");
	
	public static String checkdontspace = config.getString("Messages.Check-Without-Space").replace("&", "§");
	
	public static String checkdontmoney = config.getString("Messages.Check-Without-Money").replace("&", "§");

}
