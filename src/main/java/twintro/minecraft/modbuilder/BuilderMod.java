package twintro.minecraft.modbuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import twintro.minecraft.modbuilder.data.BuilderStruct;
import twintro.minecraft.modbuilder.data.BuilderStructRegistry;
import twintro.minecraft.modbuilder.data.FuelHandler;
import twintro.minecraft.modbuilder.data.MetadataSection;
import twintro.minecraft.modbuilder.data.MetadataSerializer;
import twintro.minecraft.modbuilder.data.RecipeRegistry;
import twintro.minecraft.modbuilder.data.ResourceConverter;
import twintro.minecraft.modbuilder.data.resources.ResourceDeserializer;
import twintro.minecraft.modbuilder.data.resources.blocks.BaseBlockResource;
import twintro.minecraft.modbuilder.data.resources.items.BaseItemResource;
import twintro.minecraft.modbuilder.data.resources.meta.ModbuilderResource;
import twintro.minecraft.modbuilder.data.resources.recipes.BaseRecipe;
import twintro.minecraft.modbuilder.data.resources.structures.BaseStructureResource;

@Mod(modid = BuilderMod.MODID, version = BuilderMod.VERSION)
public class BuilderMod {
	public static final String MODID = "modbuilder";
	public static final String VERSION = "0.1";

	private static Configuration config;

	public static Configuration getConfig() {
		return config;
	}
	
	/**
	 * Contains a name-block reference for every {@link Block} that will be registered.
	 */
	public static Map<String,Block> customBlocks = new HashMap<String,Block>();
	/**
	 * Contains a name-block reference for every {@link Item} that will be registered.
	 */
	public static Map<String,Item> customItems = new HashMap<String,Item>();
	/**
	 * Contains a model location for each registered {@link Item}.
	 */
	private Map<Item, String> itemModels = new HashMap<Item, String>();

	/**
	 * Contains a model location for each registered {@link Block}.
	 */
	private Map<Block, String> blockModels = new HashMap<Block, String>();

	/**
	 * Contains the name of each registered {@link Item}.
	 */
	private Set<String> registeredItems = new HashSet<String>();
	
	/**
	 * Contains the name of each registered {@link Block}.
	 */
	private Set<String> registeredBlocks = new HashSet<String>();
	
	/**
	 * Contains the name of each registered {@link Structure}.
	 */
	private Set<String> registeredStructures = new HashSet<String>();
	/**
	 * Contains all structures that will be generated.
	 */
	BuilderStructRegistry registry = new BuilderStructRegistry();
	
	/**
	 * Contains all items that need to be registered as a fuel {@link ItemStack}.
	 */
	private Map<Item, Integer> fuels = new HashMap<Item, Integer>();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		importResources(Minecraft.getMinecraft().getResourceManager());

