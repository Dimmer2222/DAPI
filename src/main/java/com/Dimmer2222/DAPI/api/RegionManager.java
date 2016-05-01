package com.Dimmer2222.DAPI.api;

import com.Dimmer2222.DAPI.exceptions.RegionError;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RegionManager {

    private static String StandartString = "Region.RegionName";
    File file;
    YamlConfiguration config;

    public RegionManager(String Path, String File){
        file = new File(Path, File);
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void createRegion(String regionName, Location loc1, Location loc2) {
            save(false);
            config.set("Region.RegionName." + regionName.toLowerCase(), regionName.toLowerCase());
            config.set("Region.RegionName." + regionName.toLowerCase() + ".region", new Region(regionName ,loc1, loc2).serialize());
            save(true);
        }



    public Region getRegion(String regionName) throws RegionError {
        save(false);
        if (config.get("Region.RegionName." + regionName.toLowerCase()) == null) {
            throw new RegionError();
        } else {

            World world = Bukkit.getWorld(config.getConfigurationSection(RegionManager.StandartString).getString(regionName.toLowerCase() + ".region.world"));
            if(world != null){
                int minx = config.getConfigurationSection(RegionManager.StandartString).getInt(regionName.toLowerCase() + ".region.minx");
                int miny = config.getConfigurationSection(RegionManager.StandartString).getInt(regionName.toLowerCase() + ".region.miny");
                int minz = config.getConfigurationSection(RegionManager.StandartString).getInt(regionName.toLowerCase() + ".region.minz");
                int maxx = config.getConfigurationSection(RegionManager.StandartString).getInt(regionName.toLowerCase() + ".region.maxx");
                int maxy = config.getConfigurationSection(RegionManager.StandartString).getInt(regionName.toLowerCase() + ".region.maxy");
                int maxz = config.getConfigurationSection(RegionManager.StandartString).getInt(regionName.toLowerCase() + ".region.maxz");
                return new Region(regionName ,new Location(world ,minx, miny, minz), new Location(world, maxx, maxy, maxz));
            }


        }
        return null;
    }


    public void deleteRegion(String regionName) throws RegionError{
        save(false);
        if(config.get("Region.RegionName." + regionName.toLowerCase()) == null){
            throw new RegionError();
        }else{
        config.set("Region.RegionName." + regionName.toLowerCase(), null);
            save(true);
        }
    }


    public List<Region> getRegions(){
        List<Region> regions = new ArrayList<>();
        for(String name : config.getConfigurationSection("Region.RegionName").getKeys(false)){
            try {
                regions.add(getRegion(name));
            }catch (RegionError e){
                e.printStackTrace();
            }
        }
        if(regions.isEmpty()){
            throw new NullPointerException();
        }
        return regions;
    }

    private void save(boolean b){
        try {
        if(!file.exists()){
                file.createNewFile();
            }
            if(b){
               config.save(file);
          }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
}
