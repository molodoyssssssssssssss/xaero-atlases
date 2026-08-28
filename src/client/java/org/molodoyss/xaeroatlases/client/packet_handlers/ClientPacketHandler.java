package org.molodoyss.xaeroatlases.client.packet_handlers;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.molodoyss.xaeroatlases.Xaeroatlases;
import org.molodoyss.xaeroatlases.client.XaeroatlasesClient;
import org.molodoyss.xaeroatlases.payload.ClientboundEnabledCompassCordsPacket;
import org.molodoyss.xaeroatlases.payload.ClientboundRDBGameruleValuePacket;

public class ClientPacketHandler {
    public static void handleAll() {
        ClientPlayNetworking.registerGlobalReceiver(ClientboundRDBGameruleValuePacket.TYPE, ClientPacketHandler::handleReducedDebugInfoGetPacket);
        ClientPlayNetworking.registerGlobalReceiver(ClientboundEnabledCompassCordsPacket.TYPE, ClientPacketHandler::handleEnabledCompassCoordsGetPacket);
    }

    public static void handleReducedDebugInfoGetPacket(ClientboundRDBGameruleValuePacket payload, ClientPlayNetworking.Context context) {
        XaeroatlasesClient.setReducedDebugInfo(payload.value());
        Xaeroatlases.LOGGER.info("Got value of gamerule 'reduced_debug_info' from server!");
    }

    public static void handleEnabledCompassCoordsGetPacket(ClientboundEnabledCompassCordsPacket payload, ClientPlayNetworking.Context context) {
        XaeroatlasesClient.setEnabledCompassCoords(payload.value());
        Xaeroatlases.LOGGER.info("Got value from server of config value 'enable_coords_with_compass': %s".formatted(String.valueOf(payload.value())));
    }

}
