package org.uhc.startup;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onPlayerDeathModule implements Listener{

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player entity = event.getEntity();
		String playerName = entity.getName();
		Bukkit.broadcastMessage(playerName + " died!");
		return;
	}
	
	@EventHandler
	public Location getDeathLocation(PlayerDeathEvent event) {
		Player player = event.getEntity();
		String person = player.getName();
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        int xInt = (int) x;
        int yInt = (int) y;
        int skullHeigth = yInt + 1;
        int zInt = (int) z;
        Location location = player.getLocation();
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "setblock " + xInt + " " + skullHeigth + " " + zInt + " minecraft:player_head{Owner:{Name:\"" + person + "\"}} replace");
        Bukkit.getServer().getWorld("world").getBlockAt(xInt,yInt,zInt).setType(Material.BLACK_STAINED_GLASS_PANE);
        return location;
	}
	
	
}
