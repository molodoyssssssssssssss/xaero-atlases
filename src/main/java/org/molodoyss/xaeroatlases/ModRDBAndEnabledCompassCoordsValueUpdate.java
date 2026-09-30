package org.molodoyss.xaeroatlases;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.level.gamerules.GameRules;
import org.molodoyss.xaeroatlases.config.ModConfig;
import org.molodoyss.xaeroatlases.payload.ClientboundEnabledCompassCordsPacket;
import org.molodoyss.xaeroatlases.payload.ClientboundRDBGameruleValuePacket;

public class ModRDBAndEnabledCompassCoordsValueUpdate {
    public static void init() {
        ServerPlayerEvents.JOIN.register(player -> {
            ClientboundRDBGameruleValuePacket packet = new ClientboundRDBGameruleValuePacket(player.level().getServer().getGameRules().get(GameRules.REDUCED_DEBUG_INFO));
            ClientboundEnabledCompassCordsPacket packet1 = new ClientboundEnabledCompassCordsPacket(ModConfig.isEnabledShowingCoordsWithCompass());
            ServerPlayNetworking.send(player, packet);
            ServerPlayNetworking.send(player, packet1);
        });
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            ClientboundRDBGameruleValuePacket packet = new ClientboundRDBGameruleValuePacket(newPlayer.level().getServer().getGameRules().get(GameRules.REDUCED_DEBUG_INFO));
            ClientboundEnabledCompassCordsPacket packet1 = new ClientboundEnabledCompassCordsPacket(ModConfig.isEnabledShowingCoordsWithCompass());
            ServerPlayNetworking.send(newPlayer, packet);
            ServerPlayNetworking.send(newPlayer, packet1);
        });
    }
}
