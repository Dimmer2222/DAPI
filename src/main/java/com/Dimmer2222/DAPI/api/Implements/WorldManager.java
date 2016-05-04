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
    }

    @Override
    public void generateWorld(String worldName, World.Environment environment, WorldType worldType) throws WorldError{

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
        config.set("World.WorldName." + worldName.toLowerCase() + "world.enabled", true);
        save(true);
    }

    public void setup(){

    }

    @Override
    public Set<String> getWorlds() throws WorldError{
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
        return new File((String) config.get("World.WorldName." + worldName.toLowerCase() + ".worldFolder"));
    }

    public boolean isAnimals(String worldName){
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
        try{
            for(String world : getWorlds()){
                if(world.equalsIgnoreCase(worldName))
                    Bukkit.getWorld(worldName.toLowerCase()).setPVP(PvP);
            }

        }catch (WorldError e){
            e.printStackTrace();
        }
    }

    public void setSpawnLocation(String worldName, Location loc){

        try{
            for(String world : getWorlds()){
                if(world.equalsIgnoreCase(worldName))
                    Bukkit.getWorld(worldName.toLowerCase()).setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
            }

        }catch (WorldError e){
            e.printStackTrace();
        }

    }

    public void setAnimals(String worldName ,boolean active){
        try{
            for(String world : getWorlds()){
                if(world.equalsIgnoreCase(worldName))
                    Bukkit.getWorld(worldName.toLowerCase()).setSpawnFlags(config.getBoolean(""), active);
            }

        }catch (WorldError e){
            e.printStackTrace();
        }
    }

    public void setMonster(String worldName ,boolean active){

    }

    public void setDifficulty(String worldName ,Difficulty diff){

    }

    public void setWorldType(String worldName, WorldType worldType){

    }

    public void setEnvironment(String worldName, World.Environment environment){

    }

    @Override
    public void loadWorlds() {
        for(String name : config.getConfigurationSection("World.WorldName").getKeys(false)){
            if(Bukkit.getWorlds().contains(Bukkit.getWorld(name))) {
            } if(config.getBoolean("World.WorldName." + name + ".world.enabled")){

                }else {
                    Bukkit.unloadWorld(name, true);
                }
            }
        }


    @Override
    public World getWorld(String worldName) throws WorldError{

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
    public void disableWorld(boolean disable){
        if(disable == true){

        }else{

        }
    }

    @Override
    public void deleteWorld(String worldName) throws WorldError{
        save(false);
        if(config.get("World.WorldName." + worldName.toLowerCase()) == null){
            throw new WorldError();
        }

        File file = (File) config.get("World.WorldName." + worldName.toLowerCase() + ".worldFolder");
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
}
