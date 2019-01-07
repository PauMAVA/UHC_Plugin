package org.uhc.startup;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;


@SuppressWarnings("deprecation")
public class scoreboardCounterModule implements Listener {
	
	 //CONSTRUCTOR-----------------------------------
	 uhcCore indirectPlugin;
	 public scoreboardCounterModule(uhcCore passedPlugin) {
		 this.indirectPlugin = passedPlugin;
	 }
	 
	 uhcStartCmd directPlugin;
	 public scoreboardCounterModule(uhcStartCmd passedPlugin) {
		 this.directPlugin = passedPlugin;
	 }
	 scoreboardCounterTask scoreboardCounterTask = new scoreboardCounterTask(this);
	//CONSTRUCTOR-----------------------------------
	ScoreboardManager manager = Bukkit.getServer().getScoreboardManager();
	Scoreboard timerBoard = manager.getNewScoreboard();
	Objective episodeObjective = timerBoard.registerNewObjective("episode", "dummy");
public void module() {
	episodeObjective.setDisplayName(ChatColor.BLUE + "UHC T1" + ChatColor.RED + " | " + ChatColor.GREEN + "preAlpha 0.6");
	episodeObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
	scoreboardCounterTask.runTaskTimerAsynchronously(uhcCore.getInstance(), 0L, 20L);
	
}
	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		final Player player = e.getPlayer();
		player.setScoreboard(this.indirectPlugin.timerBoard);
		episodeObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
		return;
	}
}
