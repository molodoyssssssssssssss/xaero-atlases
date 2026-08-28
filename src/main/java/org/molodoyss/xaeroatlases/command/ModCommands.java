package org.molodoyss.xaeroatlases.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.resources.Identifier;
import net.minecraft.server.permissions.PermissionLevel;
import org.molodoyss.xaeroatlases.Xaeroatlases;

public class ModCommands {
    private static final Identifier XAERO_ATLASES_COMMAND_PERMISSION_ID = Identifier.fromNamespaceAndPath(Xaeroatlases.ID, "xaero-atlases-command-permission");



    public static void init() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, buildContext, selection) -> {
            dispatcher.register(Commands.literal("xaero-atlases")
                    .requires(
                            commandSourceStack -> commandSourceStack.checkPermission(XAERO_ATLASES_COMMAND_PERMISSION_ID, PermissionLevel.GAMEMASTERS)
                    ).then(
                            Commands.literal("config")
                                    .then(Commands.literal("set")
                                            .then(Commands.literal("enable_coords_with_compass")
                                                    .then(Commands.argument("value", BoolArgumentType.bool())
                                                            .executes(context -> ConfigCommands.configSetEnabledCoordsWithCompassCommand(context, context.getArgument("value", Boolean.class)))
                                                    )
                                            )
                                            .then(Commands.literal("reduced_debug_info")
                                                    .then(Commands.argument("value", BoolArgumentType.bool())
                                                            .executes(context -> ConfigCommands.configSetReducedDebugInfoCommand(context, context.getArgument("value", Boolean.class)))
                                                    )
                                            )
                                    )
                                    .then(Commands.literal("get")
                                            .then(Commands.literal("enable_coords_with_compass")
                                                    .executes(ConfigCommands::configGetEnabledCoordsWithCompassCommand)
                                            )
                                            .then(Commands.literal("reduced_debug_info")
                                                    .executes(ConfigCommands::configGetReducedDebugInfoCommand)
                                            )
                                    )
                                    .then(Commands.literal("reload")
                                            .executes(ConfigCommands::reloadConfigCommand)
                                    )
                    )
            );
        }));
    }
}
