package org.molodoyss.xaeroatlases.client.mixin;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.ClientboundPacketListener;
import net.minecraft.network.protocol.game.ClientboundGameRuleValuesPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.gamerules.GameRules;
import org.molodoyss.xaeroatlases.Xaeroatlases;
import org.molodoyss.xaeroatlases.client.XaeroatlasesClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Inject(method = "handleGameRuleValues", at = @At("TAIL"))
    private void setReducedDebugInfo(final ClientboundGameRuleValuesPacket packet, CallbackInfo ci) {
        Xaeroatlases.LOGGER.info("aaaa");

        String value = packet.values().get(ResourceKey.create(Registries.GAME_RULE, Identifier.withDefaultNamespace("reduced_debug_info")));
        for (ResourceKey<?> key : packet.values().keySet()) {
            Xaeroatlases.LOGGER.info(key.toString());
        }

        Xaeroatlases.LOGGER.info(value);
    }
}
