package com.Dimmer2222.small;

import com.Dimmer2222.small.commands.*;
import com.Dimmer2222.small.events.TestEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		super.onEnable();
		config();
		if(testDAPI()) {
			new WartungenCommand(this);
			new WarpCommand(this);
			new MainCommand(this).checkforUpdate();
			new ClearChat(this);
			new JoinEffectsCommand(this);
			new SupportCommand(this);
		}
		else this.getServer().getPluginManager().registerEvents(new TestEvent(), this);


	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	public boolean testDAPI(){
		if(this.getServer().getPluginManager().getPlugin("DAPI") != null){
			return true;
		}
		return false;
	}

	public void config() {
		reloadConfig();
		getConfig().options().header("SmallUtils's Einstellungen");
		getConfig().addDefault("SmallUtils.Events.Wartungen.An/Aus", false);
		getConfig().addDefault("SmallUtils.Events.JoinEffects.An/Aus", true);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}


	public static String getPDataFolder() {
		return "plugins/SmallUtils/";
	}

}






