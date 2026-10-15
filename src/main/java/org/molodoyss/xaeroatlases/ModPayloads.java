package org.molodoyss.xaeroatlases;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.molodoyss.xaeroatlases.payloads.ClientboundEnabledCompassCoordsPayload;
import org.molodoyss.xaeroatlases.payloads.ClientboundReducedDebugInfoValuePayload;
import org.molodoyss.xaeroatlases.payloads.ClientboundShowWorldMapPayload;

public class ModPayloads {
    public static void init() {
        PayloadTypeRegistry.playS2C().register(ClientboundReducedDebugInfoValuePayload.TYPE, ClientboundReducedDebugInfoValuePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ClientboundEnabledCompassCoordsPayload.TYPE, ClientboundEnabledCompassCoordsPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ClientboundShowWorldMapPayload.TYPE, ClientboundShowWorldMapPayload.CODEC);
    }
}
