package teamport.crafting2.client.gui;

import net.minecraft.core.InventoryAction;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeRegistry;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.*;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotCrafting;
import net.minecraft.core.world.World;
import teamport.crafting2.core.block.entity.TileWorkbenchII;

import java.util.List;

public class ContainerWorkbenchII extends Container {
	private final EntityPlayer player;
	private final InventoryCrafting craftMatrix;
	TileWorkbenchII tileEntity;

	@Override
	public void onCraftMatrixChanged(IInventory iinventory) {
		for (int i = 0; i < tileEntity.getSizeInventory(); i++) {
			if (i == 0) {
				tileEntity.setInventorySlotContents(i, Registries.RECIPES.findMatchingRecipe(this.craftMatrix));
			}
		}
	}

	public ContainerWorkbenchII(InventoryPlayer inventory, TileWorkbenchII tileEntity) {
		this.tileEntity = tileEntity;
		this.player = inventory.player;
		craftMatrix = new InventoryCrafting(this, 3, 3);

		 for (int recX = 0; recX < 8; recX++) {
			 for (int recY = 0; recY < 5; recY++) {
				 addSlot(new SlotCrafting(player, inventory, craftMatrix, recY + recX * 8, 8 + recX * 18, 18 + recY * 18));
			 }
		 }

		 for (int inX = 0; inX < 2; inX++) {
			 for (int inY = 0; inY < 9; inY++) {
				 addSlot(new Slot(tileEntity, inY + (inX*9), 8 + inY * 18, 112 + (18*inX)));
			 }
		 }

		// PLAYER INVENTORY //
		// Main Inventory
		for (int xSlots = 0; xSlots < 3; ++xSlots) {
			for (int ySlots = 0; ySlots < 9; ySlots++) {
				addSlot(new Slot(inventory, ySlots + xSlots * 9 + 9, 8 + ySlots * 18, 152 + xSlots * 18));
			}
		}

		// Hotbar
		for (int hotbar = 0; hotbar < 9; hotbar++) {
			addSlot(new Slot(inventory, hotbar, 8 + hotbar * 18, 211));
		}
		onCraftMatrixChanged(tileEntity);
	}

	@Override
	public List<Integer> getMoveSlots(InventoryAction inventoryAction, Slot slot, int i, EntityPlayer entityPlayer) {
		return null;
	}

	@Override
	public List<Integer> getTargetSlots(InventoryAction inventoryAction, Slot slot, int i, EntityPlayer entityPlayer) {
		return null;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return tileEntity.canInteractWith(player);
	}
}
