package org.molodoyss.xaeroatlases.payload;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import org.molodoyss.xaeroatlases.Xaeroatlases;

import java.util.List;

public record ClientboundShowWorldmapPacket(boolean isShowingCoords) implements CustomPacketPayload {
    public static final Identifier ID = Xaeroatlases.id("clientbound/show_worldmap_packet");
    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundShowWorldmapPacket> CODEC = StreamCodec.composite(
        ByteBufCodecs.BOOL,
        ClientboundShowWorldmapPacket::isShowingCoords,
        ClientboundShowWorldmapPacket::new
    );
    public static final Type<ClientboundShowWorldmapPacket> TYPE = new Type<>(ID);


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void broadcast(List<ServerPlayer> players, boolean isShowingCoords) {
        players.forEach(player -> ServerPlayNetworking.send(player, new ClientboundShowWorldmapPacket(isShowingCoords)));
    }
}
