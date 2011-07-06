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
package com.massivedynamics.spawny;

import com.nijiko.permissions.PermissionHandler;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A plugin class that sets up this plugin
 * @author Cruz Bishop
 * @version 1.0.0.0
 */
public class SpawnyPlugin extends JavaPlugin {
    
    /**
     * The permission handler
     */
    private PermissionHandler permissionHandler;

    /**
     * Gets the permission handler
     * @return The permission handler
     */
    public PermissionHandler getPermissionsHandler() {
        return permissionHandler;
    }
    /**
     * The instance of this class
     */
    private static SpawnyPlugin instance;

    /**
     * Gets the SpawnyPlugin instance
     * @return The instance
     */
    public static SpawnyPlugin getInstance() {
        if (instance == null) {
            instance = new SpawnyPlugin();
        }
        return instance;
    }

    @Override
    public void onDisable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onEnable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
