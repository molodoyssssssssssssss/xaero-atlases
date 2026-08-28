package org.molodoyss.xaeroatlases;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.network.protocol.game.ClientboundGameRuleValuesPacket;
import net.minecraft.server.commands.GameRuleCommand;
import net.minecraft.world.level.gamerules.GameRules;
import org.molodoyss.xaeroatlases.command.ModCommands;
import org.molodoyss.xaeroatlases.config.ModConfig;
import org.molodoyss.xaeroatlases.loot.ModLootTableModifier;
import org.molodoyss.xaeroatlases.payload.ClientboundEnabledCompassCordsPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Xaeroatlases implements ModInitializer {
    public static final String ID = "xaero_atlases";
    public static final Logger LOGGER = LoggerFactory.getLogger("Xaero Atlases");


    @Override
    public void onInitialize() {
        ModRDBAndEnabledCompassCoordsValueUpdate.init();
        ModItems.init();
        ModLootTableModifier.modify();
        ModPayloads.register();
        ModConfig.init();
        ModCommands.init();

        LOGGER.info("Initialized!");
    }
}