		config = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(this);

		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		for (Entry<Item, String> entry : itemModels.entrySet())
			mesher.register(entry.getKey(), 0,
					new ModelResourceLocation(entry.getValue(), "inventory"));
		for (Entry<Block, String> entry : blockModels.entrySet())
			mesher.register(Item.getItemFromBlock(entry.getKey()), 0,
					new ModelResourceLocation(entry.getValue(), "inventory"));
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(BuilderMod.MODID))
			syncConfig();
	}

	/**
	 * Imports resources from resource packs that contain the "modbuilder"
	 * metadata.
	 * 
	 * @param manager
	 *            - the resource manager to use
	 */
	private void importResources(IResourceManager manager) {
		List entries = Minecraft.getMinecraft().getResourcePackRepository().getRepositoryEntries();
		for (Object entry : entries) {
			try {
				MetadataSection data = (MetadataSection) ((ResourcePackRepository.Entry)entry).getResourcePack()
						.getPackMetadata(new MetadataSerializer(), "modbuilder");
				if (data.modbuilder != null)
					importResources(manager, data.modbuilder);
			} catch (IOException e) {
				// ignore 
			}
		}
		GameRegistry.registerWorldGenerator(registry, 0);
		IFuelHandler f = new FuelHandler(fuels);
		GameRegistry.registerFuelHandler(f);
	}

	/**
	 * Imports resources using the metadata from a resource pack.
	 * 
	 * @param manager
	 *            - the resource manager to use
	 * @param data
	 *            - the metadata that contains the resource names
	 */
	private void importResources(IResourceManager manager, ModbuilderResource data) {
		ResourceDeserializer deserializer = new ResourceDeserializer();
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(BaseItemResource.class, deserializer);
		builder.registerTypeAdapter(BaseBlockResource.class, deserializer);
		builder.registerTypeAdapter(BaseStructureResource.class, deserializer);
		builder.registerTypeAdapter(BaseRecipe.class, deserializer);
		Gson gson = builder.create();
		
		Map<String,BaseBlockResource> blockResources = new HashMap<String,BaseBlockResource>();
		Map<String,BaseItemResource> itemResources = new HashMap<String,BaseItemResource>();
		for (String path : data.blocks) {
			try {
				ResourceLocation location = new ResourceLocation(BuilderMod.MODID + ":blocks/" + path + ".json");
				IResource resource = manager.getResource(location);
				BaseBlockResource blockResource = gson.fromJson(new InputStreamReader(resource.getInputStream()),
						BaseBlockResource.class);
				Block block = ResourceConverter.toEmptyBlock(blockResource);
				block.setUnlocalizedName(BuilderMod.MODID+"_"+path);
				if (!registeredBlocks.contains(path)) {
					registeredBlocks.add(path);
					blockModels.put(block, blockResource.model);
					customBlocks.put(path, block);
					blockResources.put(path, blockResource);
				}
			} catch (IOException e) {
				// ignore
			}
		}
		
		for (String path : data.items) {
			try {
				ResourceLocation location = new ResourceLocation(BuilderMod.MODID + ":items/" + path + ".json");
				IResource resource = manager.getResource(location);
				BaseItemResource itemResource = gson.fromJson(new InputStreamReader(resource.getInputStream()),
						BaseItemResource.class);
				Item item = ResourceConverter.toEmptyItem(itemResource);
				item.setUnlocalizedName(BuilderMod.MODID+"_"+path);
				if (!registeredItems.contains(path)) {
					registeredItems.add(path);
					itemModels.put(item, itemResource.model);
					customItems.put(path, item);
					itemResources.put(path, itemResource);
				}
			} catch (IOException e) {
				// ignore
			}
		}
		
		for (String path : blockResources.keySet()) {
			BaseBlockResource resource = blockResources.get(path);
			Block block = ResourceConverter.toBlock(customBlocks.get(path), resource);
			GameRegistry.registerBlock(block, path);
			if (resource.burntime != null)
				fuels.put(Item.getItemFromBlock(block), resource.burntime);
		}
		
		for (String path : itemResources.keySet()) {
			BaseItemResource resource = itemResources.get(path);
			Item item = ResourceConverter.toItem(customItems.get(path), resource);
			GameRegistry.registerItem(item, path);
			if (resource.burntime != null)
				fuels.put(item, resource.burntime);
		}
		
		for (String path : data.structures) {
			try {
				ResourceLocation location = new ResourceLocation(BuilderMod.MODID + ":structures/" + path + ".json");
				IResource resource = manager.getResource(location);
				BaseStructureResource structureResource = gson.fromJson(new InputStreamReader(resource.getInputStream()),
						BaseStructureResource.class);
				BuilderStruct structure = ResourceConverter.toStructure(structureResource);
				if (!registeredStructures.contains(path)) {
					registeredStructures.add(path);
					registry.structs.add(structure);
				}
			} catch (IOException e) {
				// ignore
			}
		}
		
		for (String path : data.recipes) {
			try {
				ResourceLocation location = new ResourceLocation(BuilderMod.MODID + ":recipes/" + path + ".json");
				String s = location.getResourcePath();
				IResource resource = manager.getResource(location);
				BaseRecipe recipe = gson.fromJson(new InputStreamReader(resource.getInputStream()), BaseRecipe.class);
				RecipeRegistry.register(recipe);
			} catch (IOException e) {
				// ignore
			}
		}
	}

	private void syncConfig() {
		if (config.hasChanged())
			config.save();
	}
}
