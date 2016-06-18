package com.github.Dimmer2222.DLIB.api.Implements;

import com.github.Dimmer2222.DLIB.exceptions.TeamError;
import com.github.Dimmer2222.DLIB.interfaces.Teams;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.*;

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

/*
Beta!
 */

/**
 * @see Teams
 */

public class TeamManager implements Teams{

    private YamlConfiguration teamconfig;
    private File file;



    /**
     * Create a Object of the Class
     * @param Path Plugin Folder Path
     * @param TeamFileName File Name
     */
    public TeamManager(String Path, String TeamFileName) {
        file = new File(Path, TeamFileName);
        teamconfig = YamlConfiguration.loadConfiguration(file);
    }



    @Override
    public void createTeam(UUID PlayerUUID, String TeamName) throws TeamError {
        save(false);
        if(teamconfig.get("Team.TeamName." + TeamName) == null) {
            teamconfig.set("Team.TeamName." + TeamName, TeamName);
            save(true);
            try {
                joinTeam(PlayerUUID, TeamName);
            } catch (TeamError e) {
                e.printStackTrace();
            }
        }else{
            throw new TeamError();
        }
    }

    @Override
    public void joinTeam(UUID PlayerUUID, String TeamName) throws TeamError{
        save(false);
        if(teamconfig.getString("Team.TeamName." + TeamName) != null){
            if(teamconfig.get("Team.TeamName." + TeamName + ".PlayerUUID." + PlayerUUID) != null){
                throw new TeamError();
            }
                teamconfig.set("Team.TeamName." + TeamName + ".PlayerUUID." + PlayerUUID, String.valueOf(PlayerUUID));
                save(true);
        }else{
            throw new TeamError();
        }
    }

    @Override
    public void deleteTeam(String TeamName) throws TeamError {
        save(false);
        if (teamconfig.getString("Team.TeamName." + TeamName) != null) {

            //to awkward
            /*for(Player p : Bukkit.getOnlinePlayers()) {
                if (this.getTeam(p.getUniqueId()) != null) {
                    if(this.getTeam(p.getUniqueId()).equalsIgnoreCase(TeamName)){
                        teamconfig.set("Team.TeamName." + TeamName + ".PlayerUUID." + p.getUniqueId(), null);
                        save(true);
                }
              }
           }
                */

            teamconfig.set("Team.TeamName." + TeamName, null);
            save(true);



        }else{
            throw new TeamError();
        }

    }

    @Override
    public void leaveTeam(UUID PlayerUUID, String TeamName) throws TeamError{
        save(false);
        if(teamconfig.getString("Team.TeamName." + TeamName) != null && teamconfig.getString("Team.TeamName." + TeamName + ".PlayerUUID." + PlayerUUID) != null){
            teamconfig.set("Team.TeamName." + TeamName + ".PlayerUUID." + PlayerUUID, null);
            save(true);
        }else{
            throw new TeamError();
        }
    }

    @Override
    public String getTeam(UUID PlayerUUID) {
        for(String teamname : teamconfig.getConfigurationSection("Team").getConfigurationSection("TeamName").getKeys(false)){
            if(teamconfig.get("Team.TeamName." + teamname + ".PlayerUUID." + PlayerUUID) != null) {
                    return teamname;
                }
            }
        return null;
    }

    /**
     * This Method is to save and to generate a new File then no one exist.
     * @param b Disable saving and generate a new File when no File exist by false, by true saving + checking if file exist
     */

    private void save(boolean b) {
        try {
            if (!file.exists())
                file.createNewFile();
            if(b){
                teamconfig.save(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

