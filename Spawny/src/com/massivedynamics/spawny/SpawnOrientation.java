package com.massivedynamics.spawny;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * class for saving & retrieving the spawn orientations
 * @author Jacob
 */
public class SpawnOrientation {

	final SpawnyPlugin plugin;
	final File saveFile;
	final Map<String, Float> directions = new HashMap<String, Float>();

	public SpawnOrientation(SpawnyPlugin plugin) {
		this.plugin = plugin;
		File test = new File(plugin.getDataFolder(), "spawn_directions");
		if (!test.exists()) {
			boolean fileOK = true;
			try {
				if (!plugin.getDataFolder().exists()) {
					if (!plugin.getDataFolder().mkdirs()) {
						fileOK = false;
					}
				}
				if (!test.createNewFile()) {
					fileOK = false;
				}
			} catch (Exception ex) {
				fileOK = false;
			}
			if (!fileOK) {
				plugin.getLogger().warning("[Spawny] could not create save file - spawn will not include orientations");
				saveFile = null;
				return;
			}
		}
		if (!test.canRead() || !test.canWrite()) {
			plugin.getLogger().warning("[Spawny] no permissions to use save file - spawn will not include orientations");
			saveFile = null;
			return;
		}
		saveFile = test;
		FileReader fstream = null;
		BufferedReader in = null;
		try {
			fstream = new FileReader(saveFile.getAbsolutePath());
			in = new BufferedReader(fstream);
			int n = 0;
			for (String line = null; (line = in.readLine()) != null && line.length() > 0; ++n) {
				// if was edited in openoffice, will instead have semicolins..
				String[] l = line.replace(";", ",").replace(",,", ", ,").split(",");
				if (l.length == 2 && isFloat(l[1])) {
					float y = Float.parseFloat(l[1]);
					//if(0<=y && y<4)
					directions.put(l[0], y);
				}
			}
		} catch (Exception e) {
			plugin.getLogger().warning("[Spawny] could not read save file - spawn will not include orientations");
		} finally {
			try {
				if (in != null) in.close();
				if (fstream != null) fstream.close();
			} catch (IOException ex) {
			}
		}
	}

	public float getDirection(String world) {
		//System.out.println(world + " is " + directions.get(world));
		return directions.containsKey(world) ? directions.get(world) : 0;
	}

	public void setDirection(String world, float y) {
		directions.put(world, y);
		save();
	}

	public void save() {
		if (saveFile != null) {
			try {
				FileWriter fstream = null;
				fstream = new FileWriter(saveFile.getAbsolutePath());
				//System.out.println("writing to " + tosave.getAbsolutePath());
				BufferedWriter out = new BufferedWriter(fstream);
				for (Map.Entry e : directions.entrySet()) {
					out.write(e.getKey() + "," + e.getValue());
					out.newLine();
				}
				out.flush();
				out.close();
			} catch (Exception e) {
				plugin.getLogger().log(Level.WARNING, "[Spawny] could not save orientation file", e);
			}
		}
	}

	static boolean isFloat(String s) {
		try {
			Float.parseFloat(s);
			return true;
		} catch (Throwable t) {
			return false;
		}
	}
}
