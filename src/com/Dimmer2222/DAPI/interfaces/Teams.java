package com.Dimmer2222.DAPI.interfaces;

import com.Dimmer2222.DAPI.exceptions.CoreException;
import com.Dimmer2222.DAPI.exceptions.TeamError;

import java.util.UUID;

// Copyright (c) Dimmer2222. All Rights Reserved
// Licensed under the MIT License. Read the MIT License under https://tldrlegal.com/license/mit-license#summary

public interface Teams {


    /**
     * Methode zum erstellen eines Teams
     * @param PlayerUUID UUID des Gründers
     * @param TeamName Angabe von dem Namen des Teams
     * @throws CoreException Exception wird geworfen wenn du schon in einem Team bist
     */
    void createTeam(UUID PlayerUUID, String TeamName) throws CoreException;

    /**
     * Methode zum Joinen eines Teams
     * @param PlayerUUID UUID des Spielers das Einem Team joinen will
     * @param TeamName Name des Teams
     * @throws TeamError Wird geworfen wenn das Team nicht gefunden werden kann
     * @throws CoreException Wird geworfen wenn du schon in einem Team bist
     */

    /**
     * @param PlayerUUID
     * @param TeamName
     * @throws TeamError
     * @throws CoreException
     */

    void joinTeam(UUID PlayerUUID, String TeamName) throws TeamError, CoreException;

    void deleteTeam(String TeamName) throws TeamError;

    void leaveTeam(UUID PlayerUUID, String TeamName) throws TeamError;

    String getTeam(UUID PlayerUUID) throws TeamError;
}