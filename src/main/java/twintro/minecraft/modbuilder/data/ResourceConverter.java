package twintro.minecraft.modbuilder.data;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import example.main.ModInformation;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import twintro.minecraft.modbuilder.data.resources.TabResource;
import twintro.minecraft.modbuilder.data.resources.blocks.BaseBlockResource;
import twintro.minecraft.modbuilder.data.resources.blocks.BlockResource;
import twintro.minecraft.modbuilder.data.resources.items.BaseItemResource;
import twintro.minecraft.modbuilder.data.resources.items.FoodItemResource;
import twintro.minecraft.modbuilder.data.resources.items.ItemResource;
import twintro.minecraft.modbuilder.data.resources.items.ToolItemResource;
import twintro.minecraft.modbuilder.data.resources.recipes.ItemStackResource;
import twintro.minecraft.modbuilder.data.resources.structures.BaseStructureResource;
import twintro.minecraft.modbuilder.data.resources.structures.GroundStructureResource;
import twintro.minecraft.modbuilder.data.resources.structures.OreStructureResource;

/**
 * Contains methods for converting resource objects to Minecraft objects.
 */
public class ResourceConverter {
	public static Map<String,Block> blocks = new HashMap<String,Block>();
	public static Map<String,Item> items = new HashMap<String,Item>();
	
	public static Block getBlockFromName(String block){
		if (block.startsWith("minecraft:")) return Block.getBlockFromName(block);
		if (block.startsWith("modbuilder:") && blocks.containsKey(block)) return blocks.get(block);
		return null;
	}
	
	public static Item getItemFromName(String item){
		if (item.startsWith("minecraft:")) return Item.getByNameOrId(item);
		if (item.startsWith("modbuilder:") && items.containsKey(item)) return items.get(item);
		if (item.startsWith("modbuilder:") && blocks.containsKey(item)) return Item.getItemFromBlock(blocks.get(item));
		return null;
	}
	
	private static IBlockState toBlockState(String block){
		if (block.contains("#")) return getBlockFromName(block.split("#")[0]).getStateFromMeta(Integer.parseInt(block.split("#")[1]));
		else return getBlockFromName(block).getDefaultState();
	}
	
	public static Block toBlock(BaseBlockResource resource) {
		if (resource instanceof BlockResource)
			return toBlock((BlockResource) resource);
		return null;
	}

	public static BuilderBlock toBlock(BlockResource resource) {
		BuilderBlock block = new BuilderBlock(new BuilderBlockMaterial(resource), resource.drops,
				resource.solid!=null ? resource.solid : true,
				resource.opaque!=null ? resource.opaque : false,
				resource.cutout!=null ? resource.cutout : false);
		if (resource.flammability != null || resource.firespreadspeed != null)
			Blocks.fire.setFireInfo(block,
						resource.flammability != null ? resource.flammability : 0,
						resource.firespreadspeed != null ? resource.firespreadspeed : 0);
		if (resource.tab != null) 
			block.setCreativeTab(ResourceHelper.tabs.get(resource.tab));
		if (resource.light != null)
			block.setLightLevel(((float)resource.light)/15);
		if (resource.opacity != null)
			block.setLightOpacity(resource.opacity);
		if (resource.slipperiness != null)
			block.slipperiness = resource.slipperiness;
		if (resource.hardness != null)
			block.setHardness(resource.hardness);
		else
			block.setHardness(1);
		if (resource.resistance != null)
			block.setResistance(resource.resistance);
		if (resource.unbreakable != null)
			if (resource.unbreakable)
				block.setBlockUnbreakable();
		if (resource.harvesttype != null && resource.harvestlevel != null)
			block.setHarvestLevel(resource.harvesttype, resource.harvestlevel);
		return block;
	}

	public static Item toItem(BaseItemResource resource) {
		if (resource instanceof ItemResource)
			return toItem((ItemResource) resource);
		else if (resource instanceof ToolItemResource)
			return toItem((ToolItemResource) resource);
		else if (resource instanceof FoodItemResource)
			return toItem((FoodItemResource) resource);

		return null;
	}

	public static Item toItem(ItemResource resource) {
		BuilderItem item = new BuilderItem(resource.tabs != null ? getTabs(resource.tabs) : null);
		if (resource.stacksize != null)
			item.setMaxStackSize(resource.stacksize);
		if (resource.container != null)
			item.setContainerItem(Item.getByNameOrId(resource.container));
		return item;
	}

	public static ItemFood toItem(final FoodItemResource resource) {
		BuilderItemFood item;
		if (resource.saturation == null)
			item = new BuilderItemFood(
					resource.amount,
					resource.wolf != null ? resource.wolf : false,
					resource.effects,
					resource.tabs != null ? getTabs(resource.tabs) : null,
					resource.container != null ? new ItemStack(Item.getByNameOrId(resource.container)) : null);
		else
			item = new BuilderItemFood(
					resource.amount, resource.saturation,
					resource.wolf != null ? resource.wolf : false,
					resource.effects,
					resource.tabs != null ? getTabs(resource.tabs) : null,
					resource.container != null ? new ItemStack(Item.getByNameOrId(resource.container)) : null);
		if (resource.stacksize != null)
			item.setMaxStackSize(resource.stacksize);
		if (resource.container != null)
			item.setContainerItem(Item.getByNameOrId(resource.container));
		if (resource.alwaysedible != null)
			if (resource.alwaysedible)
				item.setAlwaysEdible();
		return item;
	}

