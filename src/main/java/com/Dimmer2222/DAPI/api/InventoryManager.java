package com.Dimmer2222.DAPI.api;

/*
New
 */

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.io.File;


public class InventoryManager {

    Inventory inv;
    File file;
    FileConfiguration config;

    public InventoryManager(String Path, String File) {
        file = new File(Path, File);
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void generateInventorywithType(InventoryHolder holder, InventoryType type, String title){
         inv = Bukkit.createInventory(holder ,type ,title);
    }

    public void generateInventorywithType(InventoryHolder holder, InventoryType type){
         inv = Bukkit.createInventory(holder ,type);
    }

    public void generateInventoryChest(InventoryHolder holder, int size, String title){
         inv = Bukkit.createInventory(holder, size, title);

    }

    public void generateInventoryChest(InventoryHolder holder, int size){
         inv = Bukkit.createInventory(holder, size);

    }

    public Inventory getInventory(){
        return inv;
    }




}
