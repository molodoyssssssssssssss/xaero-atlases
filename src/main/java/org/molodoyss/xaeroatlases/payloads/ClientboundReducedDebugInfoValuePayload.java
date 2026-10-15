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

public record ClientboundReducedDebugInfoValuePayload(boolean value) implements CustomPacketPayload {
    public static Type<ClientboundReducedDebugInfoValuePayload> TYPE = new Type<>(ModNetworkingConstants.CLIENTBOUND_REDUCED_DEBUG_INFO_VALUE);
    public static StreamCodec<FriendlyByteBuf, ClientboundReducedDebugInfoValuePayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            ClientboundReducedDebugInfoValuePayload::value,
            ClientboundReducedDebugInfoValuePayload::new
    );


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void send(List<ServerPlayer> players, boolean value) {
        players.forEach(player -> {
            ServerPlayNetworking.send(player, new ClientboundReducedDebugInfoValuePayload(value));
        });
    }
}
