package twintro.minecraft.modbuilder.editor.resources;

import javax.swing.ImageIcon;

import twintro.minecraft.modbuilder.data.resources.recipes.ItemStackResource;
import twintro.minecraft.modbuilder.editor.Editor;

public class MaterialResources {
	public static final String[] vanillaBlocks = new String[]{
			"Acacia Fence", "Acacia Fence Gate", "Acacia Leaves", "Acacia Sapling", "Acacia Wood", "Acacia Wood Planks", "Acacia Wood Slab", "Acacia Wood Stairs", "Activator Rail", "Allium", "Andesite", "Anvil", "Azure Bluet", "Barrier", "Beacon", "Bedrock", "Birch Fence", "Birch Fence Gate", "Birch Leaves", "Birch Sapling", "Birch Wood", "Birch Wood Planks", "Birch Wood Slab", "Birch Wood Stairs", "Black Carpet", "Black Stained Clay", "Black Stained Glass", "Black Stained Glass Pane", "Black Wool", "Block of Coal", "Block of Diamond", "Block of Emerald", "Block of Gold", "Block of Iron", "Block of Quartz", "Block of Redstone", "Blue Carpet", "Blue Orchid", "Blue Stained Clay", "Blue Stained Glass", "Blue Stained Glass Pane", "Blue Wool", "Bookshelf", "Brick Stairs", "Bricks", "Bricks Slab", "Brown Carpet", "Brown Stained Clay", "Brown Stained Glass", "Brown Stained Glass Pane", "Brown Wool", "Button", "Button", "Cactus", "Carpet", "Chest", "Chiseled Quartz Block", "Chiseled Red Sandstone", "Chiseled Sandstone", "Chiseled Stone Brick Monster Egg", "Chiseled Stone Bricks", "Clay", "Coal Ore", "Coarse Dirt", "Cobblestone", "Cobblestone Monster Egg", "Cobblestone Slab", "Cobblestone Stairs", "Cobblestone Wall", "Cobweb", "Command Block", "Cracked Stone Brick Monster Egg", "Cracked Stone Bricks", "Crafting Table", "Cyan Carpet", "Cyan Stained Clay", "Cyan Stained Glass", "Cyan Stained Glass Pane", "Cyan Wool", "Dandelion", "Dark Oak Fence", "Dark Oak Fence Gate", "Dark Oak Leaves", "Dark Oak Sapling", "Dark Oak Wood", "Dark Oak Wood Planks", "Dark Oak Wood Slab", "Dark Oak Wood Stairs", "Dark Prismarine", "Daylight Sensor", "Dead Bush", "Detector Rail", "Diamond Ore", "Diorite", "Dirt", "Dispenser", "Double Tallgrass", "Dragon Egg", "Dropper", "Emerald Ore", "Enchantment Table", "End Portal", "End Stone", "Ender Chest", "Farmland", "Fern", "Furnace", "Glass", "Glass Pane", "Glowstone", "Gold Ore", "Granite", "Grass", "Grass Block", "Gravel", "Gray Carpet", "Gray Stained Clay", "Gray Stained Glass", "Gray Stained Glass Pane", "Gray Wool", "Green Carpet", "Green Stained Clay", "Green Stained Glass", "Green Stained Glass Pane", "Green Wool", "Hardened Clay", "Hay Bale", "Hopper", "Ice", "Iron Bars", "Iron Ore", "Iron Trapdoor", "Jack o'Lantern", "Jukebox", "Jungle Fence", "Jungle Fence Gate", "Jungle Leaves", "Jungle Sapling", "Jungle Wood", "Jungle Wood Planks", "Jungle Wood Slab", "Jungle Wood Stairs", "Ladder", "Lapis Lazuli Block", "Lapis Lazuli Ore", "Large Fern", "Lever", "Light Blue Carpet", "Light Blue Stained Clay", "Light Blue Stained Glass", "Light Blue Stained Glass Pane", "Light Blue Wool", "Light Gray Carpet", "Light Gray Stained Clay", "Light Gray Stained Glass", "Light Gray Stained Glass Pane", "Light Gray Wool", "Lilac", "Lily Pad", "Lime Carpet", "Lime Stained Clay", "Lime Stained Glass", "Lime Stained Glass Pane", "Lime Wool", "Magenta Carpet", "Magenta Stained Clay", "Magenta Stained Glass", "Magenta Stained Glass Pane", "Magenta Wool", "Melon", "Monster Spawner", "Moss Stone", "Mossy Cobblestone Wall", "Mossy Stone Brick Monster Egg", "Mossy Stone Bricks", "Mushroom", "Mushroom", "Mushroom", "Mushroom", "Mycelium", "Nether Brick", "Nether Brick Fence", "Nether Brick Slab", "Nether Brick Stairs", "Nether Quartz Ore", "Netherrack", "Note Block", "Oak Fence", "Oak Fence Gate", "Oak Leaves", "Oak Sapling", "Oak Wood", "Oak Wood Planks", "Oak Wood Slab", "Oak Wood Stairs", "Obsidian", "Orange Carpet", "Orange Stained Clay", "Orange Stained Glass", "Orange Stained Glass Pane", "Orange Tulip", "Orange Wool", "Oxeye Daisy", "Packed Ice", "Peony", "Pillar Quartz Block", "Pink Carpet", "Pink Stained Clay", "Pink Stained Glass", "Pink Stained Glass Pane", "Pink Tulip", "Pink Wool", "Piston", "Podzol", "Polished Andesite", "Polished Diorite", "Polished Granite", "Poppy", "Powered Rail", "Prismarine", "Prismarine Bricks", "Pumpkin", "Purple Carpet", "Purple Stained Clay", "Purple Stained Glass", "Purple Stained Glass Pane", "Purple Wool", "Quartz Slab", "Quartz Stairs", "Rail", "Red Carpet", "Red Sand", "Red Sandstone", "Red Sandstone Slab", "Red Sandstone Stairs", "Red Stained Clay", "Red Stained Glass", "Red Stained Glass Pane", "Red Tulip", "Red Wool", "Redstone Lamp", "Redstone Ore", "Redstone Torch", "Rose Bush", "Sand", "Sandstone", "Sandstone Slab", "Sandstone Stairs", "Sea Lantern", "Shrub", "Slightly Damaged Anvil", "Slime Block", "Smooth Red Sandstone", "Smooth Sandstone", "Snow", "Snow", "Soul Sand", "Sponge", "Spruce Fence", "Spruce Fence Gate", "Spruce Leaves", "Spruce Sapling", "Spruce Wood", "Spruce Wood Planks", "Spruce Wood Slab", "Spruce Wood Stairs", "Sticky Piston", "Stone", "Stone Brick Monster Egg", "Stone Brick Stairs", "Stone Bricks", "Stone Bricks Slab", "Stone Monster Egg", "Stone Pressure Plate", "Stone Slab", "Sunflower", "TNT", "Torch", "Trapped Chest", "Tripwire Hook", "Very Damaged Anvil", "Vines", "Weighted Pressure Plate (Heavy)", "Weighted Pressure Plate (Light)", "Wet Sponge", "White Stained Clay", "White Stained Glass", "White Stained Glass Pane", "White Tulip", "White Wool", "Wooden Pressure Plate", "Wooden Slab", "Wooden Trapdoor", "Yellow Carpet", "Yellow Stained Clay", "Yellow Stained Glass", "Yellow Stained Glass Pane", "Yellow Wool", 
	};
	public static final String[] vanillaBlockIds = new String[]{
			"minecraft:acacia_fence", "minecraft:acacia_fence_gate", "minecraft:leaves2#0", "minecraft:sapling#4", "minecraft:log2#0", "minecraft:planks#4", "minecraft:wooden_slab#4", "minecraft:acacia_stairs", "minecraft:activator_rail", "minecraft:red_flower#2", "minecraft:stone#5", "minecraft:anvil#0", "minecraft:red_flower#3", "minecraft:barrier", "minecraft:beacon", "minecraft:bedrock", "minecraft:birch_fence", "minecraft:birch_fence_gate", "minecraft:leaves#2", "minecraft:sapling#2", "minecraft:log#2", "minecraft:planks#2", "minecraft:wooden_slab#2", "minecraft:birch_stairs", "minecraft:carpet#15", "minecraft:stained_hardened_clay#15", "minecraft:stained_glass#15", "minecraft:stained_glass_pane#15", "minecraft:wool#15", "minecraft:coal_block", "minecraft:diamond_block", "minecraft:emerald_block", "minecraft:gold_block", "minecraft:iron_block", "minecraft:quartz_block#0", "minecraft:redstone_block", "minecraft:carpet#11", "minecraft:red_flower#1", "minecraft:stained_hardened_clay#11", "minecraft:stained_glass#11", "minecraft:stained_glass_pane#11", "minecraft:wool#11", "minecraft:bookshelf", "minecraft:brick_stairs", "minecraft:brick_block", "minecraft:stone_slab#4", "minecraft:carpet#12", "minecraft:stained_hardened_clay#12", "minecraft:stained_glass#12", "minecraft:stained_glass_pane#12", "minecraft:wool#12", "minecraft:stone_button", "minecraft:wooden_button", "minecraft:cactus", "minecraft:carpet#0", "minecraft:chest", "minecraft:quartz_block#1", "minecraft:red_sandstone#1", "minecraft:sandstone#1", "minecraft:monster_egg#5", "minecraft:stonebrick#3", "minecraft:clay", "minecraft:coal_ore", "minecraft:dirt#1", "minecraft:cobblestone", "minecraft:monster_egg#1", "minecraft:stone_slab#3", "minecraft:stone_stairs", "minecraft:cobblestone_wall#0", "minecraft:web", "minecraft:command_block", "minecraft:monster_egg#4", "minecraft:stonebrick#2", "minecraft:crafting_table", "minecraft:carpet#9", "minecraft:stained_hardened_clay#9", "minecraft:stained_glass#9", "minecraft:stained_glass_pane#9", "minecraft:wool#9", "minecraft:yellow_flower", "minecraft:dark_oak_fence", "minecraft:dark_oak_fence_gate", "minecraft:leaves2#1", "minecraft:sapling#5", "minecraft:log2#1", "minecraft:planks#5", "minecraft:wooden_slab#5", "minecraft:dark_oak_stairs", "minecraft:prismarine#2", "minecraft:daylight_detector", "minecraft:deadbush", "minecraft:detector_rail", "minecraft:diamond_ore", "minecraft:stone#3", "minecraft:dirt#0", "minecraft:dispenser", "minecraft:double_plant#2", "minecraft:dragon_egg", "minecraft:dropper", "minecraft:emerald_ore", "minecraft:enchanting_table", "minecraft:end_portal_frame", "minecraft:end_stone", "minecraft:ender_chest", "minecraft:farmland", "minecraft:tallgrass#2", "minecraft:furnace", "minecraft:glass", "minecraft:glass_pane", "minecraft:glowstone", "minecraft:gold_ore", "minecraft:stone#1", "minecraft:tallgrass#1", "minecraft:grass", "minecraft:gravel", "minecraft:carpet#7", "minecraft:stained_hardened_clay#7", "minecraft:stained_glass#7", "minecraft:stained_glass_pane#7", "minecraft:wool#7", "minecraft:carpet#13", "minecraft:stained_hardened_clay#13", "minecraft:stained_glass#13", "minecraft:stained_glass_pane#13", "minecraft:wool#13", "minecraft:hardened_clay", "minecraft:hay_block", "minecraft:hopper", "minecraft:ice", "minecraft:iron_bars", "minecraft:iron_ore", "minecraft:iron_trapdoor", "minecraft:lit_pumpkin", "minecraft:jukebox", "minecraft:jungle_fence", "minecraft:jungle_fence_gate", "minecraft:leaves#3", "minecraft:sapling#3", "minecraft:log#3", "minecraft:planks#3", "minecraft:wooden_slab#3", "minecraft:jungle_stairs", "minecraft:ladder", "minecraft:lapis_block", "minecraft:lapis_ore", "minecraft:double_plant#3", "minecraft:lever", "minecraft:carpet#3", "minecraft:stained_hardened_clay#3", "minecraft:stained_glass#3", "minecraft:stained_glass_pane#3", "minecraft:wool#3", "minecraft:carpet#8", "minecraft:stained_hardened_clay#8", "minecraft:stained_glass#8", "minecraft:stained_glass_pane#8", "minecraft:wool#8", "minecraft:double_plant#1", "minecraft:waterlily", "minecraft:carpet#5", "minecraft:stained_hardened_clay#5", "minecraft:stained_glass#5", "minecraft:stained_glass_pane#5", "minecraft:wool#5", "minecraft:carpet#2", "minecraft:stained_hardened_clay#2", "minecraft:stained_glass#2", "minecraft:stained_glass_pane#2", "minecraft:wool#2", "minecraft:melon_block", "minecraft:mob_spawner", "minecraft:mossy_cobblestone", "minecraft:cobblestone_wall#1", "minecraft:monster_egg#3", "minecraft:stonebrick#1", "minecraft:brown_mushroom", "minecraft:red_mushroom", "minecraft:brown_mushroom_block", "minecraft:red_mushroom_block", "minecraft:mycelium", "minecraft:nether_brick", "minecraft:nether_brick_fence", "minecraft:stone_slab#6", "minecraft:nether_brick_stairs", "minecraft:quartz_ore", "minecraft:netherrack", "minecraft:noteblock", "minecraft:oak_fence", "minecraft:oak_fence_gate", "minecraft:leaves#0", "minecraft:sapling#0", "minecraft:log#0", "minecraft:planks#0", "minecraft:wooden_slab#0", "minecraft:oak_stairs", "minecraft:obsidian", "minecraft:carpet#1", "minecraft:stained_hardened_clay#1", "minecraft:stained_glass#1", "minecraft:stained_glass_pane#1", "minecraft:red_flower#5", "minecraft:wool#1", "minecraft:red_flower#8", "minecraft:packed_ice", "minecraft:double_plant#5", "minecraft:quartz_block#2", "minecraft:carpet#6", "minecraft:stained_hardened_clay#6", "minecraft:stained_glass#6", "minecraft:stained_glass_pane#6", "minecraft:red_flower#7", "minecraft:wool#6", "minecraft:piston", "minecraft:dirt#2", "minecraft:stone#6", "minecraft:stone#4", "minecraft:stone#2", "minecraft:red_flower#0", "minecraft:golden_rail", "minecraft:prismarine#0", "minecraft:prismarine#1", "minecraft:pumpkin", "minecraft:carpet#10", "minecraft:stained_hardened_clay#10", "minecraft:stained_glass#10", "minecraft:stained_glass_pane#10", "minecraft:wool#10", "minecraft:stone_slab#7", "minecraft:quartz_stairs", "minecraft:rail", "minecraft:carpet#14", "minecraft:sand#1", "minecraft:red_sandstone#0", "minecraft:stone_slab2", "minecraft:red_sandstone_stairs", "minecraft:stained_hardened_clay#14", "minecraft:stained_glass#14", "minecraft:stained_glass_pane#14", "minecraft:red_flower#4", "minecraft:wool#14", "minecraft:redstone_lamp", "minecraft:redstone_ore", "minecraft:redstone_torch", "minecraft:double_plant#4", "minecraft:sand#0", "minecraft:sandstone#0", "minecraft:stone_slab#1", "minecraft:sandstone_stairs", "minecraft:sea_lantern", "minecraft:tallgrass#0", "minecraft:anvil#1", "minecraft:slime_block", "minecraft:red_sandstone#2", "minecraft:sandstone#2", "minecraft:snow_layer", "minecraft:snow", "minecraft:soul_sand", "minecraft:sponge#0", "minecraft:spruce_fence", "minecraft:spruce_fence_gate", "minecraft:leaves#1", "minecraft:sapling#1", "minecraft:log#1", "minecraft:planks#1", "minecraft:wooden_slab#1", "minecraft:spruce_stairs", "minecraft:sticky_piston", "minecraft:stone#0", "minecraft:monster_egg#2", "minecraft:stone_brick_stairs", "minecraft:stonebrick#0", "minecraft:stone_slab#5", "minecraft:monster_egg#0", "minecraft:stone_pressure_plate", "minecraft:stone_slab#0", "minecraft:double_plant#0", "minecraft:tnt", "minecraft:torch", "minecraft:trapped_chest", "minecraft:tripwire_hook", "minecraft:anvil#2", "minecraft:vine", "minecraft:heavy_weighted_pressure_plate", "minecraft:light_weighted_pressure_plate", "minecraft:sponge#1", "minecraft:stained_hardened_clay#0", "minecraft:stained_glass#0", "minecraft:stained_glass_pane#0", "minecraft:red_flower#6", "minecraft:wool#0", "minecraft:wooden_pressure_plate", "minecraft:stone_slab#2", "minecraft:trapdoor", "minecraft:carpet#4", "minecraft:stained_hardened_clay#4", "minecraft:stained_glass#4", "minecraft:stained_glass_pane#4", "minecraft:wool#4", 
	};
	public static final String[] vanillaItems = new String[]{
			"Acacia Door", "Apple", "Armor Stand", "Arrow", "Awkward Potion", "Baked Potato", "Bed", "Birch Door", "Black Banner", "Blaze Powder", "Blaze Rod", "Blue Banner", "Boat", "Bone", "Bone Meal", "Book", "Book and Quill", "Bottle o' Enchanting", "Bow", "Bowl", "Bread", "Brewing Stand", "Brick", "Brown Banner", "Bucket", "Cactus Green", "Cake", "Carrot", "Carrot on a Stick", "Cauldron", "Chain Boots", "Chain Chestplate", "Chain Helmet", "Chain Leggings", "Charcoal", "Clay", "Clock", "Clownfish", "Clownfish", "Coal", "Cocoa Beans", "Compass", "Cooked Chicken", "Cooked Fish", "Cooked Mutton", "Cooked Porkchop", "Cooked Rabbit", "Cooked Salmon", "Cookie", "Creeper Head", "Cyan Banner", "Cyan Dye", "Dandelion Yellow", "Dark Oak Door", "Diamond", "Diamond Axe", "Diamond Boots", "Diamond Chestplate", "Diamond Helmet", "Diamond Hoe", "Diamond Horse Armor", "Diamond Leggings", "Diamond Pickaxe", "Diamond Shovel", "Diamond Sword", "Egg", "Emerald", "Empty Map", "Enchanted Book", "Ender Pearl", "Eye of Ender", "Feather", "Fermented Spider Eye", "Fire Charge", "Firework Rocket", "Firework Star", "Fishing Rod", "Flint", "Flint and Steel", "Flower Pot", "Ghast Tear", "Glass Bottle", "Glistering Melon", "Glowstone Dust", "Gold Horse Armor", "Gold Ingot", "Gold Nugget", "Golden Apple", "Golden Axe", "Golden Boots", "Golden Carrot", "Golden Chestplate", "Golden Helmet", "Golden Hoe", "Golden Leggings", "Golden Pickaxe", "Golden Shovel", "Golden Sword", "Gray Banner", "Gray Dye", "Green Banner", "Gunpowder", "Head", "Ink Sac", "Iron Axe", "Iron Boots", "Iron Chestplate", "Iron Door", "Iron Helmet", "Iron Hoe", "Iron Horse Armor", "Iron Ingot", "Iron Leggings", "Iron Pickaxe", "Iron Shovel", "Iron Sword", "Item Frame", "Jungle Door", "Lapis Lazuli", "Lava Bucket", "Lead", "Leather", "Leather Boots", "Leather Cap", "Leather Pants", "Leather Tunic", "Light Blue Banner", "Light Blue Dye", "Light Gray Banner", "Light Gray Dye", "Lime Banner", "Lime Dye", "Magenta Banner", "Magenta Dye", "Magma Cream", "Map", "Melon", "Melon Seeds", "Milk", "Minecart", "Minecart with Chest", "Minecart with Command Block", "Minecart with Furnace", "Minecart with Hopper", "Minecart with TNT", "Mundane Potion", "Mushroom Stew", "Music Disc 11", "Music Disc 13", "Music Disc Blocks", "Music Disc Cat", "Music Disc Chirp", "Music Disc Far", "Music Disc Mall", "Music Disc Mellohi", "Music Disc Stal", "Music Disc Strad", "Music Disc Wait", "Music Disc Ward", "Name Tag", "Nether Brick", "Nether Quartz", "Nether Star", "Nether Wart", "Oak Door", "Orange Banner", "Orange Dye", "Painting", "Paper", "Pink Banner", "Pink Dye", "Poisonous Potato", "Potato", "Potion of Fire Resistance", "Potion of Harming", "Potion of Healing", "Potion of Invisibility", "Potion of Leaping", "Potion of Night Vision", "Potion of Poison", "Potion of Regeneration", "Potion of Slowness", "Potion of Strength", "Potion of Swiftness", "Potion of Water Breathing", "Potion of Weakness", "Prismarine Crystals", "Prismarine Shard", "Pufferfish", "Pufferfish", "Pumpkin Pie", "Pumpkin Seeds", "Purple Banner", "Purple Dye", "Rabbit Hide", "Rabbit Stew", "Rabbit's Foot", "Raw Beef", "Raw Chicken", "Raw Fish", "Raw Mutton", "Raw Porkchop", "Raw Rabbit", "Raw Salmon", "Red Banner", "Redstone", "Redstone Comparator", "Redstone Repeater", "Rose Red", "Rotten Flesh", "Saddle", "Seeds", "Shears", "Sign", "Skeleton Skull", "Slimeball", "Snowball", "Spawn Bat", "Spawn Blaze", "Spawn Cave Spider", "Spawn Chicken", "Spawn Cow", "Spawn Creeper", "Spawn Enderman", "Spawn Endermite", "Spawn Ghast", "Spawn Guardian", "Spawn Horse", "Spawn Magma Cube", "Spawn Mooshroom", "Spawn Ocelot", "Spawn Pig", "Spawn Rabbit", "Spawn Sheep", "Spawn Silverfish", "Spawn Skeleton", "Spawn Slime", "Spawn Spider", "Spawn Squid", "Spawn Villager", "Spawn Witch", "Spawn Wolf", "Spawn Zombie", "Spawn Zombie Pigman", "Spider Eye", "Splash Potion of Fire Resistance", "Splash Potion of Harming", "Splash Potion of Healing", "Splash Potion of Invisibility", "Splash Potion of Leaping", "Splash Potion of Night Vision", "Splash Potion of Poison", "Splash Potion of Regeneration", "Splash Potion of Slowness", "Splash Potion of Strength", "Splash Potion of Swiftness", "Splash Potion of Water Breathing", "Splash Potion of Weakness", "Spruce Door", "Steak", "Stick", "Stone Axe", "Stone Hoe", "Stone Pickaxe", "Stone Shovel", "Stone Sword", "String", "Sugar", "Sugar Canes", "Thick Potion", "Water Bottle", "Water Bucket", "Wheat", "White Banner", "Wither Skeleton Skull", "Wooden Axe", "Wooden Hoe", "Wooden Pickaxe", "Wooden Shovel", "Wooden Sword", "Written Book", "Yellow Banner", "Zombie Head", 
	};
	public static final String[] vanillaItemIds = new String[]{
			"minecraft:acacia_door", "minecraft:apple", "minecraft:armor_stand", "minecraft:arrow", "minecraft:potionitem#16", "minecraft:baked_potato", "minecraft:bed", "minecraft:birch_door", "minecraft:banner#0", "minecraft:blaze_powder", "minecraft:blaze_rod", "minecraft:banner#4", "minecraft:boat", "minecraft:bone", "minecraft:dye#15", "minecraft:book", "minecraft:writable_book", "minecraft:experience_bottle", "minecraft:bow", "minecraft:bowl", "minecraft:bread", "minecraft:brewing_stand", "minecraft:brick", "minecraft:banner#3", "minecraft:bucket", "minecraft:dye#2", "minecraft:cake", "minecraft:carrot", "minecraft:carrot_on_a_stick", "minecraft:cauldron", "minecraft:chainmail_boots", "minecraft:chainmail_chestplate", "minecraft:chainmail_helmet", "minecraft:chainmail_leggings", "minecraft:coal#1", "minecraft:clay_ball", "minecraft:clock", "minecraft:fish#2", "minecraft:cooked_fish#2", "minecraft:coal#0", "minecraft:dye#3", "minecraft:compass", "minecraft:cooked_chicken", "minecraft:cooked_fish#0", "minecraft:cooked_mutton", "minecraft:cooked_porkchop", "minecraft:cooked_rabbit", "minecraft:cooked_fish#1", "minecraft:cookie", "minecraft:skull#4", "minecraft:banner#6", "minecraft:dye#6", "minecraft:dye#11", "minecraft:dark_oak_door", "minecraft:diamond", "minecraft:diamond_axe", "minecraft:diamond_boots", "minecraft:diamond_chestplate", "minecraft:diamond_helmet", "minecraft:diamond_hoe", "minecraft:diamond_horse_armor", "minecraft:diamond_leggings", "minecraft:diamond_pickaxe", "minecraft:diamond_shovel", "minecraft:diamond_sword", "minecraft:egg", "minecraft:emerald", "minecraft:map", "minecraft:enchanted_book", "minecraft:ender_pearl", "minecraft:ender_eye", "minecraft:feather", "minecraft:fermented_spider_eye", "minecraft:fire_charge", "minecraft:fireworks", "minecraft:firework_charge", "minecraft:fishing_rod", "minecraft:flint", "minecraft:flint_and_steel", "minecraft:flower_pot", "minecraft:ghast_tear", "minecraft:glass_bottle", "minecraft:speckled_melon", "minecraft:glowstone_dust", "minecraft:golden_horse_armor", "minecraft:gold_ingot", "minecraft:gold_nugget", "minecraft:golden_apple", "minecraft:golden_axe", "minecraft:golden_boots", "minecraft:golden_carrot", "minecraft:golden_chestplate", "minecraft:golden_helmet", "minecraft:golden_hoe", "minecraft:golden_leggings", "minecraft:golden_pickaxe", "minecraft:golden_shovel", "minecraft:golden_sword", "minecraft:banner#8", "minecraft:dye#8", "minecraft:banner#2", "minecraft:gunpowder", "minecraft:skull#3", "minecraft:dye#0", "minecraft:iron_axe", "minecraft:iron_boots", "minecraft:iron_chestplate", "minecraft:iron_door", "minecraft:iron_helmet", "minecraft:iron_hoe", "minecraft:iron_horse_armor", "minecraft:iron_ingot", "minecraft:iron_leggings", "minecraft:iron_pickaxe", "minecraft:iron_shovel", "minecraft:iron_sword", "minecraft:item_frame", "minecraft:jungle_door", "minecraft:dye#4", "minecraft:lava_bucket", "minecraft:lead", "minecraft:leather", "minecraft:leather_boots", "minecraft:leather_helmet", "minecraft:leather_leggings", "minecraft:leather_chestplate", "minecraft:banner#12", "minecraft:dye#12", "minecraft:banner#7", "minecraft:dye#7", "minecraft:banner#10", "minecraft:dye#10", "minecraft:banner#13", "minecraft:dye#13", "minecraft:magma_cream", "minecraft:filled_map", "minecraft:melon", "minecraft:melon_seeds", "minecraft:milk_bucket", "minecraft:minecart", "minecraft:chest_minecart", "minecraft:command_block_minecart", "minecraft:furnace_minecart", "minecraft:hopper_minecart", "minecraft:tnt_minecart", "minecraft:potionitem#64", "minecraft:mushroom_stew", "minecraft:record_11", "minecraft:record_13", "minecraft:record_blocks", "minecraft:record_cat", "minecraft:record_chirp", "minecraft:record_far", "minecraft:record_mall", "minecraft:record_mellohi", "minecraft:record_stal", "minecraft:record_strad", "minecraft:record_wait", "minecraft:record_ward", "minecraft:name_tag", "minecraft:netherbrick", "minecraft:quartz", "minecraft:nether_star", "minecraft:nether_wart", "minecraft:oak_door", "minecraft:banner#14", "minecraft:dye#14", "minecraft:painting", "minecraft:paper", "minecraft:banner#9", "minecraft:dye#9", "minecraft:poisonous_potato", "minecraft:potato", "minecraft:potionitem#3", "minecraft:potionitem#12", "minecraft:potionitem#5", "minecraft:potionitem#14", "minecraft:potionitem#11", "minecraft:potionitem#6", "minecraft:potionitem#4", "minecraft:potionitem#1", "minecraft:potionitem#10", "minecraft:potionitem#9", "minecraft:potionitem#2", "minecraft:potionitem#13", "minecraft:potionitem#8", "minecraft:prismarine_crystals", "minecraft:prismarine_shard", "minecraft:fish#3", "minecraft:cooked_fish#3", "minecraft:pumpkin_pie", "minecraft:pumpkin_seeds", "minecraft:banner#5", "minecraft:dye#5", "minecraft:rabbit_hide", "minecraft:rabbit_stew", "minecraft:rabbit_foot", "minecraft:beef", "minecraft:chicken", "minecraft:fish#0", "minecraft:mutton", "minecraft:porkchop", "minecraft:rabbit", "minecraft:fish#1", "minecraft:banner#1", "minecraft:redstone", "minecraft:comparator", "minecraft:repeater", "minecraft:dye#1", "minecraft:rotten_flesh", "minecraft:saddle", "minecraft:wheat_seeds", "minecraft:shears", "minecraft:sign", "minecraft:skull#0", "minecraft:slime_ball", "minecraft:snowball", "minecraft:spawn_egg#65", "minecraft:spawn_egg#61", "minecraft:spawn_egg#59", "minecraft:spawn_egg#93", "minecraft:spawn_egg#92", "minecraft:spawn_egg#50", "minecraft:spawn_egg#58", "minecraft:spawn_egg#67", "minecraft:spawn_egg#56", "minecraft:spawn_egg#68", "minecraft:spawn_egg#100", "minecraft:spawn_egg#62", "minecraft:spawn_egg#96", "minecraft:spawn_egg#98", "minecraft:spawn_egg#90", "minecraft:spawn_egg#101", "minecraft:spawn_egg#91", "minecraft:spawn_egg#60", "minecraft:spawn_egg#51", "minecraft:spawn_egg#55", "minecraft:spawn_egg#52", "minecraft:spawn_egg#94", "minecraft:spawn_egg#120", "minecraft:spawn_egg#66", "minecraft:spawn_egg#95", "minecraft:spawn_egg#54", "minecraft:spawn_egg#57", "minecraft:spider_eye", "minecraft:potionitem#16387", "minecraft:potionitem#16396", "minecraft:potionitem#16389", "minecraft:potionitem#16398", "minecraft:potionitem#16395", "minecraft:potionitem#16390", "minecraft:potionitem#16388", "minecraft:potionitem#16385", "minecraft:potionitem#16394", "minecraft:potionitem#16393", "minecraft:potionitem#16386", "minecraft:potionitem#16397", "minecraft:potionitem#16392", "minecraft:spruce_door", "minecraft:cooked_beef", "minecraft:stick", "minecraft:stone_axe", "minecraft:stone_hoe", "minecraft:stone_pickaxe", "minecraft:stone_shovel", "minecraft:stone_sword", "minecraft:string", "minecraft:sugar", "minecraft:reeds", "minecraft:potionitem#32", "minecraft:potionitem#0", "minecraft:water_bucket", "minecraft:wheat", "minecraft:banner#15", "minecraft:skull#1", "minecraft:wooden_axe", "minecraft:wooden_hoe", "minecraft:wooden_pickaxe", "minecraft:wooden_shovel", "minecraft:wooden_sword", "minecraft:written_book", "minecraft:banner#11", "minecraft:skull#2", 
	};
	
