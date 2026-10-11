package org.molodoyss.xaeroatlases;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.GameRules;
import org.molodoyss.xaeroatlases.config.ModConfig;

public class ModRDBAndEnabledCompassCoordsValueUpdate {
    public static void init() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            FriendlyByteBuf RDIBuf = PacketByteBufs.create();
            FriendlyByteBuf ECCBuf = PacketByteBufs.create();
            RDIBuf.writeBoolean(handler.player.server.getGameRules().getBoolean(GameRules.RULE_REDUCEDDEBUGINFO));
            ECCBuf.writeBoolean(ModConfig.isEnabledShowingCoordsWithCompass());
            ServerPlayNetworking.send(handler.player, ModNetworkingConstants.CLIENTBOUND_REDUCED_DEBUG_INFO_VALUE, RDIBuf);
            ServerPlayNetworking.send(handler.player, ModNetworkingConstants.CLIENTBOUND_ENABLED_COMPASS_COORDS, ECCBuf);
        });
    }
}
