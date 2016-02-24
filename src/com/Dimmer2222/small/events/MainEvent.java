package com.Dimmer2222.small.events;

import com.Dimmer2222.DAPI.api.BukkitEasier;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class MainEvent implements Listener{

    private JavaPlugin js;
    protected String temp = "";
    protected boolean isNewerVersionAvailable = false;

    /*

    Dieser Konstruktor ist für

     */

    public MainEvent(JavaPlugin js){
        BukkitEasier.registerEvents(js, this);
        this.js = js;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(isNewerVersionAvailable){
            if(e.getPlayer().hasPermission("SmallUtils.Update"))
            e.getPlayer().sendMessage(ChatColor.AQUA + "Die neue Version " + temp + " ist verfügbar Sie besitzen die Version " + js.getDescription().getVersion() + " . Link: https://www.mediafire.com/folder/3mke72rf34mz4/SmallUtils.");
        }
    }

}
