package twintro.minecraft.modbuilder.data;

import java.util.Set;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemTool;

/**
 * Base class for new {@link ItemTool}s.
 */
public class BuilderItemTool extends ItemTool {
	CreativeTabs[] tabs;

	/**
	 * 
	 * @param attackDamage
	 * 		-The damage dealt to any entity the player hits when holding this tool.
	 * @param material
	 * 		-The tool material this tool is made of. This contains properties such as mining speed and durability.
	 * @param effectiveBlocks
	 * 		-Set of all Block objects this tool is effective on.
	 */
	public BuilderItemTool(float attackDamage, ToolMaterial material, Set effectiveBlocks, CreativeTabs[] tabs) {
		super(attackDamage, material, effectiveBlocks);
		this.tabs = tabs;
	}
	
	@Override
	public CreativeTabs[] getCreativeTabs() {
		return tabs != null ? tabs : super.getCreativeTabs();
	}
}
