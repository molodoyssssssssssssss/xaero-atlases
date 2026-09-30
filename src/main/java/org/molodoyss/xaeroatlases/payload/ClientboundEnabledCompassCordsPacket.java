package org.molodoyss.xaeroatlases.payload;

import com.mojang.serialization.MapCodec;
import com.sun.jna.platform.win32.WinDef;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import org.molodoyss.xaeroatlases.Xaeroatlases;

import java.util.List;

public record ClientboundEnabledCompassCordsPacket(boolean value) implements CustomPacketPayload {

    public static Identifier ID = Xaeroatlases.id("clientbound/enabled_compass_coords_packet");
    public static Type<ClientboundEnabledCompassCordsPacket> TYPE = new Type<>(ID);
    public static StreamCodec<RegistryFriendlyByteBuf, ClientboundEnabledCompassCordsPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            ClientboundEnabledCompassCordsPacket::value,
            ClientboundEnabledCompassCordsPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void broadcast(List<ServerPlayer> players, boolean value) {
        players.forEach(player -> ServerPlayNetworking.send(player, new ClientboundEnabledCompassCordsPacket(value)));
    }
}
