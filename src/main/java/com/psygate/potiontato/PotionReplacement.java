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

import java.util.List;
import java.util.Objects;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author psygate (http://github.com/psygate)
 */
public class PotionReplacement {

    private final PotionEffectType type;
    private final List<Integer> levels;
    private final boolean replaceInInventory;
    private final boolean stopBrewing;
    private final PotionEffectType replacement;

    public PotionReplacement(PotionEffectType type, List<Integer> levels, boolean replaceInInventory, boolean stopBrewing, PotionEffectType replacement) {
        this.type = type;
        this.levels = levels;
        this.replaceInInventory = replaceInInventory;
        this.stopBrewing = stopBrewing;
        this.replacement = replacement;
    }

    public PotionEffectType getType() {
        return type;
    }

    public List<Integer> getLevels() {
        return levels;
    }

    public boolean isReplaceInInventory() {
        return replaceInInventory;
    }

    public boolean isStopBrewing() {
        return stopBrewing;
    }

    public PotionEffectType getReplacement() {
        return replacement;
    }

    public boolean hasReplacement() {
        return replacement != null;
    }

    public boolean hasLevel(final int level) {
        return levels.contains(level);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + Objects.hashCode(this.levels);
        hash = 89 * hash + (this.replaceInInventory ? 1 : 0);
        hash = 89 * hash + (this.stopBrewing ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.replacement);
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
        final PotionReplacement other = (PotionReplacement) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.levels, other.levels)) {
            return false;
        }
        if (this.replaceInInventory != other.replaceInInventory) {
            return false;
        }
        if (this.stopBrewing != other.stopBrewing) {
            return false;
        }
        if (!Objects.equals(this.replacement, other.replacement)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PotionReplacement{" + getType() + " - "
                + levels.toString()
                + " Replace in Inventory:"
                + isReplaceInInventory()
                + ((isReplaceInInventory()) ? getReplacement().getName() : "")
                + " Stop brewing: " + isStopBrewing()
                + "}";
    }

}
