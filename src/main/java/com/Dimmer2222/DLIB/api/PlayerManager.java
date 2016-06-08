package com.Dimmer2222.DLIB.api;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

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

public class PlayerManager {

    public static Player getPlayer(String PlayerName) {
        Player player = null;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().equalsIgnoreCase(PlayerName))
                player = p;
        }
        return player;
    }

    public static OfflinePlayer getOfflinePlayer(String PlayerName) {
        OfflinePlayer offlinePlayer = null;
        for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
            if (p.getName().equalsIgnoreCase(PlayerName))
                offlinePlayer = p;
        }
        return offlinePlayer;
    }

    public static Player getPlayer(UUID PlayerUUID){
        Player player = null;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getUniqueId().equals(PlayerUUID))
                player = p;
        }
        return player;
    }

    public static OfflinePlayer getOfflinePlayer(UUID PlayerUUID){
        OfflinePlayer offlinePlayer = null;
        for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
            if (p.getUniqueId().equals(PlayerUUID))
                offlinePlayer = p;
        }
        return offlinePlayer;
    }

    public static Player[] getPlayers(){
        Player[] p = (Player[]) Bukkit.getOnlinePlayers().toArray();
        return p;
    }

    public static OfflinePlayer[] getOfflinePlayers(){
        return Bukkit.getOfflinePlayers();
    }
}
