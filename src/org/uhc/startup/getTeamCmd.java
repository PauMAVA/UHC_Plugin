package org.uhc.startup;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getTeamCmd {
	//CONSTRUCTOR-----------------------------------
		uhcCore directPlugin;
		public getTeamCmd(uhcCore passedPlugin) {
			this.directPlugin = passedPlugin;
		}
		teamGetterModule teamGetterModule = new teamGetterModule(this);
	//CONSTRUCTOR-----------------------------------
		
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args) {
		
		if (commandLabel.equalsIgnoreCase("getteam")) {
			if (args.length != 1) {
				Player player = (Player) theSender;
				player.sendMessage("Invalid arguments! Usage: /getteam <Team Name>");
				return false;
			}
			else {
				teamGetterModule.checkForTeam(theSender, args[0]);
			}
		}
		return true;
	}
}
