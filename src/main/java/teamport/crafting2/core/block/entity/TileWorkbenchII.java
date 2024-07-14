package teamport.crafting2.core.block.entity;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.sound.SoundCategory;
import teamport.crafting2.extra.C2Config;

public class TileWorkbenchII extends TileEntity implements IInventory {
	private int workbenchState = 0;
	private ItemStack[] slots = new ItemStack[19];

	public double playerDistance = 7.0;
	public float doorAngle = 0.0F;
	public static final float OPEN_SPEED = 0.2F;

	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return slots[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (slots[i] != null) {
			if (slots[i].stackSize <= j) {
				ItemStack contents = slots[i];

				slots[i] = null;
				return contents;
			} else {
				ItemStack splitStack = slots[i].splitStack(j);
				if (slots[i].stackSize <= 0) slots[i] = null;

				return splitStack;
			}
		}

		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		slots[i] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();

		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "crafting2_workbenchII";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return worldObj.getBlockTileEntity(x, y, z) == this && player.distanceToSqr(x + 0.5, y + 0.5, z + 0.5) <= 64;
	}

	@Override
	public void sortInventory() {

	}

	public int getFreeSlot() {
		for (int i = 0; i < slots.length; i++) {
			if (getStackInSlot(i) == null) return i;
		}

		return -1;
	}

	public int findStack(ItemStack stack) {
		for (int i = 0; i < slots.length; i++) {
			ItemStack stackInSlot = getStackInSlot(i);
			if (stackInSlot != null) {
				if (stackInSlot.itemID == stack.itemID && stackInSlot.isItemStackDamageable()) {
					if (stackInSlot.getItemDamageForDisplay() == stack.getItemDamageForDisplay() &&
						stackInSlot.getMaxStackSize() > stack.getMaxStackSize()) {
						return i;
					}
				} else {
					if (getStackInSlot(i).getMaxStackSize() > getStackInSlot(i).stackSize) {
						return i;
					}
				}
			}
		}

		return -1;
	}

	public boolean addCraftResultToInventory(ItemStack stack) {
		int index = findStack(stack);
		while (index > -1) {
			ItemStack slotStack = getStackInSlot(index);

			if (slotStack.isStackable()) {
				if (slotStack.getMaxStackSize() - slotStack.stackSize > stack.stackSize) {
					slotStack.stackSize += stack.stackSize;
					stack.stackSize = 0;
					setInventorySlotContents(index, slotStack);
				} else {
					stack.stackSize -= (slotStack.getMaxStackSize() - slotStack.stackSize);
					slotStack.stackSize = slotStack.getMaxStackSize();
					setInventorySlotContents(index, slotStack);
				}
			}

			if (stack.stackSize <= 0) return true;
			index = getFreeSlot();
		}

		if (stack.stackSize > 0) {
			index = getFreeSlot();

			if (index > -1) setInventorySlotContents(index, stack);
			else return false;
		}

		return true;
	}

	public TileWorkbenchII getCopy() {
		TileWorkbenchII clone = new TileWorkbenchII();

		for (int i = 0; i < slots.length; i++) {
			if (getStackInSlot(i) != null) clone.setInventorySlotContents(i, getStackInSlot(i).copy());
			else clone.setInventorySlotContents(i, null);
		}

		return clone;
	}

	@Override
	public void tick() {
		super.tick();

		if (C2Config.cfg.getBoolean("WorkbenchII.doorEnabled")) {
			EntityPlayer player = worldObj.getClosestPlayer(x + 0.5, y + 0.5, z + 0.5, 10.0);

			if (player != null) {
				playerDistance = player.distanceToSqr(x, y, z);
				if (playerDistance < 7.0F) {
					doorAngle += OPEN_SPEED;
					if(doorAngle > 1.8F) doorAngle = 1.8F;

					if (workbenchState != 1) {
						workbenchState = 1;

						if (C2Config.cfg.getBoolean("WorkbenchII.soundEnabled"))
							worldObj.playSoundEffect(player, 1003, x, y, z, 0);
					}
				} else {
					doorAngle -= OPEN_SPEED;
					if(doorAngle < 0.0F) doorAngle = 0.0F;

					if (workbenchState != 0) {
						workbenchState = 0;

						if (C2Config.cfg.getBoolean("WorkbenchII.soundEnabled"))
							worldObj.playSoundEffect(player, 1003, x, y, z, 0);
					}
				}
			}
		}
	}
}
