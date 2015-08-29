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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.bukkit.Material;
import static org.bukkit.Material.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import static org.bukkit.potion.PotionEffectType.*;
import org.bukkit.potion.PotionType;
import java.util.Set;
import org.bukkit.block.BrewingStand;
import org.bukkit.inventory.BrewerInventory;

/**
 *
 * @author psygate (http://github.com/psygate)
 */
public class PotionWrapper {

//    private static final Map<Short, Potion> potions = new HashMap<>();
//    private static final Map<Potion, Short> reversepotions = new HashMap<>();
    private final static Set<Potion> vanilla = new HashSet<>();
    private final static Set<Integer> vanillaids = new HashSet<>();
    private final static Map<Integer, PotionEffectType> idToTypeMap = new HashMap<>();
    private final static Map<PotionEffectType, Integer> typeToIdMap = new HashMap<>();
    private final static Map<PotionEffectType, List<ItemStack>> materials = new HashMap<>();
    private final static Set<Recipe> recipes = new HashSet<>();

    //Enhancements
    private final static ItemStack splash = new ItemStack(Material.SULPHUR, 1);
    private final static ItemStack enhance = new ItemStack(Material.GLOWSTONE_DUST, 1);
    private final static ItemStack extend = new ItemStack(Material.REDSTONE, 1);

