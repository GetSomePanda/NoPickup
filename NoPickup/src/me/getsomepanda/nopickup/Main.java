package me.getsomepanda.nopickup;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin 
{
	
	CommandsClass cmdexec;
	ListenerClass listener;
	File configFile = new File(this.getDataFolder(), "config.yml");
	FileConfiguration defaultConfig;
	ArrayList<Integer> blockedItemsArray;
	
	public Permission helpPermission = new Permission("nopickup.help");
	public Permission addPermission = new Permission("nopickup.add");
	public Permission removePermission = new Permission("nopickup.remove");
	public Permission bypassPermission = new Permission("nopickup.bypass");
	
	@Override
	public void onEnable()
	{
		listener = new ListenerClass(this);
		cmdexec = new CommandsClass(this);
		defaultConfig = this.getConfig();
		
		PluginManager PManager = getServer().getPluginManager();
		PManager.addPermission(helpPermission);
		PManager.addPermission(addPermission);
		PManager.addPermission(removePermission);
		PManager.addPermission(bypassPermission);
		
		if(cfgExists(configFile) == false) {
			loadDefaultCFG();
		}
		
		blockedItemsArray = (ArrayList<Integer>) defaultConfig.getIntegerList("blockedItems");
		
		getCommand("nopickup").setExecutor(cmdexec);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean cfgExists(File cfg) {
		if(cfg.exists()) {
			return true;
		} else return false;
	}
	
	public void reloadCFG() 
	{
		this.reloadConfig();
        this.saveConfig();
        System.out.println("[NoPickup] Config reloaded!");
	}
	
	public void loadDefaultCFG() {
		defaultConfig.options().copyDefaults(true);
		saveDefaultConfig();
		return;
	}
	
	public ArrayList<Integer> returnBlockedItems () {
		blockedItemsArray = (ArrayList<Integer>) defaultConfig.getIntegerList("blockedItems");
		return blockedItemsArray;
	}
	
	public void removeBlockID (Integer itemID2) {
		blockedItemsArray = (ArrayList<Integer>) defaultConfig.getIntegerList("blockedItems");
		blockedItemsArray.remove(itemID2);
		defaultConfig.set("blockedItems", blockedItemsArray);
		saveConfig();
		return;
	}
	
	public void addBlockID (Integer itemID) {
		blockedItemsArray = (ArrayList<Integer>) defaultConfig.getIntegerList("blockedItems");
		blockedItemsArray.add(itemID);
		defaultConfig.set("blockedItems", blockedItemsArray);
		saveConfig();
		return;
	}
	
	
	public boolean returnPermsBool() {
		if(defaultConfig.getString("permissionsOn").equalsIgnoreCase("true")) {
			return true;
		} else {
			if(defaultConfig.getString("permissionsOn").equalsIgnoreCase("false")) {
				return false;
			}
		}
		return false;
	}

}