	private static String[] vanillaBlocksMetaless;
	private static String[] vanillaItemsMetaless;
	
	public static String[] getVanillaBlocksMetaless(){
		if (vanillaBlocksMetaless == null) createMetalessBlocks();
		return vanillaBlocksMetaless;
	}
	
	public static String[] getVanillaItemsMetaless(){
		if (vanillaItemsMetaless == null) createMetalessItems();
		return vanillaItemsMetaless;
	}
	
	private static void createMetalessBlocks(){
		vanillaBlocksMetaless = new String[0];
		for (int i = 0; i < vanillaBlockIds.length; i++){
			if (vanillaBlockIds[i].split("#").length == 1 || vanillaBlockIds[i].endsWith("#0")){
				vanillaBlocksMetaless = extendArray(vanillaBlocksMetaless, vanillaBlocks[i]);
			}
		}
	}
	
	private static void createMetalessItems(){
		vanillaItemsMetaless = new String[0];
		for (int i = 0; i < vanillaItemIds.length; i++){
			if (vanillaItemIds[i].split("#").length == 1 || vanillaItemIds[i].endsWith("#0")){
				vanillaItemsMetaless = extendArray(vanillaItemsMetaless, vanillaItems[i]);
			}
		}
	}
	
	private static String[] extendArray(String[] oldArray, String newItem){
		String[] newArray = new String[oldArray.length + 1];
		for (int i = 0; i < oldArray.length; i++)
			newArray[i] = oldArray[i];
		newArray[oldArray.length] = newItem;
		return newArray;
	}
	