    //Base ingredients
    private final static ItemStack goldencarrot = new ItemStack(Material.GOLDEN_CARROT, 1);
    private final static ItemStack rabbitfoot = new ItemStack(Material.RABBIT_FOOT, 1);
    private final static ItemStack magmacream = new ItemStack(Material.MAGMA_CREAM, 1);
    private final static ItemStack netherwart = new ItemStack(Material.NETHER_WARTS, 1);
    private final static ItemStack sugar = new ItemStack(Material.SUGAR, 1);
    private final static ItemStack pufferfish = new ItemStack(Material.RAW_FISH, 1, (short) 3);
    private final static ItemStack glisteringmelon = new ItemStack(Material.SPECKLED_MELON, 1);
    private final static ItemStack spidereye = new ItemStack(Material.SPIDER_EYE, 1);
    private final static ItemStack ghasttear = new ItemStack(Material.GHAST_TEAR, 1);
    private final static ItemStack blazepowder = new ItemStack(Material.BLAZE_POWDER, 1);
    private final static ItemStack fermentedspidereye = new ItemStack(Material.FERMENTED_SPIDER_EYE, 1);
    private static final ItemStack increasedamage = new ItemStack(Material.POTION, 1, (short) 8201);
    private static final ItemStack splashincreasedamage = new ItemStack(Material.POTION, 1, (short) 16393);
    private static final ItemStack slow = new ItemStack(Material.POTION, 1, (short) 8202);
    private static final ItemStack splashslow = new ItemStack(Material.POTION, 1, (short) 16394);
    private static final ItemStack jump = new ItemStack(Material.POTION, 1, (short) 8203);
    private static final ItemStack splashjump = new ItemStack(Material.POTION, 1, (short) 16395);
    private static final ItemStack harm = new ItemStack(Material.POTION, 1, (short) 8204);
    private static final ItemStack splashharm = new ItemStack(Material.POTION, 1, (short) 16396);
    private static final ItemStack waterbreathing = new ItemStack(Material.POTION, 1, (short) 8205);
    private static final ItemStack splashwaterbreathing = new ItemStack(Material.POTION, 1, (short) 16397);
    private static final ItemStack invisibility = new ItemStack(Material.POTION, 1, (short) 8206);
    private static final ItemStack splashinvisibility = new ItemStack(Material.POTION, 1, (short) 16398);
    private static final ItemStack enhancedregeneration = new ItemStack(Material.POTION, 1, (short) 8225);
    private static final ItemStack enhancedsplashregeneration = new ItemStack(Material.POTION, 1, (short) 16417);
    private static final ItemStack enhancedspeed = new ItemStack(Material.POTION, 1, (short) 8226);
    private static final ItemStack enhancedsplashspeed = new ItemStack(Material.POTION, 1, (short) 16418);
    private static final ItemStack enhancedpoison = new ItemStack(Material.POTION, 1, (short) 8228);
    private static final ItemStack enhancedsplashpoison = new ItemStack(Material.POTION, 1, (short) 16420);
    private static final ItemStack enhancedheal = new ItemStack(Material.POTION, 1, (short) 8229);
    private static final ItemStack enhancedsplashheal = new ItemStack(Material.POTION, 1, (short) 16421);
    private static final ItemStack enhancedincreasedamage = new ItemStack(Material.POTION, 1, (short) 8233);
    private static final ItemStack enhancedsplashincreasedamage = new ItemStack(Material.POTION, 1, (short) 16425);
    private static final ItemStack enhancedjump = new ItemStack(Material.POTION, 1, (short) 8235);
    private static final ItemStack enhancedsplashjump = new ItemStack(Material.POTION, 1, (short) 16427);
    private static final ItemStack enhancedharm = new ItemStack(Material.POTION, 1, (short) 8236);
    private static final ItemStack enhancedsplashharm = new ItemStack(Material.POTION, 1, (short) 16428);
    private static final ItemStack extendedregeneration = new ItemStack(Material.POTION, 1, (short) 8257);
    private static final ItemStack extendedsplashregeneration = new ItemStack(Material.POTION, 1, (short) 16449);
    private static final ItemStack extendedspeed = new ItemStack(Material.POTION, 1, (short) 8258);
    private static final ItemStack extendedsplashspeed = new ItemStack(Material.POTION, 1, (short) 16450);
    private static final ItemStack extendedfireresistance = new ItemStack(Material.POTION, 1, (short) 8259);
    private static final ItemStack extendedsplashfireresistance = new ItemStack(Material.POTION, 1, (short) 16451);
    private static final ItemStack extendedpoison = new ItemStack(Material.POTION, 1, (short) 8260);
    private static final ItemStack extendedsplashpoison = new ItemStack(Material.POTION, 1, (short) 16452);
    private static final ItemStack extendednightvision = new ItemStack(Material.POTION, 1, (short) 8262);
    private static final ItemStack extendedsplashnightvision = new ItemStack(Material.POTION, 1, (short) 16454);
    private static final ItemStack extendedweakness = new ItemStack(Material.POTION, 1, (short) 8264);
    private static final ItemStack extendedsplashweakness = new ItemStack(Material.POTION, 1, (short) 16456);
    private static final ItemStack extendedincreasedamage = new ItemStack(Material.POTION, 1, (short) 8265);
    private static final ItemStack extendedsplashincreasedamage = new ItemStack(Material.POTION, 1, (short) 16457);
    private static final ItemStack extendedslow = new ItemStack(Material.POTION, 1, (short) 8266);
    private static final ItemStack extendedsplashslow = new ItemStack(Material.POTION, 1, (short) 16458);
    private static final ItemStack extendedjump = new ItemStack(Material.POTION, 1, (short) 8267);
    private static final ItemStack extendedsplashjump = new ItemStack(Material.POTION, 1, (short) 16459);
    private static final ItemStack extendedwaterbreathing = new ItemStack(Material.POTION, 1, (short) 8269);
    private static final ItemStack extendedsplashwaterbreathing = new ItemStack(Material.POTION, 1, (short) 16461);
    private static final ItemStack extendedinvisibility = new ItemStack(Material.POTION, 1, (short) 8270);
    private static final ItemStack extendedsplashinvisibility = new ItemStack(Material.POTION, 1, (short) 16462);
    private static final ItemStack regeneration = new ItemStack(Material.POTION, 1, (short) 8193);
    private static final ItemStack splashregeneration = new ItemStack(Material.POTION, 1, (short) 16385);
    private static final ItemStack speed = new ItemStack(Material.POTION, 1, (short) 8194);
    private static final ItemStack splashspeed = new ItemStack(Material.POTION, 1, (short) 16386);
    private static final ItemStack fireresistance = new ItemStack(Material.POTION, 1, (short) 8195);
    private static final ItemStack splashfireresistance = new ItemStack(Material.POTION, 1, (short) 16387);
    private static final ItemStack poison = new ItemStack(Material.POTION, 1, (short) 8196);
    private static final ItemStack splashpoison = new ItemStack(Material.POTION, 1, (short) 16388);
    private static final ItemStack heal = new ItemStack(Material.POTION, 1, (short) 8197);
    private static final ItemStack splashheal = new ItemStack(Material.POTION, 1, (short) 16389);
    private static final ItemStack nightvision = new ItemStack(Material.POTION, 1, (short) 8198);
    private static final ItemStack splashnightvision = new ItemStack(Material.POTION, 1, (short) 16390);
    private static final ItemStack weakness = new ItemStack(Material.POTION, 1, (short) 8200);
    private static final ItemStack splashweakness = new ItemStack(Material.POTION, 1, (short) 16392);
    private static final ItemStack waterbottle = new ItemStack(Material.POTION, 1, (short) 0);
    private static final ItemStack awkwardpotion = new ItemStack(Material.POTION, 1, (short) 16);

