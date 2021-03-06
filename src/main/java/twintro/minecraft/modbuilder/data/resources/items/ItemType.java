package twintro.minecraft.modbuilder.data.resources.items;

/**
 * The enum for all item types.
 */
public enum ItemType {
	regular(ItemResource.class), food(FoodItemResource.class), tool(ToolItemResource.class);

	private Class<? extends BaseItemResource> value;

	private ItemType(Class<? extends BaseItemResource> value) {
		this.value = value;
	}

	public Class<? extends BaseItemResource> getValue() {
		return (value);
	}
}
