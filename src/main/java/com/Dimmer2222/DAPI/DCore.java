package com.Dimmer2222.DAPI;

import org.bukkit.Bukkit;

public class DCore {


    /**
     * Check if the DAPI is installed on your Server if it fails its give you a Exception that means that the Method and the Class is not found
     * @return if the DAPI is installed on the server it returns true otherwise it's return false
     */

   public static boolean isDAPIInstalled(){
        if(Bukkit.getServer().getPluginManager().getPlugin("DAPI") != null){
            return true;
        }
        return false;
    }

    /**
     * Get a instance of a class that implements the DAPI
     * @param classfromDAPI Instance of the Class that implements the DAPI
     * @return Instance of the DAPI interface
     */

   public static DAPI getInstance(DAPI classfromDAPI){
        return Main.dapi.get(classfromDAPI.getClass().getPackage().getName() + "." + classfromDAPI.getClass().getName());
    }

    /**
     * Register a Class that implements the DAPI interface
     * @param dapi Class that implements the DAPI interface
     */

    public static void registerDAPI(DAPI dapi){
        Main.dapi.put(dapi.getClass().getPackage().getName() + "." + dapi.getClass().getName(), dapi);
    }

}
