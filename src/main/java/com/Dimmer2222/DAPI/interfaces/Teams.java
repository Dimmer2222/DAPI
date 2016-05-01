package com.Dimmer2222.DAPI.interfaces;

import com.Dimmer2222.DAPI.exceptions.CoreException;
import com.Dimmer2222.DAPI.exceptions.TeamError;

import java.util.UUID;

// Copyright (c) Dimmer2222. All Rights Reserved
// Licensed under the MIT License. Read the MIT License under https://tldrlegal.com/license/mit-license#summary

public interface Teams {


    /**
     * Method to create a Team
     * @param PlayerUUID UUID of the Team Creator
     * @param TeamName Name of the Team
     * @throws CoreException Will be thrown when you are in a Team or the Team exist already
     */

    void createTeam(UUID PlayerUUID, String TeamName) throws CoreException;

    /**
     * Method to join a Teams
     * @param PlayerUUID UUID from the Player
     * @param TeamName Name of the Team
     * @throws TeamError It will be thrown than the Team don't exist
     * @throws CoreException It will be thrown than you are in a Team
     */

    void joinTeam(UUID PlayerUUID, String TeamName) throws TeamError, CoreException;

    /**
     * Method to delete a Team
     * @param TeamName Name of the Team
     * @throws TeamError Will be thrown than the Team don't exist.
     */

    void deleteTeam(String TeamName) throws TeamError;

    /**
     * Method to let a Player leave a Team
     * @param PlayerUUID Name of the UUID from the Player
     * @param TeamName Name of the Team
     * @throws TeamError It will be thrown than the Player don't have a Team
     */

    void leaveTeam(UUID PlayerUUID, String TeamName) throws TeamError;

    /**
     * Method to get the Team from a Player
     * @param PlayerUUID PlayerUUID of a Player
     * @return The Team of a Player
     * @throws TeamError It will be thrown than the Player don't have a Team.
     */

    String getTeam(UUID PlayerUUID) throws TeamError;
}