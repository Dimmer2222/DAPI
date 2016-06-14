package com.Dimmer2222.DLIB.api;

import com.Dimmer2222.DLIB.exceptions.ConfigNotSetException;
import com.Dimmer2222.DLIB.exceptions.ConfigSetException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/*
The MIT License (MIT)

Copyright (c) 2016 Dimmer2222

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/


public class ObjectConfig<O, V> {

    File file;
    FileConfiguration config;

    /**
     * Create a Object of the Class
     * @param Path Plugin Folder Path
     * @param Name File Name
     */
    public ObjectConfig(String Path, String Name){
        file = new File(Path, Name);
        config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Save a Object with a Value
     * @param object Object
     * @param value value
     * @throws ConfigSetException Then the Object is already saved.
     */

    public void saveObject(O object, V value) throws ConfigSetException{
        save(false);
        if(config.get("ObjectConfig." + object) != null){
            throw new ConfigSetException();
        }
        config.set("ObjectConfig." + object, value);
        save(true);
    }

    /**
     * Get the value from a Object
     * @param object object
     * @return the Value that it given to the object
     * @throws ConfigNotSetException Then the Object is not saved.
     */
    @SuppressWarnings("unchecked")
    public V getObjectValue(O object) throws ConfigNotSetException{
        save(false);
        if(config.get("ObjectConfig." + object) == null){
            throw new ConfigNotSetException();
        }
        return (V) config.get("ObjectConfig." + object);
    }

    /**
     * Change the value of a Object
     * @param object object
     * @param value value
     * @throws ConfigNotSetException Then the Object is not saved
     */
    public void changeObjectValue(O object, V value) throws ConfigNotSetException{
        save(false);
        if(config.get("ObjectConfig." + object) == null){
            throw new ConfigNotSetException();
        }
        config.set("ObjectConfig." + object, value);
        save(true);
    }


    /**
     * This Method is to save and to generate a new File then no one exist.
     * @param b Disable saving and generate a new File when no File exist by false, by true saving + checking if file exist
     */
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
