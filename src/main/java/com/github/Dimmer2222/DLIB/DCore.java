package com.github.Dimmer2222.DLIB;

import org.bukkit.Bukkit;

public class DCore {


    /**
     * Check if the DLIB is installed on your Server if it fails its give you a Exception that means that the Method and the Class is not found
     * @return if the DLIB is installed on the server it returns true otherwise it's return false
     */

   public static boolean isDLIBInstalled(){
        if(Bukkit.getServer().getPluginManager().getPlugin("DLIB") != null){
            return true;
        }
        return false;
    }

    /**
     * Get a instance of a class that implements the DLIB
     * @param classfromDLIB Instance of the Class that implements the DLIB
     * @return Instance of the DLIB interface
     */

   public static DLIB getInstance(DLIB classfromDLIB){
        return Main.DLIB.get(classfromDLIB.getClass().getPackage().getName() + "." + classfromDLIB.getClass().getName());
    }

    /**
     * Register a Class that implements the DLIB interface
     * @param dlib Class that implements the DLIB interface
     */

    public static void registerDLIB(DLIB dlib){
        Main.DLIB.put(dlib.getClass().getPackage().getName() + "." + dlib.getClass().getName(), dlib);
    }


}