    private static final ItemStack thickpotion = new ItemStack(Material.POTION, 1, (short) 32);
    private static final ItemStack extendedmundanepotion = new ItemStack(Material.POTION, 1, (short) 64);
    private static final ItemStack mundanepotion = new ItemStack(Material.POTION, 1, (short) 8192);

    //Potions
    static {
        /*
         373:16 Awkward Potion
         373:32 Thick Potion
         373:64 Mundane Potion
         373:8193 Regeneration Potion (0:45)
         373:8194 Swiftness Potion (3:00)
         373:8195 Fire Resistance Potion (3:00)
         373:8196 Poison Potion (0:45)
         373:8197 Healing Potion
         373:8200 Weakness Potion (1:30)
         373:8201 Strength Potion (3:00)
         373:8202 Slowness Potion (1:30)
         373:8204 Harming Potion
         373:8225 Regeneration Potion II (0:22)
         373:8226 Swiftness Potion II (1:30)
         373:8228 Poison Potion II (0:22)
         373:8229 Healing Potion II
         373:8233 Strength Potion II (1:30)
         373:8236 Harming Potion II
         373:8257 Regeneration Potion (2:00)
         373:8258 Swiftness Potion (8:00)
         373:8259 Fire Resistance Potion (8:00)
         373:8260 Poison Potion (2:00)
         373:8264 Weakness Potion (4:00)
         373:8265 Strength Potion (8:00)
         373:8266 Slowness Potion (4:00)
         373:16378 Fire Resistance Splash (2:15)
         373:16385 Regeneration Splash (0:33)
         373:16386 Swiftness Splash (2:15)
         373:16388 Poison Splash (0:33)
         373:16389 Healing Splash
         373:16392 Weakness Splash (1:07)
         373:16393 Strength Splash (2:15)
         373:16394 Slowness Splash (1:07)
         373:16396 Harming Splash
         373:16418 Swiftness Splash II (1:07)
         373:16420 Poison Splash II (0:16)
         373:16421 Healing Splash II
         373:16425 Strength Splash II (1:07)
         373:16428 Harming Splash II
         373:16449 Regeneration Splash (1:30)
         373:16450 Swiftness Splash (6:00)
         373:16451 Fire Resistance Splash (6:00)
         373:16452 Poison Splash (1:30)
         373:16456 Weakness Splash (3:00)
         373:16457 Strength Splash (6:00)
         373:16458 Slowness Splash (3:00)
         373:16471 Regeneration Splash II (0:16)

         */

//        recipes.put(new PWRecipe(, splash, extend), null)
        final Map<PotionEffectType, int[]> vanillapots = new HashMap<>();

//        final int[] vanillaids = new int[]{
        vanillapots.put(REGENERATION, new int[]{8193, 8225, 8257, 16385, 16417, 16449});
        vanillapots.put(SPEED, new int[]{8194, 8226, 8258, 16386, 16418, 16450});
        vanillapots.put(FIRE_RESISTANCE, new int[]{8195, 8259, 16387, 16451});
        vanillapots.put(POISON, new int[]{8196, 8228, 8260, 16388, 16420, 16452});
        vanillapots.put(HEAL, new int[]{8197, 8229, 16389, 16421});
        vanillapots.put(NIGHT_VISION, new int[]{8198, 8262, 16390, 16454});
        vanillapots.put(WEAKNESS, new int[]{8200, 8264, 16392, 16456});
        vanillapots.put(INCREASE_DAMAGE, new int[]{8201, 8233, 8265, 16393, 16425, 16457});
        vanillapots.put(SLOW, new int[]{8202, 8266, 16394, 16458});
        vanillapots.put(JUMP, new int[]{8203, 8235, 8267, 16395, 16427, 16459});
        vanillapots.put(HARM, new int[]{8204, 8236, 16396, 16428});
        vanillapots.put(WATER_BREATHING, new int[]{8205, 8269, 16397, 16461});
        vanillapots.put(INVISIBILITY, new int[]{8206, 8270, 16398, 16462});
//        };

        for (PotionEffectType type : vanillapots.keySet()) {
            for (int id : vanillapots.get(type)) {
                addPotionEffect(type, id & 15);
                vanillaids.add(id);
                vanilla.add(new PWPotion((short) id));
            }
        }

        PWRecipe[] recipes = new PWRecipe[]{
            new PWRecipe(netherwart, waterbottle, awkwardpotion),
            new PWRecipe(sugar, waterbottle, mundanepotion),
            new PWRecipe(magmacream, waterbottle, mundanepotion),
            new PWRecipe(glisteringmelon, waterbottle, mundanepotion),
            new PWRecipe(ghasttear, waterbottle, mundanepotion),
            new PWRecipe(rabbitfoot, waterbottle, mundanepotion),
            new PWRecipe(blazepowder, waterbottle, mundanepotion),
            new PWRecipe(spidereye, waterbottle, mundanepotion),
            new PWRecipe(enhance, waterbottle, thickpotion),
            new PWRecipe(extend, waterbottle, extendedmundanepotion),
            new PWRecipe(fermentedspidereye, waterbottle, weakness),
            //--------------------------------------------------------
            new PWRecipe(goldencarrot, awkwardpotion, nightvision),
            new PWRecipe(extend, nightvision, extendednightvision),
            new PWRecipe(fermentedspidereye, nightvision, invisibility),
            new PWRecipe(fermentedspidereye, extendednightvision, extendedinvisibility),
            new PWRecipe(extend, invisibility, extendedinvisibility),
            //--------------------------------------------------------
            new PWRecipe(magmacream, awkwardpotion, fireresistance),
            new PWRecipe(extend, fireresistance, extendedfireresistance),
            new PWRecipe(fermentedspidereye, fireresistance, slow),
            new PWRecipe(fermentedspidereye, extendedfireresistance, extendedslow),
            new PWRecipe(extend, slow, extendedslow),
            //--------------------------------------------------------
            new PWRecipe(rabbitfoot, awkwardpotion, jump),
            new PWRecipe(extend, jump, extendedjump),
            new PWRecipe(enhance, jump, enhancedjump),
            new PWRecipe(fermentedspidereye, jump, slow),
            //--------------------------------------------------------
            new PWRecipe(sugar, awkwardpotion, speed),
            new PWRecipe(extend, speed, extendedspeed),
            new PWRecipe(enhance, speed, enhancedspeed),
            new PWRecipe(fermentedspidereye, speed, slow),
            new PWRecipe(fermentedspidereye, extendedspeed, extendedslow),
            //--------------------------------------------------------
            new PWRecipe(pufferfish, awkwardpotion, waterbreathing),
            new PWRecipe(extend, waterbreathing, extendedwaterbreathing),
            new PWRecipe(fermentedspidereye, waterbreathing, harm),
            //--------------------------------------------------------
            new PWRecipe(glisteringmelon, awkwardpotion, heal),
            new PWRecipe(enhance, heal, enhancedheal),
            new PWRecipe(fermentedspidereye, heal, harm),
            new PWRecipe(fermentedspidereye, jump, enhancedharm),
            //--------------------------------------------------------
            new PWRecipe(spidereye, awkwardpotion, poison),
            new PWRecipe(extend, poison, extendedpoison),
            new PWRecipe(enhance, poison, enhancedpoison),
            new PWRecipe(fermentedspidereye, poison, harm),
            new PWRecipe(fermentedspidereye, extendedpoison, enhancedharm),
            //--------------------------------------------------------
            new PWRecipe(ghasttear, awkwardpotion, regeneration),
            new PWRecipe(extend, regeneration, extendedregeneration),
            new PWRecipe(enhance, regeneration, enhancedregeneration),
            //--------------------------------------------------------
            new PWRecipe(blazepowder, awkwardpotion, increasedamage),
            new PWRecipe(extend, regeneration, extendedincreasedamage),
            new PWRecipe(enhance, regeneration, enhancedincreasedamage),
            //--------------------------------------------------------
            new PWRecipe(fermentedspidereye, waterbottle, weakness),
            new PWRecipe(extend, weakness, extendedweakness),
            //--------------------------------------------------------
            new PWRecipe(splash, nightvision, splashnightvision),
            new PWRecipe(splash, extendednightvision, extendedsplashnightvision),
            new PWRecipe(splash, invisibility, splashinvisibility),
            new PWRecipe(splash, extendedinvisibility, extendedsplashinvisibility),
            new PWRecipe(splash, jump, splashjump),
            new PWRecipe(splash, extendedjump, extendedsplashjump),
            new PWRecipe(splash, enhancedjump, enhancedsplashjump),
            new PWRecipe(splash, fireresistance, splashfireresistance),
            new PWRecipe(splash, extendedfireresistance, extendedsplashfireresistance),
            new PWRecipe(splash, slow, splashslow),
            new PWRecipe(splash, extendedslow, extendedsplashslow),
            new PWRecipe(splash, speed, splashspeed),
            new PWRecipe(splash, extendedspeed, extendedsplashspeed),
            new PWRecipe(splash, enhancedspeed, enhancedsplashspeed),
            new PWRecipe(splash, waterbreathing, splashwaterbreathing),
            new PWRecipe(splash, extendedwaterbreathing, extendedsplashwaterbreathing),
            new PWRecipe(splash, heal, splashheal),
            new PWRecipe(splash, enhancedheal, enhancedsplashheal),
            new PWRecipe(splash, harm, splashharm),
            new PWRecipe(splash, enhancedharm, enhancedsplashharm),
            new PWRecipe(splash, poison, splashpoison),
            new PWRecipe(splash, enhancedpoison, enhancedsplashpoison),
            new PWRecipe(splash, extendedpoison, extendedsplashpoison),
            new PWRecipe(splash, regeneration, splashregeneration),
            new PWRecipe(splash, extendedregeneration, extendedsplashregeneration),
            new PWRecipe(splash, enhancedregeneration, enhancedsplashregeneration),
            new PWRecipe(splash, increasedamage, splashincreasedamage),
            new PWRecipe(splash, extendedincreasedamage, extendedsplashincreasedamage),
            new PWRecipe(splash, enhancedincreasedamage, enhancedsplashincreasedamage),
            new PWRecipe(splash, weakness, splashweakness),
            new PWRecipe(splash, extendedweakness, extendedsplashweakness)};

        PotionWrapper.recipes.addAll(Arrays.asList(recipes));
    }

