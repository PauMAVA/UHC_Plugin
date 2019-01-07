package org.uhc.startup;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


public class uhcStartConfig implements Listener {
    //CONSTRUCTOR-----------------------------------
	uhcStartCmd directplugin;
	public uhcStartConfig(uhcStartCmd passedPlugin){
		this.directplugin = passedPlugin;
	}
	public scoreboardCounterModule scoreboardCounterModule;
	uhcCore indirectPlugin;
	public uhcStartConfig(uhcCore passedPlugin) {
		this.indirectPlugin = passedPlugin;
		scoreboardCounterModule = new scoreboardCounterModule(passedPlugin);
	}


    //CONSTRUCTOR-----------------------------------

public void module() {
  Bukkit.broadcastMessage(ChatColor.BLUE + "Setting up world border...");
  boolean borderIsDone = setBorder();
  stateCheck(borderIsDone, "Set World Border");
  
  Bukkit.broadcastMessage(ChatColor.BLUE + "Setting up timer scoreboard...");
  Bukkit.broadcastMessage(ChatColor.GREEN + "Success!");
  
  Bukkit.broadcastMessage(ChatColor.BLUE + "Setting up gamerules...");
  boolean gamerulesAreDone = setGamerules();
  stateCheck(gamerulesAreDone, "Set Gamerules");
  
  setPlayerStats();
  tpPlayers();
  
  return;
}


public boolean setBorder() {
 World world = Bukkit.getWorld("world");
 WorldBorder border = world.getWorldBorder();
 border.setCenter(0.0, 0.0);
 border.setSize(2500.0);
 if (border.getSize() == 2500.0) {
	 return true;
 }
 
 else {
	return false; 
 }
}

	public boolean setGamerules() {
		Bukkit.getServer().setDefaultGameMode(GameMode.SURVIVAL);
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamerule naturalRegeneration false");
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamerule announceAdvancements false");
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "difficulty hard");
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "time set day");
		return true;	
	}

	
	public boolean setPlayerStats() {
		for(Player everyone: Bukkit.getOnlinePlayers()) {
			everyone.setHealth(20);
			everyone.setSaturation(20);
			everyone.setFoodLevel(20);
		}
		
		return true;
	}
	
	public boolean tpPlayers() {
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "effect give @a minecraft:slow_falling 40");
		List<?> team1 = uhcCore.getInstance().getConfig().getList("teams.Al-Qaparro");
		String[] team1Array = team1.toArray(new String[0]);
		List<?> team2 = uhcCore.getInstance().getConfig().getList("teams.SquaredSlaves");
		String[] team2Array = team2.toArray(new String[0]);
		List<?> team3 = uhcCore.getInstance().getConfig().getList("teams.VerduresSalades");
		String[] team3Array = team3.toArray(new String[0]);
		List<?> team4 = uhcCore.getInstance().getConfig().getList("teams.DieKleineburste");
		String[] team4Array = team4.toArray(new String[0]);
		
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp " + team1Array[0] + " 1230 150 1230");
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp " + team1Array[1] + " 1230 150 1230");
		
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp " + team2Array[0] + " -1230 150 1230");
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp " + team2Array[1] + " -1230 150 1230");
		
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp " + team3Array[0] + " 1230 150 -1230");
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp " + team3Array[1] + " 1230 150 -1230");
		
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp " + team4Array[0] + " -1230 150 -1230");
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp " + team4Array[1] + " -1230 150 -1230");
		
		return true;
	}
public void stateCheck(boolean valueChecker, String operation) {
  if (valueChecker == false) {
    Bukkit.broadcastMessage(ChatColor.RED + "Failed do perform operation: " + operation);
    return;
  }
  else {
    Bukkit.broadcastMessage(ChatColor.GREEN + "Success!");
    return;
  }
  
}

}