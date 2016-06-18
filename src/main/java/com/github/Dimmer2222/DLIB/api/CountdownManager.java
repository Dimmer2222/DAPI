package com.github.Dimmer2222.DLIB.api;

import com.github.Dimmer2222.DLIB.exceptions.ValueNotExistException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
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

public class CountdownManager {

    //HashMap that saves the first System.currentTime();
    private Map<UUID, Long> Time;
    //HashMap that saves the difference of the System.currentTime();
    private Map<UUID, Long> diffTime;

    public CountdownManager(){
        Time = new HashMap<>();
        diffTime = new HashMap<>();
    }

    //Variable where long is the Cooldown
    private long Cooldown = 0;

    /**
     * Save all Player with a Countdown this is 0L
     */

    public void saveallPlayers() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!Time.containsKey(p.getUniqueId())) {
                Time.put(p.getUniqueId(), 0L);
                diffTime.put(p.getUniqueId(), 0L);
            }
        }
    }

    /**
     * Save the Player UUID for a Countdown in a HashMap and set a Value for the Cooldown Variable that is for where long the the Player has to wait
     * @param PlayerUUID UUID from the Player
     * @param secondsorMinutes A Number for seconds or minutes
     */

    public void createCountdownforPlayer(UUID PlayerUUID, long secondsorMinutes) {
        Time.put(PlayerUUID, System.currentTimeMillis());
        Cooldown = secondsorMinutes;
    }

    /**
     * Save the Player UUID in a HashMap for a Cooldown
     * @param PlayerUUID UUID from the Player
     */
    public void createCountdownforPlayer(UUID PlayerUUID) {

        Time.put(PlayerUUID, System.currentTimeMillis());
    }

    /**
     * Check if the Player has a Cooldown higher than 0L than he have not it will return false
     * @param PlayerUUID UUID of the Player
     * @param seconds Should it calculate in seconds or Minutes if true it calculate in seconds
     * @return if the Player don't have a Countdown it will return false otherwise true
     * @throws ValueNotExistException It will be thrown if the Player isn't saved in the HashMap
     * */
    public boolean hasPlayeraCountdown(UUID PlayerUUID, boolean seconds) throws ValueNotExistException{
        if(!Time.containsKey(PlayerUUID)){
           throw new ValueNotExistException();
        }
        if (Time.get(PlayerUUID) != 0L) {
            if (seconds) {
                long diff = ((System.currentTimeMillis() - Time.get(PlayerUUID))) / 1000L;
                System.out.println(diff);
                if (diff < Cooldown) {
                    diffTime.put(PlayerUUID, diff);
                    return true;
                } else {
                    diffTime.put(PlayerUUID, 0L);
                    Time.put(PlayerUUID, 0L);
                    return false;
                }

            } else {
                long diff = ((System.currentTimeMillis() - Time.get(PlayerUUID))) / 1000L / 60L;
                if (diff < Cooldown) {
                    diffTime.put(PlayerUUID, diff);
                    return true;

                } else {
                    diffTime.put(PlayerUUID, 0L);
                    Time.put(PlayerUUID, 0L);
                    return false;
                }
            }
        }
        return false;
    }


    /**
     * Force to remove a Player from the HashMap
     * @param PlayerUUID UUID of the Player
     * @throws ValueNotExistException If the Player is'nt saved in the HashMap it will throw this Exception
     */
    public void removeCountdownfromPlayer(UUID PlayerUUID) throws ValueNotExistException {
        if (!Time.containsKey(PlayerUUID)) {
            throw new ValueNotExistException();
        }
        diffTime.remove(PlayerUUID);
        Time.remove(PlayerUUID);
    }

    /**
     * Set the long of the Cooldown
     * @param time where long should the Player wait
     */
    public void setCooldown(long time) {
        this.Cooldown = time;
    }

    /**
     * Get the number where big the Cooldown is
     * @return the Number where long the Player should wait.
     */
    public long getCooldown() {
        return this.Cooldown;
    }

    /**
     * Return the Difference of the Time where the Player has wait.
     * @param PlayerUUID UUID from the Player
     * @return Value of the where the Player wait.
     * @throws ValueNotExistException If the Player isn't saved in this HashMap it will throw this Exception
     */
    public long getDifferencefromPlayer(UUID PlayerUUID) throws ValueNotExistException {
        if (Time.containsKey(PlayerUUID)) {
            return diffTime.get(PlayerUUID);
        } else {
            throw new ValueNotExistException();
        }

    }
}
