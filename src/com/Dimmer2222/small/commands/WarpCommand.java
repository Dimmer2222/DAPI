package com.Dimmer2222.small.commands;

import com.Dimmer2222.DAPI.api.BukkitEasier;
import com.Dimmer2222.DAPI.exceptions.CoreException;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Dimmer2222.small.Main;
import com.Dimmer2222.DAPI.api.WarpManager;


public final class WarpCommand implements CommandExecutor{

	Main m;
	Player p = null;
	WarpManager wm;
     public WarpCommand(Main m) {
	 BukkitEasier.registerCommand(m, "suwarp", this);
	 BukkitEasier.registerCommand(m, "susetwarp", this);
	 BukkitEasier.registerCommand(m, "sudelwarp", this);
	 wm = new WarpManager(Main.getPDataFolder(), "Warp.yml");

	 this.m = m;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			p = (Player) sender;
		}
		
		if(cmd.getName().equalsIgnoreCase("suwarp")){
		if(p != null){	
			if(args.length == 0){
				p.sendMessage("Hier ist die Warplist:");
				try {
					p.sendMessage(wm.getWarps());
				}catch (NullPointerException e){
					p.sendMessage("Es wurde noch kein Warp erstellt.");
				}
			}
			if(args.length == 1) {

					Location loc = wm.getWarp(args[0]);
					if (loc != null) {
						if (loc.getWorld().getName() != null) {
							p.teleport(loc);
							p.sendMessage("Du wurdest zum Warp " + args[0] + " gewarpt");
						} else {
							sender.sendMessage("Der Warp exestiert nicht.");
						}
					}else{
						p.sendMessage("Die Welt wurde nicht gefunden.");
					}
				}

		}else{
			sender.sendMessage("Dieser Befehl ist nur fuer Spieler.");
		
		}
		return true;
		}
	
				if(cmd.getName().equalsIgnoreCase("susetwarp")){
					if(p != null){
					if(args.length == 1) {
						try {
								wm.createWarp(args[0], p.getLocation());
								p.sendMessage("Der Warp " + args[0] + " wurde gesetzt.");

						}catch(CoreException e){
							sender.sendMessage("Dieser Warp " + args[0] + " exestiert schon.");
						}
					}
					if(args.length == 0){
						return false;
				}
					}
				
				else{
					sender.sendMessage("Dieser Befehl ist nur fuer Spieler.");
				
				
				}
					return true;
	}
		
				if(cmd.getName().equalsIgnoreCase("sudelwarp")){
					if(p != null){
					if(args.length == 1){
						try {
							if (wm.getWarp(args[0]) != null) {
								wm.deleteWarp(args[0]);
								p.sendMessage("Der Warp " + args[0] + " wurde geloescht.");
							} else {
								sender.sendMessage("Dieser Warp " + args[0] + " exestiert nicht.");
							}
						}catch (CoreException e){
							e.printStackTrace();
						}
					}
					if(args.length == 0){
						return false;
					}
					}
				else{
					sender.sendMessage("Dieser Befehl ist nur fuer Spieler.");
				
				}
					return true;
				}
					
					
				
					
				
	
				
		
	
			
			
		
		
		
	
		return false;
	}
	
	

}
