package org.molodoyss.xaeroatlases;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.molodoyss.xaeroatlases.payload.ClientboundEnabledCompassCordsPacket;
import org.molodoyss.xaeroatlases.payload.ClientboundRDBGameruleValuePacket;
import org.molodoyss.xaeroatlases.payload.ClientboundShowWorldmapPacket;

public class ModPayloads {
    public static void register() {
        PayloadTypeRegistry.clientboundPlay().register(ClientboundRDBGameruleValuePacket.TYPE, ClientboundRDBGameruleValuePacket.CODEC);
        PayloadTypeRegistry.clientboundPlay().register(ClientboundEnabledCompassCordsPacket.TYPE, ClientboundEnabledCompassCordsPacket.CODEC);
        PayloadTypeRegistry.clientboundPlay().register(ClientboundShowWorldmapPacket.TYPE, ClientboundShowWorldmapPacket.CODEC);
    }
}
