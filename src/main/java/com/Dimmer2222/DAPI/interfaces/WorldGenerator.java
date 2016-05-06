package com.Dimmer2222.DAPI.interfaces;

import com.Dimmer2222.DAPI.exceptions.WorldError;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldType;

import java.io.File;
import java.util.Set;

public interface WorldGenerator{

    void generateWorld(String worldName, World.Environment environment, WorldType worldType) throws WorldError;

    World getWorld(String worldName) throws WorldError;

    void deleteWorld(String worldName) throws WorldError;

    void removefromConfig(String worldName) throws WorldError;
    
    Set<String> getWorlds() throws WorldError;

    public boolean isPvP(String worldName) throws WorldError;

    Location getSpawnLocation(String worldName) throws WorldError;

    File getWorldFolder(String worldName);

    boolean isAnimals(String worldName);

    boolean isMonsters(String worldName);

    Difficulty getDifficulty(String worldName);

    WorldType getWorldType(String worldName);

    World.Environment getEnvironment(String worldName);

    void setPvP(String worldName, boolean PvP);

    void setSpawnLocation(String worldName, Location loc);

    void setAnimals(String worldName ,boolean active);

    void setMonster(String worldName ,boolean active);

    void setDifficulty(String worldName ,Difficulty diff);

}
