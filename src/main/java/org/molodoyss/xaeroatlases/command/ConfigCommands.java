package org.molodoyss.xaeroatlases.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.molodoyss.xaeroatlases.config.ModConfig;
import org.molodoyss.xaeroatlases.payload.ClientboundEnabledCompassCordsPacket;

public class ConfigCommands {
    public static int reloadConfigCommand(CommandContext<CommandSourceStack> context) {
        ModConfig.init();
        context.getSource().sendSystemMessage(Component.translatable("commands.xaero-atlases.config.reload.succesful"));
        return 1;
    }

    public static int configSetEnabledCoordsWithCompassCommand(CommandContext<CommandSourceStack> context, boolean value) {
        ModConfig.setEnabledShowingCoordsWithCompass(value);
        context.getSource().sendSystemMessage(Component.translatable("commands.xaero-atlases.config.set.enable_coords_with_compass.succesful", String.valueOf(value)));
        return 1;
    }

    public static int configGetEnabledCoordsWithCompassCommand(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSystemMessage(Component.translatable("commands.xaero-atlases.config.get.enable_coords_with_compass.succesful", String.valueOf(ModConfig.isEnabledShowingCoordsWithCompass())));
        return 1;
    }

    public static int configGetReducedDebugInfoCommand(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSystemMessage(Component.translatable("commands.xaero-atlases.config.get.reduced_debug_info.succesful", String.valueOf(ModConfig.isEnabledShowingCoordsWithCompass())));
        return 1;
    }
    public static int configSetReducedDebugInfoCommand(CommandContext<CommandSourceStack> context, boolean value) {
        ModConfig.setReducedDebugInfo(value);
        context.getSource().sendSystemMessage(Component.translatable("commands.xaero-atlases.config.set.reduced_debug_info.succesful", String.valueOf(value)));
        return 1;
    }

}
