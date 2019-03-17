package org.uhc.startup;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class uhcCore extends JavaPlugin implements Listener
{	
	//CONSTRUCTOR
	customCraftings customCraftings = new customCraftings(this);
	//lifeScoreboard lifeScoreboard = new lifeScoreboard(this);
	scoreboardCounterModule scoreboardCounterModule = new scoreboardCounterModule(this);
	public static uhcCore instance;
	public Objective obj;
	public Scoreboard timerBoard = null;
	public Objective objective = null;
	
	Logger uhcLog = Bukkit.getLogger();

	@Override
	public void onEnable() 
	{
		//loadConfiguration();
		instance = this;
		
		customCraftings.recipes();
		
		uhcLog.info("UhcPLugin Init Sequence");
		this.getCommand("uhcstart").setExecutor(new uhcStartCmd(this));
		this.getCommand("registerteam").setExecutor(new registerTeamCmd(this));
		this.getCommand("music").setExecutor(new musicModule(this));
		this.getCommand("musicfinal").setExecutor(new musicFinal(this));
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	@Override
	public void onDisable()
	{
	
		uhcLog.info("Plugin Terminated!");
	}
	
	
	public void loadConfiguration() {
		this.getConfig().addDefault("Teams", null);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	public static uhcCore getInstance() {
		return instance;
	}


	//@EventHandler
	//public void onPlayerDmg(EntityDamageEvent dmgEvent) {
		
		//Entity entity = dmgEvent.getEntity();
		
		//if(entity instanceof Player) {
			//Player dmgPlayer = ((Player) entity).getPlayer();
			//int actualLife = (int) dmgPlayer.getHealth();
			//lifeScoreboard.module(dmgPlayer, actualLife);
		//}
		//else {
			//return;
		//}
		
	//}
	
	//@EventHandler
	//public void onPlayerHeal(EntityRegainHealthEvent healEvent) {
		//Entity entity = healEvent.getEntity();
		
		//if(entity instanceof Player) {
			//Player healPlayer = ((Player) entity).getPlayer();
			//int actualLife = (int) healPlayer.getHealth();
			//lifeScoreboard.module(healPlayer, actualLife);
		//}
		
		//else {
			//return;
		//}
		
		
		
	//}
	
}

