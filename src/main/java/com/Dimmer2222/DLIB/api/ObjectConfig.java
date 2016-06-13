package com.Dimmer2222.DLIB.api;

import com.Dimmer2222.DLIB.exceptions.ConfigNotSetException;
import com.Dimmer2222.DLIB.exceptions.ConfigSetException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ObjectConfig<O, V> {

    File file;
    FileConfiguration config;

    public ObjectConfig(String Path, String Name){
        file = new File(Path, Name);
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveObject(O object, V value) throws ConfigSetException{
        save(false);
        if(config.get("ObjectConfig." + object) != null){
            throw new ConfigSetException();
        }
        config.set("ObjectConfig." + object, value);
        save(true);
    }

    @SuppressWarnings("unchecked")
    public V getObjectValue(O object) throws ConfigNotSetException{
        save(false);
        if(config.get("ObjectConfig." + object) == null){
            throw new ConfigNotSetException();
        }
        return (V) config.get("ObjectConfig." + object);
    }

    public void changeObjectValue(O object, V value) throws ConfigNotSetException{
        save(false);
        if(config.get("ObjectConfig." + object) == null){
            throw new ConfigNotSetException();
        }
        config.set("ObjectConfig." + object, value);
        save(true);
    }


    private void save(boolean b){
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (b) {
                config.save(file);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