	public static ItemTool toItem(ToolItemResource resource) {
		ToolMaterial material = EnumHelper.addToolMaterial("",
				resource.harvestlevel   != null ? resource.harvestlevel   : 2,
				resource.durability     != null ? resource.durability     : 250,
				resource.efficiency     != null ? resource.efficiency     : 6.0F,
				resource.damage         != null ? resource.damage         : 0.5F,
				resource.enchantability != null ? resource.enchantability : 10);
		if (resource.repairitem != null) {
			ItemStack repair;
			if (resource.repairitem.contains("#")) repair=new ItemStack(Item.getByNameOrId(resource.repairitem.split("#")[0]),Integer.parseInt(resource.repairitem.split("#")[1]));
			else repair=new ItemStack(Item.getByNameOrId(resource.repairitem.split("#")[0]));
			material.setRepairItem(repair);
		}
		else if (resource.repairblock != null) {
			ItemStack repair;
			if (resource.repairblock.contains("#")) repair=new ItemStack(getBlockFromName(resource.repairblock.split("#")[0]),Integer.parseInt(resource.repairblock.split("#")[1]));
			else repair=new ItemStack(getBlockFromName(resource.repairblock.split("#")[0]));
			material.setRepairItem(repair);
		}
		Set effectiveBlocks = new LinkedHashSet();
		if (resource.blocks != null) {
			for(String block:resource.blocks)
				effectiveBlocks.add(getBlockFromName(block));
		}
		BuilderItemTool item = new BuilderItemTool(material.getDamageVsEntity(), material, effectiveBlocks, resource.tabs != null ? getTabs(resource.tabs) : null);
		if (resource.stacksize != null)
			item.setMaxStackSize(resource.stacksize);
		if (resource.container != null)
			item.setContainerItem(Item.getByNameOrId(resource.container));
		return item;
	}
	
	public static BuilderStruct toStructure(BaseStructureResource resource) {
		if (resource instanceof OreStructureResource)
			return toStructure((OreStructureResource) resource);
		else if (resource instanceof GroundStructureResource)
			return toStructure((GroundStructureResource) resource);
		return null;
	}
	
	public static BuilderStructOre toStructure(OreStructureResource resource) {
		BuilderStructOre structure = new BuilderStructOre(
				toBlockState(resource.block),
				resource.replaceblock   !=null ? toBlockState(resource.replaceblock) : null,
				resource.dimension      !=null ? resource.dimension      : 0,
				resource.maxveinsize    !=null ? resource.maxveinsize    : 8,
				resource.chancestospawn !=null ? resource.chancestospawn : 16,
				resource.minY           !=null ? resource.minY           : 1,
				resource.maxY           !=null ? resource.maxY           : 64);
		return structure;
	}
	
	public static BuilderStructGround toStructure(GroundStructureResource resource) {
		Set onlyonblocks = new LinkedHashSet();
		if (resource.onlyonblocks != null && resource.onlyonblocks.size()>0) {
			for (String key : resource.onlyonblocks)
				onlyonblocks.add(toBlockState(key));
		}
		BuilderStructGround structure = new BuilderStructGround(
				toBlockState(resource.block), onlyonblocks,
				resource.dimension      !=null ? resource.dimension      : 0,
				resource.amountperchunk !=null ? resource.amountperchunk : 32);
		return structure;
	}

	public static ItemStack toItemStack(ItemStackResource resource) {
		Item item = null;
		if (resource.item != null) {
			item = Item.getByNameOrId(resource.item);
			if (resource.container != null)
				item.setContainerItem(resource.container == "" ? null : Item.getByNameOrId(resource.container));
		}
		Block block = null;
		if (resource.block != null)
			block = getBlockFromName(resource.block);

		ItemStack stack = item != null ?
				new ItemStack(item, resource.amount != null ? resource.amount: 1) :
				new ItemStack(block, resource.amount != null ? resource.amount: 1);
		if (resource.meta != null)
			stack.setItemDamage(resource.meta);
		for (Entry<String, Integer> enchant : resource.enchantments.entrySet())
			stack.addEnchantment(Enchantment.getEnchantmentByLocation(enchant.getKey()), enchant.getValue());
		return stack;
	}

	private static CreativeTabs[] getTabs(Set<TabResource> keys) {
		CreativeTabs[] tabs = new CreativeTabs[keys.size()];
		int i = 0;
		for (TabResource key : keys)
			tabs[i++] = ResourceHelper.tabs.get(key);
		return tabs;
	}
}
