package twintro.minecraft.modbuilder.data.resources.structures;

import java.util.Set;

import net.minecraft.block.Block;

/**
 * The ground cover structure type.
 */
public class GroundStructureResource extends BaseStructureResource {
	/**
	 * The block that will be generated.
	 */
	public String block;
	/**
	 * The blocks that the ground cover needs to stand on.
	 * If this is null or empty, the ground cover will be generated on any block.
	 */
	public Set<String> onlyonblocks;
	/**
	 * The dimension the ground cover will be generated in. Use -1 for nether, 0 for overworld and 1 for end.
	 */
	public Integer dimension;
	/**
	 * The amount of blocks the generator will try to generate in one chunk. A try may fail if it is not on a correct block or if the ground is already occupied (by tall grass for example).
	 */
	public Integer amountperchunk;
	
	public GroundStructureResource() {
		this.type = StructureType.ground;
	}
}
