package com.Dimmer2222.DAPI.api;

import org.bukkit.Bukkit;

import org.bukkit.plugin.java.JavaPlugin;

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
@Deprecated
public class CountdownManager {


    /**
     * Diese Variable ist dazu da um eine Sekunde in Ticks für den Erzeuger zu zeigen damit er nicht nachkucken muss nach wie vielen Ticks
     * eine Sekunde vorübergeht.
     */

    public static final long SECOND = 20;


    /**
     * Diese Variable ist dazu da um einen bestimmten synchronen TaskTimer zu beenden nicht nutzen wenn man einen asynchronen Task Timer nutzt.
     */

    public int id = 0;


    /**
     * Dies ist dazu da um einen neuen asynchonen TaskTimer von Bukkit zu starten.
     * @param js Man gibt hier die Hauptklasse des Plugins an
     * @param start Wann es nach einem Start(Reload) des Servers in Ticks startet.
     * @param second Nach wie vielen Ticks die Aufforderungen vom Runnable Interfaces neu ausführt werden.
     * @param runnable Sehe das Interface Runnable
     * @see Runnable
     */

    public void startasynchronouslyCountdown(JavaPlugin js, long start, long second, Runnable runnable) {
       Bukkit.getScheduler().runTaskTimerAsynchronously(js, runnable, start, second);
    }


    public void startCountdown(JavaPlugin js, long start, long second, Runnable runnable){
        Bukkit.getScheduler().runTaskTimer(js, runnable,start, second);
    }

    public void stopCountdown(int id) {
        Bukkit.getScheduler().cancelTask(id);
    }


    public void stopAllPluginCountdowns(JavaPlugin js){
        Bukkit.getScheduler().cancelTasks(js);
    }



  public int getID(){
        return id;
    }


    }



