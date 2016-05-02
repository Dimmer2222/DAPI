package com.Dimmer2222.DAPI.api;

import com.Dimmer2222.DAPI.exceptions.ValueNotExistException;
import com.Dimmer2222.DAPI.exceptions.WrongValueException;
import org.bukkit.Bukkit;
import org.bukkit.conversations.NullConversationPrefix;
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

    private Map<UUID, Long> Time = new HashMap<>();
    private Map<UUID, Long> diffTime = new HashMap<>();

    private long Cooldown = 0;

    public void saveallPlayers() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!Time.containsKey(p.getUniqueId())) {
                Time.put(p.getUniqueId(), 0L);
                diffTime.put(p.getUniqueId(), 0L);
            } else {
                System.out.println(Time.get(p.getUniqueId()));
            }

        }
    }

    public void createCountdownforPlayer(UUID PlayerUUID, long secondsorMinutes) {
        Time.put(PlayerUUID, System.currentTimeMillis());
        Cooldown = secondsorMinutes;
    }

    public void createCountdownforPlayer(UUID PlayerUUID) {

        Time.put(PlayerUUID, System.currentTimeMillis());
    }

    public boolean hasPlayeraCountdown(UUID PlayerUUID, boolean seconds) throws WrongValueException {
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


    public void removeCountdownfromPlayer(UUID PlayerUUID) throws ValueNotExistException {
        if (!Time.containsKey(PlayerUUID)) {
            throw new ValueNotExistException();
        }
        diffTime.remove(PlayerUUID);
        Time.remove(PlayerUUID);
    }

    public void setCooldown(long time) {
        this.Cooldown = time;
    }

    public long getCooldown() {
        return this.Cooldown;
    }

    public long getDifferencefromPlayer(UUID PlayerUUID) throws ValueNotExistException {
        if (Time.containsKey(PlayerUUID)) {
            return diffTime.get(PlayerUUID);
        } else {
            throw new ValueNotExistException();
        }

    }
}
