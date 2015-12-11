package twintro.minecraft.modbuilder.data.resources.recipes;

public class SmeltingRecipe extends BaseRecipe {
	public ItemStackResource input;
	public ItemStackResource output;
	public float xp;

	public SmeltingRecipe() {
		type = RecipeType.smelting;
	}
}