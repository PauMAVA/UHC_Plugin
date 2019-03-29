package org.uhc.startup;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;

public class packetBlocker implements Listener {
	
	//CONSTRUCTOR-----------------------------------
	uhcCore directPlugin;
	public packetBlocker(uhcCore passedPlugin) {
		this.directPlugin = passedPlugin;
	}
	//CONSTRUCTOR-----------------------------------
	public void packetHandler(Player player) {
		
		ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
			
			//@Override
			//public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
				
				//return;
			//}
			
			@Override
			public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
				if(packet instanceof PacketPlayOutChat && uhcCore.getInstance().getConfig().getInt("status.nether") == 0) {
					Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "PACKET BLOCKED !==! " + ChatColor.BLUE + packet.toString());
					return;
				}
				super.write(channelHandlerContext, packet, channelPromise);
			}
			
			
		};
		ChannelPipeline channelPipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
		channelPipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
		return;
	}
	
	public void addPlayer(Player player) {
		try {
			wait(15000);
		} catch (InterruptedException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "packetBlocker.java throws" + e.toString() + "on line 48!");
		}
		removePlayer(player);
		packetHandler(player);
		return;
	}
	
	public void removePlayer(Player player) {
		Channel channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
		channel.eventLoop().submit(() -> {
			channel.pipeline().remove(player.getName());
			return null;
		});
		if(uhcCore.getInstance().getConfig().getInt("status.nether") == 0) {
			uhcCore.getInstance().getConfig().set("status.nether", 1);
		}
		return;
	}

}
