package com.Dimmer2222.DLIB.api;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
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

public class BukkitEasier {

    /**
     * In a couple of Seconds register Commands.
     *
     * @param jp      The Name of the JavaPlugin Class(Main Class)
     * @see JavaPlugin
     * @param command Name of the Command
     * @param ce      CommandExecutor
     * @see CommandExecutor
     */

    public static void registerCommand(JavaPlugin jp, String command, CommandExecutor ce) {
        jp.getCommand(command).setExecutor(ce);
    }

    /**
     * In a couple of Seconds register Event.
     *
     * @param jp JavaPlugin
     * @param l  Listener
     */

    public static void registerEvents(JavaPlugin jp, Listener l) {
        jp.getServer().getPluginManager().registerEvents(l, jp);
    }


}