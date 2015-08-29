/*


 The MIT License (MIT)

 Copyright (c) 2015 psygate (http://github.com/psygate)

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.

 */
package com.psygate.potiontato.potions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.bukkit.Material;
import static org.bukkit.Material.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;

/**
 *
 * @author psygate (http://github.com/psygate)
 */
public class PotionRecipe {

    private final static List<PotionRecipe> recipes = new LinkedList<>();

    private final ItemStack inputMaterial;
    private final Potion fromPotion;

    private PotionRecipe(ItemStack inputMaterial, Potion fromPotion) {
        this.inputMaterial = inputMaterial;
        this.fromPotion = fromPotion;
    }
}
