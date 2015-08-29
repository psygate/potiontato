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
package com.psygate.potiontato;

import com.psygate.potiontato.potions.Potion;
import com.psygate.potiontato.potions.PotionWrapper;
import com.psygate.potiontato.potions.Recipe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

/**
 *
 * @author psygate (http://github.com/psygate)
 */
public class MainConfiguration implements Configuration {

    private final boolean enabled;
    private final Map<PotionEffectType, PotionEffectType> replacements = new HashMap<>();
    private final Set<Potion> forbidden = new HashSet<>();
    private final Set<Recipe> recipes = new HashSet<>();
//    private final Set<Potion> stopBrewing = new HashSet<>();
//    private final Map<Potion, Boolean> replaceInInventory = new HashMap<>();

    public MainConfiguration(Potiontato potiontato) {
        FileConfiguration cnf = potiontato.getConfig();
        enabled = cnf.getBoolean("enabled");

        /*
         forbidden_potions:
         - strength:
         level: all
         replaceInInventory: true
         stopBrewing: true
         replacement: REGEN
        
         */
        List<Map<String, Map<String, ?>>> forbiddensection = (List<Map<String, Map<String, ?>>>) cnf.getList("forbidden_potions");
        for (Map<String, Map<String, ?>> potionspec : forbiddensection) {
            for (String effecttypename : potionspec.keySet()) {
                Set<Potion> localforbidden = new HashSet<>();
                PotionEffectType type = Potiontato.potionTypeForName(effecttypename);
                boolean normal = (Boolean) potionspec.get(effecttypename).get("normal");
                boolean enhanced = (Boolean) potionspec.get(effecttypename).get("increase_potency");
                boolean extended = (Boolean) potionspec.get(effecttypename).get("increase_duration");
                boolean splash = (Boolean) potionspec.get(effecttypename).get("make_splash");
                boolean replaceInInventory = (Boolean) potionspec.get(effecttypename).get("replaceInInventory");
                boolean stopBrewing = (Boolean) potionspec.get(effecttypename).get("stopBrewing");
                if (!"".equals(potionspec.get(effecttypename).get("replacement"))) {
                    PotionEffectType reptype = Potiontato.potionTypeForName((String) potionspec.get(effecttypename).get("replacement"));
                    replacements.put(type, reptype);

                }

                if (normal) {
                    Potion nogo = PotionWrapper.potionFor(Potiontato.potionTypeForName(effecttypename), false, false, true, false);
                    localforbidden.add(nogo);
                }

                if (enhanced) {
                    Potion nogo = PotionWrapper.potionFor(Potiontato.potionTypeForName(effecttypename), true, false, true, false);
                    localforbidden.add(nogo);
                }

                if (extended) {
                    Potion nogo = PotionWrapper.potionFor(Potiontato.potionTypeForName(effecttypename), false, true, true, false);
                    localforbidden.add(nogo);
                }

                if (normal && splash) {
                    Potion nogo = PotionWrapper.potionFor(Potiontato.potionTypeForName(effecttypename), false, false, false, true);
                    localforbidden.add(nogo);
                }

                if (enhanced && splash) {
                    Potion nogo = PotionWrapper.potionFor(Potiontato.potionTypeForName(effecttypename), true, false, false, true);
                    localforbidden.add(nogo);
                }

                if (extended && splash) {
                    Potion nogo = PotionWrapper.potionFor(Potiontato.potionTypeForName(effecttypename), false, true, false, true);
                    localforbidden.add(nogo);
                }

                if (stopBrewing) {
                    Set<Recipe> banned = PotionWrapper.recipesFor(localforbidden);
                    System.out.println("Forbidden recipe added. " + banned);
                    recipes.addAll(banned);
//                    this.stopBrewing.addAll(forbidden);
                }

                if (replaceInInventory) {
                    System.out.println("Forbidden potion added. " + localforbidden);
                    this.forbidden.addAll(localforbidden);
                }

            }
//        
        }
//      
        System.out.println("Forbidden from Consumption: " + this.forbidden);
        System.out.println("Forbidden from Brewing: " + this.recipes);
        System.out.println("Replacements: " + this.replacements);

    }

    @Override
    public boolean isForbidden(Potion pot) {
        return forbidden.contains(pot);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private List<Integer> parseLevel(Object level, PotionEffectType type) {
        ArrayList<Integer> intlevels = new ArrayList<>();
        if (level instanceof Integer) {
            intlevels.add((Integer) level);
        } else if (level instanceof String) {
            String levelsstr = ((String) level).replace(" ", "").replace("\t", "").replace(";", ",");

            if (levelsstr.toLowerCase().equals("all")) {
                intlevels.add(-1);  //Signifies all levels.
            } else {

                String[] levels = levelsstr.split(",");

                for (String levelstr : levels) {
                    if ("".equals(levelstr)) {
                        continue;
                    }

                    intlevels.add(Integer.parseInt(levelstr));
                }
            }
        } else {
            throw new IllegalArgumentException("Configuration broken around levels: " + level);
        }
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Level String: {0} {1}", new Object[]{level != null ? level.toString() : "null", level != null ? level.getClass() : "null"});
        return intlevels;
    }

//    @Override
//    public boolean isStopBrewing(Potion pot) {
//        return stopBrewing.contains(pot);
//    }
    @Override
    public boolean hasReplacement(Potion pot) {
        return forbidden.contains(pot) && replacements.containsKey(pot.getEffectType());
    }

    @Override
    public Potion getReplacement(Potion pot) {
        Potion newpot = PotionWrapper.potionFor(replacements.get(pot.getEffectType()), pot.isEnhanced(), pot.isExtended(), pot.isDrinkable(), pot.isSplash());
        return newpot;
//        return replacements.get(pot.getEffectType());
    }

    @Override
    public Set<Recipe> getForbiddenRecipes() {
        return recipes;//PotionWrapper.recipesFor(PotionEffectType.INCREASE_DAMAGE, false, false, true, false);
    }

    @Override
    public Set<Potion> getForbiddenPotions() {
        return forbidden; //new HashSet<>(Arrays.asList(PotionWrapper.potionFor(PotionEffectType.INCREASE_DAMAGE, false, false, true, false)));
    }

}
