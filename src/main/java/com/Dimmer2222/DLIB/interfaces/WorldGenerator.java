package com.Dimmer2222.DLIB.interfaces;

import com.Dimmer2222.DLIB.exceptions.WorldError;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldType;

import java.io.File;
import java.util.Set;

public interface WorldGenerator{


    /**
     * Generate a World
     * @param worldName Name of the World
     * @param environment Environment of the World
     * @param worldType Type of the World
     * @throws WorldError Will be thrown if the World already exist
     */
    void generateWorld(String worldName, World.Environment environment, WorldType worldType) throws WorldError;

    /**
     * Get a World that the Plugin is loading
     * @param worldName Name of the World
     * @return a World
     * @throws WorldError Will be thrown if the Plugin don't know the World
     */
    World getWorld(String worldName) throws WorldError;

    /**
     * Delete a World that the Plugin is loading
     * @param worldName Name of the World
     * @throws WorldError Will be thrown if the Plugin don't load the World or the World is not existing.
     */
    void deleteWorld(String worldName) throws WorldError;

    /**
     * Force the Plugin to forget a World
     * @param worldName Name of the World
     * @throws WorldError Will be thrown if the Plugin already not know the World or the World is not existing.
     */
    void removefromConfig(String worldName) throws WorldError;

    /**
     * Get a Set of the Names of the Worlds that the Plugin is loading.
     * @return All Names of World that the Plugin is loading
     */
    Set<String> getWorlds();

    /**
     * Return if PvP is enabled
     * @param worldName Name of the World
     * @return Is PvP enabled
     * @throws WorldError Will be thrown if the Plugin don't now the World
     */
    boolean isPvP(String worldName) throws WorldError;

    /**
     * Get the Spawn Location of a World
     * @param worldName Name of the World
     * @return Location of a World
     * @throws WorldError Will be thrown if the Plugin don't know the world.
     */
    Location getSpawnLocation(String worldName) throws WorldError;

    /**
     * Get the Folder of the World
     * @param worldName The Name of the World
     * @return The Folder of the World
     * @throws WorldError Will be thrown if the Plugin don't know the World.
     */
    File getWorldFolder(String worldName) throws WorldError;

    /**
     * Get if the Animals are enabled
     * @param worldName Name of the World
     * @return if true the animals are enabled if false otherwise
     * @throws WorldError Will be thrown if the Plugin don't know the World.
     */
    boolean isAnimals(String worldName) throws WorldError;

    /**
     * Get if the Monsters are enabled
     * @param worldName Name of the World
     * @return if true the monsters are enabled if false otherwise
     * @throws WorldError Will be thrown if the Plugin don't know the World.
     */

    boolean isMonsters(String worldName) throws WorldError;

    /**
     * Get the Difficulty of the World
     * @param worldName Name of the World
     * @return The difficulty of the World
     * @throws WorldError Will be thrown if the Plugin don't know the World.
     */

    Difficulty getDifficulty(String worldName) throws WorldError;

    /**
     * Return the World Type
     * @param worldName Name of the World
     * @return World Type
     * @throws WorldError Will be thrown if the Plugin don't know the World.
     */

    WorldType getWorldType(String worldName) throws WorldError;

    /**
     * Get the Environment of the World
     * @param worldName Name of the World
     * @return The World Environment
     * @throws WorldError Will be thrown if the Plugin don't know the World.
     */
    World.Environment getEnvironment(String worldName) throws WorldError;

    /**
     * Set if PvP is enabled
     * @param worldName Name of the World
     * @param PvP if true PvP is enabled if false otherwise
     * @throws WorldError Will be thrown if the Plugin don't know the World.
     */
    void setPvP(String worldName, boolean PvP) throws WorldError;

    /**
     * Set the SpawnLocation
     * @param worldName Name of the World
     * @param loc Location of the new SpawnLocation
     * @throws WorldError Will be thrown if the Plugin don't know the World.
     */
    void setSpawnLocation(String worldName, Location loc) throws WorldError;

    /**
     * Set if animals are enabled
     * @param worldName Name of the World
     * @param active if true the animals are enabled if false otherwise
     * @throws WorldError Will be thrown if the Plugin don't know the World.
     */
    void setAnimals(String worldName ,boolean active) throws WorldError;

    /**
     * Set if monsters are enabled
     * @param worldName Name of the World
     * @param active if true the Monsters are enabled if false otherwise
     * @throws WorldError Will be thrown if the Plugin don't know the World.
     */
    void setMonster(String worldName ,boolean active) throws WorldError;

    /**
     * Set the difficulty in a World that the Plugin now
     * @param worldName Name of the World
     * @param diff Name of the Difficulty at "PEACEFUL", "EASY", "NORMAL" or "HARD"
     * @throws WorldError Will be thrown if the Plugin don't know the World.
     */
    void setDifficulty(String worldName ,String diff) throws WorldError;

    /**
     * Save it in the Config to work with it but don't force Bukkit to load a World that Bukkit is not loading.
     * @param WorldName Name of the World
     * @throws WorldError If the World already is defined in the config
     */
    void importWorld(String WorldName) throws WorldError;


}