    public static Potion potionFor(PotionEffectType type, boolean enhanced, boolean extended, boolean drinkable, boolean splash) {
        int typeid;
        try {
            typeid = typeToIdMap.get(type);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Not a normal potion. " + type + ", " + enhanced + ", " + extended + ", " + drinkable);
        }
        typeid |= (enhanced) ? 32 : 0;
        typeid |= (extended) ? 64 : 0;
        typeid |= (drinkable) ? 8192 : 0;
        typeid |= (splash) ? 16384 : 0;

        return new PWPotion((short) typeid);
    }

    public static List<Recipe> recipesFor(PotionEffectType type, boolean enhanced, boolean extended, boolean drinkable, boolean splash) {
        return potionFor(type, enhanced, extended, drinkable, splash).getRecipe();
    }

    public static Set<Recipe> recipesFor(ItemStack ingredient) {
        Set<Recipe> out = new HashSet<>();

        for (Recipe rec : recipes) {
            if (rec.getInput().isSimilar(ingredient)) {
                out.add(rec);
            }
        }

        return out;
    }

    public static Set<Recipe> recipesFor(Potion potion) {
        Set<Recipe> out = new HashSet<>();

        for (Recipe rec : recipes) {
            if (PotionWrapper.potionFor(rec.getOutput()).equals(potion)) {
                out.add(rec);
            }
        }

        return out;
    }

