package com.Dimmer2222.DAPI.api.Implements;

import com.Dimmer2222.DAPI.interfaces.Special;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class SpecialItemManager implements Special{

    ItemStack itemStack;
    Item item;
    File file;
    FileConfiguration config;

    public SpecialItemManager(String Path, String File, Item item){
        file = new File(Path, File);
        config = YamlConfiguration.loadConfiguration(file);
        this.item = item;
    }



    @Override
    public void loadSpecialObjects(){

    }

    @Override
    public void createSpecialObject() {

    }

    @Override
    public void getSpecialObject() {

    }

    @Override
    public void deleteSpecialObject() {

    }

    @Override
    public Location getSpecialObjectLocation() {
        return item.getLocation();
    }

    public ItemStack getItemStack(){
        return item.getItemStack();
    }

    public Item getItem(){
        return item;
    }
}
