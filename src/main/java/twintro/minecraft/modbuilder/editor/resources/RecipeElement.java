package twintro.minecraft.modbuilder.editor.resources;

import java.io.File;
import java.io.FileReader;

import javax.swing.ImageIcon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import twintro.minecraft.modbuilder.data.resources.ResourceDeserializer;
import twintro.minecraft.modbuilder.data.resources.recipes.BaseRecipe;
import twintro.minecraft.modbuilder.editor.generator.ResourcePackIO;

public class RecipeElement extends Element {
	public BaseRecipe recipe;
	
	public static RecipeElement getFromName(String name) throws Exception {
		RecipeElement output = null;
		
		File itemFile = new File(ResourcePackIO.getURL(
				"assets/modbuilder/recipes/" + name + ".json"));
		
		if (itemFile.exists()){
			ResourceDeserializer deserializer = new ResourceDeserializer();
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(BaseRecipe.class, deserializer);
			Gson gson = builder.create();
			
			BaseRecipe recipe = gson.fromJson(new FileReader(itemFile), BaseRecipe.class);
			
			output = new RecipeElement();
			output.name = name;
			output.recipe = recipe;
		}
		return output;
	}
	
	@Override
	public ImageIcon getImage() {
		if (recipe.output.item == null) {
			String outputName = recipe.output.block;
			if (outputName.startsWith("modbuilder:")){
				outputName = outputName.substring(11);
				try{
					return BlockElement.getFromName(outputName).getImage();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		else{
			String outputName = recipe.output.item;
			if (outputName.startsWith("modbuilder:")){
				outputName = outputName.substring(11);
				try{
					return ItemElement.getFromName(outputName).getImage();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		return new ImageIcon("src/main/resources/icon.png");
	}
}
