package org.uhc.startup;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class uhcStartConfig implements Listener {
	public static final Difficulty difficulty = Difficulty.HARD;
	public static final GameRule<Boolean> naturalRegeneration = GameRule.NATURAL_REGENERATION;
	public static final GameRule<Boolean> announceAdvancements = GameRule.ANNOUNCE_ADVANCEMENTS;
	randTpGenerator randTpGenerator = new randTpGenerator(this);
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
		World world = Bukkit.getWorld("world");
		boolean gamerulesAreDone = setGamerules(world);
		stateCheck(gamerulesAreDone, "Set Gamerules");
  
		setPlayerStats();
		tpPlayers();
  
		return;
	}


	public boolean setBorder() {
		World world = Bukkit.getWorld("world");
		WorldBorder border = world.getWorldBorder();
		border.setCenter(0.0, 0.0);
		int i = 0;
		for (@SuppressWarnings("unused") String key: uhcCore.getInstance().getConfig().getKeys(true)) {
			i++;
		}
		i--; //Remove teams: tag from count!
		double borderSize = 2500.0;
		if (i >= 5) {
			while (i != 4) {
				borderSize = borderSize + 200.0;
				i--;
			}
		}
		border.setSize(borderSize);
		Bukkit.broadcastMessage("Border diameter: " + borderSize);
		if (border.getSize() == borderSize) {
			return true;
		}
 
		else {
			return false; 
		}
	}

	public boolean setGamerules(World world) {
		Bukkit.getServer().setDefaultGameMode(GameMode.SURVIVAL);
		if (world.getGameRuleDefault(GameRule.NATURAL_REGENERATION) != false) {
			world.setGameRule(naturalRegeneration, false);
		} if(world.getGameRuleDefault(GameRule.ANNOUNCE_ADVANCEMENTS) != false) {
			world.setGameRule(announceAdvancements, false);
		}
		world.setDifficulty(difficulty);
		world.setTime(0);
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
		for (Player everyone: Bukkit.getServer().getOnlinePlayers()) {
			everyone.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 1));
		}
		String[] passArray = randTpGenerator.setBorderNumeration();
		String[][] passHashMap = randTpGenerator.setTeamSide(passArray);
		randTpGenerator.generateRandTpCoords(passHashMap);
		return true;
	}
	public void stateCheck(boolean valueChecker, String operation) {
		if (valueChecker == false) {
			Bukkit.broadcastMessage(ChatColor.RED + "Failed to perform operation: " + operation);
			return;
		}
		else {
			Bukkit.broadcastMessage(ChatColor.GREEN + "Success!");
			return;
		}
  
	}

}