    public static Set<Recipe> recipesFor(Collection<Potion> potions) {
        Set<Recipe> out = new HashSet<>();
        for (Potion potion : potions) {
            for (Recipe rec : recipes) {
                if (PotionWrapper.potionFor(rec.getOutput()).equals(potion)) {
                    out.add(rec);
                }
            }
        }

        return out;
    }

    public static Set<Recipe> recipesFor(BrewerInventory inv) {
        Set<Recipe> out = new HashSet<>();
        ItemStack ingredient = inv.getIngredient();

        for (Recipe rec : recipes) {
            for (ItemStack stack : inv.getContents()) {
                if (stack.equals(ingredient)) {
                    continue;
                }

                for (Recipe recipe : recipes) {
                    if (recipe.getMaterial().isSimilar(ingredient) && recipe.getInput().isSimilar(stack)) {
                        out.add(recipe);
                    }
                }
            }

        }

        return out;
    }

    public static ItemStack toSplashItem() {
        return splash.clone();
    }

    public static ItemStack toEnhanced() {
        return enhance.clone();
    }

    public static ItemStack toExtended() {
        return extend.clone();
    }

    private static List<ItemStack> wrap(ItemStack... stack) {
        return Arrays.asList(stack);
    }

    public static boolean contains(BrewingStand brew, Collection<Recipe> forbidden) {
        Set<Recipe> current = recipesFor(brew.getInventory());
        for (Recipe forb : forbidden) {
            if (current.contains(forb)) {
                return true;
            }
        }

        return false;
    }

//    public static short getVanillaDamage(Potion pot) {
//        return reversepotions.get(pot);
//    }
//
//    public static Potion getPotion(short durability) {
//        return potions.get(durability);
//    }
//
//    public static boolean isVanillaPotion(short durability) {
//        return vanilla.contains(getPotion(durability));
//    }
    private static void addPotionEffect(PotionEffectType type, int i) {
        idToTypeMap.put(i, type);
        typeToIdMap.put(type, i);

    }

