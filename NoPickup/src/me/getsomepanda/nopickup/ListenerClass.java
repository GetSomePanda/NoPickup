package me.getsomepanda.nopickup;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import net.md_5.bungee.api.ChatColor;

public class ListenerClass implements Listener {

	Main main;
	
	public ListenerClass(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		main = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPickUp(PlayerPickupItemEvent e)
	{
		if(e.getPlayer().hasPermission("nopickup.bypass") || e.getPlayer().isOp()) return;
		if(main.returnBlockedItems().contains(e.getItem().getItemStack().getTypeId())) 
		{
			e.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to pickup this item as it is blocked from the server!");
			List<Entity> ItemsInWorld = e.getPlayer().getWorld().getEntities();
			e.setCancelled(true);
			for(Entity item : ItemsInWorld) {
				if(item instanceof Entity) {
					if(item.getUniqueId() == e.getItem().getUniqueId())
					{
						item.remove();
						break;
					}
				}
			}
			return;
		} else return;
	}

}
