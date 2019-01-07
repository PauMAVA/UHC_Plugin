package org.uhc.startup;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class teamGetterModule {
	//CONSTRUCTOR-----------------------------------
	getTeamCmd directPlugin;
	public teamGetterModule(getTeamCmd passedPlugin) {
		this.directPlugin = passedPlugin;
	}
	
	uhcCore indirectPlugin;
	public teamGetterModule(uhcCore remotePlugin) {
		this.indirectPlugin = remotePlugin;
	}
	//CONSTRUCTOR-----------------------------------
	
	public void getTeam(CommandSender theSender, String args[]) {
		String teamToSearch = args[0];	
		boolean isCheckPassed = checkForTeam(theSender, teamToSearch);
		if (isCheckPassed == false) {
			return;
		}
			
		else {
			String teamPath = "Teams.teamToSearch";
			List<String> list = this.indirectPlugin.getConfig().getStringList(teamPath);
			String[] playersFromTeam = list.toArray(new String[0]);
			Player player = (Player) theSender;
			player.sendMessage("Team integrants are: " + playersFromTeam);
				
		}
		return;
	}
	
	public boolean checkForTeam (CommandSender theSender, String teamToSearch) {
		String checkPath =  (String) this.indirectPlugin.getConfig().get("Teams.teamToSearch");
		if (checkPath != null){
			return true;
		}
		
		else {
			Player player = (Player) theSender;
			player.sendMessage(ChatColor.RED + "Error Handler throws the following exception: No such team is registered!");
			player.sendMessage(ChatColor.YELLOW + "Use /registerteam <teamName> <Player1> <Player2>");
			return false;
		}
		
	}
	
}
