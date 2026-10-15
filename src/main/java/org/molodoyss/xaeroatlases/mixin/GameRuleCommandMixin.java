package org.molodoyss.xaeroatlases.mixin;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.commands.GameRuleCommand;
import net.minecraft.world.level.GameRules;
import org.molodoyss.xaeroatlases.ModNetworkingConstants;
import org.molodoyss.xaeroatlases.Xaeroatlases;
import org.molodoyss.xaeroatlases.payloads.ClientboundReducedDebugInfoValuePayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRuleCommand.class)
public class GameRuleCommandMixin {
    @Inject(method = "setRule", at = @At("RETURN"))
    private static <T extends GameRules.Value<T>> void listenNewRule(CommandContext<CommandSourceStack> commandContext, GameRules.Key<T> key, CallbackInfoReturnable<Integer> cir) {
        if (!key.equals(GameRules.RULE_REDUCEDDEBUGINFO)) return;
        boolean reducedDebugInfo = commandContext.getSource().getServer().getGameRules().getBoolean(GameRules.RULE_REDUCEDDEBUGINFO);

        Xaeroatlases.LOGGER.info("Gamerule 'reduced_debug_info' is now set to %b".formatted(reducedDebugInfo));

        ClientboundReducedDebugInfoValuePayload.send(commandContext.getSource().getServer().getPlayerList().getPlayers(), reducedDebugInfo);
    }
}