	public static String getId(String material){
		for (int i = 0; i < vanillaBlocks.length; i++)
			if (vanillaBlocks[i].equals(material))
				return vanillaBlockIds[i];
		for (int i = 0; i < vanillaItems.length; i++)
			if (vanillaItems[i].equals(material))
				return vanillaItemIds[i];
		return null;
	}
	
	public static String getDisplayName(ItemStackResource item){
		if (item.amount != null && item.amount > 1)
			return item.amount + " " + getMaterialName(item);
		else 
			return getMaterialName(item);
	}
	
	public static String getMaterialName(ItemStackResource item){
		if (item.block != null && !item.block.isEmpty()){
			if (item.meta == null) return item.block;
			else return item.block + "#" + item.meta;
		}
		else if (item.item != null && !item.item.isEmpty()){
			if (item.meta == null) return item.item;
			else return item.item + "#" + item.meta;
		}
		return "";
	}
	
	public static String simplifyItemStackName(String material){
		material = material.replace("modbuilder:", "");
		String prefix = "";
		if (!material.startsWith("minecraft:") && material.contains("minecraft:")){
			prefix = material.split(" ")[0] + " ";
			material = material.split(" ")[1];
		}
		for (int i = 0; i < vanillaBlockIds.length; i++)
			if (vanillaBlockIds[i].equals(material) || vanillaBlockIds[i].equals(material + "#0"))
				return prefix + vanillaBlocks[i];
		for (int i = 0; i < vanillaItemIds.length; i++)
			if (vanillaItemIds[i].equals(material) || vanillaItemIds[i].equals(material + "#0"))
				return prefix + vanillaItems[i];
		return prefix + material.replaceAll("_", " ");
	}
	
	public static ImageIcon getImage(ItemStackResource item){
		if (item == null)
			return null;
		else if (item.block != null && !item.block.isEmpty())
			return getImage(item.block);
		else if (item.item != null && !item.item.isEmpty())
			return getImage(item.item);
		else
			return new ImageIcon("src/main/resources/icon.png");
	}
	
	public static ImageIcon getImage(String material){
		if (material.startsWith("modbuilder:")){
			material = material.substring(11);
			try{
				if (Editor.getItemList().containsKey(material.replaceAll("_", " "))){
					return ItemElement.getFromName(material).getImage();
				}
				else{
					return BlockElement.getFromName(material).getImage();
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		else if (material.startsWith("minecraft:")){
			return new ImageIcon("src/main/resources/icon.png");
		}
		return new ImageIcon("src/main/resources/icon.png");
	}
	
	public static boolean isItem(String material){
		for (int i = 0; i < vanillaItemIds.length; i++)
			if (vanillaItemIds[i].equals(material) || vanillaItemIds[i].equals(material + "#0"))
				return true;
		if (material.startsWith("modbuilder:"))
			return Editor.getItemList().containsKey(material.substring(11).replaceAll("_", " "));
		return false;
	}
}
