package org.molodoyss.xaeroatlases.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import org.molodoyss.xaeroatlases.ModItems;
import org.molodoyss.xaeroatlases.Xaeroatlases;
import xaero.hud.minimap.Minimap;
import xaero.hud.minimap.common.config.option.MinimapProfiledConfigOptions;
import xaero.hud.minimap.controls.key.MinimapKeyMappings;
import xaero.hud.xminimap.controls.key.XMinimapKeyMappings;
import xaero.map.WorldMap;
import xaero.map.WorldMapSession;
import xaero.map.common.config.option.WorldMapProfiledConfigOptions;
import xaero.map.core.XaeroWorldMapCore;
import xaero.map.core.XaeroWorldMapCoreFabric;
import xaero.map.gui.GuiMap;
import xaero.map.mods.XaeroWorldMapModMenu;
import xaero.map.settings.ModSettings;
import xaero.minimap.XaeroMinimap;
import xaero.minimap.XaeroMinimapFabric;

public class XaeroatlasesClient implements ClientModInitializer {
    boolean isPressedUseKey = false;
    @Override
    public void onInitializeClient() {

        ClientTickEvents.END_CLIENT_TICK.register(level -> {

            if (Minecraft.getInstance().player == null) return;
            WorldMap.INSTANCE.getConfigs().getClientConfigManager().getCurrentProfile().set(WorldMapProfiledConfigOptions.CAVE_MODE_ALLOWED, false);
            WorldMap.INSTANCE.getConfigs().getClientConfigManager().getCurrentProfile().set(WorldMapProfiledConfigOptions.MINIMAP_RADAR, false);

            LocalPlayer player = Minecraft.getInstance().player;
            boolean isEquippedAtlas = player.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.ATLAS) || player.getItemInHand(InteractionHand.OFF_HAND).is(ModItems.ATLAS);
            for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
                if (mod.getMetadata().getId().equals("xaerominimap")) {
                    XaeroMinimap.INSTANCE.getHudConfigs().getClientConfigManager().getCurrentProfile().set(MinimapProfiledConfigOptions.WAYPOINTS_IN_WORLD, isEquippedAtlas);
                    XaeroMinimap.INSTANCE.getHudConfigs().getClientConfigManager().getCurrentProfile().set(MinimapProfiledConfigOptions.MINIMAP_ITEM, "minecraft:barrier");
                }
            }

            if (!Minecraft.getInstance().options.keyUse.isDown()) {
                isPressedUseKey = false;
            }
            while (Minecraft.getInstance().options.keyUse.isDown() && !isPressedUseKey) {
                WorldMap.INSTANCE.getConfigs().getClientConfigManager().getCurrentProfile().set(WorldMapProfiledConfigOptions.COORDINATES, false);

                isPressedUseKey = true;
                if (!isEquippedAtlas) return;
                InteractionHand hand = InteractionHand.MAIN_HAND;
                if (player.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.ATLAS)) {
                } else if (player.getItemInHand(InteractionHand.OFF_HAND).is(ModItems.ATLAS)) {
                    hand = InteractionHand.OFF_HAND;
                }
                player.swing(hand);
                Minecraft mc = Minecraft.getInstance();
                mc.gui.setScreen((Screen) (Object) new GuiMap((Screen) null, (Screen) null, WorldMapSession.getCurrentSession().getMapProcessor(), mc.getCameraEntity()));
            }
        });
    }
}
