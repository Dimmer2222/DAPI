package com.Dimmer2222.small.events;

import com.Dimmer2222.DAPI.api.BukkitEasier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinEffectsEvent implements Listener{

    private JavaPlugin js;

    /*

        Dieser Konstruktor registiert das Plugin und macht es für Bukkit Sichtbar.
        Und initalisiert die Variable js

     */

    public JoinEffectsEvent(JavaPlugin js){
        BukkitEasier.registerEvents(js, this);
        this.js = js;
    }

    /*
       Bei diesem Event wird beim Joinen ein permanenter Nachtsicht und Geschwindigkeits Effect übergeben.
       Diese Effekte werden durch den Boolean von der Standart Config "SmallUtils.Events.JoinEffects.An/Aus"
       kontrolliert.

     */

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        if(js.getConfig().getBoolean("SmallUtils.Events.JoinEffects.An/Aus")) {
            Player p = e.getPlayer();
            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000000, 0, false, false));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000000, 3, false, false));

        }

    }

}
