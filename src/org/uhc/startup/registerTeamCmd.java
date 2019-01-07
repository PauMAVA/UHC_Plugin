package org.uhc.startup;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class registerTeamCmd implements CommandExecutor {

	//CONSTRUCTOR-----------------------------------
	uhcCore directPlugin;
	public registerTeamCmd(uhcCore passedPlugin) {
		this.directPlugin = passedPlugin;
	}
	teamStartupModule teamStartupModule = new teamStartupModule(this);
	//CONSTRUCTOR-----------------------------------
	
	
public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String args[]) {
		
		if (commandLabel.equalsIgnoreCase("registerteam")) {
			if (args.length < 3) {
				Player player = (Player) theSender;
				player.sendMessage("Invalid arguments! Usage: /registerteam <Team Name> <Player 1> <Player 2>");
				return false;
			}
			
			else {
				teamStartupModule.setTeam(theSender,args);
			}
		}

		return true;
	}
}
