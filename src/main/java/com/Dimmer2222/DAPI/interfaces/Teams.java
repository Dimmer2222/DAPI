package com.Dimmer2222.DAPI.interfaces;

import com.Dimmer2222.DAPI.exceptions.CoreException;
import com.Dimmer2222.DAPI.exceptions.TeamError;

import java.util.UUID;

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