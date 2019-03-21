package org.uhc.startup;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onPlayerDeathModule {

	//CONSTRUCTOR-----------------------------------
	uhcCore directPlugin;
	public onPlayerDeathModule(uhcCore passedPlugin) {
		this.directPlugin = passedPlugin;
	}
	//CONSTRUCTOR-----------------------------------
	
	public void setDeadGamemode(PlayerDeathEvent event) {
		if (event.getEntity() instanceof Player) {
			Player entity = event.getEntity();
			String playerName = entity.getName();
			entity.setGameMode(GameMode.SPECTATOR);
			Bukkit.broadcastMessage(playerName + " died!");
		}	
		return;
	}
	
	public void setSkull(PlayerDeathEvent event) {
		Player player = event.getEntity();
		String person = player.getName();
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        int xInt = (int) x;
        int yInt = (int) y;
        int skullHeigth = yInt + 1;
        int zInt = (int) z;
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "setblock " + xInt + " " + skullHeigth + " " + zInt + " minecraft:player_head{Owner:{Name:\"" + person + "\"}} replace");
        Bukkit.getServer().getWorld("world").getBlockAt(xInt,yInt,zInt).setType(Material.BLACK_STAINED_GLASS_PANE);
        return ;
	}
	
}
