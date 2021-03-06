package twintro.minecraft.modbuilder.data.resources.recipes;

/**
 * The enum for all recipe types.
 */
public enum RecipeType {
	shaped(ShapedRecipe.class), shapeless(ShapelessRecipe.class), smelting(SmeltingRecipe.class);

	private Class<? extends BaseRecipe> value;

	private RecipeType(Class<? extends BaseRecipe> value) {
		this.value = value;
	}

	public Class<? extends BaseRecipe> getValue() {
		return (value);
	}
}
