package org.uhc.startup;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class uhcStartCmd implements CommandExecutor {
	//CONSTRUCTOR-----------------------------------
	uhcCore directPlugin;
	public uhcStartCmd(uhcCore passedPlugin) {
		this.directPlugin = passedPlugin;
	}
	startupTimerModule startupTimerModule = new startupTimerModule(this);
	uhcStartConfig uhcStartConfig = new uhcStartConfig(this);
	scoreboardCounterModule scoreboardCounterModule = new scoreboardCounterModule(this);
	//CONSTRUCTOR-----------------------------------
	
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String args[])
	{
		if (args.length == 0) {
			Bukkit.broadcastMessage(ChatColor.RED + "Time argument missing! Usage: /uhcstart <time>");
			return false;
		}
		else {
		
			if (commandLabel.equalsIgnoreCase("uhcstart"))
			{
				String amount = args[0];
				int amountInt = Integer.parseInt(amount);
				int startupTimerModuleErrorHandler = startupTimerModule.getAmount(amount);
				if (startupTimerModuleErrorHandler == -1) {
					Bukkit.broadcastMessage(ChatColor.RED + "Error handler returned an error status: Time argument must be a positive integer!");
				}
				else {
					
					int passedTaskID = startupTimerModule.runTaskTimer(this.directPlugin, 0L, 20L).getTaskId();
					startupTimerModule.getTaskId(passedTaskID);
					Bukkit.getScheduler().scheduleSyncDelayedTask(this.directPlugin, new Runnable() {
						@Override
						public void run() {
							uhcStartConfig.module();
							scoreboardCounterModule.module();
						}
						
					}, amountInt * 20);
							
							
					}
				}				
			}
		
		return true;
	}
}
