package com.Dimmer2222.DLIB.api.Implements;

import com.Dimmer2222.DLIB.exceptions.ValueExistException;
import com.Dimmer2222.DLIB.exceptions.ValueNotExistException;
import com.Dimmer2222.DLIB.interfaces.Special;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


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

public class SpecialBlockManager implements Special<Block> {

    File file;
    FileConfiguration config;

    public SpecialBlockManager(String Path, String Name) {
        file = new File(Path, Name);
        config = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public void createSpecialObject(Block object, String name) throws ValueExistException{
        save(false);
        if(config.get("SpecialBlock." + name.toLowerCase()) != null){
            throw new ValueExistException();
        }
        config.set("SpecialBlock." + name.toLowerCase(), name.toLowerCase());
        config.set("SpecialBlock." + name.toLowerCase() + ".world", object.getWorld().getName());
        config.set("SpecialBlock." + name.toLowerCase() + ".x", object.getX());
        config.set("SpecialBlock." + name.toLowerCase() + ".y", object.getY());
        config.set("SpecialBlock." + name.toLowerCase() + ".z", object.getZ());
        save(true);
    }

    public void createSpecialObject(Material object, Location loc, String name) throws ValueExistException{
        save(false);
        if(config.get("SpecialBlock." + name.toLowerCase()) != null){
            throw new ValueExistException();
        }
        loc.getBlock().setType(object);
        config.set("SpecialBlock." + name.toLowerCase(), name.toLowerCase());
        config.set("SpecialBlock." + name.toLowerCase() + ".world", loc.getWorld().getName());
        config.set("SpecialBlock." + name.toLowerCase() + ".x", loc.getBlock().getX());
        config.set("SpecialBlock." + name.toLowerCase() + ".y", loc.getBlock().getY());
        config.set("SpecialBlock." + name.toLowerCase() + ".z", loc.getBlock().getZ());
        save(true);
    }

    @Override
    public void createSpecialObject(Location loc, String name) throws ValueExistException {
        save(false);
        if(config.get("SpecialBlock." + name.toLowerCase()) != null){
            throw new ValueExistException();
        }
        config.set("SpecialBlock." + name.toLowerCase(), name.toLowerCase());
        config.set("SpecialBlock." + name.toLowerCase() + ".world", loc.getWorld().getName());
        config.set("SpecialBlock." + name.toLowerCase() + ".x", loc.getBlock().getX());
        config.set("SpecialBlock." + name.toLowerCase() + ".y", loc.getBlock().getY());
        config.set("SpecialBlock." + name.toLowerCase() + ".z", loc.getBlock().getZ());
        save(true);
    }


    @Override
    public Block getSpecialObject(String Name) throws ValueNotExistException {
        save(false);
        if(config.get("SpecialBlock." + Name.toLowerCase()) == null){
            throw new ValueNotExistException();
        }


        return new Location(Bukkit.getWorld(config.getString("SpecialBlock." + Name.toLowerCase() + ".world")) ,config.getDouble("SpecialBlock." + Name.toLowerCase() + ".x"), config.getDouble("SpecialBlock." + Name.toLowerCase() + ".y"), config.getDouble("SpecialBlock." + Name.toLowerCase() + ".z")).getBlock();
    }

    @Override
    public Set<Block> getSpecialObjects() {
        save(false);
        Set<Block> blocks = new HashSet<>();
        for(String name : config.getConfigurationSection("SpecialBlock").getKeys(false)){
            blocks.add(new Location(Bukkit.getWorld(config.getString("SpecialBlock." + name.toLowerCase() + ".world")) ,config.getDouble("SpecialBlock." + name.toLowerCase() + "x"), config.getDouble("SpecialBlock." + name.toLowerCase() + "y"), config.getDouble("SpecialBlock." + name.toLowerCase() + "z")).getBlock());
        }
        return blocks;
    }

    @Override
    public void deleteSpecialObject(String name) throws ValueNotExistException {
        save(false);
        if(config.get("SpecialBlock." + name.toLowerCase()) == null) {
                throw new ValueNotExistException();
            }
                config.set("SpecialBlock." + name.toLowerCase(), null);
        }

    @Override
    public Location getSpecialObjectLocation(String name) throws ValueNotExistException {
        save(false);
        if(config.get("SpecialBlock." + name.toLowerCase()) == null){
            throw new ValueNotExistException();
        }



        return new Location(Bukkit.getWorld(config.getString("SpecialBlock." + name.toLowerCase() + ".world")) ,config.getDouble("SpecialBlock." + name.toLowerCase() + ".x"), config.getDouble("SpecialBlock." + name.toLowerCase() + ".y"), config.getDouble("SpecialBlock." + name.toLowerCase() + ".z"));

    }


    private void save(boolean b){
        try{
        if(!file.exists()) {
                file.createNewFile();
        }
            if (b) {
                config.save(file);
            }
            }catch (IOException e){
            e.printStackTrace();
        }
    }
}


