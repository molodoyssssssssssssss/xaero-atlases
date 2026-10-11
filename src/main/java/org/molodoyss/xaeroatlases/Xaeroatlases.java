package org.molodoyss.xaeroatlases;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.molodoyss.xaeroatlases.command.ModCommands;
import org.molodoyss.xaeroatlases.config.ModConfig;
import org.molodoyss.xaeroatlases.loot.ModLootTableModifier;
import org.molodoyss.xaeroatlases.tags.ModItemTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Xaeroatlases implements ModInitializer {
    public static final String ID = "xaero_atlases";
    public static final Logger LOGGER = LoggerFactory.getLogger("Xaero Atlases");

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(ID, path);
    }


    @Override
    public void onInitialize() {
        ModItemTags.init();
        ModRDBAndEnabledCompassCoordsValueUpdate.init();
        ModItems.init();
        ModLootTableModifier.modify();
        ModPayloads.register();
        ModConfig.init();
        ModCommands.init();

        LOGGER.info("Initialized!");
    }
}
