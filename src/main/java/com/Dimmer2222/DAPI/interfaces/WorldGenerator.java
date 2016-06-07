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

    Set<String> getWorlds();

    boolean isPvP(String worldName) throws WorldError;

    Location getSpawnLocation(String worldName) throws WorldError;

    File getWorldFolder(String worldName) throws WorldError;

    boolean isAnimals(String worldName) throws WorldError;

    boolean isMonsters(String worldName) throws WorldError;

    Difficulty getDifficulty(String worldName) throws WorldError;

    WorldType getWorldType(String worldName) throws WorldError;

    World.Environment getEnvironment(String worldName) throws WorldError;

    void setPvP(String worldName, boolean PvP) throws WorldError;

    void setSpawnLocation(String worldName, Location loc) throws WorldError;

    void setAnimals(String worldName ,boolean active) throws WorldError;

    void setMonster(String worldName ,boolean active) throws WorldError;

    void setDifficulty(String worldName ,String diff) throws WorldError;

    void importWorld(String WorldName) throws WorldError;


}
