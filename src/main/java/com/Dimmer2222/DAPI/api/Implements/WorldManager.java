package com.Dimmer2222.DAPI.api.Implements;

import com.Dimmer2222.DAPI.exceptions.WorldError;
import com.Dimmer2222.DAPI.interfaces.WorldGenerator;
import org.bukkit.*;
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

public class WorldManager implements WorldGenerator {


    File file;
    FileConfiguration config;

    public WorldManager(String Path, String File){
        file = new File(Path, File);
        config = YamlConfiguration.loadConfiguration(file);
        setup();
    }

    @Override
    public void generateWorld(String worldName, World.Environment environment, WorldType worldType) throws WorldError{
        save(false);
        if(config.get("World.WorldName." + worldName.toLowerCase()) != null){
            throw new WorldError();
        }

        save(false);
        Bukkit.createWorld(WorldCreator.name(worldName).environment(environment).type(worldType));
        config.set("World.WorldName." + worldName.toLowerCase(), worldName.toLowerCase());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.pvp", Bukkit.getWorld(worldName).getPVP());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.spawn", Bukkit.getWorld(worldName).getSpawnLocation().serialize());
        config.set("World.WorldName." + worldName.toLowerCase() + ".worldFolder", Bukkit.getWorld(worldName).getWorldFolder().toString());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.animals", Bukkit.getWorld(worldName).getAllowAnimals());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.monster", Bukkit.getWorld(worldName).getAllowMonsters());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.difficulty", Bukkit.getWorld(worldName).getDifficulty());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.type", Bukkit.getWorld(worldName).getWorldType());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.environment", Bukkit.getWorld(worldName).getEnvironment());
        save(true);
    }

    private void setup() {
        for (String worldName : config.getConfigurationSection("World.WorldName").getKeys(false)){
            setPvP(worldName, config.getBoolean("World.WorldName." + worldName.toLowerCase() + ".properties.pvp"));
            setSpawnLocation(worldName, new Location(Bukkit.getWorld(config.getString("World.WorldName." + worldName.toLowerCase()) + ".properties.spawn.world") ,config.getDouble("World.WorldName." + worldName.toLowerCase() + ".properties.spawn.X"), config.getDouble("World.WorldName." + worldName.toLowerCase() + ".properties.spawn.Y"), config.getDouble("World.WorldName." + worldName.toLowerCase() + ".properties.spawn.Z"), (float) config.getDouble("World.WorldName." + worldName.toLowerCase() + ".properties.spawn.yaw"), (float) config.getDouble("World.WorldName." + config.getDouble(worldName.toLowerCase() + ".properties.spawn.pitch"))));
            setAnimals(worldName, config.getBoolean("World.WorldName." + worldName.toLowerCase() + ".properties.animals"));
            setMonster(worldName, config.getBoolean("World.WorldName." + worldName.toLowerCase() + ".properties.monster"));
            setDifficulty(worldName, (Difficulty) config.get("World.WorldName." + worldName.toLowerCase() + ".properties.difficulty"));
    }
    }


    @Override
    public Set<String> getWorlds() throws WorldError{
        save(false);
        Set<String> worlds = new HashSet<>();
        if(config.getConfigurationSection("World.WorldName").getKeys(false).isEmpty()){
            throw new WorldError();
        }
        for(String string : config.getConfigurationSection("World.WorldName").getKeys(false)){
           worlds.add(string);
        }

        return worlds;
    }

    @Override
    public boolean isPvP(String worldName) throws WorldError{
        save(false);
        if(getWorlds().contains(worldName.toLowerCase())){

        }else{
            throw new WorldError();
        }


        for(String world : getWorlds()){
            if(world.equalsIgnoreCase(worldName))
            return Bukkit.getWorld(world).getPVP();
        }
        return false;
    }

    @Override
    public Location getSpawnLocation(String worldName) throws WorldError{
        save(false);
        if(getWorlds().contains(worldName.toLowerCase())){
           throw new WorldError();
        }

        try{
            for(String world : getWorlds()){
                if(world.equalsIgnoreCase(worldName))
                    return Bukkit.getWorld(worldName.toLowerCase()).getSpawnLocation();
            }

        }catch (WorldError e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public File getWorldFolder(String worldName){
        save(false);
        return new File(config.getString("World.WorldName." + worldName.toLowerCase() + ".worldFolder"));
    }

    public boolean isAnimals(String worldName){
        save(false);
        try{
        for(String world : getWorlds()){
            if(world.equalsIgnoreCase(worldName))
                return Bukkit.getWorld(worldName.toLowerCase()).getAllowAnimals();
        }

        }catch (WorldError e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isMonsters(String worldName){
        save(false);
        try{
            for(String world : getWorlds()){
                if(world.equalsIgnoreCase(worldName))
                    return Bukkit.getWorld(worldName.toLowerCase()).getAllowMonsters();
            }

        }catch (WorldError e){
            e.printStackTrace();
        }

        return false;
    }

    public Difficulty getDifficulty(String worldName){
        save(false);
        try{
            for(String world : getWorlds()){
                if(world.equalsIgnoreCase(worldName))
                    return Bukkit.getWorld(worldName.toLowerCase()).getDifficulty();
            }

        }catch (WorldError e){
            e.printStackTrace();
        }
        return null;
    }

    public WorldType getWorldType(String worldName){
        save(false);
        try{
            for(String world : getWorlds()){
                if(world.equalsIgnoreCase(worldName))
                    return Bukkit.getWorld(worldName.toLowerCase()).getWorldType();
            }

        }catch (WorldError e){
            e.printStackTrace();
        }
        return null;
    }

    public World.Environment getEnvironment(String worldName) {
        save(false);

        try{
            for(String world : getWorlds()){
                if(world.equalsIgnoreCase(worldName))
                    return Bukkit.getWorld(worldName.toLowerCase()).getEnvironment();
            }

        }catch (WorldError e){
            e.printStackTrace();
        }

        return null;
    }

    public void setPvP(String worldName, boolean PvP){
        save(false);
        try{
            for(String world : getWorlds()){
                if(world.equalsIgnoreCase(worldName))
                    Bukkit.getWorld(worldName.toLowerCase()).setPVP(PvP);
                config.set("World.WorldName." + worldName.toLowerCase() + ".properties.pvp", PvP);
            }

        }catch (WorldError e){
            e.printStackTrace();
        }
        save(true);
    }

    public void setSpawnLocation(String worldName, Location loc){
        save(false);
        try{
            for(String world : getWorlds()){
                if(world.equalsIgnoreCase(worldName))
                    Bukkit.getWorld(worldName.toLowerCase()).setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
                     config.set("World.WorldName." + worldName.toLowerCase() + ".spawn", new Location(Bukkit.getWorld(worldName.toLowerCase()) ,loc.getX(), loc.getY(), loc.getZ()).serialize());

            }

        }catch (WorldError e){
            e.printStackTrace();
        }

        save(true);

    }

    public void setAnimals(String worldName ,boolean active){
        save(false);
        try{
            for(String world : getWorlds()){
                if(world.equalsIgnoreCase(worldName))
                    Bukkit.getWorld(worldName.toLowerCase()).setSpawnFlags(config.getBoolean("World.WorldName." + worldName.toLowerCase() + "properties.monster"), active);
                config.set("World.WorldName." + worldName.toLowerCase() + ".properties.animals", active);

            }

        }catch (WorldError e){
            e.printStackTrace();
        }
        save(true);
    }

    public void setMonster(String worldName ,boolean active){
        save(false);
        try{
            for(String world : getWorlds()){
                if(world.equalsIgnoreCase(worldName))
                    Bukkit.getWorld(worldName.toLowerCase()).setSpawnFlags(active ,config.getBoolean("World.WorldName." + worldName.toLowerCase() + "properties.animals"));
                config.set("World.WorldName." + worldName.toLowerCase() + ".properties.monster", active);
            }

        }catch (WorldError e){
            e.printStackTrace();
        }
        save(true);
    }

    public void setDifficulty(String worldName ,Difficulty diff){
        save(false);
        try{
            for(String world : getWorlds()){
                if(world.equalsIgnoreCase(worldName))
                    Bukkit.getWorld(worldName.toLowerCase()).setDifficulty(diff);
                config.set("World.WorldName." + worldName.toLowerCase() + ".properties.difficulty", diff);

            }

        }catch (WorldError e){
            e.printStackTrace();
        }
        save(true);
    }


    @Override
    public World getWorld(String worldName) throws WorldError{
        save(false);

        if(config.get("World.WorldName." + worldName.toLowerCase()) == null){
            throw new WorldError();
        }

        return Bukkit.getWorld(worldName);
    }

    public void removefromConfig(String worldName) throws WorldError{
        save(false);

        if(config.get("World.WorldName." + worldName.toLowerCase()) == null){
            throw new WorldError();
        }

        config.set("World.WorldName." + worldName.toLowerCase(), null);
        save(true);
    }

    @Override
    public void deleteWorld(String worldName) throws WorldError{
        save(false);
        if(config.get("World.WorldName." + worldName.toLowerCase()) == null){
            throw new WorldError();
        }

        Bukkit.unloadWorld(worldName, false);
        File file = new File(config.getString("World.WorldName." + worldName.toLowerCase() + ".worldFolder"));
        file.delete();
        removefromConfig(worldName);
        save(true);
    }


    private void save(boolean b){
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (b) {
                config.save(file);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Object get(String Path){
        save(false);
        return config.get(Path);
    }
}
