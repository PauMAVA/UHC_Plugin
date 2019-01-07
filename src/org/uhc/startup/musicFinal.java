package org.uhc.startup;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class musicFinal implements CommandExecutor {
	uhcCore directPlugin;
	public musicFinal(uhcCore passedPlugin) {
		this.directPlugin = passedPlugin;
	}
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args) {
		if(commandLabel.equalsIgnoreCase("musicfinal")) {
			for(Player everyone: Bukkit.getServer().getOnlinePlayers()) {
				everyone.playSound(everyone.getLocation(), Sound.MUSIC_DISC_13, 20.0F, 1.0F);
			}
			return true;
		}
		else {
			return false;
			
		}
	}
}

