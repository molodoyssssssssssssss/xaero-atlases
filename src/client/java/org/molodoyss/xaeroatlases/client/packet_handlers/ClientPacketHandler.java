package org.molodoyss.xaeroatlases.client.packet_handlers;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import org.molodoyss.xaeroatlases.ModItems;
import org.molodoyss.xaeroatlases.Xaeroatlases;
import org.molodoyss.xaeroatlases.api.Utils;
import org.molodoyss.xaeroatlases.client.XaeroatlasesClient;
import org.molodoyss.xaeroatlases.payload.ClientboundEnabledCompassCordsPacket;
import org.molodoyss.xaeroatlases.payload.ClientboundRDBGameruleValuePacket;
import org.molodoyss.xaeroatlases.payload.ClientboundShowWorldmapPacket;
import org.molodoyss.xaeroatlases.tags.ModItemTags;
import xaero.map.WorldMap;
import xaero.map.WorldMapSession;
import xaero.map.common.config.option.WorldMapProfiledConfigOptions;
import xaero.map.gui.GuiMap;

public class ClientPacketHandler {
    public static void handleAll() {
        ClientPlayNetworking.registerGlobalReceiver(ClientboundRDBGameruleValuePacket.TYPE, ClientPacketHandler::handleReducedDebugInfoGetPacket);
        ClientPlayNetworking.registerGlobalReceiver(ClientboundEnabledCompassCordsPacket.TYPE, ClientPacketHandler::handleEnabledCompassCoordsGetPacket);
        ClientPlayNetworking.registerGlobalReceiver(ClientboundShowWorldmapPacket.TYPE, ClientPacketHandler::handleShowWorldMapPacket);
    }

    public static void handleReducedDebugInfoGetPacket(ClientboundRDBGameruleValuePacket payload, ClientPlayNetworking.Context context) {
        XaeroatlasesClient.setReducedDebugInfo(payload.value());
        Xaeroatlases.LOGGER.info("Got value of gamerule 'reduced_debug_info' from server!");
    }

    public static void handleEnabledCompassCoordsGetPacket(ClientboundEnabledCompassCordsPacket payload, ClientPlayNetworking.Context context) {
        XaeroatlasesClient.setEnabledCompassCoords(payload.value());
        Xaeroatlases.LOGGER.info("Got value from server of config value 'enable_coords_with_compass': %s".formatted(String.valueOf(payload.value())));
    }

    public static void handleShowWorldMapPacket(ClientboundShowWorldmapPacket payload, ClientPlayNetworking.Context context) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            boolean isEquippedCompass = Utils.isItemInHand(player, ModItemTags.IS_SHOWING_COORDS);
            WorldMap.INSTANCE.getConfigs().getClientConfigManager().getCurrentProfile().set(WorldMapProfiledConfigOptions.COORDINATES, (!XaeroatlasesClient.isReducedDebugInfo()) || ((XaeroatlasesClient.isEnabledCompassCoords() && isEquippedCompass) || payload.isShowingCoords()));
            Minecraft.getInstance().gui.setScreen((Screen) (Object) new GuiMap((Screen) null, (Screen) null, WorldMapSession.getCurrentSession().getMapProcessor(), Minecraft.getInstance().getCameraEntity()));
        }
    }

}
