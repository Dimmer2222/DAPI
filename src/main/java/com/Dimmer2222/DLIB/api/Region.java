package com.Dimmer2222.DLIB.api;

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
