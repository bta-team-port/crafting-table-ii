package teamport.crafting2.core.recipes;

import sunsetsatellite.catalyst.Catalyst;
import sunsetsatellite.catalyst.core.util.MpGuiEntry;
import teamport.crafting2.CraftingTableII;
import teamport.crafting2.client.gui.ContainerWorkbenchII;
import teamport.crafting2.client.gui.GuiWorkbenchII;
import teamport.crafting2.core.block.entity.TileWorkbenchII;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class C2Recipes implements RecipeEntrypoint {
	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(CraftingTableII.MOD_ID);
		EntityHelper.createTileEntity(TileWorkbenchII.class, "crafting2_workbenchII");
		Catalyst.GUIS.register("crafting2_workbenchII", new MpGuiEntry(TileWorkbenchII.class, GuiWorkbenchII.class, ContainerWorkbenchII.class));
	}
}
