package teamport.crafting2.core.block;

import net.minecraft.core.block.BlockTileEntityRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import sunsetsatellite.catalyst.Catalyst;
import teamport.crafting2.core.block.entity.TileWorkbenchII;

public class BlockWorkbenchII extends BlockTileEntityRotatable {
	public BlockWorkbenchII(String key, int id) {
		super(key, id, Material.wood);
	}

	@Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, EntityPlayer player, Side side, double xHit, double yHit) {
		super.onBlockRightClicked(world, x, y, z, player, side, xHit, yHit);
		TileWorkbenchII tileEntity = (TileWorkbenchII) world.getBlockTileEntity(x, y, z);

		if (tileEntity == null) return false;

		Catalyst.displayGui(player, tileEntity, tileEntity.getInvName());
		return true;
	}

	@Override
	protected TileEntity getNewBlockEntity() {
		return new TileWorkbenchII();
	}
}
