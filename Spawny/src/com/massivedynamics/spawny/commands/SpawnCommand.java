/*
 * Copyright (C) 2011 Massive Dynamics
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.massivedynamics.spawny.commands;

import com.massivedynamics.spawny.SpawnyPlugin;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The spawn command
 * @author Cruz Bishop
 * @version 1.1.1.0
 */
public class SpawnCommand implements CommandExecutor {

    /**
     * Called when the command is executed
     * @param cs The command sender
     * @param cmnd The command
     * @param label The label
     * @param arguments The arguments
     * @return True if execution was successful
     */
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String label, String[] arguments) {

        try {

            //Is it not a player?
            if (!(cs instanceof Player)) {
                //Damn. Letting you know
                cs.sendMessage("Spawns can only be used by a player - sorry!");
                //Bye :)
                return true;
            }

            //Get the player variable
            Player player = (Player) cs;

            //Check to see if the player can spawn
            if (!player.hasPermission("spawny.spawn")) {
                //Nope. Notify
                cs.sendMessage(ChatColor.RED + "You do not have permission to spawn!");
                //And return
                return true;
            }

            //Get the player's world
            World world = player.getWorld();

            //See if the world's name is included
            if (arguments.length > 0) {
                //Get the world
                world = player.getServer().getWorld(arguments[0]);
                //See if the world exists
                if (world == null) {
                    //Nope. Notify
                    cs.sendMessage(ChatColor.RED + "The world \"" + arguments[0] + "\" does not exist!");
                    //And return
                    return true;
                }
            }

            //Teleport
            player.teleport(world.getSpawnLocation());

            //And notify
            player.sendMessage(ChatColor.GREEN + "You returned to the spawn point");

            return true;

        } catch (Exception e) {
            //Oh shit, something went wrong!
            //Letting the console know
            SpawnyPlugin.getInstance().getLogger().severe("Could not spawn  - " + e.getMessage() + " in " + e.getCause());
            //Letting the player know
            cs.sendMessage("Oh, something went wrong while spawning. Please try again later!");
            return true;
        }

    }
}
