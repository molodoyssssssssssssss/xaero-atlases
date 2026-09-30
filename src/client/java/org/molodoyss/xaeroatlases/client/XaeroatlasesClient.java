package org.molodoyss.xaeroatlases.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import org.molodoyss.xaeroatlases.ModItems;
import org.molodoyss.xaeroatlases.Xaeroatlases;
import org.molodoyss.xaeroatlases.client.commands.ModClientCommands;
import org.molodoyss.xaeroatlases.client.packet_handlers.ClientPacketHandler;
import xaero.hud.minimap.common.config.option.MinimapProfiledConfigOptions;
import xaero.map.WorldMap;
import xaero.map.WorldMapSession;
import xaero.map.common.config.option.WorldMapProfiledConfigOptions;
import xaero.map.gui.GuiMap;
import xaero.minimap.XaeroMinimap;

public class XaeroatlasesClient implements ClientModInitializer {
    boolean isPressedUseKey = false;

    private static boolean isReducedDebugInfo = false;
    private static boolean isEnabledCompassCoords = false;

    public static boolean isReducedDebugInfo() {
        return isReducedDebugInfo;
    }
    public static boolean isEnabledCompassCoords() {
        return isEnabledCompassCoords;
    }

    public static void setReducedDebugInfo(boolean isReducedDebugInfo) {
        XaeroatlasesClient.isReducedDebugInfo = isReducedDebugInfo;
    }
    public static void setEnabledCompassCoords(boolean isEnabledCompassCoords) {
        XaeroatlasesClient.isEnabledCompassCoords = isEnabledCompassCoords;
    }


    @Override
    public void onInitializeClient() {
        ClientPacketHandler.handleAll();

        ModClientCommands.init();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (client.player == null) return;

            WorldMap.INSTANCE.getConfigs().getClientConfigManager().getCurrentProfile().set(WorldMapProfiledConfigOptions.CAVE_MODE_ALLOWED, false);
            WorldMap.INSTANCE.getConfigs().getClientConfigManager().getCurrentProfile().set(WorldMapProfiledConfigOptions.MINIMAP_RADAR, false);

            LocalPlayer player = client.player;
            boolean isEquippedAtlas = player.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.ATLAS) || player.getItemInHand(InteractionHand.OFF_HAND).is(ModItems.ATLAS);
            for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
                if (mod.getMetadata().getId().equals("xaerominimap")) {
                    XaeroMinimap.INSTANCE.getHudConfigs().getClientConfigManager().getCurrentProfile().set(MinimapProfiledConfigOptions.WAYPOINTS_IN_WORLD, isEquippedAtlas);
                    XaeroMinimap.INSTANCE.getHudConfigs().getClientConfigManager().getCurrentProfile().set(MinimapProfiledConfigOptions.MINIMAP_ITEM, "minecraft:barrier");
                }
            }
        });
    }
}
