package com.Dimmer2222.DAPI.api.Implements;

import com.Dimmer2222.DAPI.api.BukkitEasier;
import com.Dimmer2222.DAPI.exceptions.WorldError;
import com.Dimmer2222.DAPI.interfaces.WorldGenerator;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        try {
            setup();
        }catch (WorldError e){
            return;
        }
    }

    @Override
    public void generateWorld(String worldName, World.Environment environment, WorldType worldType) throws WorldError{
        save(false);
        if(config.get("World.WorldName." + worldName.toLowerCase()) != null){
            throw new WorldError();
        }

        save(false);
        World world = Bukkit.createWorld(WorldCreator.name(worldName).environment(environment).type(worldType));
        config.set("World.WorldName." + worldName.toLowerCase(), worldName.toLowerCase());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.pvp", world.getPVP());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.spawn", world.getSpawnLocation().serialize());
        config.set("World.WorldName." + worldName.toLowerCase() + ".worldFolder", world.getWorldFolder().getName());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.animals", world.getAllowAnimals());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.monster", world.getAllowMonsters());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.difficulty", world.getDifficulty().toString());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.type",world.getWorldType().toString());
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.environment", world.getEnvironment().toString());
        save(true);

    }

    private void setup() throws WorldError{

       //Set<String> set = getWorlds();

      if(getWorlds().isEmpty()){
            throw new WorldError();
        }

        try {
             for(String worldName : getWorlds()) {
                 setPvP(worldName, config.getBoolean("World.WorldName." + worldName.toLowerCase() + ".properties.pvp"));
                 setSpawnLocation(worldName, new Location(Bukkit.getWorld(config.getString("World.WorldName." + worldName.toLowerCase()) + ".properties.spawn.world"), config.getDouble("World.WorldName." + worldName.toLowerCase() + ".properties.spawn.X"), config.getDouble("World.WorldName." + worldName.toLowerCase() + ".properties.spawn.Y"), config.getDouble("World.WorldName." + worldName.toLowerCase() + ".properties.spawn.Z"), (float) config.getDouble("World.WorldName." + worldName.toLowerCase() + ".properties.spawn.yaw"), (float) config.getDouble("World.WorldName." + config.getDouble(worldName.toLowerCase() + ".properties.spawn.pitch"))));
                 setAnimals(worldName, config.getBoolean("World.WorldName." + worldName.toLowerCase() + ".properties.animals"));
                 setMonster(worldName, config.getBoolean("World.WorldName." + worldName.toLowerCase() + ".properties.monster"));
                 setDifficulty(worldName, config.getString("World.WorldName." + worldName.toLowerCase() + ".properties.difficulty"));

             }
        }catch(WorldError e){
            e.printStackTrace();
        }

        }


    @Override
    public Set<String> getWorlds(){
        save(false);
        Set<String> worlds = new HashSet<>();
        for(String name : config.getConfigurationSection("World.WorldName").getKeys(false)){
            if(name == null){
                return worlds;
            }
           worlds.add(name);
        }
        return worlds;
    }

    @Override
    public boolean isPvP(String worldName) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }


            return Bukkit.getWorld(worldName.toLowerCase()).getPVP();

    }

    @Override
    public Location getSpawnLocation(String worldName) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
           throw new WorldError();
        }
        return Bukkit.getWorld(worldName.toLowerCase()).getSpawnLocation();
    }

    @Override
    public File getWorldFolder(String worldName) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }
        return new File("./" + config.getString("World.WorldName." + worldName.toLowerCase() + ".worldFolder"));
    }

    @Override
    public boolean isAnimals(String worldName) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }

                return Bukkit.getWorld(worldName.toLowerCase()).getAllowAnimals();
    }

    @Override
    public boolean isMonsters(String worldName) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }
                    return Bukkit.getWorld(worldName.toLowerCase()).getAllowMonsters();

    }

    @Override
    public Difficulty getDifficulty(String worldName) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }
                    return Bukkit.getWorld(worldName.toLowerCase()).getDifficulty();

    }

    @Override
    public WorldType getWorldType(String worldName) throws WorldError {
        save(false);
        if (!getWorlds().contains(worldName.toLowerCase())) {
            throw new WorldError();
        }

                    return Bukkit.getWorld(worldName.toLowerCase()).getWorldType();
        }

    @Override
    public World.Environment getEnvironment(String worldName) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }

                    return Bukkit.getWorld(worldName.toLowerCase()).getEnvironment();
    }

    @Override
    public void setPvP(String worldName, boolean PvP) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }
                    Bukkit.getWorld(worldName.toLowerCase()).setPVP(PvP);
                config.set("World.WorldName." + worldName.toLowerCase() + ".properties.pvp", PvP);


        save(true);
    }

    @Override
    public void setSpawnLocation(String worldName, Location loc) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }
                    Bukkit.getWorld(worldName.toLowerCase()).setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
                     config.set("World.WorldName." + worldName.toLowerCase() + ".spawn", new Location(Bukkit.getWorld(worldName.toLowerCase()) ,loc.getX(), loc.getY(), loc.getZ()).serialize());



        save(true);

    }

    @Override
    public void setAnimals(String worldName ,boolean active) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }
        Bukkit.getWorld(worldName.toLowerCase()).setSpawnFlags(config.getBoolean("World.WorldName." + worldName.toLowerCase() + "properties.monster"), active);
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.animals", active);

        save(true);
    }

    @Override
    public void setMonster(String worldName ,boolean active) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }
        Bukkit.getWorld(worldName.toLowerCase()).setSpawnFlags(active ,config.getBoolean("World.WorldName." + worldName.toLowerCase() + "properties.animals"));
        config.set("World.WorldName." + worldName.toLowerCase() + ".properties.monster", active);


        save(true);
    }

    @Override
    public void setDifficulty(String worldName , String diff) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }
        try{
                  switch(diff.toLowerCase()) {
                      case "PEACEFUL" : Bukkit.getWorld(worldName.toLowerCase()).setDifficulty(Difficulty.PEACEFUL);
                      break;
                      case "EASY" : Bukkit.getWorld(worldName.toLowerCase()).setDifficulty(Difficulty.EASY);
                      break;
                      case "NORMAL" : Bukkit.getWorld(worldName.toLowerCase()).setDifficulty(Difficulty.NORMAL);
                      break;
                      case "HARD" : Bukkit.getWorld(worldName.toLowerCase()).setDifficulty(Difficulty.HARD);
                      break;
                      default: throw new WorldError();
                      }

                config.set("World.WorldName." + worldName.toLowerCase() + ".properties.difficulty", diff.toString());


        }catch (WorldError e){
            e.printStackTrace();
        }
        save(true);
    }

    @Override
    public void importWorld(String worldName) throws WorldError{
        save(false);
        if(Bukkit.getWorld(worldName.toLowerCase()) != null) {
        if(!getWorlds().contains(worldName))
            config.set("World.WorldName." + worldName.toLowerCase(), worldName.toLowerCase());
            config.set("World.WorldName." + worldName.toLowerCase() + ".properties.pvp", Bukkit.getWorld(worldName).getPVP());
            config.set("World.WorldName." + worldName.toLowerCase() + ".properties.spawn", Bukkit.getWorld(worldName).getSpawnLocation().serialize());
            config.set("World.WorldName." + worldName.toLowerCase() + ".worldFolder", Bukkit.getWorld(worldName).getWorldFolder().toString());
            config.set("World.WorldName." + worldName.toLowerCase() + ".properties.animals", Bukkit.getWorld(worldName).getAllowAnimals());
            config.set("World.WorldName." + worldName.toLowerCase() + ".properties.monster", Bukkit.getWorld(worldName).getAllowMonsters());
            config.set("World.WorldName." + worldName.toLowerCase() + ".properties.difficulty", Bukkit.getWorld(worldName).getDifficulty().toString());
            config.set("World.WorldName." + worldName.toLowerCase() + ".properties.type", Bukkit.getWorld(worldName).getWorldType().toString());
            config.set("World.WorldName." + worldName.toLowerCase() + ".properties.environment", Bukkit.getWorld(worldName).getEnvironment().toString());
            save(true);
        }else{
            throw new WorldError();
        }
    }


    @Override
    public World getWorld(String worldName) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }

        return Bukkit.getWorld(worldName);
    }

    @Override
    public void removefromConfig(String worldName) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }


        config.set("World.WorldName." + worldName.toLowerCase(), null);
        save(true);
    }

    @Override
    public void deleteWorld(String worldName) throws WorldError{
        save(false);
        if(!getWorlds().contains(worldName.toLowerCase())){
            throw new WorldError();
        }

        for(Player p : Bukkit.getWorld(worldName).getPlayers()){
            p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        }
        Bukkit.unloadWorld(worldName, false);
        deleteDirectory(getWorldFolder(worldName.toLowerCase()));
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

    private boolean deleteDirectory(File path) {
        if( path.exists() ) {
            File[] files = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return( path.delete() );
    }

}
