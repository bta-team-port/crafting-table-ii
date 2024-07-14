package teamport.crafting2;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import teamport.crafting2.client.gui.GuiWorkbenchII;
import teamport.crafting2.core.C2Blocks;
import turniplabs.halplibe.helper.gui.GuiHelper;
import turniplabs.halplibe.helper.gui.factory.base.GuiFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class CraftingTableII implements ModInitializer, GameStartEntrypoint {
    public static final String MOD_ID = "crafting2";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Team Port presents: 'Crafting Table II'");
    }

	@Override
	public void beforeGameStart() {
		C2Blocks.registerBlocks();
	}

	@Override
	public void afterGameStart() {

	}
}
