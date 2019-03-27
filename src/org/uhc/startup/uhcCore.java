package org.uhc.startup;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_13_R2.PacketPlayInChat;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;

public class uhcCore extends JavaPlugin implements Listener
{	
	//CONSTRUCTOR
	customCraftings customCraftings = new customCraftings(this);
	//lifeScoreboard lifeScoreboard = new lifeScoreboard(this);
	scoreboardCounterModule scoreboardCounterModule = new scoreboardCounterModule(this);
	uhcStartConfig uhcStartConfig = new uhcStartConfig(this);
	onPlayerDeathModule onPlayerDeathModule  = new onPlayerDeathModule(this);
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
		
		Bukkit.getPluginManager().registerEvents(this, this);
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
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		onPlayerDeathModule.setDeadGamemode(event);
		onPlayerDeathModule.setSkull(event);
	}
	
	
	@EventHandler
	public void outPortal(EntityPortalEnterEvent event) {
		if (event.getEntity() instanceof Player)  {
			Player player = (Player)event.getEntity();
			World world = player.getWorld();
			uhcStartConfig.setGamerules(world);
		}
		return;
	}
	
	@EventHandler
	public void onPlayerChat(ServerCommandEvent console) {
		if (console.getCommand().contains("has made the advancement")) {
			Bukkit.broadcastMessage("ETS TONTO");
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		injectPlayer(event.getPlayer());
		return;
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		rmPlayer(event.getPlayer());
		return;
	}
	
	public void rmPlayer(Player player) {
		Channel channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
		channel.eventLoop().submit(() -> {
			channel.pipeline().remove(player.getName());
			return null;
		});
	}
	
	public void injectPlayer(Player player) {
		ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler(){
			@Override
			public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
				if(packet instanceof PacketPlayInChat) {
					//Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "R-IN <--" + ChatColor.GREEN + packet.toString());
				}
				super.channelRead(channelHandlerContext, packet);
			}
			
			@Override
			public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {	
				if(packet instanceof PacketPlayOutChat) {
					Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "W-OUT -->" + ChatColor.RED + packet.toString());
					//TODO
					String packetString = null;
					Bukkit.broadcastMessage(" " + packetString);
					if(packetString.contains("achievement")) {
						//Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "!===! BLOCKED !===!" + ChatColor.BLUE + packetPlayInChat.toString());
						return;
					}
				}
				super.write(channelHandlerContext, packet, channelPromise);
			}
		};
		ChannelPipeline pipe = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
		pipe.addBefore("packet_handler", player.getName(), channelDuplexHandler);
	}
	
}
