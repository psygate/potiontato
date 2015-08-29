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

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.psygate.potiontato.potions.Potion;
import com.psygate.potiontato.potions.Recipe;
import java.util.List;
import java.util.Set;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author psygate (http://github.com/psygate)
 */
public class Potiontato extends JavaPlugin {

    public final static boolean DEBUG = false;
    private final Map<String, PotionEffectType> aliases = new HashMap<>();
    private MainConfiguration conf;
    private static Potiontato instance;
    private final ConfigurationProxy confproxy;

    public Potiontato() {
        instance = this;
        for (PotionEffectType type : PotionEffectType.values()) {
            if (type == null) {
                Bukkit.getLogger().log(Level.WARNING, "Potion effect is null! ({0})", type);
            } else {
                aliases.put(type.getName().toLowerCase(), type);
            }

        }

        aliases.put("strength", PotionEffectType.INCREASE_DAMAGE);
        aliases.put("regen", PotionEffectType.REGENERATION);
        this.confproxy = new ConfigurationProxy();
    }

    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyHeader(true);
        getConfig().options().copyDefaults(true);
        if (DEBUG) {
            saveDefaultConfig();
        } else {
            saveConfig();
        }
        reloadConfig();

        this.conf = new MainConfiguration(this);

        if (conf.isEnabled()) {
            Bukkit.getPluginManager().registerEvents(new NerfListener(), this);
        }
    }

    public static PotionEffectType potionTypeForName(final String name) {
        return instance.aliases.get(name.toLowerCase());
    }

    public static Configuration getConfiguration() {
        return instance.confproxy;
    }

    private final static class ConfigurationProxy implements Configuration {

        @Override
        public boolean isEnabled() {
            return instance.conf.isEnabled();
        }

        @Override
        public boolean isForbidden(Potion pot) {
            return instance.conf.isForbidden(pot);
        }

//        @Override
//        public boolean isStopBrewing(Potion pot) {
//            return instance.conf.isStopBrewing(pot);
//        }

        @Override
        public boolean hasReplacement(Potion pot) {
            return instance.conf.hasReplacement(pot);
        }

        @Override
        public Potion getReplacement(Potion pot) {
            return instance.conf.getReplacement(pot);
        }

        @Override
        public Set<Recipe> getForbiddenRecipes() {
            return instance.conf.getForbiddenRecipes();
        }

        @Override
        public Set<Potion> getForbiddenPotions() {
            return instance.conf.getForbiddenPotions();
        }

    }

    public static Logger logger() {
        return instance.getLogger();
    }
}
