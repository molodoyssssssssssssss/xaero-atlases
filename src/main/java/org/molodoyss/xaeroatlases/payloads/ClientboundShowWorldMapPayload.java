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

public record ClientboundShowWorldMapPayload(boolean showsCoords) implements CustomPacketPayload {
    public static Type<ClientboundShowWorldMapPayload> TYPE = new Type<>(ModNetworkingConstants.CLIENTBOUND_SHOW_WORLDMAP_PACKET);
    public static StreamCodec<FriendlyByteBuf, ClientboundShowWorldMapPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            ClientboundShowWorldMapPayload::showsCoords,
            ClientboundShowWorldMapPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void send(List<ServerPlayer> players, boolean showsCoords) {
        players.forEach(player -> {
            ServerPlayNetworking.send(player, new ClientboundShowWorldMapPayload(showsCoords));
        });
    }
}
