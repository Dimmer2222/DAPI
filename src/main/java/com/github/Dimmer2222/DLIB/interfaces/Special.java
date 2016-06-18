package com.github.Dimmer2222.DLIB.interfaces;

import com.github.Dimmer2222.DLIB.exceptions.ValueExistException;
import com.github.Dimmer2222.DLIB.exceptions.ValueNotExistException;
import org.bukkit.Location;

import java.util.Set;

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

public interface Special<T> {

    /**
     * Creates a Object from a Special Type like Items or Blocks or it save the location of the Object
     * @param object Object(Items or Blocks)
     * @param name Name of the Object
     * @throws ValueExistException Will be thrown if the Object Name is already taken.
     */
    void createSpecialObject(T object, String name) throws ValueExistException;

    /**
     * Saves the Object like the first createSpecalObject method but you can give it a Location and it will not create the Object
     * @param loc Location
     * @param name Name of the Object
     * @throws ValueExistException Will be thrown if the Object Name is already taken.
     */
    void createSpecialObject(Location loc, String name) throws ValueExistException;

    /**
     * Get The Object to manipulate it
     * @param Name Name of the Object
     * @return The Object
     * @throws ValueNotExistException If the Object is'nt saved it will be thrown.
     */

    T getSpecialObject(String Name) throws ValueNotExistException;

    /**
     * Get a Set of all Object
     * @return a Set of all Object.
     */
    Set<T> getSpecialObjects();

    /**
     * Delete a Object
     * @param name Name of the Object
     * @throws ValueNotExistException If the Object already not exist or has'nt exist before.
     */
    void deleteSpecialObject(String name) throws ValueNotExistException;

    /**
     * Get The Location of the Object
     * @param name Name of the Object
     * @return Location of the Object
     * @throws ValueNotExistException if the Object is'nt saved it will be thrown.
     */
    Location getSpecialObjectLocation(String name) throws ValueNotExistException;


}
