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

import com.psygate.potiontato.potions.PotionWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.block.BrewingStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import com.psygate.potiontato.potions.Potion;
import com.psygate.potiontato.potions.Recipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;

/**
 *
 * @author psygate (http://github.com/psygate)
 */
public class NerfListener implements Listener {

    public Configuration conf = Potiontato.getConfiguration();

    @EventHandler(priority = EventPriority.HIGH)
    public void onPotionBrew(org.bukkit.event.inventory.BrewEvent ev) {

        if (PotionWrapper.contains(ev.getContents().getHolder(), conf.getForbiddenRecipes())) {

            ItemStack[] items = ev.getContents().getContents();
            for (ItemStack drop : items) {

                ev.getBlock().getWorld().dropItemNaturally(ev.getBlock().getLocation(), drop);
            }

            ev.getContents().clear();
            ev.setCancelled(true);

        }
//        Iterator<ItemStack> it = ev.getContents().iterator();
//        while (it.hasNext()) {
//            ItemStack itemstack = it.next();
//            if (itemstack == null || itemstack.getType() != Material.POTION) {
//                continue;
//            }
//
//            changePotion(itemstack);
//        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPotionConsume(PotionSplashEvent ev) {
        for (PotionEffect eff : ev.getPotion().getEffects()) {
            if (conf.getForbiddenPotions().contains(PotionWrapper.potionFor(eff.getType(), eff.getAmplifier() < 1, false, false, true))) {
                ev.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPotionConsume(org.bukkit.event.player.PlayerItemConsumeEvent ev) {
        Potion wrap = PotionWrapper.potionFor(ev.getItem());
        if (conf.getForbiddenPotions().contains(wrap)) {
            ItemStack item = ev.getPlayer().getItemInHand();
            if (conf.hasReplacement(wrap)) {
                ev.getPlayer().sendMessage("[Potiontato]" + ChatColor.RED + "This potion has been banned and replaced.");
                ItemStack stack = conf.getReplacement(wrap).toItemStack();
                item.setType(stack.getType());
                item.setDurability(stack.getDurability());
            } else {
                ev.getPlayer().sendMessage("[Potiontato]" + ChatColor.RED + "This potion has been banned.");
                item.setType(Material.AIR);
            }

            ev.getPlayer().setItemInHand(item);
            ev.setCancelled(true);
        }
//        if (ev.getItem().getType() == Material.POTION) {
//            System.out.println(Potion.fromItemStack(ev.getItem()));
//            changePotion(ev.getItem());
//            changePotion(ev.getPlayer().getItemInHand());
//        }
    }

    private void changePotion(ItemStack itemstack) {
//        Bukkit.broadcastMessage("ItemStack                 : " + itemstack);
//        Bukkit.broadcastMessage("ItemStack Data            : " + itemstack.getData());
//        Bukkit.broadcastMessage("ItemStack Dura            : " + itemstack.getDurability());
//        Bukkit.broadcastMessage("PotionEffectType (By dura):" + PotionEffectType.getById(itemstack.getDurability()));
//        Potion pot = Potion.fromItemStack(itemstack);
//
//        if (conf.isForbidden(pot)) {
//            if (conf.isStopBrewing(pot) && !conf.hasReplacement(pot)) {
//                itemstack.setType(Material.AIR);
//            } else if (conf.isStopBrewing(pot) && !conf.hasReplacement(pot)) {
//                Potion rep = conf.getReplacement(pot);
//                ItemStack other = rep.toItemStack();
//                itemstack.setType(other.getType());
//                itemstack.setDurability(other.getDurability());
//                itemstack.setItemMeta(other.getItemMeta());
////                    replacement = new PotionEffect(rep.getReplacement(), effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.hasParticles());
//            }
//
//        }

    }
}
