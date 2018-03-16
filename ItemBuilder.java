import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 MIT License

 Copyright (c) 16-3-2018 Jordieh

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
public class ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta itemMeta;
    private ItemFactory itemFactory;

    public ItemBuilder(ItemStack itemStack) {
        this.itemFactory = Bukkit.getItemFactory();
        this.itemStack = itemStack;
        this.itemMeta = this.itemFactory.getItemMeta(itemStack.getType());
    }

    public ItemBuilder(Material type) {
        this(new ItemStack(type));
    }

    public ItemBuilder(Material type, int amount) {
        this(new ItemStack(type, amount));
    }

    public ItemBuilder(Material type, int amount, short damage) {
        this(new ItemStack(type, amount, damage));
    }

    public ItemBuilder(Material type, int amount, int damage) {
        this(new ItemStack(type, amount, (short) damage));
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemFactory.asMetaFor(itemMeta, itemStack));
        return itemStack;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment ench, int level) {
        this.itemMeta.addEnchant(ench, level, false);
        return this;
    }

    public ItemBuilder addEnch(Enchantment ench, int level) {
        return this.addEnchantment(ench, level);
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        enchantments.forEach(this::addEnchantment);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        this.itemMeta.addEnchant(ench, level, true);
        return this;
    }

    public ItemBuilder addUnsafeEnch(Enchantment ench, int level) {
        return this.addUnsafeEnchantment(ench, level);
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder amount(int amount) {
        return this.setAmount(amount);
    }

    public ItemBuilder removeEnchantment(Enchantment ench) {
        this.itemMeta.removeEnchant(ench);
        return this;
    }

    public ItemBuilder removeEnch(Enchantment ench) {
        return this.removeEnchantment(ench);
    }

    public ItemBuilder setDurability(short durability) {
        this.itemStack.setDurability(durability);
        return this;
    }

    public ItemBuilder durability(short durability) {
        return this.setDurability(durability);
    }

    public ItemBuilder setDurability(int durability) {
        this.itemStack.setDurability((short) durability);
        return this;
    }

    public ItemBuilder durability(int durability) {
        return this.setDurability(durability);
    }

    @Deprecated
    public ItemBuilder setType(Material type) {
        this.itemStack.setType(type);
        return this;
    }

    @Deprecated
    public ItemBuilder type(Material type) {
        return this.setType(type);
    }

    @Deprecated
    public ItemBuilder setData(MaterialData data) {
        this.itemStack.setData(data);
        return this;
    }

    @Deprecated
    public ItemBuilder data(MaterialData data) {
        return this.setData(data);
    }

    @Deprecated
    public ItemBuilder setItemMeta(ItemMeta itemMeta) {
        this.itemStack.setItemMeta(itemMeta);
        this.itemMeta = itemMeta;
        return this;
    }

    @Deprecated
    public ItemBuilder meta(ItemMeta itemMeta) {
        return this.setItemMeta(itemMeta);
    }

    public ItemBuilder setDisplayName(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    public ItemBuilder name(String name) {
        return this.setDisplayName(name);
    }

    public ItemBuilder setName(String name) {
        return this.setDisplayName(name);
    }

    public ItemBuilder setLore(List<String> lore) {
        this.itemMeta.setLore(lore);
        return this;
    }

    public ItemBuilder addLore(String lore) {
        if (this.itemMeta.hasLore()) {
            this.itemMeta.getLore().add(lore);
        } else {
            this.itemMeta.setLore(Collections.singletonList(lore));
        }
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        this.itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        this.itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag... itemFlags) {
        this.itemMeta.removeItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder clearFlags() {
        itemMeta.getItemFlags().forEach(itemMeta::removeItemFlags);
        return this;
    }

    public ItemBuilder setEggType(EntityType type) {
        if (this.itemStack.getType() == Material.MONSTER_EGG) {
            ((SpawnEggMeta) this.itemMeta).setSpawnedType(type);
        }
        return this;
    }

    public ItemBuilder setOwningPlayer(OfflinePlayer player) {
        if (this.itemStack.getType() == Material.SKULL_ITEM) {
            ((SkullMeta) this.itemMeta).setOwningPlayer(player);
        }
        return this;
    }

    public ItemBuilder skull(OfflinePlayer owner) {
        return this.setOwningPlayer(owner);
    }

    private boolean isPotion() {
        Material material = itemStack.getType();
        return material == Material.POTION ||
                material == Material.SPLASH_POTION ||
                material == Material.LINGERING_POTION;
    }

    public ItemBuilder addCustomEffect(PotionEffect effect, boolean overwrite) {
        if (this.isPotion()) {
            ((PotionMeta) this.itemMeta).addCustomEffect(effect, overwrite);
        }
        return this;
    }

    public ItemBuilder effect(PotionEffect effect, boolean overwrite) {
        return this.addCustomEffect(effect, overwrite);
    }

    public ItemBuilder removeCustomEffect(PotionEffectType type) {
        if (this.isPotion()) {
            ((PotionMeta) this.itemMeta).removeCustomEffect(type);
        }
        return this;
    }

    public ItemBuilder removeEffect(PotionEffectType type) {
        return this.removeCustomEffect(type);
    }

    public ItemBuilder setPotionColor(Color color) {
        if (this.isPotion()) {
            ((PotionMeta) this.itemMeta).setColor(color);
        }
        return this;
    }

    public ItemBuilder potionColor(Color color) {
        return this.setPotionColor(color);
    }

    public ItemBuilder clearCustomEffects() {
        if (this.isPotion()) {
            ((PotionMeta) this.itemMeta).clearCustomEffects();
        }
        return this;
    }

    public ItemBuilder clearEffects() {
        return this.clearCustomEffects();
    }

    public ItemBuilder setMapColor(Color color) {
        if (this.itemStack.getType() == Material.MAP) {
            ((MapMeta) this.itemMeta).setColor(color);
        }
        return this;
    }

    public ItemBuilder mapColor(Color color) {
        return this.setMapColor(color);
    }

    public ItemBuilder setMapScaling(boolean value) {
        if (this.itemStack.getType() == Material.MAP) {
            ((MapMeta) this.itemMeta).setScaling(value);
        }
        return this;
    }

    public ItemBuilder mapScaling(boolean value) {
        return this.setMapScaling(value);
    }

    public ItemBuilder setMapLocationName(String name) {
        if (this.itemStack.getType() == Material.MAP) {
            ((MapMeta) this.itemMeta).setLocationName(name);
        }
        return this;
    }

    public ItemBuilder mapLocationName(String name) {
        return this.setMapLocationName(name);
    }

    private boolean isLeatherArmor() {
        Material material = itemStack.getType();
        return material == Material.LEATHER_HELMET ||
                material == Material.LEATHER_CHESTPLATE ||
                material == Material.LEATHER_LEGGINGS ||
                material == Material.LEATHER_BOOTS;
    }

    public ItemBuilder setArmorColor(Color color) {
        if (this.isLeatherArmor()) {
            ((LeatherArmorMeta) this.itemMeta).setColor(color);
        }
        return this;
    }

    public ItemBuilder resetArmorColor() {
        if (this.isLeatherArmor()) {
            ((LeatherArmorMeta) this.itemMeta)
                    .setColor(this.itemFactory.getDefaultLeatherColor());
        }
        return this;
    }

    public ItemBuilder setFireworkPower(int power) {
        if (this.itemStack.getType() == Material.FIREWORK) {
            ((FireworkMeta) this.itemMeta).setPower(power);
        }
        return this;
    }

    public ItemBuilder removeFireworkEffect(int index) {
        if (this.itemStack.getType() == Material.FIREWORK) {
            ((FireworkMeta) this.itemMeta).removeEffect(index);
        }
        return this;
    }

    public ItemBuilder addFireworkEffect(FireworkEffect effect) {
        if (this.itemStack.getType() == Material.FIREWORK) {
            ((FireworkMeta) this.itemMeta).addEffect(effect);
        }
        return this;
    }

    public ItemBuilder addFireworkEffects(Iterable<FireworkEffect> effects) {
        if (this.itemStack.getType() == Material.FIREWORK) {
            ((FireworkMeta) this.itemMeta).addEffects(effects);
        }
        return this;
    }

    public ItemBuilder addFireworkEffects(FireworkEffect... effects) {
        if (this.itemStack.getType() == Material.FIREWORK) {
            ((FireworkMeta) this.itemMeta).addEffects(effects);
        }
        return this;
    }

    public ItemBuilder setChargeEffect(FireworkEffect effect) {
        if (this.itemStack.getType() == Material.FIREWORK_CHARGE) {
            ((FireworkEffectMeta) this.itemMeta).setEffect(effect);
        }
        return this;
    }

    public ItemBuilder setBannerPattern(int i, Pattern pattern) {
        if (this.itemStack.getType() == Material.BANNER) {
            ((BannerMeta) this.itemMeta).setPattern(i, pattern);
        }
        return this;
    }

    public ItemBuilder setBannerPatterns(List<Pattern> patterns) {
        if (this.itemStack.getType() == Material.BANNER) {
            ((BannerMeta) this.itemMeta).setPatterns(patterns);
        }
        return this;
    }

    public ItemBuilder removeBannerPattern(int i) {
        if (this.itemStack.getType() == Material.BANNER) {
            ((BannerMeta) this.itemMeta).removePattern(i);
        }
        return this;
    }

    public ItemBuilder addBannerPattern(Pattern pattern) {
        if (this.itemStack.getType() == Material.BANNER) {
            ((BannerMeta) this.itemMeta).addPattern(pattern);
        }
        return this;
    }

    public ItemBuilder addBannerPatterns(Pattern... patterns) {
        if (this.itemStack.getType() == Material.BANNER) {
            BannerMeta bannerMeta = (BannerMeta) this.itemMeta;
            for (Pattern pattern : patterns) {
                bannerMeta.addPattern(pattern);
            }
        }
        return this;
    }

    private boolean isBook() {
        Material material = this.itemStack.getType();
        return material == Material.BOOK_AND_QUILL || material == Material.WRITTEN_BOOK;
    }

    public ItemBuilder addBookPage(String... pages) {
        if (this.isBook()) {
            ((BookMeta) this.itemMeta).addPage(pages);
        }
        return this;
    }

    public ItemBuilder author(String author) {
        return this.setBookAuthor(author);
    }

    public ItemBuilder setBookAuthor(String author) {
        if (this.isBook()) {
            ((BookMeta) this.itemMeta).setAuthor(author);
        }
        return this;
    }

    public ItemBuilder generation(BookMeta.Generation generation) {
        return this.setBookGeneration(generation);
    }

    public ItemBuilder setBookGeneration(BookMeta.Generation generation) {
        if (this.isBook()) {
            ((BookMeta) this.itemMeta).setGeneration(generation);
        }
        return this;
    }

    public ItemBuilder setPage(int page, String data) {
        return this.setBookPage(page, data);
    }

    public ItemBuilder setBookPage(int page, String data) {
        if (this.isBook()) {
            ((BookMeta) this.itemMeta).setPage(page, data);
        }
        return this;
    }

    public ItemBuilder title(String title) {
        return this.setBookTitle(title);
    }

    public ItemBuilder setBookTitle(String title) {
        if (this.isBook()) {
            ((BookMeta) this.itemMeta).setTitle(title);
        }
        return this;
    }

    public ItemBuilder setPages(String... pages) {
        if (this.isBook()) {
            ((BookMeta) this.itemMeta).setPages(pages);
        }
        return this;
    }

    public ItemBuilder setPages(List<String> pages) {
        if (this.isBook()) {
            ((BookMeta) this.itemMeta).setPages(pages);
        }
        return this;
    }
}
