package com.Dimmer2222.DAPI.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

public class Region implements ConfigurationSerializable {

    private int minx, miny, minz, maxx, maxy, maxz = 0;
    private World world = null;
    private World world2 = null;
    private String Name;

    public Region(String Name , Location loc1, Location loc2){
        this.minx = Math.min(loc1.getBlockX(), loc2.getBlockX());
        this.miny = Math.min(loc1.getBlockY(), loc2.getBlockY());
        this.minz = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
        this.maxx = Math.max(loc1.getBlockX(), loc2.getBlockX());
        this.maxy = Math.max(loc1.getBlockY(), loc2.getBlockY());
        this.maxz = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
        this.world = loc1.getWorld();
        this.world2 = loc2.getWorld();
        this.Name = Name;

    }

    public int getminX(){
        return minx;
    }

    public int getminY(){
        return miny;

    }

    public int getminZ(){
        return minz;
    }

    public int getmaxX(){
        return maxx;
    }

    public int getmaxY(){
        return maxy;
    }

    public int getmaxZ(){
        return maxz;
    }

    public String getWorld1(){
        return world.getName();
    }

    public String getWorld2(){
        if(world2 == null)
        return world.getName();
        return world2.getName();
    }

    public String getName(){
        return Name;
    }

    public boolean insideRegion(Location loc) {
        if (!loc.getWorld().getName().equalsIgnoreCase(getWorld1())) {
            return false;
        }

        if (    loc.getBlockX() >= this.minx && loc.getBlockX() <= this.maxx &&
                loc.getBlockY() >= this.miny && loc.getBlockY() <= this.maxy &&
                loc.getBlockZ() >= this.minz && loc.getBlockZ() <= this.maxz){
            return true;
        }


                    return false;

                }



    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("world", world.getName());
        map.put("minx", minx);
        map.put("miny", miny);
        map.put("minz", minz);
        map.put("maxx", maxx);
        map.put("maxy", maxy);
        map.put("maxz", maxz);
        return map;
    }
}
