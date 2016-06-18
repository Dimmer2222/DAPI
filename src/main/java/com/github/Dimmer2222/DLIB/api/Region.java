package com.github.Dimmer2222.DLIB.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

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

public class Region implements ConfigurationSerializable {

    private int minx, miny, minz, maxx, maxy, maxz = 0;
    private World world = null;
    private World world2 = null;
    private String Name;

    /**
     * Create a Object of this Class and use the Locations to create min und max values.
     * @param Name Name of the Region this use my RegionManager Class
     * @param loc1 Position one of the Region
     * @param loc2 Position two of the Region
     */
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

    /**
     * Get the min X Value
     * @return The min X Value
     */
    public int getminX(){
        return minx;
    }


    /**
     * Get the min Y Value
     * @return The min Y Value
     */
    public int getminY(){
        return miny;

    }

    /**
     * Get the min Z Value
     * @return The min Z Value
     */
    public int getminZ(){
        return minz;
    }

    /**
     * Get the max X Value
     * @return The max X Value
     */
    public int getmaxX(){
        return maxx;
    }

    /**
     * Get the max Y Value
     * @return The max Y Value
     */
    public int getmaxY(){
        return maxy;
    }

    /**
     * Get the max Z Value
     * @return The max Z Value
     */
    public int getmaxZ(){
        return maxz;
    }

    /**
     * Get the first World in the first Location.
     * @return The Name of the World
     */

    public String getWorld1(){
        return world.getName();
    }

    /**
     * Get the second World of the second Location.
     * @return The Name of the World.
     */
    public String getWorld2(){
        if(world2 == null)
        return world.getName();
        return world2.getName();
    }

    /**
     * Get the Name of the Region
     * @return The Name of the Region
     */
    public String getName(){
        return Name;
    }


    /**
     * Find out if a Player is in the Region
     * @param loc Player Location
     * @return if the Player is in a Region it return true if it is false otherwise.
     */
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
