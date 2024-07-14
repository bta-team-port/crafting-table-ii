package teamport.crafting2.client.gui;

import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.lwjgl.opengl.GL11;
import teamport.crafting2.core.block.entity.TileWorkbenchII;

public class GuiWorkbenchII extends GuiContainer {
	protected final I18n i18n = I18n.getInstance();
	private float scroll = 0.0F;

	public GuiWorkbenchII(InventoryPlayer inventory, TileWorkbenchII tileEntity) {
		super(new ContainerWorkbenchII(inventory, tileEntity));
		xSize = 176;
		ySize = 235;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f) {
		int texture = mc.renderEngine.getTexture("/assets/crafting2/textures/gui/crafttableii.png");
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.renderEngine.bindTexture(texture);

		int scrnX = (width - xSize) / 2;
		int scrnY = (height - ySize) / 2;
		drawTexturedModalRect(scrnX, scrnY, 0, 0, xSize, ySize);

		int scrollPos1 = scrnY + 17;
		int scrollPos2 = scrollPos1 + 88 + 2;

		// Scrollbar
		drawTexturedModalRect(scrnX + 154, scrnY + 17 + (int)((float)(scrollPos2 - scrollPos1 - 17) * scroll), 0, 240, 16, 16);
	}
}
