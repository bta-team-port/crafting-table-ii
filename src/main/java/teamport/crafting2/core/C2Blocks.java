package teamport.crafting2.core;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import teamport.crafting2.core.block.BlockWorkbenchII;
import teamport.crafting2.extra.C2Config;
import turniplabs.halplibe.helper.BlockBuilder;

import static teamport.crafting2.CraftingTableII.MOD_ID;

public class C2Blocks {
	private static int startingID = C2Config.cfg.getInt("IDs.startingBlockID");
	private static int nextID() {
		return ++startingID;
	}

	public static Block WORKBENCH_II;

	public static void registerBlocks() {
		WORKBENCH_II = new BlockBuilder(MOD_ID)
			.setHardness(2.5F)
			.setBlockSound(BlockSounds.WOOD)
			.setTags(BlockTags.MINEABLE_BY_AXE)
			.build(new BlockWorkbenchII("workbench_ii", nextID()));
	}
}
