package com.github.Dimmer2222.DLIB.api;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.github.Dimmer2222.DLIB.exceptions.ConfigNotSetException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

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

	public class PlayerConfig {

		private File file;
		private YamlConfiguration config;

		/**
		 * Create a Object of the Class
		 * @param FilePath Plugin Folder Path
		 * @param FileName File Name
		 */
		public PlayerConfig(String FilePath, String FileName) {
			file = new File(FilePath, FileName);
			config = YamlConfiguration.loadConfiguration(file);
		}


		/**
		 * Save all Player with the Boolean of false. If they don't exists.
         */
		public void savePlayers() {
			save(false);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (config.get("PlayerConfig.PlayerUUID." + p.getUniqueId()) == null) {
					config.set("PlayerConfig.PlayerUUID." + p.getUniqueId(), false);
					save(true);
				}
			}
		}

		/**
		 * This Method is to save and to generate a new File then no one exist.
		 * @param b Disable saving and generate a new File when no File exist by false, by true saving + checking if file exist
		 */
		private void save(boolean b) {
			try {
				if (!file.exists())
					file.createNewFile();
				if (b)
					config.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Get the Boolean from a Player that the Player have.
		 * @param PlayerUUID UUID of the Player
		 * @return The boolean that they Player have.
		 * @throws ConfigNotSetException It will thrown if the Player aren't saved.
         */
		public boolean getPlayerBoolean(UUID PlayerUUID) throws ConfigNotSetException {
			save(false);
			if (config.get("PlayerConfig.PlayerUUID." + PlayerUUID) != null) {
				return config.getBoolean("PlayerConfig.PlayerUUID." + PlayerUUID);
			} else {
				throw new ConfigNotSetException();
			}
		}

		/**
		 * With this method you can change the Player Boolean to false or true
 		 * @param PlayerUUID Name of the Player
		 * @param b Value of the Boolean
		 * @throws ConfigNotSetException If the Player aren't save.
         */
		public void changePlayerBoolean(UUID PlayerUUID, boolean b) throws ConfigNotSetException {
			save(false);
			if (config.get("PlayerConfig.PlayerUUID." + PlayerUUID) != null) {
				config.set("PlayerConfig.PlayerUUID." + PlayerUUID, b);
				save(true);
			} else {
				throw new ConfigNotSetException();
			}
		}
	}



