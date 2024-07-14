package teamport.crafting2.extra;

import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import static teamport.crafting2.CraftingTableII.MOD_ID;

public class C2Config {
	private static final Toml PROPERTIES = new Toml("Workbench II Config");
	public static TomlConfigHandler cfg;

	static {
		PROPERTIES.addCategory("WorkbenchII")
			.addEntry("doorEnabled", true)
			.addEntry("soundEnabled", true);

		PROPERTIES.addCategory("IDs")
			.addEntry("startingBlockID", 1100);

		cfg = new TomlConfigHandler(MOD_ID, PROPERTIES);
	}
}
