package com.Dimmer2222.small.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TestEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(p.isOp())
        p.sendMessage(ChatColor.DARK_RED + "Das Plugin SmallUtils kann nicht geladen werden weil das Plugin DAPI nicht installiert oder nicht laden konnte.");
    }
}
