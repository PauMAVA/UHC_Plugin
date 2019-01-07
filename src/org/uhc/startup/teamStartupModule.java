package org.uhc.startup;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class teamStartupModule {
	
	//CONSTRUCTOR-----------------------------------
	registerTeamCmd directPlugin;
	public teamStartupModule(registerTeamCmd passedPlugin) {
		this.directPlugin = passedPlugin;
	}
	
	uhcCore indirectPlugin;
	public teamStartupModule(uhcCore remotePlugin) {
		this.indirectPlugin = remotePlugin;
	}
	//CONSTRUCTOR-----------------------------------
	
	public void setTeam(CommandSender theSender, String args[]) {
		String teamName = args[0];
		String player1 = args[1];
		String player2 = args[2];
		boolean isCheckPassed = checkForPlayer(theSender,player1, player2);
		if (isCheckPassed == false) {
			return;
		}
		
		else {
			Player player = (Player) theSender;
			String teamPath = "teams." + teamName;
			String[] integrants = {player1, player2};
			List<String> people = Arrays.asList(integrants);
			uhcCore.getInstance().getConfig().set(teamPath, people);
			uhcCore.getInstance().saveConfig();
			player.sendMessage("Team" + teamName + "was sucessfully registered!");
			player.sendMessage(teamName + "integrants: " + player1 + player2);
		}
		return;
		
	}
	
	@SuppressWarnings("deprecation")
	public boolean checkForPlayer(CommandSender theSender, String player1, String player2) {

		Player first = Bukkit.getServer().getPlayer(player1);
		Player second = Bukkit.getServer().getPlayer(player2);
		if (first !=null && second !=null) {
			return true;
		}
		
		else {
			Player player = (Player) theSender;
			player.sendMessage(ChatColor.RED + "Error Handler throws the following exception: The players you specified are not in this server!");
			
			return false;
		}
	
	}
}
