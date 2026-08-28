package org.molodoyss.xaeroatlases.payload;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import org.molodoyss.xaeroatlases.Xaeroatlases;

import java.util.List;

public record ClientboundRDBGameruleValuePacket(boolean value) implements CustomPacketPayload {

    public static final Identifier ID = Identifier.fromNamespaceAndPath(Xaeroatlases.ID, "clientbound/reduced_debug_info_value");

    public static final Type<ClientboundRDBGameruleValuePacket> TYPE = new Type<>(ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundRDBGameruleValuePacket> CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, ClientboundRDBGameruleValuePacket::value, ClientboundRDBGameruleValuePacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void broadcast(List<ServerPlayer> players, boolean value) {
        players.forEach(player -> ServerPlayNetworking.send(player, new ClientboundRDBGameruleValuePacket(value)));
    }
}
