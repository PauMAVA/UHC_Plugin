package org.uhc.startup;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class scoreboardCounterTask extends BukkitRunnable {
	int seconds = 59;
	int minutes = 24;
	int episode = 1;
	
	scoreboardCounterModule directPlugin;
	public scoreboardCounterTask(scoreboardCounterModule passedPlugin) {
		this.directPlugin = passedPlugin;
	}
	@SuppressWarnings("deprecation")
	public void run() {
		seconds = seconds - 1;
		if (seconds == -1) {
			seconds = 59;
			minutes = minutes - 1;
		}
		
		if (minutes == -1) {
			seconds = 59;
			minutes = 24;
			episode = episode + 1;
			Bukkit.broadcastMessage(ChatColor.BOLD + (ChatColor.DARK_AQUA + "[UHC T2] " + ChatColor.DARK_GRAY + ">> " + ChatColor.GREEN + "Episode " + ChatColor.GOLD + episode + ChatColor.GREEN + " has began!"));
			if(episode == 9) {
				Bukkit.broadcastMessage(ChatColor.BOLD + (ChatColor.DARK_AQUA + "[UHC T2] " + ChatColor.DARK_GRAY + ">> " + ChatColor.GREEN + "At the end of this episode you must be at 0,0!"));
				Bukkit.broadcastMessage(ChatColor.BOLD + (ChatColor.DARK_AQUA + "[UHC T2] " + ChatColor.DARK_GRAY + ">> " + ChatColor.RED + "World border will stretch to 750 blocks (radius) throughout the episode!"));
				setBorderEp9();
			}
			if(episode == 10) {
				Bukkit.broadcastMessage(ChatColor.BOLD + (ChatColor.DARK_AQUA + "[UHC T2] " + ChatColor.DARK_GRAY + ">> " + ChatColor.GREEN +"Time to fight! GL :)"));
				Bukkit.broadcastMessage(ChatColor.BOLD + (ChatColor.DARK_AQUA + "[UHC T2] " + ChatColor.DARK_GRAY + ">> " + ChatColor.RED + "World border will stretch to 150 blocks (radius) throughout the episode!"));
				setBorderEp10();
				
			}
			
			
		}
		
		if (seconds == 40 && minutes==0) {
			for(Player everyone: Bukkit.getOnlinePlayers()) {
				everyone.playSound(everyone.getLocation(), Sound.MUSIC_DISC_FAR, 20.0F, 1.0F);
			}
			
			
		}
			ScoreboardManager manager = Bukkit.getServer().getScoreboardManager();
			Scoreboard timerBoard = manager.getNewScoreboard();
			Objective episodeObjective = timerBoard.registerNewObjective("episode", "dummy");
			Score episodes_left = episodeObjective.getScore(Bukkit.getOfflinePlayer(ChatColor.YELLOW + "Time: " + minutes +  " : " + seconds + " || Episode: "));
			episodeObjective.setDisplayName(ChatColor.BOLD + (ChatColor.BLUE + "UHC T2" + ChatColor.RED + " | " + ChatColor.GREEN + "Alpha 0.1"));
			episodes_left.setScore(episode);
			episodeObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
			for (Player p: Bukkit.getOnlinePlayers()) {
			p.setScoreboard(timerBoard);
			}
		return;	
	}
	
	public void setBorderEp9() {
		 World world = Bukkit.getWorld("world");
		 WorldBorder border = world.getWorldBorder();
		 border.setCenter(0.0, 0.0);
		 border.setSize(1250.0, 1500L);
		 return;
	}
	
	public void setBorderEp10() {
		 World world = Bukkit.getWorld("world");
		 WorldBorder border = world.getWorldBorder();
		 if (border.getSize() != 1250.0) {
			 border.setSize(1250.0);
			 
		 }
		 border.setCenter(0.0, 0.0);
		 border.setSize(300.0, 1000L);
		 return;
	}
	

}
