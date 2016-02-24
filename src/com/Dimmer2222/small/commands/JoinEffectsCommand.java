package com.Dimmer2222.small.commands;

import com.Dimmer2222.DAPI.api.BukkitEasier;
import com.Dimmer2222.small.events.JoinEffectsEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class JoinEffectsCommand extends JoinEffectsEvent implements CommandExecutor{

    JavaPlugin js;

    public JoinEffectsCommand(JavaPlugin js){
        super(js);
        BukkitEasier.registerCommand(js, "JoinEffects", this);
        this.js = js;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("JoinEffects")){
            if(js.getConfig().getBoolean("SmallUtils.Events.JoinEffects.An/Aus")){
                js.getConfig().set("SmallUtils.Events.JoinEffects.An/Aus", false);
                js.saveConfig();
                sender.sendMessage(ChatColor.AQUA + "Die Effekte beim beitreten sind nun aus.");
            }else{
                js.getConfig().set("SmallUtils.Events.JoinEffects.An/Aus", true);
                js.saveConfig();
                sender.sendMessage(ChatColor.AQUA + "Die Effecte beim beitreten sind nun an.");
            }
            return true;
        }

        return false;
    }
}
