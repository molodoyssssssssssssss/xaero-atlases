package org.molodoyss.xaeroatlases.payloads;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import org.molodoyss.xaeroatlases.ModNetworkingConstants;

import java.util.List;

public record ClientboundEnabledCompassCoordsPayload(boolean value) implements CustomPacketPayload {
    public static Type<ClientboundEnabledCompassCoordsPayload> TYPE = new Type<>(ModNetworkingConstants.CLIENTBOUND_ENABLED_COMPASS_COORDS);
    public static StreamCodec<FriendlyByteBuf, ClientboundEnabledCompassCoordsPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            ClientboundEnabledCompassCoordsPayload::value,
            ClientboundEnabledCompassCoordsPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }


    public static void send(List<ServerPlayer> players, boolean value) {
        players.forEach(player -> {
            ServerPlayNetworking.send(player, new ClientboundEnabledCompassCoordsPayload(value));
        });
    }
}
