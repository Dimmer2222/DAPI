package com.Dimmer2222.DAPI.api;

import java.io.File;
import java.io.IOException;


import com.Dimmer2222.DAPI.exceptions.ConfigNotSetException;
import com.Dimmer2222.DAPI.exceptions.CoreException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

/*
The MIT License (MIT)

Copyright (c) 2016 Dimmer2222

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

public class WarpManager {

	File file;
	YamlConfiguration config;


	public WarpManager(String filepath, String filename) {
		file = new File(filepath, filename);
		config = YamlConfiguration.loadConfiguration(file);
	}

	public void createWarp(String WarpName, Location location) throws ConfigNotSetException{
		save(false);
		WarpName = WarpName.toLowerCase();
		if(config.get("Warp.WarpName." + WarpName) == null){
			config.set("Warp.WarpName." + WarpName + ".world", location.getWorld().getName());
			config.set("Warp.WarpName." + WarpName + ".x", location.getX());
			config.set("Warp.WarpName." + WarpName + ".y", location.getY());
			config.set("Warp.WarpName." + WarpName + ".z", location.getZ());
			config.set("Warp.WarpName." + WarpName + ".yaw", location.getYaw());
			config.set("Warp.WarpName." + WarpName + ".pitch", location.getPitch());
			save(true);
		}else{
			throw new ConfigNotSetException();
		}
	}

	public Location getWarp(String WarpName) {
		save(false);
		WarpName = WarpName.toLowerCase();
		if (config.get("Warp.WarpName." + WarpName) != null) {
			Location loc = new Location(Bukkit.getWorld(config.getString("Warp.WarpName." + WarpName + ".world")), config.getDouble("Warp.WarpName." + WarpName + ".x"),
					config.getDouble("Warp.WarpName." + WarpName + ".y"), config.getDouble("Warp.WarpName." + WarpName + ".z"),
					(float) config.getDouble("Warp.WarpName." + WarpName + ".yaw"), (float) config.getDouble("Warp.WarpName." + WarpName + ".pitch"));
			return loc;
		} else {
			return null;
		}

	}

	public String getWarps() {
		StringBuilder sb = new StringBuilder("");

			for(String warps : config.getConfigurationSection("Warp").getConfigurationSection("WarpName").getKeys(false)){

				sb.append(warps + " ");

		}

		return sb.toString();

	}

	public void deleteWarp(String WarpName) throws ConfigNotSetException{
		save(false);
  	  	WarpName = WarpName.toLowerCase();
		if(config.get("Warp.WarpName." + WarpName) != null){
			config.set("Warp.WarpName." + WarpName, null);
			save(true);
		}else{
			throw new ConfigNotSetException();
		}

	}

	private void save(boolean b){
		try{
		if(!file.exists()){
			file.createNewFile();
		}
			if(b)
		config.save(file);
	}catch (IOException e){
			e.printStackTrace();
		}
	}

	}


