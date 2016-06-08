package com.Dimmer2222.DLIB.api.Implements;

import com.Dimmer2222.DLIB.exceptions.ValueExistException;
import com.Dimmer2222.DLIB.exceptions.ValueNotExistException;
import com.Dimmer2222.DLIB.interfaces.Special;
import org.bukkit.Location;
import org.bukkit.entity.Item;

import java.util.Set;

public class SpecialItemManager implements Special<Item>{

    @Override
    public void createSpecialObject(Item object, String name) throws ValueExistException {

    }

    @Override
    public void createSpecialObject(Location loc, String name) throws ValueExistException {

    }

    @Override
    public Item getSpecialObject(String Name) throws ValueNotExistException {
        return null;
    }

    @Override
    public Set<Item> getSpecialObjects() {
        return null;
    }

    @Override
    public void deleteSpecialObject(String name) throws ValueNotExistException {

    }

    @Override
    public Location getSpecialObjectLocation(String name) throws ValueNotExistException {
        return null;
    }
}
