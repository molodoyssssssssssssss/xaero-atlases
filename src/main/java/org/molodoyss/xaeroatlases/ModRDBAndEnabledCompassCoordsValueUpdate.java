package org.molodoyss.xaeroatlases;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.GameRules;
import org.molodoyss.xaeroatlases.config.ModConfig;
import org.molodoyss.xaeroatlases.payloads.ClientboundEnabledCompassCoordsPayload;
import org.molodoyss.xaeroatlases.payloads.ClientboundReducedDebugInfoValuePayload;

import java.util.List;

public class ModRDBAndEnabledCompassCoordsValueUpdate {
    public static void init() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            boolean reducedDebugInfo = handler.player.server.getGameRules().getBoolean(GameRules.RULE_REDUCEDDEBUGINFO);
            boolean enabledCompassCoords = ModConfig.isEnabledShowingCoordsWithCompass();
            ClientboundReducedDebugInfoValuePayload.send(List.of(handler.player), reducedDebugInfo);
            ClientboundEnabledCompassCoordsPayload.send(List.of(handler.player), enabledCompassCoords);
        });
    }
}
