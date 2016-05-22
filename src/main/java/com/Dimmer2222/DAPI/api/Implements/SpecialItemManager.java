package com.Dimmer2222.DAPI.api.Implements;

import com.Dimmer2222.DAPI.exceptions.ValueExistException;
import com.Dimmer2222.DAPI.exceptions.ValueNotExistException;
import com.Dimmer2222.DAPI.interfaces.Special;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

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

public class SpecialItemManager implements Special<Item>{



    File file;
    FileConfiguration config;

    public SpecialItemManager(String Path, String File){
        file = new File(Path, File);
        config = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public void createSpecialObject(Item object, String name) throws ValueExistException{
        save(false);
        if(config.get("SpecialItem." + name.toLowerCase()) != null){
            throw new ValueExistException();
        }
        config.set("SpecialItem." + name.toLowerCase(), name.toLowerCase());
        config.set("SpecialItem." + name.toLowerCase() + ".world", object.getWorld().getName());
        config.set("SpecialItem." + name.toLowerCase() + ".x", object.getLocation().getX());
        config.set("SpecialItem." + name.toLowerCase() + ".y", object.getLocation().getY());
        config.set("SpecialItem." + name.toLowerCase() + ".z", object.getLocation().getZ());
        save(true);
    }

    @Override
    public void createSpecialObject(Location loc, String name) throws ValueExistException {
        save(false);
        if(config.get("SpecialItem." + name.toLowerCase()) != null){
            throw new ValueExistException();
        }
        loc.getWorld().spawnEntity(loc, EntityType.DROPPED_ITEM);
        config.set("SpecialItem." + name.toLowerCase() + ".world", loc.getWorld().getName());
        config.set("SpecialItem." + name.toLowerCase() + ".x", loc.getX());
        config.set("SpecialItem." + name.toLowerCase() + ".y", loc.getY());
        config.set("SpecialItem." + name.toLowerCase() + ".z", loc.getZ());
        save(true);
    }


    @Override
    public Item getSpecialObject(String Name) throws ValueNotExistException {
        save(false);
        if(config.get("SpecialItem." + Name.toLowerCase()) == null){
            throw new ValueNotExistException();
        }

        for(Item item : getSpecialObjects()){
            if(item.getLocation().equals(getSpecialObjectLocation(Name))){
                return item;
            }
        }

        return null;
    }

    @Override
    public Set<Item> getSpecialObjects() {
        save(false);
        Set<Item> items = new HashSet<>();
        for (String name : config.getConfigurationSection("SpecialItem").getKeys(false)) {
            try {
                for (Entity entity : getEntities(getSpecialObjectLocation(name), 1)) {
                    if(entity instanceof Item){
                        items.add((Item) entity);
                    }
                }
            } catch (ValueNotExistException e) {
                e.printStackTrace();
            }
        }
        return items;
    }

    @Override
    public void deleteSpecialObject(String name) throws ValueNotExistException {
        save(false);
        if(config.get("SpecialItem." + name.toLowerCase()) == null) {
            throw new ValueNotExistException();
        }
        config.set("SpecialItem." + name.toLowerCase(), null);
        save(true);
    }

    @Override
    public Location getSpecialObjectLocation(String name) throws ValueNotExistException {
        save(false);
        if(config.get("SpecialItem." + name.toLowerCase()) == null){
            throw new ValueNotExistException();
        }



        return new Location(Bukkit.getWorld(config.getString("SpecialItem." + name.toLowerCase() + ".world")) ,config.getInt("SpecialItem." + name.toLowerCase() + ".x"), config.getInt("SpecialItem." + name.toLowerCase() + ".y"), config.getInt("SpecialItem." + name.toLowerCase() + ".z"));

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

    private Set<Entity> getEntities(Location loc, int range){
        Set<Entity> entities = new HashSet<>();
        for(Entity entity : loc.getWorld().getEntities()){
            if(entity.getLocation().getX() <= (loc.getX() + range) || entity.getLocation().getY() <= (loc.getY() + range) || entity.getLocation().getZ() <= (loc.getZ() + range)){
                entities.add(entity);
            }

        }
        return entities;
    }

}
