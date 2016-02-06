package me.getsomepanda.nopickup;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CommandsClass implements CommandExecutor, Listener
{
	Main main;
	ArrayList<Integer> blockedItemsArray;
	CommandMsgs cmdmsgs;
	
	public CommandsClass(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		main = plugin;
		cmdmsgs = new CommandMsgs();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	 {
		 if (cmd.getName().equalsIgnoreCase("nopickup") && sender instanceof Player) 
		 {
			Player player = (Player) sender;
			
			if(args.length == 0) 
			{
				cmdmsgs.defaultMsg(player);
				return true;
			} else {
				if(args[0].equalsIgnoreCase("help")) {
					
					if(main.returnPermsBool() == true) {
						if(player.hasPermission("nopickup.help") || player.isOp()) {
							
							cmdmsgs.helpMsg(player);
							return true;
						} else  {
							cmdmsgs.noPermission(player);
							return true;
						}
					} else {
						cmdmsgs.helpMsg(player);
						return true;
					}
				}
				
				if(args[0].equalsIgnoreCase("reload")) {
					if(!player.isOp()) {
						cmdmsgs.noPermission(player);
						return true;
					}
					main.reloadCFG();
					player.sendMessage(ChatColor.GREEN + "[NoPickup] Config reloaded!");
					return true;
				}		
				
				if(args[0].equalsIgnoreCase("remove")) 
				{
					if(args.length == 1) {
						player.sendMessage(ChatColor.DARK_RED + "Error : Please enter the blockID.");
						return true;
					}
					
					if(args[1].matches(".*[a-zA-Z]+.*")) {
						player.sendMessage(ChatColor.DARK_RED + "Error : Please use numbers for the blockID");
						return true;
					}
					
					blockedItemsArray = main.returnBlockedItems();
					int checkIfValid = Integer.parseInt(args[1].toString());
					if(!(blockedItemsArray.contains(checkIfValid))) {
						player.sendMessage(ChatColor.DARK_RED + "BlockID: " + args[1].toString() + " was not found in the blockedId's list.");
						return true;
					}
						
					if(main.returnPermsBool() == true) {
						if(player.hasPermission("nopickup.remove") || player.isOp()) {
							
							String blockIDStringed = args[1].toString();
							int blockIDInt = Integer.parseInt(blockIDStringed);
							main.removeBlockID(blockIDInt);
							
							player.sendMessage(ChatColor.DARK_BLUE + "[-] Successfully removed block id" + " " + blockIDStringed + " from the blocked ids list.");
							
							return true;
						} else  {
							cmdmsgs.noPermission(player);
							return true;
						}
					} else {
						if(player.isOp()) {
							
							String blockIDStringed = args[1].toString();
							int blockIDInt = Integer.parseInt(blockIDStringed);
							main.removeBlockID(blockIDInt);
							
							player.sendMessage(ChatColor.DARK_BLUE + "[-] Successfully removed block id" + " " + blockIDStringed + " from the blocked ids list.");
							
							return true;
						} else {
							cmdmsgs.noPermission(player);
							return true;
						}
					}
				}
				
				if(args[0].equalsIgnoreCase("add")) {
					if(args.length == 1) {
						player.sendMessage(ChatColor.DARK_RED + "Error : Please enter the blockID.");
						return true;
					}
					
					if(args[1].matches(".*[a-zA-Z]+.*")) {
						player.sendMessage(ChatColor.DARK_RED + "Error : Please use numbers for the blockID");
						return true;
					}
					
					blockedItemsArray = main.returnBlockedItems();
					int checkIfValid = Integer.parseInt(args[1].toString());
					if(blockedItemsArray.contains(checkIfValid)) {
						player.sendMessage(ChatColor.DARK_GREEN + "BlockID: " + args[1].toString() + " is already in the blockedId's list.");
						return true;
					}
						
					if(main.returnPermsBool() == true) {
						if(player.hasPermission("nopickup.add") || player.isOp()) {
							
							String blockIDStringed = args[1].toString();
							int blockIDInt = Integer.parseInt(blockIDStringed);
							main.addBlockID(blockIDInt);
							
							player.sendMessage(ChatColor.GREEN + "[+] Successfully added block id" + " " + blockIDStringed + " to the blocked ids list.");
							
							return true;
						} else  {
							cmdmsgs.noPermission(player);
							return true;
						}
					} else {
						if(player.isOp()) {
							String blockIDStringed = args[1].toString();
							int blockIDInt = Integer.parseInt(blockIDStringed);
							main.addBlockID(blockIDInt);
							
							player.sendMessage(ChatColor.GREEN + "Successfully added block id" + " " + blockIDStringed + " to the blocked ids list.");
							
							return true;
						} else {
							cmdmsgs.noPermission(player);
							return true;
						}
					}
				}
			}
		}
		return false;
	 }
}
