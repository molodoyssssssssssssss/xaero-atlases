package org.molodoyss.xaeroatlases.client.packet_handlers;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import org.molodoyss.xaeroatlases.ModNetworkingConstants;
import org.molodoyss.xaeroatlases.Xaeroatlases;
import org.molodoyss.xaeroatlases.api.Utils;
import org.molodoyss.xaeroatlases.client.XaeroatlasesClient;
import org.molodoyss.xaeroatlases.payloads.ClientboundEnabledCompassCoordsPayload;
import org.molodoyss.xaeroatlases.payloads.ClientboundReducedDebugInfoValuePayload;
import org.molodoyss.xaeroatlases.payloads.ClientboundShowWorldMapPayload;
import org.molodoyss.xaeroatlases.tags.ModItemTags;
import xaero.map.WorldMap;
import xaero.map.WorldMapSession;
import xaero.map.common.config.option.WorldMapProfiledConfigOptions;
import xaero.map.gui.GuiMap;

public class ClientPacketHandler {
    public static void handleAll() {
        ClientPlayNetworking.registerGlobalReceiver(ClientboundReducedDebugInfoValuePayload.TYPE, ClientPacketHandler::handleReducedDebugInfoGetPacket);
        ClientPlayNetworking.registerGlobalReceiver(ClientboundEnabledCompassCoordsPayload.TYPE, ClientPacketHandler::handleEnabledCompassCoordsGetPacket);
        ClientPlayNetworking.registerGlobalReceiver(ClientboundShowWorldMapPayload.TYPE, ClientPacketHandler::handleShowWorldMapPacket);
    }
    public static void handleReducedDebugInfoGetPacket(ClientboundReducedDebugInfoValuePayload payload, ClientPlayNetworking.Context context) {
        boolean value = payload.value();
        XaeroatlasesClient.setReducedDebugInfo(value);
        Xaeroatlases.LOGGER.info("Got value of gamerule 'reduced_debug_info' from server: %s!".formatted(value));
    }

    public static void handleEnabledCompassCoordsGetPacket(ClientboundEnabledCompassCoordsPayload payload, ClientPlayNetworking.Context context) {
        boolean value = payload.value();
        XaeroatlasesClient.setEnabledCompassCoords(value);
        Xaeroatlases.LOGGER.info("Got value from server of config value 'enable_coords_with_compass': %s".formatted(String.valueOf(value)));
    }

    public static void handleShowWorldMapPacket(ClientboundShowWorldMapPayload payload, ClientPlayNetworking.Context context) {
        boolean isShowingCoords = payload.showsCoords();
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            boolean isEquippedCompass = Utils.isItemInHand(player, ModItemTags.IS_SHOWING_COORDS);


            boolean showsCoords = (!XaeroatlasesClient.isReducedDebugInfo()) || ((XaeroatlasesClient.isEnabledCompassCoords() && isEquippedCompass) || isShowingCoords);
            boolean showsCoords1 = (!XaeroatlasesClient.isReducedDebugInfo());
            boolean showsCoords2 = (XaeroatlasesClient.isEnabledCompassCoords() && isEquippedCompass);
            boolean showsCoords3 = XaeroatlasesClient.isEnabledCompassCoords();
            boolean showsCoords4 = isEquippedCompass;
            boolean showsCoords5 = isShowingCoords;
            Xaeroatlases.debugLog(String.valueOf(showsCoords), Xaeroatlases.LogTypes.INFO);
            Xaeroatlases.debugLog(String.valueOf(showsCoords1), Xaeroatlases.LogTypes.INFO);
            Xaeroatlases.debugLog(String.valueOf(showsCoords2), Xaeroatlases.LogTypes.INFO);
            Xaeroatlases.debugLog(String.valueOf(showsCoords3), Xaeroatlases.LogTypes.INFO);
            Xaeroatlases.debugLog(String.valueOf(showsCoords4), Xaeroatlases.LogTypes.INFO);
            Xaeroatlases.debugLog(String.valueOf(showsCoords5), Xaeroatlases.LogTypes.INFO);
            WorldMap.INSTANCE.getConfigs().getClientConfigManager().getCurrentProfile().set(WorldMapProfiledConfigOptions.COORDINATES, showsCoords);
            Minecraft.getInstance().execute(() -> {
                Minecraft.getInstance().setScreen((Screen) (Object) new GuiMap((Screen) null, (Screen) null, WorldMapSession.getCurrentSession().getMapProcessor(), Minecraft.getInstance().getCameraEntity()));
            });
        }
    }

}
