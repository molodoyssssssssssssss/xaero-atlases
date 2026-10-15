package org.molodoyss.xaeroatlases;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.molodoyss.xaeroatlases.command.ModCommands;
import org.molodoyss.xaeroatlases.config.ModConfig;
import org.molodoyss.xaeroatlases.loot.ModLootTableModifier;
import org.molodoyss.xaeroatlases.tags.ModItemTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Xaeroatlases implements ModInitializer {
    public static final String ID = "xaero_atlases";
    public static final Logger LOGGER = LoggerFactory.getLogger("Xaero Atlases");

    private static boolean debugMode = false;

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }

    public enum LogTypes {
        INFO,
        WARN,
        ERROR
    }

    public static void disableDebugMode() {
        LOGGER.info("Disabling debug mode");
        debugMode = false;
    }
    public static void enableDebugMode() {
        LOGGER.info("Enabling debug mode");
        debugMode = true;
    }

    public static boolean switchDebugMode() {
        debugMode = !debugMode;
        LOGGER.info("Switching debug mode to %s".formatted(debugMode));
        return debugMode;
    }

    public static boolean isEnabledDebugMode() {
        return debugMode;
    }

    public static void debugLog(String msg, LogTypes type) {
        if (!debugMode) { return; }

        switch (type) {
            case INFO -> LOGGER.info(msg);
            case WARN -> LOGGER.warn(msg);
            case ERROR -> LOGGER.error(msg);
        }
    }

    @Override
    public void onInitialize() {
        ModPayloads.init();
        ModItemTags.init();
        ModNetworkingConstants.init();
        ModRDBAndEnabledCompassCoordsValueUpdate.init();
        ModItems.init();
        ModLootTableModifier.modify();
        ModConfig.init();
        ModCommands.init();

        LOGGER.info("Initialized!");
    }
}
