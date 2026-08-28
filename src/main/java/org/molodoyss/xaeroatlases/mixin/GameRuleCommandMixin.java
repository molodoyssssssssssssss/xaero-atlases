package org.molodoyss.xaeroatlases.mixin;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.commands.GameRuleCommand;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRules;
import org.molodoyss.xaeroatlases.config.ModConfig;
import org.molodoyss.xaeroatlases.payload.ClientboundRDBGameruleValuePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRuleCommand.class)
public class GameRuleCommandMixin {
    @Inject(method = "setRule", at = @At("RETURN"))
    private static <T> void listenNewRule(final CommandContext<CommandSourceStack> context, final GameRule<T> gameRule, CallbackInfoReturnable<Integer> cir) {
        if (gameRule.id().equals(GameRules.REDUCED_DEBUG_INFO.id())) {
            MinecraftServer server = context.getSource().getServer();
            ModConfig.setReducedDebugInfo(server.getGameRules().get((GameRule<Boolean>) gameRule));
        }
    }
}
