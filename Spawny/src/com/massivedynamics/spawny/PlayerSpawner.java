package com.massivedynamics.spawny;

import java.io.File;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * automatically spawn players (with exact positioning and orientation)
 * @author jascotty2
 */
public class PlayerSpawner extends PlayerListener {

	final SpawnyPlugin plugin;

	public PlayerSpawner(SpawnyPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void onPlayerRespawn(final PlayerRespawnEvent event) {
		if (!event.isBedSpawn()) {
			// to get positioning right, needs to fire after minecraft's spawn positioning
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){

				@Override
				public void run() {
					SpawnyPlugin.spawn(event.getPlayer());
				}
			}, 1);
		}
	}

	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		File dat = new File(plugin.getServer().getWorlds().get(0).getName() + File.separator + "players" + File.separator + event.getPlayer().getName() + ".dat");
		// if this is the first join, ensure that the spawn positioning is correct
		if (!dat.exists()) {
			SpawnyPlugin.spawn(event.getPlayer());
		}
	}
	
}