    public static Potion potionFor(ItemStack item) {
        return new PWPotion(item.getDurability());
    }

    private final static class PWPotion implements Potion {

        private final short id;

        public PWPotion(short id) {
            this.id = id;
        }

//        @Override
//        public boolean hasIncreasedPotency() {
//            return (id & 32) != 0;
//        }
        @Override
        public boolean isEnhanced() {
            return (id & 32) != 0;
        }

        @Override
        public boolean isDrinkable() {
            return (id & 8192) != 0;
        }

        @Override
        public boolean isSplash() {
            return (id & 16384) != 0;
        }

        @Override
        public boolean isExtended() {
            return (id & 64) != 0;
        }

        @Override
        public boolean isVanilla() {
            return vanillaids.contains((int) id);
        }

        @Override
        public PotionEffectType getEffectType() {
            return idToTypeMap.get(id & 15);
        }

        @Override
        public ItemStack toItemStack() {
            return new ItemStack(Material.POTION, 1, (short) id);
        }

        @Override
        public List<Recipe> getRecipe() {
            if (!isVanilla()) {
                throw new IllegalStateException("This is not a vanilla potion.");
            }

            ArrayList<Recipe> internal = new ArrayList<>();
            for (Recipe recipe : recipes) {
                if (recipe.getOutput().isSimilar(toItemStack())) {
                    internal.add(recipe);
                }
            }

            return internal;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 17 * hash + this.id;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final PWPotion other = (PWPotion) obj;
            if (this.id != other.id) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            String out = "";
            if (isEnhanced()) {
                out += "Enhanced ";
            }

            if (isExtended()) {
                out += "Extended ";
            }

            if (isSplash()) {
                out += "Splash ";
            }
            if (isDrinkable()) {
                out += "Drinkable ";
            }

            out += "Potion of " + getEffectType().getName();
            return out;
        }

    }

    private final static class PWRecipe implements Recipe {

        private final ItemStack material;
        private final ItemStack input;
        private final ItemStack output;

        public PWRecipe(ItemStack material, ItemStack input, ItemStack output) {
            this.material = material;
            this.input = input;
            this.output = output;
        }

        @Override
        public ItemStack getMaterial() {
            return material;
        }

        @Override
        public ItemStack getInput() {
            return input;
        }

        @Override
        public ItemStack getOutput() {
            return output;
        }

        @Override
        public boolean contains(BrewingStand brew) {
            Set<Recipe> current = recipesFor(brew.getInventory());
            return current.contains(this);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + Objects.hashCode(this.material);
            hash = 97 * hash + Objects.hashCode(this.input);
            hash = 97 * hash + Objects.hashCode(this.output);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final PWRecipe other = (PWRecipe) obj;
            if (!Objects.equals(this.material, other.material)) {
                return false;
            }
            if (!Objects.equals(this.input, other.input)) {
                return false;
            }
            if (!Objects.equals(this.output, other.output)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "PWRecipe{" + "material=" + material + ", input=" + input + ", output=" + output + '}';
        }

    }
}
