package com.Dimmer2222.small.commands;

import com.Dimmer2222.DAPI.api.BukkitEasier;
import com.Dimmer2222.DAPI.api.DCommadExcutor;
import com.Dimmer2222.small.events.WartungenEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class WartungenCommand extends WartungenEvent implements CommandExecutor{

    JavaPlugin js;

    public WartungenCommand(JavaPlugin js) {
        super(js);
        BukkitEasier.registerCommand(js, "Wartungen", this);
        BukkitEasier.registerCommandinYAML(new DCommadExcutor("Wartungen", "Um Wartungen an oder aus zu schalten", "/Wartungen", null, this), "SmallUtils.Wartungen");
        this.js = js;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {



        if(cmd.getName().equalsIgnoreCase("Wartungen")){
            if(!js.getConfig().getBoolean("SmallUtils.Events.Wartungen.An/Aus")) {
                js.getConfig().set("SmallUtils.Events.Wartungen.An/Aus", true);
                js.saveConfig();
                sender.sendMessage(ChatColor.AQUA + "Wartungen sind nun an.");
            }else{
                js.getConfig().set("SmallUtils.Events.Wartungen.An/Aus", false);
                js.saveConfig();
                sender.sendMessage(ChatColor.AQUA + "Wartungen sind nun aus.");
            }
            return true;
        }
        return false;
    }
}