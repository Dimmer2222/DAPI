package com.Dimmer2222.DLIB;

import com.Dimmer2222.DLIB.api.CountdownManager;
import com.Dimmer2222.DLIB.api.Implements.SpecialBlockManager;
import com.Dimmer2222.DLIB.api.Implements.TeamManager;
import com.Dimmer2222.DLIB.api.Implements.WorldManager;
import com.Dimmer2222.DLIB.api.PlayerConfig;
import com.Dimmer2222.DLIB.api.RegionManager;
import com.Dimmer2222.DLIB.api.WarpManager;

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

/**
 *
 * Should include a Class from the Plugin that use this
 *
 */

public interface DLIB {


/**
 *
 * Get a Instance of the WorldManager Class
 * @see WorldManager
 *
*/
    WorldManager getWorldManager();


    /**
     *
     * Get a Instance of the WorldManager Class
     * @see WorldManager
     *
     */

    CountdownManager getCountdownManager();


    /**
     *
     * Get a Instance of the PlayerConfig Class
     * @see PlayerConfig
     *
     */

    PlayerConfig getPlayerConfig();


    /**
     *
     * Get a Instance of the RegionManager Class
     * @see RegionManager
     *
     */

    RegionManager getRegionManager();


    /**
     *
     * Get a Instance of the WarpManager Class
     * @see WarpManager
     *
     */

    WarpManager getWarpManager();


    /**
     *
     * Get a Instance of the TeamManager Class
     * @see TeamManager
     *
     */

    TeamManager getTeamManager();

    /**
     *
     * Get a Instance of the SpecialBlockManager Class
     * @see SpecialBlockManager
     *
     */

    SpecialBlockManager getSpecialBlockManager();


}
