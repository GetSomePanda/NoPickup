package me.getsomepanda.nopickup;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandMsgs {
	
	//NoPickup
	public void defaultMsg(Player player) {
		player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "----------------------------");
		player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "NoPickup v1.0");
		player.sendMessage(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + 
				"Developed By:" + ChatColor.AQUA + 
				"" + ChatColor.BOLD + " GetSomePanda");
		
		player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "/nopickup help");
		player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "----------------------------");
		return;
	}
	
	//No Permission
	public void noPermission(Player player) {
		player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You do not have permission to use this command!");
		return;
	}
	
	//Help
	public void helpMsg(Player player) {
		player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "NoPickup v1.0 By GetSomePanda");
		player.sendMessage("");
		player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/nopickup add <BlockID>" + ChatColor.RESET + " : Add a block id to the blocked items to pickup.");
		player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/nopickup remove <BlockID>" + ChatColor.RESET + " : Remove a block id from the blocked items list.");
		player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/nopickup reload" + ChatColor.RESET + " : Reload Config.");
		return;
	}

}
