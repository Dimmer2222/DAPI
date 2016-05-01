package com.Dimmer2222.DAPI;

import org.bukkit.Bukkit;

public class DAPI {

    public static boolean isDAPIInstalled(){
        if(Bukkit.getServer().getPluginManager().getPlugin("DAPI") != null){
            return true;
        }
        return false;
    }

}
