# AdvancedItemBuilder
Easily create [Bukkit](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/inventory/ItemStack.html) ItemStack with ItemMeta

## Features
* Easily create complicated ItemStacks
* Player Skulls
* Fireworks
* Potions
* Books
* Banners
* Spawneggs (`ItemBuilder#setEggType(EntityType type)`)
* MapMeta
* Leather Armor
* ~~NMS Support~~ (Planned)

## Usage
1. Copy the [ItemBuilder.java](https://github.com/Jordieh/AdvancedItemBuilder/blob/master/ItemBuilder.java) file to your project into a separate class (don't forget to define your package)
2. See [below](https://github.com/Jordieh/AdvancedItemBuilder#examples) for coding examples
3. Please do not make edits to the license
4. You can submit a [pull request](https://github.com/Jordieh/AdvancedItemBuilder/pulls) if you want to add feautures

## Examples
**Result**

![Image of Yaktocat](https://i.gyazo.com/451fec9318f857bb179f02f0ba194a8d.gif)

Golden Apples
```java
ItemStack itemStack = new ItemBuilder(Material.APPLE)
        .setName(ChatColor.GOLD + "Golden Apple")
        .addLore(ChatColor.GRAY + "Example golden apple")
        .addUnsafeEnch(Enchantment.ARROW_INFINITE, 10)
        .amount(33)
        .type(Material.GOLDEN_APPLE)
        .build();
```
Custom Books
```java
ItemStack itemStack = new ItemBuilder(Material.WRITTEN_BOOK)
        .author("Special Author")
        .title(ChatColor.BLUE + "Very cool blue")
        .generation(BookMeta.Generation.TATTERED)
        .addBookPage("Page 1 content", "Page 2 content")
        .build();
```
Player heads
```java
ItemStack itemStack = new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3)
        .setOwningPlayer(Bukkit.getOfflinePlayer("Notch"))
        .name(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Head of Notch")
        .setAmount(3)
        .build();
```
Pickaxe
```java
ItemStack itemStack = new ItemBuilder(Material.IRON_PICKAXE)
        .setUnbreakable(true)
        .addEnch(Enchantment.DIG_SPEED, 3)
        .addItemFlag(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS)
        .name(ChatColor.WHITE + "Special pickaxe")
        .setAmount(3)
        .durability(9)
        .build();
```
Firework Charge
```java
ItemStack itemStack = new ItemBuilder(Material.FIREWORK_CHARGE)
        .setChargeEffect(FireworkEffect.builder().flicker(true).build())
        .amount(4)
        .build();
```
Fireworks
```java
ItemStack itemStack = new ItemBuilder(Material.FIREWORK)
        .setName("Explosive rocket")
        .setLore("Very", "Special", "Rocket")
        .addFireworkEffect(FireworkEffect.builder().withColor(Color.AQUA).build())
        .amount(100)
        .build();
```
Potions
```java
ItemStack itemStack = new ItemBuilder(Material.LINGERING_POTION)
        .setPotionColor(Color.MAROON)
        .effect(new PotionEffect(PotionEffectType.ABSORPTION, 1, 1), false)
        .type(Material.SPLASH_POTION)
        .build();
```
Banners
```java
ItemStack itemStack = new ItemBuilder(Material.BANNER, 1, (short) 5)
        .addBannerPattern(new Pattern(DyeColor.BLUE, PatternType.BRICKS))
        .addBannerPattern(new Pattern(DyeColor.RED, PatternType.CREEPER))
        .removeBannerPattern(1)
        .build();
```