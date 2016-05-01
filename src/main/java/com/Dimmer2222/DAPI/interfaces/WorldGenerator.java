package com.Dimmer2222.DAPI.interfaces;

import com.Dimmer2222.DAPI.exceptions.WorldError;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldType;

import java.io.File;
import java.util.Set;

/*The MIT License (MIT)

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

public interface WorldGenerator {

    void generateWorld(String worldName, World.Environment environment, WorldType worldType) throws WorldError;

    World getWorld(String worldName) throws WorldError;

    void deleteWorld(String worldName) throws WorldError;

    void removefromConfig(String worldName) throws WorldError;

    void setup();

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

    void setWorldType(String worldName, WorldType worldType);

    void setEnvironment(String worldName, World.Environment environment);

    void loadWorlds();

    void disableWorld(boolean disable);

}
