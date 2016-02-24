package com.Dimmer2222.small.events;

import com.Dimmer2222.DAPI.api.BukkitEasier;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class WartungenEvent implements Listener {

        JavaPlugin js;

        public WartungenEvent(JavaPlugin js){
            BukkitEasier.registerEvents(js, this);
            this.js = js;
        }

        @EventHandler(priority = EventPriority.HIGHEST)
        public void OnJoin(PlayerJoinEvent e){
            if(js.getConfig().getBoolean("SmallUtils.Events.Wartungen.An/Aus")) {
                if (!e.getPlayer().hasPermission("SmallUtils.Wartungen.join")) {
                    e.getPlayer().kickPlayer(ChatColor.DARK_RED + "Wartungen");
                    e.setJoinMessage("");
                }
            }
        }

        @EventHandler(priority = EventPriority.HIGHEST)
        public void onLeave(PlayerQuitEvent e){
            if(js.getConfig().getBoolean("SmallUtils.Events.Wartungen.An/Aus")) {
                if (!e.getPlayer().hasPermission("SmallUtils.Wartungen.join")) {
                    e.setQuitMessage("");
                }
            }

        }

    }

