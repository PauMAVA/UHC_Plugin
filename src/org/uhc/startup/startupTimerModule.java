package org.uhc.startup;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class startupTimerModule extends BukkitRunnable {
	int time_left;
	private int taskID;
	//CONSTRUCTOR-----------------------------------
	uhcStartCmd directPlugin;
	public startupTimerModule(uhcStartCmd passedPlugin) {
		this.directPlugin = passedPlugin;
	}
	
	//CONSTRUCTOR-----------------------------------
	public int getAmount(String amount) {
		int isValid = varTypeErrorHandler(amount);
		if (isValid == -1) {
			return -1;
		}
		else {
			time_left = Integer.parseInt(amount);
			if (time_left < 1) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}

	private int varTypeErrorHandler(String amount) {
		try {
			time_left = Integer.parseInt(amount);
		}
		catch (NumberFormatException nfe) {
			return -1;
		}
		return 0;
	} 	

	public int getTaskId(int passedTaskID) {
		taskID = passedTaskID;
		Bukkit.broadcastMessage("Task ID is (instant): " + taskID);
		return taskID;
	}


		@Override
		public void run() {	
			ChatColor timeColor;
			float pitch;
			if (time_left != 0) {
				if (time_left <= 3) {
					timeColor = ChatColor.RED;
					pitch = (float) (1.8 - (time_left * 0.25));
				}
			else {
				timeColor = ChatColor.GREEN;
				pitch = 1;
			}
			Bukkit.broadcastMessage(ChatColor.GOLD + "STARTING UHC IN " + timeColor+ time_left);
			for (Player everyone: Bukkit.getOnlinePlayers()) {
				everyone.playSound(everyone.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20.0F, pitch);
			}
			time_left = time_left - 1;	
			}
			else {
			Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "title @a title {\"text\":\" STARTING UHC!\",\"color\":\"green\"}");
			for (Player everyone: Bukkit.getOnlinePlayers()) {
				everyone.playSound(everyone.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 20.0F, 1.0F);
			}
			Bukkit.getScheduler().cancelTask(taskID);
			}
		}

}