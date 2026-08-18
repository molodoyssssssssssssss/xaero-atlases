package org.molodoyss.xaeroatlases;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.commands.GameRuleCommand;
import net.minecraft.world.level.gamerules.GameRules;
import org.molodoyss.xaeroatlases.loot.ModLootTableModifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Xaeroatlases implements ModInitializer {
    public static final String ID = "xaero_atlases";
    public static final Logger LOGGER = LoggerFactory.getLogger("Xaero Atlases");


    @Override
    public void onInitialize() {
        ModItems.init();
        ModLootTableModifier.modify();
        LOGGER.info("Initialized!");
        ServerTickEvents.END_LEVEL_TICK.register(level -> {
            level.getGameRules().set(GameRules.REDUCED_DEBUG_INFO, true, level.getServer());
        });
    }
}
